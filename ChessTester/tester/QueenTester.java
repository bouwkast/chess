/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Queen;

/**
 * @author andy
 *
 */
public class QueenTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkWhiteQueenPosition() {
        // White Queen is located at row 7, col 3
        game = new Chess();
        boolean isQueen = game.getPieceAt(7, 3) instanceof Queen;
        boolean isQueenWhite = game.getPieceAt(7, 3)
                .getColor() == PColor.White;
        assertTrue(isQueen && isQueenWhite);
    }
    
    @Test
    public void checkBlackQueenPosition() {
        // Black Queen is located at row 0, col 3
        game = new Chess();
        boolean isQueen = game.getPieceAt(0, 3) instanceof Queen;
        boolean isQueenBlack = game.getPieceAt(0, 3)
                .getColor() == PColor.Black;
        assertTrue(isQueen && isQueenBlack);
    }
    
    @Test
    public void queenMovementUp() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 2, 4, queen));
    }
    
    @Test
    public void queenMovementUpRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 2, 6, queen));
    }
    
    @Test
    public void queenMovementRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 4, 7, queen));
    }
    
    @Test
    public void queenMovementDownRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertTrue(game.checkMove(2, 2, 5, 5, queen));
    }
    
    @Test
    public void queenMovementDown() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertTrue(game.checkMove(2, 2, 5, 2, queen));
    }
    
    @Test
    public void queenMovementDownLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 5, queen);
        assertTrue(game.checkMove(2, 5, 5, 2, queen));
    }
    
    @Test
    public void queenMovementLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(5, 7, queen);
        assertTrue(game.checkMove(5, 7, 5, 0, queen));
    }
    
    @Test
    public void queenMovementUpLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(5, 7, queen);
        assertTrue(game.checkMove(5, 7, 2, 4, queen));
    }
    
    @Test
    public void queenInvalidMovement() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertFalse(game.checkMove(2, 2, 4, 3, queen));
    }
}
