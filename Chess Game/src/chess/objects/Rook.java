package chess.objects;

import chess.main.Chess;

public class Rook extends Piece {
	/*******************************************************************
	 * Constructor for the Rook that sets its PColor
	 * 
	 * @param color is the PColor to set the Rook to
	 ******************************************************************/
	public Rook(PColor color) {
		super(color, true, "Rook");
		this.color = color;
		if (color.equals(PColor.White))
			this.icon = "\u2656"; // white
		else
			this.icon = "\u265c"; // black
	}
	
	@Override
	public boolean checkMovement(int r1, int c1, int r2, int c2,
			Chess chess) {
		
		if ((r1 != r2 && c1 == c2) || (r1 == r2 && c1 != c2)) {
			Movement move = new Movement(r1, c1, r2, c2, this, chess);
			return move.checkLateral();
		}
		return false;
	}
}
