package chess.objects;

import chess.main.Chess;

public class King extends Piece implements java.io.Serializable {

    private Cell current, targeted;

    /*******************************************************************
     * Constructor for the King that sets its PColor. While both Kings
     * are alive the game will continue.
     *
     * @param color is the PColor of the King to make
     ******************************************************************/
    public King(PColor color) {
        super(color, "King");
        this.color = color;
        if (color.equals(PColor.White))
            this.icon = "\u2654"; // white
        else
            this.icon = "\u265a"; // black
        this.score = 99999;
    }

    /*******************************************************************
     * Copy constructor for the King
     *
     * @param other is the King to copy
     ******************************************************************/
    public King(King other) {
        super(other);
    }

    @Override
    public boolean checkMovement(Cell current, Cell targeted,
                                 Chess chess) {
        this.current = current;
        this.targeted = targeted;
        /* King is similar to the queen, but can only move 1 spot */
        Movement move = new Movement(current, targeted, this, chess);
        // Check if the attempted move is diagonal
        if (absRow() == absCol()) {
            if (absRow() == 1 && absCol() == 1) {
                return move.checkDiagonal();
            } else {
                return false;
            }
            // Check to make sure it is one spot

            // Check if the attempted move is lateral
        } else if ((absRow() == 0 && absCol() == 1)
                || (absRow() == 1 && absCol() == 0)) {
            return move.checkLateral();
        } else {
            // Check for castling
            return checkCastling(this, chess);
        }
        // If neither, return false because it is an invalid move
    }

    /*******************************************************************
     * Checks to see if the move is a valid castle move
     *
     * @param king is the King to castle
     * @return a boolean value whether the king can be castled
     ******************************************************************/
    public boolean checkCastling(King king, Chess chess) {
        if (!king.hasMoved()) { // King can't have moved yet
            if ((current.getRow() == 0 && targeted.getRow() == 0) || (current.getRow() == 7 && targeted.getRow() == 7)) {
                if (absCol() == 2 && targeted.getCol() == 6 || targeted.getCol() == 2) { // moving two cols
                    if (current.getCol() < targeted.getCol()) { // going to the right
                        return castleCheckRight(king, chess);
                    } else { // going to the left
                        return castleCheckLeft(king, chess);
                    }
                }
            }
        }
        return false;
    }

    public int absCol() {
        return Math.abs(current.getCol() - targeted.getCol());
    }

    public int absRow() {
        return Math.abs(current.getRow() - targeted.getRow());
    }

    /*******************************************************************
     * Checks to see if the "queenside" castle is valid
     *
     * @param king is the King to castle
     * @return a boolean value whether the King can be castled
     ******************************************************************/
    public boolean castleCheckLeft(Piece king, Chess chess) {
        if (absCol() != 2) {
            return false;
        }
        if (chess.getPieceAt(targeted.getRow(), targeted.getCol() - 2) != null) {
            Piece toCastle = chess.getPieceAt(targeted.getRow(), targeted.getCol() - 2);
            if (toCastle instanceof Rook && !toCastle.hasMoved()) {
                // Need 3 empty and non-check cells
                if (chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1) != null)
                    return false;
                if (chess.getPieceAt(targeted.getRow(), targeted.getCol()) != null)
                    return false;
                if (chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1) != null)
                    return false;
                if (chess.isFutureCheck(current, chess.getBoard().getCellAt(targeted.getRow(), targeted.getCol() + 1), king))
                    return false;
                return true;
            }
        }
        return false;
    }

    /*******************************************************************
     * Checks to see if move is a valid "kingside" castle
     *
     * @param king is the King to castle
     * @return a boolean value whether it is a valid "kingside" castle
     ******************************************************************/
    public boolean castleCheckRight(Piece king, Chess chess) {
        if (absCol() != 2)
            return false;
        if (chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1) != null) {
            Piece toCastle = chess.getPieceAt(targeted.getRow(), targeted.getCol() + 1);
            if (toCastle instanceof Rook && !toCastle.hasMoved()) {
                if (chess.getPieceAt(targeted.getRow(), targeted.getCol() - 1) != null)
                    return false;
                if (chess.getPieceAt(targeted.getRow(), targeted.getCol()) != null)
                    return false;
                if (chess.isFutureCheck(current, chess.getBoard().getCellAt(targeted.getRow(), targeted.getCol() - 1), king))
                    return false;
                return true;
            }
        }
        return false;
    }
}
