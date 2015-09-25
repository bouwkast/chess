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
		if(color.equals(PColor.White))
			this.icon = "\u2654";
		else
			this.icon = "\u265a";
	}
	
}
