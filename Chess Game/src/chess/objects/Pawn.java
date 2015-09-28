package chess.objects;

public class Pawn extends Piece {
	
	/**
	 * If the Pawn has moved it can only go one spot, otherwise it can go 2
	 * (implemented as protected variable in Piece
	 */
//	private boolean hasMoved;
	
	
	/*Local instance variables are not needed - use protected
	 * instances from parent class
	 */
	//private final static String NAME = "P";
	
	/**
	 * Constructor for Pawn that initializes hasMoved to false.
	 * 
	 * @param color is the color of the Pawn
	 * @param isAlive is whether the pawn is alive
	 */
	public Pawn(PColor color, boolean isAlive) {
		super(color, isAlive, "Pawn");
		hasMoved = false;
		if(color.equals(PColor.White))
			this.icon = "\u2659";
		else
			this.icon = "\u265f";
	}
	
	/**
	 * Secondary constructor that sets color, isAlive, and hasMoved
	 * 
	 * @param color is the color of the Pawn
	 * @param isAlive is whether the pawn is alive
	 * @param hasMoved is whether the pawn has moved
	 */
	public Pawn(PColor color, boolean isAlive, boolean hasMoved) {
		super(color, isAlive);
		this.hasMoved = hasMoved;
		if(color.equals(PColor.White))
			this.icon = "\u2659";
		else
			this.icon = "\u265f";
	}
	
//	public boolean isHasMoved(){
//		return hasMoved;
//	}
//	
//	public void setHasMoved(boolean moved) {
//		this.hasMoved = moved;
//	}

}
