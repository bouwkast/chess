package chess.objects;

public class Bishop extends Piece {
	
	private final static String NAME = "B";
	
	/** Is the color of the piece **/
	private PColor color;

	public Bishop(PColor color) {
		super(color, true, NAME); // Set the color and make it alive
		this.color = color;
	}
	
	/**
	 * Returns the name of the Knight, basically for testing board setup
	 */
	public String toString() {
		return NAME;
	}

	/**
	 * Gets the color of the Bishop
	 * 
	 * @return the color of the bishop
	 */
	public PColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the Bishop
	 * 
	 * @param color is the color to set
	 */
	public void setColor(PColor color) {
		this.color = color;
	}

}
