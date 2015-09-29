package chess.objects;

public class Bishop extends Piece {
	/*******************************************************************
	 * Constructor for the Bishop piece with its specified PColor
	 * 
	 * @param color
	 *            is the PColor of the Bishop
	 ******************************************************************/
	public Bishop(PColor color) {
		super(color, true, "Bishop"); // Set the color and make it alive
		this.color = color;
		if(color.equals(PColor.White))
			this.icon = "\u2657"; // white
		else
			this.icon = "\u265d"; // black
	}
}
