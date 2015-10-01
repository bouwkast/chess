package chess.tester;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Pawn;
import chess.objects.Piece;

public class ChessTester {
	
	Chess game = new Chess();
	
	Pawn whitePawn = (Pawn)game.getPieceAt(6, 1);
	Pawn blackPawn = (Pawn)game.getPieceAt(1, 1);

	@Test
	public void testWPawnMove1() {
		game = new Chess();
		assertFalse(game.checkMove(6, 1, 3, 2, (whitePawn)));
	}
	
	@Test
	public void testBPawnMove1() {
		game = new Chess();
		assertFalse(game.checkMove(6, 1, 3, 2, (blackPawn)));
	}
	
	@Test
	public void testWPawnMove2() {
		game = new Chess();
		assertTrue(game.checkMove(6, 1, 5, 1, (whitePawn)));
	}
	
	@Test
	public void testBPawnMove2() {
		game = new Chess();
		assertTrue(game.checkMove(1, 1, 2, 1, (blackPawn)));
	}
	
	@Test
	public void testWPawnHasMoved() {
		game = new Chess();
		game.checkMove(6, 1, 5, 1, (whitePawn));
		assertTrue(whitePawn.isHasMoved());
	}
	
	@Test
	public void testBPawnHasMoved() {
		game = new Chess();
		game.checkMove(1, 1, 2, 1, (blackPawn));
		assertTrue(blackPawn.isHasMoved());
	}
	
	@Test
	public void testWPawnHasMoved2() {
		game = new Chess();
		assertFalse(whitePawn.isHasMoved());
	}
	
	@Test
	public void testBPawnHasMoved2() {
		game = new Chess();
		assertFalse(blackPawn.isHasMoved());
	}
	
	@Test
	public void testWPawnHasMoved3() {
		game = new Chess();
		game.checkMove(6, 1, 3, 1, (whitePawn));
		assertFalse(whitePawn.isHasMoved());
	}
	
	@Test
	public void testBPawnHasMoved3() {
		game = new Chess();
		game.checkMove(1, 1, 4, 1, (blackPawn));
		assertFalse(blackPawn.isHasMoved());
	}
	
	@Test
	public void testWPawnMove2Rows1() {
		game = new Chess();
		assertTrue(game.checkMove(6, 1, 4, 1, whitePawn) && whitePawn.isHasMoved());
	}
	
	@Test
	public void testWPawnMove2Rows2() {
		
	}
	
	@Test
	public void testBPawnMove2Rows1() {
		
	}
	
	@Test
	public void testBPawnMove2Rows2() {
		
	}

}
