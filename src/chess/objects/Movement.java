package chess.objects;

import chess.main.Chess;

public class Movement implements java.io.Serializable {

	/** Rows and columns of the piece and cell to check */
	private int r1, c1, r2, c2;

	private Cell selected, targeted;
	/** Game to check the movement with */
	private Chess chess;
	/** The piece to be moved */
	private Piece piece;

	public Movement(Cell selected, Cell targeted, Piece piece,
			Chess chess) {
		this.selected = selected;
        this.targeted = targeted;
		this.piece = piece;
		this.chess = chess;
        setVariables();
	}

    private void setVariables() {
        r1 = selected.getRow();
        c1 = selected.getCol();
        r2 = targeted.getRow();
        c2 = targeted.getCol();
    }

    /*******************************************************************
	 * Checks the Lateral movement of the piece for its movement UP,
	 * RIGHT, DOWN, and LEFT
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	public boolean checkLateral() {
		if (r1 != r2 && c1 == c2) {
			return checkVertical();
		} else if (r1 == r2 && c1 != c2) {
			return checkHorizontal();
		}
		return false;
	}

	/*******************************************************************
	 * Checks the piece's movement vertically to make sure it is a valid
	 * move.
	 *
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	private boolean checkVertical() {
		int direction = r1 > r2 ? 1 : -1; // direction piece moving
		// We sub direction to be able to go up or down
		for (int row = r1 - direction; row != r2 - direction; row -=
				direction) {
			if (row != r2) { // not at final cell
				if (chess.getPieceAt(row, c1) != null) // piece in the
														// way
					return false;
			} else { // at the final cell
				if (chess.getPieceAt(row, c2) == null
						|| chess.getPieceAt(row, c2)
								.getColor() != piece.getColor()) // valid
					return true;
			}
		}
		return false; // invalid move
	}

	/*******************************************************************
	 * Checks the Piece's movement horizontally to make sure it is a
	 * valid move.
	 *
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	private boolean checkHorizontal() {
		int direction = c1 > c2 ? 1 : -1; // left or right
		for (int col = c1 - direction; col != c2 - direction; col -=
				direction) {
			if (col != c2) { // not at final cell
				if (chess.getPieceAt(r1, col) != null) // piece in the
														// way
					return false;
			} else { // at the final cell
				if (chess.getPieceAt(r2, col) == null
						|| chess.getPieceAt(r2, col)
								.getColor() != piece.getColor()) // valid
					return true;
			}
		}
		return false; // invalid move
	}

	/*******************************************************************
	 * Checks the Piece's movement diagonally to make sure it is a valid
	 * move.
	 *
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	public boolean checkDiagonal() {
		int xDir = c1 > c2 ? 1 : -1;
		int yDir = r1 > r2 ? 1 : -1;
		for (int row = r1 - yDir, col = c1 - xDir; row != r2 - yDir
				&& col != c2 - xDir; row -= yDir, col -= xDir) {
			if (row != r2) { // need to check cells in-between
				if (chess.getPieceAt(row, col) != null)
					return false; // Piece is in the way
			} else { // at the final cell
				if (chess.getPieceAt(row, col) == null
						|| chess.getPieceAt(row, col)
								.getColor() != piece.getColor())
					return true; // valid move
			}
		}
		return false; // invalid move
	}

}
