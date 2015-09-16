package chess.main;

import chess.objects.Board;

public class Chess {
	
	public static void main(String[] args){
		Board test = new Board();
		test.Reset();
		test.setKings();
		test.setQueens();
		test.setBishops();
		test.setKnights();
		test.setRooks();
		test.setPawns();
		
		System.out.print(test.printBoard());
		
	}

}
