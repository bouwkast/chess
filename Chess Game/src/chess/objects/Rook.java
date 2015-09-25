package chess.objects;

public class Rook extends Piece {
	
	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	
	/*private static final String NAME = "R";
	private PColor color;*/
	
	/**
	 * Constructor for the Rook that sets its color and runs the
	 * super constructor for Piece.
	 * DON'T THINK WE NEED INSTANCE VAR. COLOR FOR UNIQUE PIECES
	 * @param color
	 */
	public Rook (PColor color) {
		super(color, true, "Rook"); // Set the color of the Rook and make it alive
		this.color = color;
		if(color.equals(PColor.White))
			this.icon = "\u2656";
		else
			this.icon = "\u265c";
	}
	
}
