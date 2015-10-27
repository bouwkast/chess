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

	// TODO delete, this serves no purpose Chess() is the same
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
	public void movePieceTo(int r1, int c1, int r2, int c2,
			Piece piece) {
		// Set the second cell to the pawn
		board.getCellAt(r2, c2).setChessPiece(piece);
		// Set the previous cell to null
		board.getCellAt(r1, c1).setChessPiece(null);
		// If it was the Piece's first move, set that it has moved
		if (!piece.isHasMoved()) {
			piece.setHasMoved(true);
		}
		if (isWKingCheck()) {
			// System.out.println("In MovePieceTo WhiteKing");
		}
		if (isBKingCheck()) {
			// System.out.println("In MovePieceTo BlackKing");
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
	 * @return a boolean value whether the pawn can be moved
	 ******************************************************************/
	private boolean checkPawn(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (Math.abs(r1 - r2) > 2)
			return false;
		if (Math.abs(c1 - c2) > 1)
			return false;

		return pawnMove(r1, c1, r2, c2, pawn);
	}

	/*******************************************************************
	 * Checks each move that the Pawn can make to ensure that it is
	 * valid. This method is partially color independent, meaning that
	 * it does use the color for the proper movements, but different
	 * formulas for each color are unnecessary.
	 * 
	 * @param r1 is the row of the Pawn to check
	 * @param c1 is the col of the Pawn to check
	 * @param r2 is the row of the cell we are trying to move to
	 * @param c2 is the col of the cell we are trying to move to
	 * @param pawn is the Pawn we are checking
	 * @return a boolean value whether the pawn can be moved
	 ******************************************************************/
	private boolean pawnMove(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		if (r1 != r2) { // If rows are equal, invalid move
			int direction = pawn.getColor() == PColor.White ? 1 : -1;
			if (r1 - r2 == (2 * direction) && !pawn.isHasMoved()
					&& c1 - c2 == 0) {
				// Move two rows
				if (getPieceAt(r1 - direction, c1) == null
						&& getPieceAt(r2, c2) == null) {
					return true;
				}
			} else if (r1 - r2 == (1 * direction) && c1 - c2 == 0) {
				// Moving one row
				if (getPieceAt(r2, c1) == null) {
					return true;
				}
			} else if (r1 - r2 == (1 * direction)
					&& Math.abs(c1 - c2) == 1) {
				// Trying to capture
				if (getPieceAt(r2, c2) != null && getPieceAt(r2, c2)
						.getColor() != pawn.getColor()) {
					return true;
				}
			}
		}
		return false;
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
				// movePieceTo(r1, c1, r2, c2, knight);
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
	 * Checks to see if a pawn is up for promotion
	 * 
	 * @param row is the row of the Piece to check
	 * @param col is the col of the Piece to check
	 * @param piece is the Piece to check
	 * @return a boolean value whether the Piece can be promoted
	 ******************************************************************/
	public boolean checkPawnPromotion(int row, int col, Piece piece) {
		if (getPieceAt(row, col) instanceof Pawn) {
			if (row == 0 || row == 7)
				return true;
		}
		return false;
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
		} else {
			// Check for castling
			return checkCastling(r1, c1, r2, c2, king);
		}
		// If neither, return false because it is an invalid move
		return false;

	}

	/*******************************************************************
	 * Checks to see if the move is a valid castle move
	 * 
	 * @param r1 is the row of the King to castle
	 * @param c1 is the col of the King to castle
	 * @param r2 is the row of the castling move
	 * @param c2 is the col of the castling move
	 * @param king is the King to castle
	 * @return a boolean value whether the king can be castled
	 ******************************************************************/
	public boolean checkCastling(int r1, int c1, int r2, int c2,
			King king) {
		if (!king.isHasMoved()) { // King can't have moved yet
			if ((r1 == 0 && r2 == 0) || (r1 == 7 && r2 == 7)) {
				if (Math.abs(c1 - c2) == 2) { // moving two cols
					if (c1 < c2) { // going to the right
						return castleCheckRight(r1, c1, r2, c2, king);
					} else { // going to the left
						return castleCheckLeft(r1, c1, r2, c2, king);
					}
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks to see if the "queenside" castle is valid
	 * 
	 * @param r1 is the row of the King
	 * @param c1 is the col of the King
	 * @param r2 is the row of the castling move
	 * @param c2 is the col of the castling move
	 * @param king is the King to castle
	 * @return a boolean value whether the King can be castled
	 ******************************************************************/
	private boolean castleCheckLeft(int r1, int c1, int r2, int c2,
			King king) {
		if (Math.abs(c1 - c2) != 2) {
			return false;
		}
		if (getPieceAt(r2, c2 - 2) != null) {
			Piece toCastle = getPieceAt(r2, c2 - 2);
			if (toCastle instanceof Rook && !toCastle.isHasMoved()) {
				// Need 3 empty and non-check cells
				if (getPieceAt(r2, c2 - 1) != null)
					return false;
				if (getPieceAt(r2, c2) != null)
					return false;
				if (getPieceAt(r2, c2 + 1) != null)
					return false;
				if (isFutureCheck(r1, c1, r2, c2, king))
					return false;
				if (isFutureCheck(r1, c1, r2, c2 + 1, king))
					return false;
				return true;
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks to see if move is a valid "kingside" castle
	 * 
	 * @param r1 is the row of the King
	 * @param c1 is the col of the King
	 * @param r2 is the row of the castling move
	 * @param c2 is the col of the castling move
	 * @param king is the King to castle
	 * @return a boolean value whether it is a valid "kingside" castle
	 ******************************************************************/
	private boolean castleCheckRight(int r1, int c1, int r2, int c2,
			King king) {
		if (Math.abs(c1 - c2) != 2)
			return false;
		if (getPieceAt(r2, c2 + 1) != null) {
			Piece toCastle = getPieceAt(r2, c2 + 1);
			if (toCastle instanceof Rook && !toCastle.isHasMoved()) {
				if (getPieceAt(r2, c2 - 1) != null)
					return false;
				if (getPieceAt(r2, c2) != null)
					return false;
				if (isFutureCheck(r1, c1, r2, c2 - 1, king))
					return false;
				if (isFutureCheck(r1, c1, r2, c2, king))
					return false;
				return true;
			}
		}
		return false;
	}

	/*******************************************************************
	 * Executes the appropriate castling move
	 * 
	 * @param r1 is the row of the King to castle
	 * @param c1 is the col of the King to castle
	 * @param r2 is the row of the castling move
	 * @param c2 is the col of the castling move
	 * @param king is the King to castle
	 ******************************************************************/
	public void executeCastle(int r1, int c1, int r2, int c2,
			King king) {
		if (castleCheckRight(r1, c1, r2, c2, king)) { // kingside
			movePieceTo(r1, c1, r2, c2, king);
			Piece toCastle = getPieceAt(r2, c2 + 1);
			movePieceTo(r1, c2 + 1, r1, c2 - 1, toCastle);
		} else { // queenside
			movePieceTo(r1, c1, r2, c2, king);
			Piece toCastle = getPieceAt(r2, c2 - 2);
			movePieceTo(r1, c2 - 2, r1, c2 + 1, toCastle);
		}
	}

	/*******************************************************************
	 * Checks the Lateral movement of the piece for its movement UP,
	 * RIGHT, DOWN, and LEFT
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
		if (r1 != r2 && c1 == c2) {
			return checkVertical(r1, c1, r2, c2, piece);
		} else if (r1 == r2 && c1 != c2) {
			return checkHorizontal(r1, c1, r2, c2, piece);
		}
		return false;
	}

	/*******************************************************************
	 * Checks the piece's movement vertically to make sure it is a valid
	 * move.
	 * 
	 * @param r1 is the row of the Piece to check
	 * @param c1 is the col of the Piece to check
	 * @param r2 is the row of the Cell we are trying to move to
	 * @param c2 is the col of the Cell we are trying to move to
	 * @param piece is the Piece we are checking
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	private boolean checkVertical(int r1, int c1, int r2, int c2,
			Piece piece) {
		int direction = r1 > r2 ? 1 : -1; // direction piece moving
		// We sub direction to be able to go up or down
		for (int row = r1 - direction; row != r2 - direction; row -=
				direction) {
			if (row != r2) { // not at final cell
				if (getPieceAt(row, c1) != null) // piece in the way
					return false;
			} else { // at the final cell
				if (getPieceAt(row, c2) == null || getPieceAt(row, c2)
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
	 * @param r1 is the row of the Piece to check
	 * @param c1 is the col of the Piece to check
	 * @param r2 is the row of the Cell we are trying to move to
	 * @param c2 is the col of the Cell we are trying to move to
	 * @param piece is the Piece we are checking
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	private boolean checkHorizontal(int r1, int c1, int r2, int c2,
			Piece piece) {
		int direction = c1 > c2 ? 1 : -1; // left or right
		for (int col = c1 - direction; col != c2 - direction; col -=
				direction) {
			if (col != c2) { // not at final cell
				if (getPieceAt(r1, col) != null) // piece in the way
					return false;
			} else { // at the final cell
				if (getPieceAt(r2, col) == null || getPieceAt(r2, col)
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
	 * @param r1 is the row of the Piece to check
	 * @param c1 is the col of the Piece to check
	 * @param r2 is the row of the Cell we are trying to move to
	 * @param c2 is the col of the Cell we are trying to move to
	 * @param piece is the Piece we are checking
	 * @return a boolean value whether the move is valid
	 ******************************************************************/
	private boolean checkDiagonal(int r1, int c1, int r2, int c2,
			Piece piece) {
		int xDir = c1 > c2 ? 1 : -1;
		int yDir = r1 > r2 ? 1 : -1;
		for (int row = r1 - yDir, col = c1 - xDir; row != r2 - yDir
				&& col != c2 - xDir; row -= yDir, col -= xDir) {
			if (row != r2) { // need to check cells in-between
				if (getPieceAt(row, col) != null)
					return false; // Piece is in the way
			} else { // at the final cell
				if (getPieceAt(row, col) == null
						|| getPieceAt(row, col).getColor() != piece
								.getColor())
					return true; // valid move
			}
		}
		return false; // invalid move
	}


	private boolean isBKingCheck() {
		int[] location = board.findBKing();
		int kRow = location[0];
		int kCol = location[1];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null) {
					Piece temp = getPieceAt(row, col);
					if (temp.getColor() == PColor.White) {
						// Try to move the piece to white king
						if (checkMove(row, col, kRow, kCol, temp)) {
							// System.out.println("BK in Check");
							return true; // white king is in check
						}
					}
				}
			}
		}
		// System.out.println("BK not in Check");
		return false;
	}

	private boolean isWKingCheck() {
		/*
		 * The idea is to try and move every single black piece to where
		 * the king is, if they can move then it is in check.
		 */
		// First need to find the white king
		int[] location = board.findWKing();
		int kRow = location[0];
		int kCol = location[1];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null) {
					Piece temp = getPieceAt(row, col);
					if (temp.getColor() == PColor.Black) {
						// Try to move the piece to white king
						if (checkMove(row, col, kRow, kCol, temp)) {
							// System.out.println("WK in Check");
							return true; // white king is in check
						}
					}
				}
			}
		}
		// System.out.println("WK not in Check");
		return false;
	}

	private boolean isCheck() {

		return false;
	}

	private boolean isFutureCheck(int r1, int c1, int r2, int c2,
			Piece piece) {
		Piece old = getPieceAt(r1, c1);
		boolean moved = old.isHasMoved();
		Piece pCellToCheck;
		/* If result is 1 it means the move puts your king in check */
		int result = -1; // 0 is false, 1 is true
		if (getPieceAt(r2, c2) != null) {
			pCellToCheck = getPieceAt(r2, c2);
		} else {
			pCellToCheck = null;
		}
		movePieceTo(r1, c1, r2, c2, piece);
		if (piece.getColor() == PColor.White) {
			if (isWKingCheck()) {
				// We can't move our piece to risk our king
				result = 1;
			} else {
				result = 0;
			}
		} else {
			if (isBKingCheck()) {
				// We can't move our piece to risk our king
				result = 1;
			} else {
				result = 0;
			}
		}
		// Need to reset the pieces
		setPieceAt(r1, c1, old);
		getPieceAt(r1, c1).setHasMoved(moved);
		setPieceAt(r2, c2, pCellToCheck);

		if (result == 1) {
			// System.out.println("result is 1");
			return true;
		}
		// System.out.println("result is 0");
		return false;

	}

	/*******************************************************************
	 * Check the Piece's possible move, and move it if necessary
	 * 
	 * @param r1 is the row of the first Cell
	 * @param c1 is the col of the first Cell
	 * @param r2 is the row of the second Cell
	 * @param c2 is the col of the second Cell
	 * @param piece is the Piece that we are checking
	 * @return a boolean value whether the Piece was moved
	 ******************************************************************/
	public boolean checkMove(int r1, int c1, int r2, int c2,
			Piece piece) {
		if (piece instanceof Pawn) {
			if (checkPawn(r1, c1, r2, c2, (Pawn) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (piece instanceof Knight) {
			if (checkKnight(r1, c1, r2, c2, (Knight) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (piece instanceof Bishop) {
			if (checkBishop(r1, c1, r2, c2, (Bishop) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (piece instanceof Rook) {
			if (checkRook(r1, c1, r2, c2, (Rook) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (piece instanceof Queen) {
			if (checkQueen(r1, c1, r2, c2, (Queen) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (piece instanceof King) {
			if (checkKing(r1, c1, r2, c2, (King) piece)) {
				if (isFutureCheck(r1, c1, r2, c2, piece)) {
					return false;
				}
				return true;
			} else {
				return false;

			}
		}

		System.out.println("Something went wrong......");
		return false;
	}

}
