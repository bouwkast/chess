package chess.objects;

public class Queen extends Piece {
	/*******************************************************************
	 * Constructor for the Queen that sets its PColor
	 * 
	 * @param color is the PColor to set the Queen to
	 ******************************************************************/
	public Queen(PColor color) {
		super(color, true, "Queen"); // Set the color and make it alive
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2655"; // white
		else
			this.icon = "\u265b"; // black
	}
}
