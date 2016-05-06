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

    public boolean isValidMove(Cell initial, Cell targeted) {

        if (initial.getPiece() == null)
            return false; // no piece to move
        if (targeted.getPiece() != null)
            if (initial.getPiece().getColor() == targeted.getPiece().getColor()) {
                return false;
            }



        return true;
    }


}
