package chess.main;

import chess.objects.Board;

public class Chess {
	
	private Board board;
	
	public Chess() {
		board = new Board(1);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	/* No longer needed, moved into ChessController*/
//	public static void main(String[] args){
//		Board test = new Board(1);
//		System.out.print(test.printBoard());	
//	}

}
