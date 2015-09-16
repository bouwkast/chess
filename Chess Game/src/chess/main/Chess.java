package chess.main;

import chess.objects.Board;

public class Chess {
	
	public static void main(String[] args){
		Board test = new Board();
		test.Reset();
		test.SetKings();
		test.SetQueens();
		test.SetBishops();
		test.SetKnights();
		test.SetRook();
		test.SetPawns();
		
		Board test2 = new Board();
		test2.Reset();
		test2.setKings2();
		test2.setQueens2();
		test2.setBishops2();
		test2.setKnights2();
		test2.setRooks2();
		test2.setPawns2();
		
		System.out.print(test2.printBoard());
		
	}

}
