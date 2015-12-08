package chess.objects;

import chess.main.Chess;

public class Queen extends Piece implements java.io.Serializable {
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
	
	/*******************************************************************
	 * Copy constructor for the Queen 
	 * 
	 * @param other is the Queen to copy
	 ******************************************************************/
	public Queen(Queen other) {
		super(other);
	}
	
	@Override
	public boolean checkMovement(int r1, int c1, int r2, int c2,
			Chess chess) {
		/* The Queen can move either diagonally or laterally */
		Movement move = new Movement(r1, c1, r2, c2, this, chess);
		// Check if the attempted move is diagonal
		if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
			return move.checkDiagonal();

			// Check if the attempted move is lateral
		} else if ((Math.abs(r1 - r2) == 0 && c1 != c2)
				|| (r1 != r2 && Math.abs(c1 - c2) == 0)) {
			return move.checkLateral();
		}

		// If neither, return false because it is an invalid move
		return false;
	}
}
