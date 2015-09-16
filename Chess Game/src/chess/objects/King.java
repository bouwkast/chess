package chess.objects;

public class King extends Piece {

	private static final String NAME = "Q";
	private PColor color;
	
	/**
	 * Constructor for the King that runs the super and sets the color
	 * @param color is the color to set
	 */
	public King(PColor color) {
		super(color, true, NAME); // Set the color and make it alive
		this.color = color;
	}
	
	/**
	 * Returns the name of the King, basically for testing board setup
	 */
	public String toString() {
		return NAME;
	}

	/**
	 * Gets the color of the King
	 * 
	 * @return the color of the king
	 */
	public PColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the King
	 * 
	 * @param color is the color to set
	 */
	public void setColor(PColor color) {
		this.color = color;
	}
	
}
