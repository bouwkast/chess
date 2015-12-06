/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.Knight;
import chess.objects.PColor;
import chess.objects.Pawn;

/**
 * @author andy
 *
 */
public class KnightTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkWhiteKnightsPosition() {
        // White Knights are at row 7, col 1 and 6
        game = new Chess();
        boolean isLeftKnight = game.getPieceAt(7, 1) instanceof Knight;
        boolean isRightKnight = game.getPieceAt(7, 6) instanceof Knight;
        boolean isLeftWhite = game.getPieceAt(7, 1)
                .getColor() == PColor.White;
        boolean isRightWhite = game.getPieceAt(7, 6)
                .getColor() == PColor.White;
        assertTrue(isLeftKnight && isRightKnight && isLeftWhite
                && isRightWhite);
    }
    
    @Test
    public void checkBlackKnightsPositions() {
        // Black Knights are at row 0, col 1 and 6
        game = new Chess();
        boolean isLeftKnight = game.getPieceAt(0, 1) instanceof Knight;
        boolean isRightKnight = game.getPieceAt(0, 6) instanceof Knight;
        boolean isLeftBlack = game.getPieceAt(0, 1)
                .getColor() == PColor.Black;
        boolean isRightBlack = game.getPieceAt(0, 6)
                .getColor() == PColor.Black;
                
        assertTrue(isLeftKnight && isRightKnight && isLeftBlack
                && isRightBlack);
    }
    
    @Test
    public void knightMovementUpLeftTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 3, 2, knight));
    }
    
    @Test
    public void knightMovementUpLeftOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightMovementUpRightTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 3, 6, knight));
    }
    
    @Test
    public void knightMovementUpRightOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 2, 5, knight));
    }
    
    @Test
    public void knightMovementDownRightTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 5, 6, knight));
    }
    
    @Test
    public void knightMovementDownRightOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 6, 5, knight));
    }
    
    @Test
    public void knightMovementDownLeftTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 5, 2, knight));
    }
    
    @Test
    public void knightMovementDownLeftOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 6, 3, knight));
    }
    
    @Test
    public void knightCantMoveToSameColorPieceBlack() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, blackPawn);
        assertFalse(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightCantMoveToSameColorPieceWhite() {
        game = new Chess();
        Knight knight = new Knight(PColor.White);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, whitePawn);
        assertFalse(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightCanCapture() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, whitePawn);
        assertTrue(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooManyRows() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 7, 3, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooManyCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 2, 1, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewRows() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 4, 2, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 2, 4, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewCols2() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 3, 4, knight));
    }
}
