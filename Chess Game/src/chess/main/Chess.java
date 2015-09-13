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
	}

}
