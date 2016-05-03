package chess.objects;

public class King extends Piece {

    public King(int color) {
        super(color, "King", color == 0 ? "\u265A" : "\u2654", false, 5000);
    }

    public King(int color, boolean hasMoved) {
        super(color, "King", color == 0 ? "\u265A" : "\u2654", hasMoved, 5000);
    }

}
