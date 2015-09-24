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
		if(color.equals(Color.White))
			this.icon = "\u2657";
		else
			this.icon = "\u265d";
	}

	/*******************************************************************
	 * Returns the temporary name of the Bishop which is B
	 * 
	 * @return a string value containing the piece's name
	 ******************************************************************/
	public String toString() {
		return name;
	}

	/*******************************************************************
	 * Returns the enum color of the piece, either WHITE or BLACK
	 * 
	 * @return the PColor of the piece
	 ******************************************************************/
	public PColor getColor() {
		return color;
	}

	/*******************************************************************
	 * Sets the PColor of the Bishop
	 * 
	 * @param color
	 *            is the PColor to set
	 ******************************************************************/
	public void setColor(PColor color) {
		this.color = color;
	}

}
