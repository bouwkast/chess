package chess.objects;

public class Pawn extends Piece {
	
	/**
	 * If the Pawn has moved it can only go one spot, otherwise it can go 2
	 */
	private boolean hasMoved;
	
	/**
	 * Constructor for Pawn that initializes hasMoved to false.
	 * 
	 * @param color is the color of the Pawn
	 * @param isAlive is whether the pawn is alive
	 */
	public Pawn(PColor color, boolean isAlive) {
		super(color, isAlive);
		hasMoved = false;
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
	}

	/**
	 * Return whether the Pawn has moved or not
	 * 
	 * @return the hasMoved
	 */
	public boolean isHasMoved() {
		return hasMoved;
	}

	/**
	 * Set whether the Pawn has moved or not
	 * @param hasMoved the hasMoved to set
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}
