package chess.main;

import chess.objects.Board;

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

	/* No longer needed, moved into ChessController */
	// public static void main(String[] args){
	// Board test = new Board(1);
	// System.out.print(test.printBoard());
	// }

}
