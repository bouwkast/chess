package chess.main;

import chess.objects.Board;
import chess.objects.Piece;

public class Chess implements java.io.Serializable {

    private Board board;

    public Chess() {
        this.board = new Board();
    }

    public Piece getPieceAt(int row, int col) {
        return board.getCellAt(row, col).getPiece();
    }


}
