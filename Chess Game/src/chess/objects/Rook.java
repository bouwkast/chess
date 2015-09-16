package chess.objects;

public class Rook extends Piece {
	
	private static final String NAME = "R";
	private PColor color;
	
	/**
	 * Constructor for the Rook that sets its color and runs the
	 * super constructor for Piece.
	 * DON'T THINK WE NEED INSTANCE VAR. COLOR FOR UNIQUE PIECES
	 * @param color
	 */
	public Rook (PColor color) {
		super(color, true, NAME); // Set the color of the Rook and make it alive
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
