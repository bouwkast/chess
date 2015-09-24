package chess.objects;

public class King extends Piece {

	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	
	/*private static final String NAME = "K";
	private PColor color;*/
	
	/**
	 * Constructor for the King that runs the super and sets the color
	 * @param color is the color to set
	 */
	public King(PColor color) {
		super(color, true, "King"); // Set the color and make it alive
		this.color = color;
		if(color.equals(Color.White))
			this.icon = "\u2654";
		else
			this.icon = "\u265b";
	}
	
	/**
	 * Returns the name of the King, basically for testing board setup
	 */
	public String toString() {
		return name;
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
