package chess.main;

import chess.objects.Board;
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
	 * @param col
	 * @return
	 ******************************************************************/
	public Piece getPieceAt(int row, int col) {
		return this.getBoard().getCellAt(row, col).getChessPiece();
	}

	/**
	 * This method is not done yet, but is just to test how to check the
	 * move for the pieces, right now it only works for Pawns and only
	 * cares about vertical movement, no collision or anything, but it
	 * works
	 */
	public boolean checkMove(int r1, int c1, int r2, int c2,
			Piece piece) {
		if (piece instanceof Pawn) {
			System.out.println(
					"Pawn" + r1 + " " + c1 + " " + r2 + " " + c2);
			// Want to check if it is a valid move
			if (piece.isHasMoved()) { // If it has moved we need more
										// checks
				if (Math.abs(r1 - r2) > 1) {
					System.out.println("INVALID MOVE");
				} else {
					System.out.println("VALID MOVE");
					board.getCellAt(r2, c2).SetChessPiece(piece);
					board.getCellAt(r1, c1).SetChessPiece(null);
					return true;
				}
			} else {
				// It hasn't moved assuming it isn't moving diagonally
				// now
				if (Math.abs(r1 - r2) > 2) {
					System.out.println("INVALID MOVE");
				} else {
					System.out.println("VALID MOVE");
					board.getCellAt(r2, c2).SetChessPiece(piece);
					board.getCellAt(r1, c1).SetChessPiece(null);
					piece.setHasMoved(true);
					return true;
				}
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
