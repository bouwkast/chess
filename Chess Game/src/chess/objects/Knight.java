package chess.objects;

public class Knight extends Piece {

	/*******************************************************************
	 * Constructor for the Knight that takes it's PColor
	 * 
	 * @param color is the PColor to set the Knight to
	 ******************************************************************/
	public Knight(PColor color) {
		super(color, true, "Knight"); // Set the color and make it alive
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2658"; // white
		else
			this.icon = "\u265e"; // black
	}

}
