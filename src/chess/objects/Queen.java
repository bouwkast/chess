package chess.objects;

import chess.main.Chess;

public class Queen extends Piece implements java.io.Serializable {

	private Cell current, targeted;
	/*******************************************************************
	 * Constructor for the Queen that sets its PColor
	 * 
	 * @param color is the PColor to set the Queen to
	 ******************************************************************/
	public Queen(PColor color) {
		super(color, "Queen"); // Set the color and make it alive
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2655"; // white
		else
			this.icon = "\u265b"; // black
		this.score = 90;
	}

    public int absCol() {
        return Math.abs(current.getCol() - targeted.getCol());
    }

    public int absRow() {
        return Math.abs(current.getRow() - targeted.getRow());
    }
	
	/*******************************************************************
	 * Copy constructor for the Queen 
	 * 
	 * @param other is the Queen to copy
	 ******************************************************************/
	public Queen(Queen other) {
		super(other);
	}
	
	@Override
	public boolean checkMovement(Cell current, Cell targeted, Chess chess) {
        this.current = current;
        this.targeted = targeted;
		/* The Queen can move either diagonally or laterally */
		Movement move = new Movement(current, targeted, this, chess);
		// Check if the attempted move is diagonal
		if (absRow() == absCol()) {
			return move.checkDiagonal();

			// Check if the attempted move is lateral
		} else if ((absRow() == 0 && current.getCol() != targeted.getCol())
				|| (current.getRow() != targeted.getRow() && absCol() == 0)) {
			return move.checkLateral();
		}

		// If neither, return false because it is an invalid move
		return false;
	}
}
