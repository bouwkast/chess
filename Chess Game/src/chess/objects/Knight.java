package chess.objects;

public class Knight extends Piece {
	
	private static final String NAME = "N";
	private PColor color;

	public Knight(PColor color) {
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
	 * Gets the color of the knight
	 * 
	 * @return the color of the knight
	 */
	public PColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the Knight
	 * 
	 * @param color is the color to set
	 */
	public void setColor(PColor color) {
		this.color = color;
	}

}
