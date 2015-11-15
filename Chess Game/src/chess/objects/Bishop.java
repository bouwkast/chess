package chess.objects;

import chess.main.Chess;

public class Bishop extends Piece {
	/*******************************************************************
	 * Constructor for the Bishop piece with its specified PColor
	 * 
	 * @param color is the PColor of the Bishop
	 ******************************************************************/
	public Bishop(PColor color) {
		super(color, "Bishop"); // Set the color and make it alive
		if (color.equals(PColor.White))
			this.icon = "\u2657"; // white
		else
			this.icon = "\u265d"; // black
		this.score = 30;
	}
	
	/*******************************************************************
	 * Copy constructor for the Bishop 
	 * 
	 * @param other is the Bishop to copy
	 ******************************************************************/
	public Bishop(Bishop other) {
		super(other);
	}

	@Override
	public boolean checkMovement(int r1, int c1, int r2, int c2,
			Chess chess) {
		// Bishops row and col must change by same amount
		if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
			Movement move = new Movement(r1, c1, r2, c2, this, chess);
			return move.checkDiagonal();

		} else { // Not a diagonal move
			return false;
		}
	}
}
