package chess.main;

import chess.objects.Board;
import chess.objects.King;
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
		setPieceAt(r2, c2, piece);
		// Set the previous cell to null
		setPieceAt(r1, c1, null);
		// If it was the Piece's first move, set that it has moved
		if (!piece.isHasMoved()) {
			piece.setHasMoved(true);
		}
	}

	// TODO move to the pawn class
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
		if (king.castleCheckRight(r1, c1, r2, c2, king, this)) { // kingside
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
	 * Determines whether the specified King is in check
	 * 
	 * @param color is the color of the King
	 * @return a boolean value whether the king is in check
	 ******************************************************************/
	private boolean isKingInCheck(PColor color) {
		int[] location = board.findKing(color);
		int kRow = location[0];
		int kCol = location[1];
		// Want to try to move opposite colors to the king specified
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null) {
					Piece temp = getPieceAt(row, col);
					if (temp.getColor() != color) {
						// Try to move the piece to king
						if (checkMove(row, col, kRow, kCol, temp)) {
							return true; // white king is in check
						}
					}
				}
			}
		}
		return false;
	}

	/*******************************************************************
	 * Checks to see if the specified king is in checkmate, it does this
	 * by having the king in check and seeing if there are any valid
	 * moves that can get the king out of the check. If not, then it is
	 * considered to be a checkmate and the game is over.
	 * 
	 * @param color is the PColor to check
	 * @return a boolean value whether color is in checkmate
	 ******************************************************************/
	private boolean isKingInCheckmate(PColor color) {
		if (isKingInCheck(color)) {
			// If the king is in check try to see if it can get out
			// check every move of their color to see if they can get
			// out

			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (getPieceAt(row, col) != null) {
						Piece toCheck = getPieceAt(row, col);
						if (toCheck.getColor() == color) {
							for (int r = 0; r < 8; r++) {
								for (int c = 0; c < 8; c++) {
									if (checkMove(row, col, r, c,
											toCheck)) {
										return false;
									}
								}
							}
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	/*******************************************************************
	 * Checks to see if the specified player, by color, is out of valid
	 * moves.
	 * 
	 * @param color is the PColor to check
	 * @return a boolean value whether the player has any valid moves
	 ******************************************************************/
	private boolean isOutOfMoves(PColor color) {

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null) {
					Piece toCheck = getPieceAt(row, col);
					if (toCheck.getColor() == color) {
						for (int r = 0; r < 8; r++) {
							for (int c = 0; c < 8; c++) {
								if (checkMove(row, col, r, c,
										toCheck)) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	/*******************************************************************
	 * Checks to see if there is a stalemate in the game
	 * 
	 * @param color is the PColor to check is there is a stalemate
	 * @return a boolean value whether there is a stalemate
	 ******************************************************************/
	private boolean isStalemate(PColor color) {
		if (!isKingInCheck(color)) { // king can't be in check
			// check to see if any valid moves are left for the player
			return isOutOfMoves(color);
		}
		return false;
	}

	/*******************************************************************
	 * Checks to see if the game is over, either by a player winning or
	 * by stalemate.
	 * 
	 * If result is 0 it means that the white king is in checkmate and
	 * Black wins.
	 * 
	 * If result is 1 it means that the black king is in checkmate and
	 * White wins.
	 * 
	 * If result is 2 it means that there is a stalemate, nobody wins.
	 * 
	 * If result is -1 it means that the game is still going.
	 * 
	 * @return an integer value of whether the game is over
	 ******************************************************************/
	public int isGameOver() {
		int result = -1; // default assumption game is still going
		if (isKingInCheckmate(PColor.White))
			result = 0; // white in checkmate, black wins
		else if (isKingInCheckmate(PColor.Black))
			result = 1; // black in checkmate, white wins
		else if (isStalemate(PColor.White)
				|| isStalemate(PColor.Black))
			result = 2; // stalemate, no winner
		return result; // whether the game is over
	}

	/*******************************************************************
	 * Checks to see if a piece is moved to a cell if it will put their
	 * king at risk. This is done before you move the piece.
	 * 
	 * @param r1 is the row of the Piece
	 * @param c1 is the col of the Piece
	 * @param r2 is the row of the Cell to move to
	 * @param c2 is the col of the Cell to move to
	 * @param piece is the Piece to check
	 * @return a boolean value whether it puts the King at risk
	 ******************************************************************/
	public boolean isFutureCheck(int r1, int c1, int r2, int c2,
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
			if (isKingInCheck(PColor.White)) {
				result = 1;
			} else {
				result = 0;
			}
		} else {
			if (isKingInCheck(PColor.Black)) {
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
			return true;
		}
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

		if (piece.checkMovement(r1, c1, r2, c2, this)) {
			if (isFutureCheck(r1, c1, r2, c2, piece)) {
				return false;
			}
			return true;
		}
		return false;
	}
}
