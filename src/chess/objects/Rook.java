package chess.objects;

public class Rook extends Piece {

    public Rook(int color) {
        super(color, "Rook", color == 0 ? "\u265C" : "\u2656", false, 10);
    }

    public Rook(int color, boolean hasMoved) {
        super(color, "Rook", color == 0 ? "\u265C" : "\u2656", hasMoved, 10);
    }
}
