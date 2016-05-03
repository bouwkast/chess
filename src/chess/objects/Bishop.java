package chess.objects;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color, "Bishop", color == 0 ? "\u265D" : "\u2657", false, 5);
    }

    public Bishop(int color, boolean hasMoved) {
        super(color, "Bishop", color == 0 ? "\u265D" : "\u2657", hasMoved, 5);
    }
}
