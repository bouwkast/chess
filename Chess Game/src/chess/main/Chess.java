package chess.main;

import chess.objects.Bishop;
import chess.objects.Board;
import chess.objects.King;
import chess.objects.Knight;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Piece;
import chess.objects.Queen;
import chess.objects.Rook;

public class Chess {

	/** This is the board that will be used within the chess game */
	private Board board;

	/*******************************************************************
	 * Default constructor - in the future we may add some parameters
	 * like opening from a new board, number of players, level of AI,
	 * ect.
	 ******************************************************************/
	public Chess() {
		board = new Board();
	}

	public void reset() {
		board = new Board();
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
	 * @param board is the board to set
	 ******************************************************************/
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/*******************************************************************
	 * Method that will set the specified Cell to the specified Piece
	 * 
	 * @param row is the row of the Cell
	 * @param col is the col of the Cell
	 * @param piece is the Piece to set
	 ******************************************************************/
	public void setPieceAt(int row, int col, Piece piece) {
		getBoard().getCellAt(row, col).setChessPiece(piece);
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

	/*******************************************************************
	 * Simply moves one piece from one cell to the one specified. No
	 * checking should be done in this function, it is a basic helper
	 * method, so all checking if the piece can go in that cell should
	 * be done outside.
	 * 
	 * This will remove any piece in the second cell and replace it with
	 * the piece that was in the first cell.
	 * 
	 * @param r1 is the row of the first cell
	 * @param c1 is the col of the first cell
	 * @param r2 is the row of the second cell
	 * @param c2 is the col of the second cell
	 * @param piece is the piece to move
	 ******************************************************************/
	private void movePieceTo(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Set the second cell to the pawn
		board.getCellAt(r2, c2).setChessPiece(piece);
		// Set the previous cell to null
		board.getCellAt(r1, c1).setChessPiece(null);
		// If it was the Piece's first move, set that it has moved
		if (!piece.isHasMoved()) {
			piece.setHasMoved(true);
		}
	}

	/*******************************************************************
	 * Checks whether the pawn is making a valid move or not.
	 * 
	 * @param r1 is the row of the first Cell w/ Pawn
	 * @param c1 is the col of the first Cell w/ Pawn
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param pawn is the pawn we are checking
	 * @return a boolean value whether the pawn was moved
	 ******************************************************************/
	private boolean checkPawn(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		// Each color Pawn has its own unique movements
		if (pawn.getColor() == PColor.White) {
			return checkWPawn(r1, c1, r2, c2, pawn);
		} else { // Pawn is black
			return checkBPawn(r1, c1, r2, c2, pawn);
		}
	}

	private boolean checkWPawn(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		/*
		 * White pawns can move UP, U and L, or U and R Black pawns can
		 * move DOWN, D and L, or D and R
		 */
		if (r1 - r2 == 1 && c1 == c2) { // only moving one row UP
			if (getPieceAt(r2, c2) == null) { // If Cell is empty
				movePieceTo(r1, c1, r2, c2, pawn);
				return true; // Valid move
			} else {
				return false; // Piece is blocking the way
			}

		} else if (!pawn.isHasMoved() && r1 - r2 == 2 && c1 == c2) {
			// Pawn hasn't moved is moving two rows UP
			if (getPieceAt(r2 + 1, c1) == null
					&& getPieceAt(r2, c2) == null) {
				// Check if both spots are empty, if so valid move
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false; // There is a Piece blocking the way
			}

		} else if (r1 - r2 == 1 && Math.abs(c1 - c2) == 1) {
			// If it is moving diagonally 1 Cell
			if (getPieceAt(r2, c2) != null && getPieceAt(r2, c2)
					.getColor() != pawn.getColor()) {
				// Check to make sure there is an enemy Piece there
				
				// White Pawns capture U and L or U and R
				return cUpLeft(r1, c1, r2, c2, pawn)
						|| cUpRight(r1, c1, r2, c2, pawn);
			} else {
				return false; // Invalid pawn capture move
			}

		} else {
			return false; // Invalid moves
		}
	}

	private boolean checkBPawn(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		// Pawn is only moving 1 row DOWN
		if (r2 - r1 == 1 && c1 == c2) {
			if (getPieceAt(r2, c2) == null) {
				// If the Cell is empty, valid move
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false; // Piece is blocking the way
			}

		} else if (!pawn.isHasMoved() && r2 - r1 == 2 && c1 == c2) {
			// Pawn hasn't moved is moving two rows DOWN
			if (getPieceAt(r2 - 1, c1) == null
					&& getPieceAt(r2, c2) == null) {
				// Check both Cells to make sure they are empty
				movePieceTo(r1, c1, r2, c2, pawn);
				return true;
			} else {
				return false; // There is a Piece blocking the way
			}

		} else if (r2 - r1 == 1 && Math.abs(c1 - c2) == 1) {
			// Pawn is moving diagonally
			if (getPieceAt(r2, c2) != null && getPieceAt(r2, c2)
					.getColor() != pawn.getColor()) {
				// Make sure enemy Pawn is there
				
				// Black Pawns can only capture D and L, or D and R
				return cDownLeft(r1, c1, r2, c2, pawn)
						|| cDownRight(r1, c1, r2, c2, pawn);
			} else {
				return false; // Invalid pawn capture move
			}

		} else {
			return false; // Invalid moves
		}
	}

	/*******************************************************************
	 * Checks to see if a move containing the Knight is a valid move. To
	 * be valid the move must either be 2 rows and 1 col over OR be 2
	 * cols and 1 row over. Also, it must be a different color the the
	 * knight passed as a parameter.
	 * 
	 * @param r1 is the row of the first Cell w/ Knight
	 * @param c1 is the col of the first Cell w/ Knight
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param knight is the Knight we are checking
	 * @return a boolean value of whether the Knight was moved
	 ******************************************************************/
	private boolean checkKnight(int r1, int c1, int r2, int c2,
			Knight knight) {

		// First need to calc the difference for the rows and cols
		if ((Math.abs(r1 - r2) == 2 && Math.abs(c1 - c2) == 1)
				|| (Math.abs(r1 - r2) == 1
						&& Math.abs(c1 - c2) == 2)) {
			// Need to check the cell it is moving to
			if (getPieceAt(r2, c2) == null || knight
					.getColor() != getPieceAt(r2, c2).getColor()) {
				// Valid move, we can move the Knight
				movePieceTo(r1, c1, r2, c2, knight);
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

	/*******************************************************************
	 * Checks the movement of the Bishop Piece
	 * 
	 * @param r1 is the row of the first Cell w/ Bishop
	 * @param c1 is the col of the first Cell w/ Bishop
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param bishop is the Bishop that we are checking
	 * @return a boolean value whether the Bishop was moved
	 ******************************************************************/
	private boolean checkBishop(int r1, int c1, int r2, int c2,
			Bishop bishop) {
		// Bishops row and col must change by same amount
		if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
			return checkDiagonal(r1, c1, r2, c2, bishop);

		} else { // Not a diagonal move
			return false;
		}
	}
	/*******************************************************************
	 * Checks the movement of the Rook in it's four possible directions.
	 * Up, Right, Down, and Left. For each move, either the row or the
	 * col can't change while the other one does
	 * 
	 * Uses the checkLateral method.
	 * 
	 * @param r1 is the row of the first Cell w/ Rook
	 * @param c1 is the col of the first Cell w/ Rook
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param rook is the Rook that we are checking
	 * @return a boolean value whether the Rook was moved
	 ******************************************************************/
	private boolean checkRook(int r1, int c1, int r2, int c2,
			Rook rook) {
		/*
		 * Rooks, row can change cols are constant OR cols can change
		 * and rows are constant
		 */
		if ((r1 != r2 && c1 == c2) || (r1 == r2 && c1 != c2)) {
			return checkLateral(r1, c1, r2, c2, rook);
		} else { // Not a lateral movement
			return false;
		}
	}

	/*******************************************************************
	 * Checks the movement of the Queen, it uses the check diagonal and
	 * check Lateral
	 * 
	 * @param r1 is the row of the first Cell w/ Queen
	 * @param c1 is the col of the first Cell w/ Queen
	 * @param r2 is the row of the second Cell
	 * @param c2 is the row of the second Cell
	 * @param queen is the Queen that we are checking
	 * @return a boolean value whether the Queen was moved
	 ******************************************************************/
	private boolean checkQueen(int r1, int c1, int r2, int c2,
			Queen queen) {

		/* The Queen can move either diagonally or laterally */
		// Check if the attempted move is diagonal
		if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
			return checkDiagonal(r1, c1, r2, c2, queen);

			// Check if the attempted move is lateral
		} else if ((Math.abs(r1 - r2) == 0 && c1 != c2)
				|| (r1 != r2 && Math.abs(c1 - c2) == 0)) {
			return checkLateral(r1, c1, r2, c2, queen);
		}

		// If neither, return false because it is an invalid move
		return false;
	}

	/*******************************************************************
	 * Checks the movement of the King, similar to the Queen just the
	 * King can only move one spot. Aren't checking for Check or
	 * Checkmate, but if it is captured the game should end.
	 * 
	 * @param r1 is the row of the first Cell w/ King
	 * @param c1 is the col of the first Cell w/ King
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param king is the King that we are checking
	 * @return a boolean value whether the King was moved
	 ******************************************************************/
	private boolean checkKing(int r1, int c1, int r2, int c2,
			King king) {

		/* King is similar to the queen, but can only move 1 spot */
		
		// Check if the attempted move is diagonal
		if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
			// Check to make sure it is one spot
			if (Math.abs(r1 - r2) == 1 && Math.abs(c1 - c2) == 1)
				return checkDiagonal(r1, c1, r2, c2, king);

			// Check if the attempted move is lateral
		} else if ((Math.abs(r1 - r2) == 0 && Math.abs(c1 - c2) == 1)
				|| (Math.abs(r1 - r2) == 1
						&& Math.abs(c1 - c2) == 0)) {
			return checkLateral(r1, c1, r2, c2, king);
		}

		// If neither, return false because it is an invalid move
		return false;

	}

	/*******************************************************************
	 * Checks the Lateral(PROPER WORD? Couldn't think of what to call
	 * it) movement of the piece for its movement UP, RIGHT, DOWN, and
	 * LEFT
	 * 
	 * @param r1 is the row for the first Cell
	 * @param c1 is the col for the first Cell
	 * @param r2 is the row for the second Cell
	 * @param c2 is the col for the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkLateral(int r1, int c1, int r2, int c2,
			Piece piece) {
		if (r1 > r2 && c1 == c2) { // Check UP
			return checkUp(r1, c1, r2, c2, piece);
		} else if (r1 == r2 && c1 < c2) { // Check RIGHT
			return checkRight(r1, c1, r2, c2, piece);
		} else if (r1 < r2 && c1 == c2) { // Check DOWN
			return checkDown(r1, c1, r2, c2, piece);
		} else if (r1 == r2 && c1 > c2) { // Check LEFT
			return checkLeft(r1, c1, r2, c2, piece);
		}
		return false;
	}

	/*******************************************************************
	 * Checks the Piece's movement UP
	 * 
	 * @param r1 is the row for the first Cell
	 * @param c1 is the col for the first Cell
	 * @param r2 is the row for the second Cell
	 * @param c2 is the col for the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkUp(int r1, int c1, int r2, int c2,
			Piece piece) {
		// r1 is greater than r2, c1 and c2 are equal
		for (int x = r1 - 1; x >= r2; x--) { // We are going up
			// We don't care about the cols because they are the same
			if (x != r2) { // if true we are checking all cells inbtwn
				if (getPieceAt(x, c1) != null) {
					return false; // Piece is blocking the way
				}
			} else { // We are at the final Cell
				// use c2 because we are at the second clicked Cell
				if (getPieceAt(x, c2) == null || getPieceAt(x, c2)
						.getColor() != piece.getColor()) {
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				} else {
					return false; // same color piece is there
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks the Piece's movement to the RIGHT
	 * 
	 * @param r1 is the row for the first Cell
	 * @param c1 is the col for the first Cell
	 * @param r2 is the row for the second Cell
	 * @param c2 is the col for the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkRight(int r1, int c1, int r2, int c2,
			Piece piece) {
		// c2 is greater than c1, r1 and r2 are equal
		// don't care about the row because they are the same
		for (int y = c1 + 1; y <= c2; y++) {
			if (y != c2) { // checking all Cells inbetween
				if (getPieceAt(r1, y) != null) {
					return false; // piece is blocking the way
				}
			} else { // we are at the destination
				// use r2 because we are at the second clicked Cell
				if (getPieceAt(r2, y) == null || getPieceAt(r2, y)
						.getColor() != piece.getColor()) {
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				} else {
					return false; // same color piece is there
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks the Piece's movement DOWN
	 * 
	 * @param r1 is the row for the first Cell
	 * @param c1 is the col for the first Cell
	 * @param r2 is the row for the second Cell
	 * @param c2 is the col for the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkDown(int r1, int c1, int r2, int c2,
			Piece piece) {
		// r2 is greater than r1, c1 and c2 are the same
		for (int x = r1 + 1; x <= r2; x++) {
			if (x != r2) { // checking all Cells inbetween
				if (getPieceAt(x, c1) != null) {
					return false; // Piece is blocking the way
				}
			} else { // we are at the destination
				// use c2 because we are at the second clicked Cell
				if (getPieceAt(x, c2) == null || getPieceAt(x, c2)
						.getColor() != piece.getColor()) {
					movePieceTo(r1, c1, r2, c2, piece);
					return true; // Valid move
				} else {
					return false; // same color piece is there
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks the Piece's movement LEFT
	 * 
	 * @param r1 is the row for the first Cell
	 * @param c1 is the col for the first Cell
	 * @param r2 is the row for the second Cell
	 * @param c2 is the col for the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkLeft(int r1, int c1, int r2, int c2,
			Piece piece) {
		// c1 is greater than c2, r1 and r2 are equal
		for (int y = c1 - 1; y >= c2; y--) {
			if (y != c2) { // check all Cells inbetween
				if (getPieceAt(r1, y) != null) {
					return false; // Piece is blocking the way
				}
			} else { // we are at the destination
				// use r2 because we are at the second clicked Cell
				if (getPieceAt(r2, y) == null || getPieceAt(r2, y)
						.getColor() != piece.getColor()) {
					movePieceTo(r1, c1, r2, c2, piece);
					return true; // valid move
				} else {
					return false; // same color Piece is there
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Helper method that checks the piece's diagonal movement
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean checkDiagonal(int r1, int c1, int r2, int c2,
			Piece piece) {
		if (r1 > r2 && c1 < c2) { // Up and right
			return cUpRight(r1, c1, r2, c2, piece);
		} else if (r1 < r2 && c1 < c2) { // Down and right
			return cDownRight(r1, c1, r2, c2, piece);
		} else if (r1 < r2 && c1 > c2) { // Down and left
			return cDownLeft(r1, c1, r2, c2, piece);
		} else if (r1 > r2 && c1 > c2) { // Up and left
			return cUpLeft(r1, c1, r2, c2, piece);
		}
		return false;
	}

	/*******************************************************************
	 * Check the Piece's movement up and to the right
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean cUpRight(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Keep track of current cell being checked with x and y
		for (int x = r1 - 1, y = c1 + 1; x >= r2
				&& y <= c2; x--, y++) {
			if (x != r2) { // Check all cells in-between
				if (getPieceAt(x, y) != null) {
					return false; // Piece was blocking the way
				}
			} else { // At the final cell check its piece
				if (getPieceAt(x, y) == null || getPieceAt(x, y)
						.getColor() != piece.getColor()) {
					// Cell is either empty or contains an enemy
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				}
			}
		}
		return false; // Invalid Move
	}

	/*******************************************************************
	 * Check the Piece's movement down and to the right
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean cDownRight(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Keep track of current Cell with x and y
		for (int x = r1 + 1, y = c1 + 1; x <= r2
				&& y <= c2; x++, y++) {
			if (x != r2) { // Check all cells inbetween
				if (getPieceAt(x, y) != null) {
					// If there is a Piece blocking the way
					return false;
				}
			} else { // At the final cell check its piece
				if (getPieceAt(x, y) == null || getPieceAt(x, y)
						.getColor() != piece.getColor()) {
					// Make sure Cell is empty or contains an enemy
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				}
			}
		}
		return false; // Invalid move
	}

	/*******************************************************************
	 * Check the Piece's movement down and to the left
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean cDownLeft(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Keep track of current Cell with x and y
		for (int x = r1 + 1, y = c1 - 1; x <= r2
				&& y >= c2; x++, y--) {
			if (x != r2) { // Check all cells inbetween
				if (getPieceAt(x, y) != null) {
					// If there is a Piece blocking the way
					return false;
				}
			} else { // At the final cell check its piece
				if (getPieceAt(x, y) == null || getPieceAt(x, y)
						.getColor() != piece.getColor()) {
					// Makes sure Cell is empty or contains an enemy
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				}
			}
		}
		return false; // Invalid move
	}

	/*******************************************************************
	 * Check the Piece's movement up and to the left
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	private boolean cUpLeft(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Keep track of current Cell with x and y
		for (int x = r1 - 1, y = c1 - 1; x >= r2
				&& y >= c2; x--, y--) {
			if (x != r2) { // Check all cells inbetween
				if (getPieceAt(x, y) != null) {
					// If there is a Piece blocking the way
					return false;
				}
			} else { // At the final cell check its piece
				if (getPieceAt(x, y) == null || getPieceAt(x, y)
						.getColor() != piece.getColor()) {
					// Cell is empty or contains an enemy
					movePieceTo(r1, c1, r2, c2, piece);
					return true;
				}
			}
		}
		return false; // Invalid Move
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
		} else if (piece instanceof Knight) {
			System.out.println("KNIGHT");
			if (checkKnight(r1, c1, r2, c2, (Knight) piece)) {
				System.out.println("VALID MOVE KNIGHT");
				return true;
			} else {
				System.out.println("INVALID MOVE KNIGHT");
				return false;
			}
		} else if (piece instanceof Bishop) {
			System.out.println("BISHOP");
			if (checkBishop(r1, c1, r2, c2, (Bishop) piece)) {
				System.out.println("VALID MOVE BISHOP");
				return true;
			} else {
				System.out.println("INVALID MOVE BISHOP");
				return false;
			}
		} else if (piece instanceof Rook) {
			System.out.println("ROOK");
			if (checkRook(r1, c1, r2, c2, (Rook) piece)) {
				System.out.println("VALID MOVE ROOK");
				return true;
			} else {
				System.out.println("INVALID MOVE ROOK");
				return false;
			}
		} else if (piece instanceof Queen) {
			if (checkQueen(r1, c1, r2, c2, (Queen) piece)) {
				System.out.println("VALID MOVE QUEEN");
				return true;
			} else {
				System.out.println("INVALID MOVE QUEEN");
				return false;
			}
		} else if (piece instanceof King) {
			if (checkKing(r1, c1, r2, c2, (King) piece)) {
				System.out.println("VALID MOVE KING");
				return true;
			} else {
				System.out.println("INVALID MOVE KING");
				return false;

			}
		}
		System.out.println("Something went wrong......");
		return false;
	}

}
