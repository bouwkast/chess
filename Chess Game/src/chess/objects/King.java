package chess.objects;

public class King extends Piece {

	/*******************************************************************
	 * Constructor for the King that sets its PColor. While both Kings
	 * are alive the game will continue.
	 * 
	 * @param color
	 *            is the PColor of the King to make
	 ******************************************************************/
	public King(PColor color) {
		super(color, true, "King");
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2654"; // white
		else
			this.icon = "\u265a"; // black
	}
}
