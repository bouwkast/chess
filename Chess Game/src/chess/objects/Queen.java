package chess.objects;

public class Queen extends Piece {
	private static final String NAME = "Q";
	private PColor color;
	
	/**
	 * Constructor for the Queen that runs the super and sets the color
	 * @param color is the color to set
	 */
	public Queen(PColor color) {
		super(color, true, NAME); // Set the color and make it alive
		this.color = color;
	}
	
	/**
	 * Returns the name of the Queen, basically for testing board setup
	 */
	public String toString() {
		return NAME;
	}

	/**
	 * Gets the color of the Queen
	 * 
	 * @return the color of the queen
	 */
	public PColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the Queen
	 * 
	 * @param color is the color to set
	 */
	public void setColor(PColor color) {
		this.color = color;
	}

}
