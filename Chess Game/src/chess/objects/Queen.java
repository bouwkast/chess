package chess.objects;

public class Queen extends Piece {
	
	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	
	/*private static final String NAME = "Q";
	private PColor color;*/
	
	/**
	 * Constructor for the Queen that runs the super and sets the color
	 * @param color is the color to set
	 */
	public Queen(PColor color) {
		super(color, true, "Queen"); // Set the color and make it alive
		this.color = color;
		if(color.equals(Color.White))
			this.icon = "\u2655";
		else
			this.icon = "\u265b";
	}
	
	/**
	 * Returns the name of the Queen, basically for testing board setup
	 */
	public String toString() {
		return name;
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
