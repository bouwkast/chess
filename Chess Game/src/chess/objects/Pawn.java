package chess.objects;

import chess.main.Chess;

public class Pawn extends Piece {
    
    /*******************************************************************
     * Constructor for the Pawn that takes it's PColor
     * 
     * @param color is the PColor of the Pawn to make
     ******************************************************************/
    
    public Pawn(PColor color) {
        super(color, true, "Pawn");
        hasMoved = false;
        if (color.equals(PColor.White))
            this.icon = "\u2659"; // white
        else
            this.icon = "\u265f"; // black
    }
    
    @Override
    public boolean checkMovement(int r1, int c1, int r2, int c2,
            Chess chess) {
        if (Math.abs(r1 - r2) > 2 || Math.abs(c1 - c2) > 1)
            return false;
            
        return pawnMove(r1, c1, r2, c2, chess);
    }
    
    private boolean pawnMove(int r1, int c1, int r2, int c2,
            Chess chess) {
        if (r1 != r2) { // pawns can't move side to side
            int direction = this.getColor() == PColor.White ? 1 : -1;
            if (r1 - r2 == (2 * direction) && !this.isHasMoved()
                    && c1 - c2 == 0) {
                // Move two rows
                if (chess.getPieceAt(r1 - direction, c1) == null
                        && chess.getPieceAt(r2, c2) == null) {
                        
                    chess.getBoard().setnewPassantMove(true);
                    chess.getBoard().setnewPassantRow(r1 - direction);
                    chess.getBoard().setnewPassantCol(c1);
                    return true;
                }
            } else if (r1 - r2 == (1 * direction) && c1 - c2 == 0) {
                // Moving one row
                if (chess.getPieceAt(r2, c1) == null) {
                    return true;
                }
            } else if (r1 - r2 == (1 * direction)
                    && Math.abs(c1 - c2) == 1) {
                // Trying to capture
                if (chess.getPieceAt(r2, c2) != null
                        && chess.getPieceAt(r2, c2).getColor() != this
                                .getColor()) {
                    return true;
                }
                
                // Attempting a Passant Move
                else if (chess.getBoard().getCurrentPassantMove()) {
                    if ((r2 == chess.getBoard().getcurrentPassantRow())
                            && (c2 == chess.getBoard()
                                    .getcurrentPassantCol())) {
                        chess.getBoard().getCellAt(r2 - direction, c2)
                                .setChessPiece(null);
                        chess.getBoard().setpassantCapture(true);
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
    
 
    }
    
    

