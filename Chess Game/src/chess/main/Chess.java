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
	 * @param row the row of the piece
	 * @param col the col of the piece
	 * @return the piece at the specified cell
	 ******************************************************************/
	public Piece getPieceAt(int row, int col) {
		return this.getBoard().getCellAt(row, col).getChessPiece();
	}
	
	private boolean checkPawn(int r1, int c1, int r2, int c2, Pawn pawn) {
		// Check to see if the pawn has moved
		if(pawn.isHasMoved()) {
			// Want to get the color of the pawn for proper checking
			if(pawn.getColor() == PColor.White) {
				// If the pawn is white it is in the bottom rows, so the rows should decrease by 1
				if(r2 - r1 == 1) {
					// Potentially a valid move
					// First check if it is a capture
					if(Math.abs(c2 - c1) != 0) {
						if(Math.abs(c2 - c1) > 1) { // Can only move one row diagonally
							return false;
						} else {
							// Need to check if the cell they are moving too contains an enemy
							if(getPieceAt(r2, c2) != null && getPieceAt(r2, c2).getColor() == PColor.Black) {
								// It is an enemy piece, capture it and move the pawn
								// Set the second cell to the pawn
								board.getCellAt(r2, c2).SetChessPiece(pawn);
								// Set the previous cell to null
								board.getCellAt(r1, c1).SetChessPiece(null);
								return true;
							} else {
								// The cell doesn't contain an enemy, so it is an invalid move
								return false;
							}
						}
						// If it isn't a capture we want to check if the pawn can move to the cell
					} else if(Math.abs(c2 - c1) == 0) {
						if(getPieceAt(r2, c2) == null) {
							// If the cell is empty move the pawn to that cell
							// DUPLICATE CODE! *************************************************************
							// Set the second cell to the pawn
							board.getCellAt(r2, c2).SetChessPiece(pawn);
							// Set the previous cell to null
							board.getCellAt(r1, c1).SetChessPiece(null);
							return true;
						} else {
							return false;
						}
					}
				} else {
					// Invalid move for the pawn
					return false;
				}
			} else {
				// The pawn is black and it is in the upper rows, so the rows should increase
				if(r1 - r2 == 1) {
					// Check if it is a capture move
					if(Math.abs(c2 - c1) != 0) {
						if(Math.abs(c2 - c1) > 1) { // Can only move one col over
							return false;
						} else { // Else it is a potential capture move
							// We need to check if the cell contains an enemy
							if(getPieceAt(r2, c2) != null && getPieceAt(r2, c2).getColor() == PColor.White) {
								// It is an enemy piece, capture it and move the pawn
								// Set the second cell to the pawn
								board.getCellAt(r2, c2).SetChessPiece(pawn);
								// Set the previous cell to null
								board.getCellAt(r1, c1).SetChessPiece(null);
								return true;
							} else {
								// The cell doesn't contain an enemy, invalid move
								return false;
							}
						}
						// Else it isn't a capture piece and is only moving 1 row
					} else if(Math.abs(c2 - c1) == 0) {
						if(getPieceAt(r2, c2) == null) {
							// If the cell is empty move the pawn to that cell
							// DUPLICATE CODE! *************************************************************
							// Set the second cell to the pawn
							board.getCellAt(r2, c2).SetChessPiece(pawn);
							// Set the previous cell to null
							board.getCellAt(r1, c1).SetChessPiece(null);
							return true;
						} else {
							return false;
						}
					}
				} else {
					// The pawn can only move row
					return false;
				}
			}
		} else {
			// The pawn has not moved yet and can move up to 2 rows
			// Could use a recursive call here
			// Check to see if the pawn is white, if it is, it's at the bottom rows
			if(pawn.getColor() == PColor.White) {
				if(r1 - r2 == 2 || r1 - r2 == 1) {
					if(r1 - r2 == 2) {
						// The pawn can only move two rows, not any cols
						if(c1 != c2) {
							// Pawn can't move two rows and move diagonally
							return false;
						} else { // The pawn is not moving diagonally 
							// We need to check if the cell two rows up the board is empty
							if(getPieceAt(r2, c2) == null) {
								// We can move our pawn to the new cell because it is empty
								// Set the second cell to the pawn
								board.getCellAt(r2, c2).SetChessPiece(pawn);
								// The the first cell to null
								board.getCellAt(r1, c1).SetChessPiece(null);
								pawn.setHasMoved(true);
								return true;
							} else {
								// The cell is not empty, so it is an invalid move
								return false;
							}
						}
					} else { // The pawn is only moving one row
						if(getPieceAt(r2, c2) == null) {
							// If the cell is empty move the pawn to that cell
							// DUPLICATE CODE! *************************************************************
							// Set the second cell to the pawn
							board.getCellAt(r2, c2).SetChessPiece(pawn);
							// Set the previous cell to null
							board.getCellAt(r1, c1).SetChessPiece(null);
							return true;
						} else {
							return false;
						}
					}
				} else {
					// Invalid move
					return false;
				}
			} else { // The color of the pawn is Black
				if(r2 - r1 == 2 || r2 - r1 == 1) {
					if(r2 - r1 == 2) {
						// The pawn can only move two rows, not any cols
						if(c1 != c2) {
							// Pawn can't move two rows and move diagonally
							return false;
						} else { // The pawn is not moving diagonally 
							// We need to check if the cell two rows up the board is empty
							if(getPieceAt(r2, c2) == null) {
								// We can move our pawn to the new cell because it is empty
								// Set the second cell to the pawn
								board.getCellAt(r2, c2).SetChessPiece(pawn);
								// The the first cell to null
								board.getCellAt(r1, c1).SetChessPiece(null);
								pawn.setHasMoved(true);
								return true;
							} else {
								// The cell is not empty, so it is an invalid move
								return false;
							}
						}
					} else { // The pawn is only moving one row
						if(getPieceAt(r2, c2) == null) {
							// If the cell is empty move the pawn to that cell
							// DUPLICATE CODE! *************************************************************
							// Set the second cell to the pawn
							board.getCellAt(r2, c2).SetChessPiece(pawn);
							// Set the previous cell to null
							board.getCellAt(r1, c1).SetChessPiece(null);
							return true;
						} else {
							return false;
						}
					}
				} else {
					// Invalid move
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * This method is not done yet, but is just to test how to check the
	 * move for the pieces, right now it only works for Pawns and only
	 * cares about vertical movement, no collision or anything, but it
	 * works
	 */
	public boolean checkMove(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Goal is to make more complex
		if(piece instanceof Pawn) {
			if(checkPawn(r1, c1, r2, c2, (Pawn)piece)) {
				System.out.println("VALID MOVE");
			} else {
				System.out.println("INVALID MOVE");
			}
		} else {
			System.out.println("Not a Pawn");
		}
		return false;
//		if (piece instanceof Pawn) {
//			System.out.println(
//					"Pawn" + r1 + " " + c1 + " " + r2 + " " + c2);
//			// Want to check if it is a valid move
//			if (piece.isHasMoved()) { // If it has moved we need more
//										// checks
//				if (Math.abs(r1 - r2) > 1) {
//					System.out.println("INVALID MOVE");
//				} else {
//					System.out.println("VALID MOVE");
//					board.getCellAt(r2, c2).SetChessPiece(piece);
//					board.getCellAt(r1, c1).SetChessPiece(null);
//					return true;
//				}
//			} else {
//				// It hasn't moved assuming it isn't moving diagonally
//				// now
//				if (Math.abs(r1 - r2) > 2) {
//					System.out.println("INVALID MOVE");
//				} else {
//					System.out.println("VALID MOVE");
//					board.getCellAt(r2, c2).SetChessPiece(piece);
//					board.getCellAt(r1, c1).SetChessPiece(null);
//					piece.setHasMoved(true);
//					return true;
//				}
//			}
		
	}

	/* No longer needed, moved into ChessController */
	// public static void main(String[] args){
	// Board test = new Board(1);
	// System.out.print(test.printBoard());
	// }

}
