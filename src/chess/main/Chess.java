package chess.main;

import chess.objects.Board;
import chess.objects.Cell;
import chess.objects.Piece;

public class Chess implements java.io.Serializable {

    private Board board;

    public Chess() {
        this.board = new Board();
    }

    public Piece getPieceAt(int row, int col) {
        return board.getCellAt(row, col).getPiece();
    }

    public Board getBoard() {
        return board;
    }

    public boolean movePiece(Cell initial, Cell targeted) {
        if (isValidMove(initial, targeted)) {

        }
        return true;
    }

    public boolean checkPawnMove(Cell initial, Cell targeted) {

        return false;
    }

    public boolean checkKnightMove(Cell initial, Cell targeted) {
        // move is 2 row 1 col or 1 row 2 col
        if (Math.abs(initial.getRow() - targeted.getRow()) == 2 && Math.abs(initial.getCol() - targeted.getCol())
                == 1 || Math.abs(initial.getRow() - targeted.getRow()) == 1 && Math.abs(initial.getCol() - targeted.getCol()) == 2) {
            return true;
        }

        return false;
    }

    public boolean checkBishopMove(Cell initial, Cell targeted) {

        return false;
    }

    public boolean checkRookMove(Cell initial, Cell targeted) {
        // rooks move vertical or horizontal

        if(initial.getCol() != targeted.getCol() && initial.getRow() != targeted.getRow())
            return false; // diagonal move
        if(initial.getRow() != targeted.getRow()) { // vertical
            int dir = initial.getRow() < targeted.getRow() ? 1 : -1; // 1 is going down
            for(int row = initial.getRow() + dir; row != targeted.getRow(); row += dir) {
                if(board.getCellAt(row, initial.getCol()).getPiece() != null)
                    return false; // piece in way
            }
            return true;
        } else { // horizontal
            int dir = initial.getCol() < targeted.getCol() ? 1 : -1; // 1 is going right
            for(int col = initial.getCol() + 1; col != targeted.getCol(); col += dir) {
                if(board.getCellAt(initial.getRow(), col).getPiece() != null)
                    return false; // piece in way
            }
            return true;
        }
    }

    public boolean checkQueenMove(Cell initial, Cell targeted) {

        return false;
    }

    public boolean checkKingMove(Cell initial, Cell targeted) {

        return false;
    }

    public boolean isValidMove(Cell initial, Cell targeted) {

        if (initial.getPiece() == null)
            return false; // no piece to move
        if (targeted.getPiece() != null)
            if (initial.getPiece().getColor() == targeted.getPiece().getColor()) {
                return false;
            }
        switch (initial.getPiece().getName()) {
            case ("Pawn"):
                return checkPawnMove(initial, targeted);
            case ("Knight"):
                return checkKnightMove(initial, targeted);
            case ("Bishop"):
                return checkBishopMove(initial, targeted);
            case ("Rook"):
                return checkRookMove(initial, targeted);
            case ("Queen"):
                return checkQueenMove(initial, targeted);
            case ("King"):
                return checkKingMove(initial, targeted);
            default:
                return false;

        }
    }


}
