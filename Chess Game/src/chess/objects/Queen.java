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
		if(color.equals(PColor.White))
			this.icon = "\u2655";
		else
			this.icon = "\u265b";
	}
	
}
