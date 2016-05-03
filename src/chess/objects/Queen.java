package chess.objects;

public class Queen extends Piece {

    public Queen(int color) {
        super(color, "Queen", color == 0 ? "\u265B" : "\u2655", false, 50);
    }

    public Queen(int color, boolean hasMoved) {
        super(color, "Queen", color == 0 ? "\u265B" : "\u2655", hasMoved, 50);
    }

}
