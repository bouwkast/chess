package chess.objects;

public class Pawn extends Piece {

	/*******************************************************************
	 * Constructor for the Pawn that takes it's PColor
	 * 
	 * @param color
	 *            is the PColor of the Pawn to make
	 ******************************************************************/
	public Pawn(PColor color) {
		super(color, true, "Pawn");
		hasMoved = false;
		if (color.equals(PColor.White))
			this.icon = "\u2659";
		else
			this.icon = "\u265f";
	}
}
