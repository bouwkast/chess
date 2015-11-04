package chess.objects;

import chess.main.Chess;

public class King extends Piece {
    
    /*******************************************************************
     * Constructor for the King that sets its PColor. While both Kings
     * are alive the game will continue.
     * 
     * @param color is the PColor of the King to make
     ******************************************************************/
    public King(PColor color) {
        super(color, true, "King");
        this.color = color;
        if (color.equals(PColor.White))
            this.icon = "\u2654"; // white
        else
            this.icon = "\u265a"; // black
    }
    
    @Override
    public boolean checkMovement(int r1, int c1, int r2, int c2,
            Chess chess) {
        /* King is similar to the queen, but can only move 1 spot */
        Movement move = new Movement(r1, c1, r2, c2, this, chess);
        // Check if the attempted move is diagonal
        if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
            // Check to make sure it is one spot
            if (Math.abs(r1 - r2) == 1 && Math.abs(c1 - c2) == 1)
                return move.checkDiagonal();
            // Check if the attempted move is lateral
        } else if ((Math.abs(r1 - r2) == 0 && Math.abs(c1 - c2) == 1)
                || (Math.abs(r1 - r2) == 1 && Math.abs(c1 - c2) == 0)) {
            return move.checkLateral();
        } else {
            // Check for castling
            return checkCastling(r1, c1, r2, c2, this, chess);
        }
        // If neither, return false because it is an invalid move
        return false;
        
    }
    
    /*******************************************************************
     * Checks to see if the move is a valid castle move
     * 
     * @param r1 is the row of the King to castle
     * @param c1 is the col of the King to castle
     * @param r2 is the row of the castling move
     * @param c2 is the col of the castling move
     * @param king is the King to castle
     * @return a boolean value whether the king can be castled
     ******************************************************************/
    public boolean checkCastling(int r1, int c1, int r2, int c2,
            King king, Chess chess) {
        if (!king.isHasMoved()) { // King can't have moved yet
            if ((r1 == 0 && r2 == 0) || (r1 == 7 && r2 == 7)) {
                if (Math.abs(c1 - c2) == 2) { // moving two cols
                    if (c1 < c2) { // going to the right
                        return castleCheckRight(r1, c1, r2, c2, king,
                                chess);
                    } else { // going to the left
                        return castleCheckLeft(r1, c1, r2, c2, king,
                                chess);
                    }
                }
            }
        }
        return false;
    }
    
    /*******************************************************************
     * Checks to see if the "queenside" castle is valid
     * 
     * @param r1 is the row of the King
     * @param c1 is the col of the King
     * @param r2 is the row of the castling move
     * @param c2 is the col of the castling move
     * @param king is the King to castle
     * @return a boolean value whether the King can be castled
     ******************************************************************/
    public boolean castleCheckLeft(int r1, int c1, int r2, int c2,
            King king, Chess chess) {
        if (Math.abs(c1 - c2) != 2) {
            return false;
        }
        if (chess.getPieceAt(r2, c2 - 2) != null) {
            Piece toCastle = chess.getPieceAt(r2, c2 - 2);
            if (toCastle instanceof Rook && !toCastle.isHasMoved()) {
                // Need 3 empty and non-check cells
                if (chess.getPieceAt(r2, c2 - 1) != null)
                    return false;
                if (chess.getPieceAt(r2, c2) != null)
                    return false;
                if (chess.getPieceAt(r2, c2 + 1) != null)
                    return false;
                if (chess.isFutureCheck(r1, c1, r2, c2 + 1, king))
                    return false;
                return true;
            }
        }
        return false;
    }
    
    /*******************************************************************
     * Checks to see if move is a valid "kingside" castle
     * 
     * @param r1 is the row of the King
     * @param c1 is the col of the King
     * @param r2 is the row of the castling move
     * @param c2 is the col of the castling move
     * @param king is the King to castle
     * @return a boolean value whether it is a valid "kingside" castle
     ******************************************************************/
    public boolean castleCheckRight(int r1, int c1, int r2, int c2,
            King king, Chess chess) {
        if (Math.abs(c1 - c2) != 2)
            return false;
        if (chess.getPieceAt(r2, c2 + 1) != null) {
            Piece toCastle = chess.getPieceAt(r2, c2 + 1);
            if (toCastle instanceof Rook && !toCastle.isHasMoved()) {
                if (chess.getPieceAt(r2, c2 - 1) != null)
                    return false;
                if (chess.getPieceAt(r2, c2) != null)
                    return false;
                if (chess.isFutureCheck(r1, c1, r2, c2 - 1, king))
                    return false;
                return true;
            }
        }
        return false;
    }
}
