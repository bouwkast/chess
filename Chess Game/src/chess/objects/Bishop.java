package chess.objects;

public class Bishop extends Piece {

	private final static String NAME = "B";

	/** Is the color of the piece **/
	private PColor color;

	/*******************************************************************
	 * Constructor for the Bishop piece with its specified PColor
	 * 
	 * @param color
	 *            is the PColor of the piece
	 ******************************************************************/
	public Bishop(PColor color) {
		super(color, true, NAME); // Set the color and make it alive
		this.color = color;
	}

	/*******************************************************************
	 * Returns the temporary name of the Bishop which is B
	 * 
	 * @return a string value containing the piece's name
	 ******************************************************************/
	public String toString() {
		return NAME;
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
