package chess.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import chess.objects.Bishop;
import chess.objects.Board;
import chess.objects.King;
import chess.objects.Knight;
import chess.objects.Move;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Piece;
import chess.objects.Queen;
import chess.objects.Rook;

public class Chess {

	/** This is the board that will be used within the chess game */
	private Board board;
	/** Stack of Moves to undo previous moves */
	private Stack<Move> moves;
	/** Boolean to tell if a move is en passant */
	private boolean enPassCap;

	/*******************************************************************
	 * Default constructor - in the future we may add some parameters
	 * like opening from a new board, number of players, level of AI,
	 * ect.
	 ******************************************************************/
	public Chess() {
		board = new Board();
		moves = new Stack<Move>();
		enPassCap = false;
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
		makeMove(r1, c1, r2, c2);
		// Set the second cell to the pawn
		setPieceAt(r2, c2, piece);
		// Set the previous cell to null
		setPieceAt(r1, c1, null);
	}

	/*******************************************************************
	 * Makes a copy of a move that is about to be made.
	 * 
	 * @param r1 the selected row
	 * @param c1 the selected col
	 * @param r2 the targeted row
	 * @param c2 the targeted col
	 ******************************************************************/
	public void makeMove(int r1, int c1, int r2, int c2) {
		Piece selPiece = getPieceAt(r1, c1); // shouldn't be null
		Piece tarPiece = getPieceAt(r2, c2); // can be null
		Move toMake = new Move(r1, c1, r2, c2, selPiece, tarPiece);
		moves.add(toMake.clone());
	}

	/*******************************************************************
	 * Undo the previously made move
	 ******************************************************************/
	public void unMakeMove() {
		Move toUnMake = moves.pop();
		int r1 = toUnMake.getR1();
		int c1 = toUnMake.getC1();
		int r2 = toUnMake.getR2();
		int c2 = toUnMake.getC2();
		setPieceAt(r1, c1, createCopy(toUnMake.getSelPiece()));
		setPieceAt(r2, c2, createCopy(toUnMake.getTarPiece()));
	}

	/*******************************************************************
	 * Allows for an easy way to copy the appropriate Piece
	 * 
	 * @param piece is the Piece to create a copy of
	 * @return a copy of the Piece
	 ******************************************************************/
	private Piece createCopy(Piece piece) {
		if (piece == null)
			return null;
		switch (piece.getName()) {
		case "Pawn":
			return new Pawn((Pawn) piece);
		case "Rook":
			return new Rook((Rook) piece);
		case "Bishop":
			return new Bishop((Bishop) piece);
		case "Knight":
			return new Knight((Knight) piece);
		case "Queen":
			return new Queen((Queen) piece);
		default:
			return new King((King) piece);
		}
	}

	/*******************************************************************
	 * Gets the stack of Moves that have been currently made
	 * 
	 * @return a Stack of Move
	 ******************************************************************/
	public Stack<Move> getMoves() {
		return moves;
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
			Piece king) {
		King toCheck = (King) king;
		if (toCheck.castleCheckRight(r1, c1, r2, c2, king, this)) {
			// kingside
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
	 * Executes an en Passant move
	 * 
	 * @param r1 is the initial row
	 * @param c1 is the initial col
	 * @param r2 is the targeted row
	 * @param c2 is the targeted col
	 * @param pawn is the Pawn we are moving
	 ******************************************************************/
	public void executeEnPassant(int r1, int c1, int r2, int c2,
			Pawn pawn) {
		movePieceTo(r1, c1, r2, c2, pawn);
		setPieceAt(r1, c2, null);
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
			if(generateMoves(color).size() == 0)
				return true;
//			return isOutOfMoves(color);
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
		boolean moved = old.hasMoved();
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
		unMakeMove();
		// setPieceAt(r1, c1, old);
		// getPieceAt(r1, c1).setHasMoved(moved);
		// setPieceAt(r2, c2, pCellToCheck);

		if (result == 1) {
			return true;
		}
		return false;

	}

	/*******************************************************************
	 * Generates all of the possible moves for the specified color. TODO
	 * Maybe store all alive pieces in a map for quicker lookup
	 * 
	 * @param color is the PColor to search
	 * @return a List of all valid Moves
	 ******************************************************************/
	public List<Move> generateMoves(PColor color) {
		List<Move> validMoves = new ArrayList<Move>();
		int direction = color == PColor.White ? 1 : -1;
		boolean passVal = false;
		for (int r1 = 0; r1 < 8; r1++) {
			for (int c1 = 0; c1 < 8; c1++) {
				// First two loops go through board looking for color
				if (getPieceAt(r1, c1) != null
						&& getPieceAt(r1, c1).getColor() == color) {
					// it is a black piece
					for (int r2 = 0; r2 < 8; r2++) {
						for (int c2 = 0; c2 < 8; c2++) {
							if(r2 - direction > -1 && r2 - direction < 8) {
								passVal = getBoard().getCellAt(r1 - direction, c2).isPassant();
							}
							if (checkMove(r1, c1, r2, c2,
									getPieceAt(r1, c1))) {
								if(r2 - direction > -1 && r2 - direction < 8) {
									getBoard().getCellAt(r1 - direction, c2).setPassant(passVal);
								}
								Move toAdd = new Move(r1, c1, r2, c2,
										getPieceAt(r1, c1),
										getPieceAt(r2, c2));
								validMoves.add(toAdd.clone());
								
							}
						}
					}
				}
			}
		}
		return validMoves;
	}

	/*******************************************************************
	 * From the list of all valid moves, this method utilizes a negaMax
	 * algorithm to determine what the best possible move it can make
	 * with the specified depth.
	 * 
	 * @param color is the PColor we are checking
	 * @return the best possible Move to be made
	 ******************************************************************/
	public Move getBestMove(PColor color) {
		List<Move> validMoves = generateMoves(color);
		int bestResult = Integer.MIN_VALUE;
		Move bestMove = null;
		int depth = 2;

		for (Move move : validMoves) {
			int r1 = move.getR1();
			int c1 = move.getC1();
			int r2 = move.getR2();
			int c2 = move.getC2();

			movePieceTo(r1, c1, r2, c2,
					createCopy(move.getSelPiece()));

			int result = -1 * negaMax(depth, color);
			unMakeMove();
			if (result > bestResult) {
				bestResult = result;
				bestMove = move;
			}
		}
		System.out.println(bestResult);
		return bestMove;
	}

	/*******************************************************************
	 * A negaMax algorithm that determines what the best possible move
	 * will be for the specified color.
	 * 
	 * For more information, Wikipedia and some chess wikis explain it
	 * really well.
	 * 
	 * @param depth is how many plies to search
	 * @param color is what PColor we are looking at
	 * @return an integer value of the best score.
	 ******************************************************************/
	public int negaMax(int depth, PColor color) {
		if (depth <= 0 || isGameOver() != -1)
			return evauluateScores();

		List<Move> moves = generateMoves(color);
		int currentMax = Integer.MIN_VALUE;

		for (Move currentMove : moves) {

			int r1 = currentMove.getR1();
			int c1 = currentMove.getC1();
			int r2 = currentMove.getR2();
			int c2 = currentMove.getC2();

			movePieceTo(r1, c1, r2, c2,
					createCopy(currentMove.getSelPiece()));
			int score = -1 * negaMax(depth - 1, color);
			unMakeMove();
			if (score > currentMax) {
				currentMax = score;
			}
		}
		return currentMax;
	}

	/*******************************************************************
	 * Evaluates the "score" of either side by adding together each
	 * colors remaining Pieces' score values
	 * 
	 * TODO only coded for the black side right now
	 * 
	 * @return an int containing the score
	 ******************************************************************/
	private int evauluateScores() {
		int scoreWhite = 0;
		int scoreBlack = 0;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null) {
					if (getPieceAt(row, col)
							.getColor() == PColor.Black) {
						scoreBlack += getPieceAt(row, col).getScore();
					} else {
						scoreWhite += getPieceAt(row, col).getScore();
					}
				}
			}
		}
		// Each side has a score for remaining pieces now
		return scoreBlack - scoreWhite;
	}

	/*******************************************************************
	 * DESCRIPTION
	 * 
	 * @return the enPassCap
	 ******************************************************************/
	public boolean isEnPassCap() {
		return enPassCap;
	}

	/*******************************************************************
	 * DESCRIPTION 
	 *
	 * @param enPassCap the enPassCap to set
	 ******************************************************************/
	public void setEnPassCap(boolean enPassCap) {
		this.enPassCap = enPassCap;
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
			if (!piece.hasMoved())
				piece.setHasMoved(true);
			return true;
		}
		return false;
	}
}
