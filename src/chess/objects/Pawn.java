package chess.objects;

import chess.main.Chess;

public class Pawn extends Piece implements java.io.Serializable {

    private Cell current, targeted;


    /*******************************************************************
     * Constructor for the Pawn that takes it's PColor
     * 
     * @param color is the PColor of the Pawn to make
     ******************************************************************/
    
    public Pawn(PColor color) {
        super(color, "Pawn");
        hasMoved = false;
        if (color.equals(PColor.White))
            this.icon = "\u2659"; // white
        else
            this.icon = "\u265f"; // black
        this.score = 10;
    }
    
    /*******************************************************************
     * Copy constructor for the Pawn
     * 
     * @param other is the Pawn to copy
     ******************************************************************/
    public Pawn(Pawn other) {
        super(other);
    }

    public int absCol() {
        return Math.abs(current.getCol() - targeted.getCol());
    }

    public int absRow() {
        return Math.abs(current.getRow() - targeted.getRow());
    }
    
    /*******************************************************************
     * Checks the movement for the Pawn
     *
     * @param chess is the Chess
     * @return a boolean value whether it is a valid move
     ******************************************************************/
    @Override
    public boolean checkMovement(Cell current, Cell targeted, Chess chess) {
        this.current = current;
        this.targeted = targeted;
        if (absRow() > 2 || absCol() > 1)
            return false;
            
        return pawnMove(chess);
    }
    
    /*******************************************************************
     * Checks for each of the Pawn's unique movements if the move valid
     *
     * @param chess is the Chess
     * @return a boolean value whether it is a valid move
     ******************************************************************/
    private boolean pawnMove(Chess chess) {
        if (current.getRow() != targeted.getRow()) {// pawns can't move side to side
            int direction = this.getColor() == PColor.White ? 1 : -1;
            if (current.getRow() - targeted.getRow() == (2 * direction) && !this.hasMoved()
                    && current.getCol() - targeted.getCol() == 0) {
                // Move two rows
                if (chess.getPieceAt(current.getRow() - direction, current.getCol()) == null
                        && chess.getPieceAt(targeted.getRow(), targeted.getCol()) == null) {
                    if (targeted.getCol() == 7) { // Right side of the board, check
                                   // left only
                        if (chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1) != null
                                && chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1)
                                        .getColor() != this
                                                .getColor()) {
                            chess.getBoard()
                                    .getCellAt(current.getRow() - direction, targeted.getCol())
                                    .setPassant(true);
                        }
                    } else if (targeted.getCol() == 0) { // Left side of the board,
                                          // check right
                        if (chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1) != null
                                && chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1)
                                        .getColor() != this
                                                .getColor()) {
                            chess.getBoard()
                                    .getCellAt(current.getRow() - direction, targeted.getCol())
                                    .setPassant(true);
                        }
                    } else { // Has two adjacent cells L and R, check
                             // both
                        if ((chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1) != null
                                && chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1)
                                        .getColor() != this.getColor())
                                || (chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1) != null
                                        && chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1)
                                                .getColor() != this
                                                        .getColor())) {
                            chess.getBoard()
                                    .getCellAt(current.getRow() - direction, targeted.getCol())
                                    .setPassant(true);
                        }
                    }
                    return true;
                }
            } else if (current.getRow() - targeted.getRow() == (1 * direction) && current.getCol() - targeted.getCol() == 0) {
                // Moving one row
                if (chess.getPieceAt(targeted.getRow(), current.getCol()) == null) {
                    return true;
                }
            } else if (current.getRow() - targeted.getRow() == (1 * direction)
                    && Math.abs(current.getCol() - targeted.getCol()) == 1) {
                // Trying to capture
                if ((chess.getPieceAt(targeted.getRow(), targeted.getCol()) != null
                        && chess.getPieceAt(targeted.getRow(), targeted.getCol()).getColor() != this
                                .getColor())
                        || chess.getBoard().getCellAt(targeted.getRow(), targeted.getCol())
                                .isPassant()) {
                    if (chess.getBoard().getCellAt(targeted.getRow(), targeted.getCol()).isPassant())
                        chess.setEnPassCap(true);
                    return true;
                }
            }
        }
        return false;
    }
    
    /*******************************************************************
     * Method will create a passant cell if necessary
     ******************************************************************/
    private void createPassantCell(int r1, int c1, int r2, int c2,
            Chess chess, int direction) {
        if (c2 == 7) { // Right side of the board, check left only
            if (chess.getPieceAt(r2, c2 - 1) != null
                    && chess.getPieceAt(r2, c2 - 1).getColor() != this
                            .getColor()) {
                chess.getBoard().getCellAt(r1 - direction, c2)
                        .setPassant(true);
            }
        } else if (c2 == 0) { // Left side of the board, check right
            if (chess.getPieceAt(r2, c2 + 1) != null
                    && chess.getPieceAt(r2, c2 + 1).getColor() != this
                            .getColor()) {
                chess.getBoard().getCellAt(r1 - direction, c2)
                        .setPassant(true);
            }
        } else { // Has two adjacent cells L and R, check both
            if ((chess.getPieceAt(r2, c2 + 1) != null
                    && chess.getPieceAt(r2, c2 + 1).getColor() != this
                            .getColor())
                    || (chess.getPieceAt(r2, c2 - 1) != null
                            && chess.getPieceAt(r2, c2 - 1)
                                    .getColor() != this.getColor())) {
                chess.getBoard().getCellAt(r1 - direction, c2)
                        .setPassant(true);
            }
        }
    }
    
    /*******************************************************************
     * Checks if the cell the Pawn is moving to is a passant cell
     * 
     * @param r1 is the initial row
     * @param c1 is the initial col
     * @param r2 is the targeted row
     * @param c2 is the targeted col
     * @param pawn is the Pawn we are moving
     * @param chess is the current Chess game
     * @return a boolean value whether it is a valid en passant move
     ******************************************************************/
    public boolean checkEnPassant(int r1, int c1, int r2, int c2,
            Pawn pawn, Chess chess) {
        if (chess.getBoard().getCellAt(r2, c2).isPassant()) {
            return true;
        }
        return false;
    }
    
}
