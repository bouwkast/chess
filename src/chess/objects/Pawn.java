package chess.objects;

public class Pawn extends Piece {

    public Pawn(int color) {
        super(color, "Pawn", color == 0 ? "\u265F" : "\u2659", false, 1);
    }

    public Pawn(int color, boolean hasMoved) {
        super(color, "Pawn", color == 0 ? "\u265F" : "\u2659", hasMoved, 1);
    }
    
}
