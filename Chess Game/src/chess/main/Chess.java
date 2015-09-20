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
	
	public static void main(String[] args){
		Board test = new Board();
		test.reset();
		test.setKings();
		test.setQueens();
		test.setBishops();
		test.setKnights();
		test.setRooks();
		test.setPawns();
		test.GenerateBackground();
		System.out.print(test.printBoard());
		
		
	}

}
