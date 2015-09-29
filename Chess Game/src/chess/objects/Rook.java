package chess.objects;

public class Rook extends Piece {
	/*******************************************************************
	 * Constructor for the Rook that sets its PColor
	 * 
	 * @param color is the PColor to set the Rook to
	 ******************************************************************/
	public Rook(PColor color) {
		super(color, true, "Rook");
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2656"; // white
		else
			this.icon = "\u265c"; // black
	}
}
