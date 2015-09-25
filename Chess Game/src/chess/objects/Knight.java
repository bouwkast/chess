package chess.objects;

public class Knight extends Piece {
	
	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	
	/*private static final String NAME = "N";
	private PColor color;*/

	public Knight(PColor color) {
		super(color, true, "Knight"); // Set the color and make it alive
		this.color = color;
		if(color.equals(PColor.White))
			this.icon = "\u2658";
		else
			this.icon = "\u265e";
	}

}
