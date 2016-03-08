/**
 * 
 */
package tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.King;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Queen;

/**
 * @author andy
 *
 */
public class CheckmateTesting {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void kingInCheckTrue() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Pawn blackPawn = new Pawn(PColor.Black);
        
        game.setPieceAt(6, 3, whiteKing);
        game.setPieceAt(4, 4, blackPawn);
        
        assertTrue(game.isFutureCheck(6, 3, 5, 3, whiteKing));
    }
    
    @Test
    public void kingInCheckFalse() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Pawn blackPawn = new Pawn(PColor.Black);
        
        game.setPieceAt(6, 3, whiteKing);
        game.setPieceAt(4, 4, blackPawn);
        
        assertFalse(game.isFutureCheck(6, 3, 6, 2, whiteKing));
    }
    
    @Test
    public void whiteKingInCheckmateTrue() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Queen blackQueen1 = new Queen(PColor.Black);
        Queen blackQueen2 = new Queen(PColor.Black);
        Queen blackQueen3 = new Queen(PColor.Black);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 3, blackQueen1);
        game.setPieceAt(7, 5, blackQueen2);
        game.setPieceAt(6, 3, blackQueen3);
        
        assertEquals(game.isGameOver(), 0);
    }
    
    @Test
    public void whiteKingInCheckmateTrue2() {
        game = new Chess();
        game.movePieceTo(6, 6, 4, 6, game.getPieceAt(6, 6));
        game.movePieceTo(1, 4, 3, 4, game.getPieceAt(1, 4));
        game.movePieceTo(6, 5, 5, 5, game.getPieceAt(6, 5));
        game.movePieceTo(0, 3, 4, 7, game.getPieceAt(0, 3));
        assertTrue(game.isGameOver() == 0);
    }
    
    @Test
    public void whiteKingInCheckmateFalse() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Queen blackQueen1 = new Queen(PColor.Black);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 3, blackQueen1);
        
        assertEquals(game.isGameOver(), -1);
    }
    
    @Test
    public void blackKingInCheckmateTrue() {
        game = new Chess();
        King blackKing = new King(PColor.Black);
        Queen whiteQueen1 = new Queen(PColor.White);
        
        game.setPieceAt(0, 4, blackKing);
        game.setPieceAt(0, 3, whiteQueen1);
        
        assertEquals(game.isGameOver(), -1);
    }
    
    @Test
    public void blackKingInCheckmateFalse() {
        game = new Chess();
        King blackKing = new King(PColor.Black);
        Queen whiteQueen1 = new Queen(PColor.White);
        Queen whiteQueen2 = new Queen(PColor.White);
        Queen whiteQueen3 = new Queen(PColor.White);
        
        game.setPieceAt(0, 4, blackKing);
        game.setPieceAt(0, 3, whiteQueen1);
        game.setPieceAt(0, 5, whiteQueen2);
        game.setPieceAt(1, 4, whiteQueen3);
        
        assertEquals(game.isGameOver(), 1);
    }
    
    /**Start of Stalemate checking**/
    
    @Test
    public void gameOverByStalemateBlackNoMoves() {
        game = new Chess();
        game.getBoard().reset();
        game.setPieceAt(0, 7, new King(PColor.Black));
        game.setPieceAt(2, 6, new Queen(PColor.White));
        game.setPieceAt(1, 5, new King(PColor.White));
        equals(game.isGameOver() == 2);
    }
    
    @Test
    public void gameOverByStalemateWhiteNoMoves() {
        game = new Chess();
        game.getBoard().reset();
        game.setPieceAt(0, 7, new King(PColor.White));
        game.setPieceAt(2, 6, new Queen(PColor.Black));
        game.setPieceAt(1, 5, new King(PColor.Black));
        equals(game.isGameOver() == 2);
    }

}
