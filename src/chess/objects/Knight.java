package chess.objects;

public class Knight extends Piece{

    public Knight(int color) {
        super(color, "Knight", color == 0 ? "\u265E" : "\u2658", false, 3);
    }

    public Knight(int color, boolean hasMoved) {
        super(color, "Knight", color == 0 ? "\u265E" : "\u2658", hasMoved, 3);
    }

}
