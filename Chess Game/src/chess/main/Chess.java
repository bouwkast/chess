package chess.main;

import chess.objects.Board;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Piece;

public class Chess {

	/** This is the board that will be used within the chess game */
	private Board board;

	/*******************************************************************
	 * Default constructor - in the future we may add some parameters
	 * like opening from a new board, number of players, level of AI,
	 * ect.
	 ******************************************************************/
	public Chess() {
		board = new Board(1);
	}

	/*******************************************************************
	 * Returns the board object
	 * 
	 * @return the board that is within the game
	 ******************************************************************/
	public Board getBoard() {
		return board;
	}

	/*******************************************************************
	 * Sets the board to the specified parameter
	 * 
	 * @param board
	 *            is the board to set
	 ******************************************************************/
	public void setBoard(Board board) {
		this.board = board;
	}

	/*******************************************************************
	 * Makes it easier to get the piece
	 * 
	 * @param row
	 *            the row of the piece
	 * @param col
	 *            the col of the piece
	 * @return the piece at the specified cell
	 ******************************************************************/
	public Piece getPieceAt(int row, int col) {
		return this.getBoard().getCellAt(row, col).getChessPiece();
	}

	/*******************************************************************
	 * Helper method for the white pawns that checks for valid movement
	 * vertically. Just a note the white pawns are located in row 6 in
	 * the beginning. For this method the cols are the same, so we
	 * aren't capturing anything.
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param pawn
	 *            is the pawn we are trying to move
	 * @return a boolean value whether the pawn was moved successfully
	 ******************************************************************/
	private boolean pawnWhiteVert(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (r1 - r2 > 2) { // Pawns cannot move more than two rows.
			System.out.println("OOPS");
			return false;
		}
		// Only gets here if it is 2 or less for the rows
		// If it has moved it can only go 1 row
		if (pawn.isHasMoved()) {
			if (r1 - r2 == 1 && board.getCellAt(r2, c2) == null) {
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false;
			}
		} else { // If it hasn't moved it can go two rows
			if (r1 - r2 == 2 || r1 - r2 == 1) {
				if (getPieceAt(r2, c2) == null) {
					movePieceTo(r1, c1, r2, c2, pawn);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	/*******************************************************************
	 * Simply moves one piece from one cell to the one specified. No
	 * checking should be done in this function, it is a basic helper
	 * method, so all checking if the piece can go in that cell should
	 * be done outside.
	 * 
	 * This will remove any piece in the second cell and replace it with
	 * the piece that was in the first cell.
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param piece
	 *            is the piece to move
	 ******************************************************************/
	private void movePieceTo(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Set the second cell to the pawn
		board.getCellAt(r2, c2).SetChessPiece(piece);
		// Set the previous cell to null
		board.getCellAt(r1, c1).SetChessPiece(null);
	}

	/*******************************************************************
	 * This checks if the pawn is making a valid move diagonally.
	 * Currently there has to be a piece there, but later en passant,
	 * must be implemented
	 * 
	 * EN PASSANT NOT IMPLEMENTED YET
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param pawn
	 *            is the pawn that we are checking
	 * @return a boolean value whether the pawn was moved
	 ******************************************************************/
	private boolean pawnWhiteDiag(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (r1 - r2 != 1) {
			return false;
		} else {
			if (getPieceAt(r2, c2) != null
					&& getPieceAt(r2, c2).getColor() == PColor.Black) {
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false;
			}
		}
	}

	/*******************************************************************
	 * This checks if the black pawn is making a valid move vertically.
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param pawn
	 *            is the pawn we are checking
	 * @return a boolean value whether the pawn was moved
	 ******************************************************************/
	private boolean pawnBlackVert(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		// Pawns at most can only move 2 rows
		if (r2 - r1 > 2) {
			return false;
		}
		if (pawn.isHasMoved()) {
			if (r2 - r1 == 1 && getPieceAt(r2, c2) == null) {
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false;
			}
		} else { // If it hasn't moved it can go two rows
			if ((r2 - r1 == 2 || r2 - r1 == 1)
					&& getPieceAt(r2, c2) == null) {
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false;
			}
		}
	}

	/*******************************************************************
	 * This checks if the black pawn is making a valid move diagonally.
	 * 
	 * EN PASSANT NOT IMPLEMENTED YET
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param pawn
	 *            is the pawn that we are checking
	 * @return a boolean value whether the pawn was moved
	 ******************************************************************/
	private boolean pawnBlackDiag(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (r2 - r1 != 1) {
			return false;
		} else {
			if (getPieceAt(r2, c2) != null
					&& getPieceAt(r2, c2).getColor() == PColor.White) {
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false;
			}
		}
	}

	/*******************************************************************
	 * Checks whether the pawn is making a valid move or not.
	 * 
	 * @param r1
	 *            is the row of the first cell
	 * @param c1
	 *            is the col of the first cell
	 * @param r2
	 *            is the row of the second cell
	 * @param c2
	 *            is the col of the second cell
	 * @param pawn
	 *            is the pawn we are checking
	 * @return a boolean value whether the pawn was moved
	 ******************************************************************/
	private boolean checkPawn(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (pawn.getColor() == PColor.White) {
			if (c1 == c2) { // Not moving diagonally
				return pawnWhiteVert(r1, c1, r2, c2, pawn);
			} else { // It is moving diagonally
				if (Math.abs(c1 - c2) == 1) { // Can only move 1 col
					return pawnWhiteDiag(r1, c1, r2, c2, pawn);
				} else {
					return false;
				}
			}
		} else { // The pawn is black
			if (c1 == c2) { // Not moving diagonally
				return pawnBlackVert(r1, c1, r2, c2, pawn);
			} else { // It is moving diagonally
				if (Math.abs(c1 - c2) == 1) {
					return pawnBlackDiag(r1, c1, r2, c2, pawn);
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * This method is not done yet, but is just to test how to check the
	 * move for the pieces, right now it only works for Pawns and only
	 * cares about vertical movement, no collision or anything, but it
	 * works
	 */
	public boolean checkMove(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Relatively decent checking style for pawn
		if (piece instanceof Pawn) {
			if (checkPawn(r1, c1, r2, c2, (Pawn) piece)) {
				System.out.println("VALID MOVE");
				return true;
			} else {
				System.out.println("INVALID MOVE");
				return false;
			}
		} else {
			System.out.println("Not a Pawn");
		}
		return false;
	}

	/* No longer needed, moved into ChessController */
	// public static void main(String[] args){
	// Board test = new Board(1);
	// System.out.print(test.printBoard());
	// }

}
