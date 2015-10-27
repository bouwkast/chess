package chess.objects;

import chess.main.Chess;

public class Knight extends Piece {

	/*******************************************************************
	 * Constructor for the Knight that takes it's PColor
	 * 
	 * @param color is the PColor to set the Knight to
	 ******************************************************************/
	public Knight(PColor color) {
		super(color, true, "Knight"); // Set the color and make it alive
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2658"; // white
		else
			this.icon = "\u265e"; // black
	}

	@Override
	public boolean checkMovement(int r1, int c1, int r2, int c2,
			Chess chess) {
		// First need to calc the difference for the rows and cols
		if ((Math.abs(r1 - r2) == 2 && Math.abs(c1 - c2) == 1)
				|| (Math.abs(r1 - r2) == 1
						&& Math.abs(c1 - c2) == 2)) {
			// Need to check the cell it is moving to
			if (chess.getPieceAt(r2, c2) == null
					|| this.getColor() != chess.getPieceAt(r2, c2)
							.getColor()) {
				// Valid move, we can move the Knight
				return true;

			} else {
				// Invalid move, it contains the same color as Knight
				return false;
			}

		} else {
			// Invalid move pattern
			return false;
		}
	}

}
