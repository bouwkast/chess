package chess.objects;

public class Bishop extends Piece {

	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	
	/*private final static String NAME = "B";

	/** Is the color of the piece **/
	/*private PColor color;*/

	/*******************************************************************
	 * Constructor for the Bishop piece with its specified PColor
	 * 
	 * @param color
	 *            is the PColor of the piece
	 ******************************************************************/
	public Bishop(PColor color) {
		super(color, true, "Bishop"); // Set the color and make it alive
		this.color = color;
		if(color.equals(PColor.White))
			this.icon = "\u2657";
		else
			this.icon = "\u265d";
	}
}
