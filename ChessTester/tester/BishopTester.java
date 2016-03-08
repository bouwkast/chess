/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.Board;
import chess.objects.PColor;
import chess.objects.Pawn;

/**
 * @author andy
 *
 */
public class BishopTester {


    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkWhiteBishopsPosition() {
        // White Bishops are at row 7, col 2 and 5
        game = new Chess();
        boolean isLeftBishop = game.getPieceAt(7, 2) instanceof Bishop;
        boolean isRightBishop = game.getPieceAt(7, 5) instanceof Bishop;
        boolean isLeftWhite = game.getPieceAt(7, 2)
                .getColor() == PColor.White;
        boolean isRightWhite = game.getPieceAt(7, 5)
                .getColor() == PColor.White;
        assertTrue(isLeftBishop && isRightBishop && isLeftWhite
                && isRightWhite);
    }
    
    
    @Test
    public void checkBlackBishopsPositions() {
        // Black Bishops are at row 0, col 2 and 5
        game = new Chess();
        boolean isLeftBishop = game.getPieceAt(0, 2) instanceof Bishop;
        boolean isRightBishop = game.getPieceAt(0, 5) instanceof Bishop;
        boolean isLeftBlack = game.getPieceAt(0, 2)
                .getColor() == PColor.Black;
        boolean isRightBlack = game.getPieceAt(0, 5)
                .getColor() == PColor.Black;
        assertTrue(isLeftBishop && isRightBishop && isLeftBlack
                && isRightBlack);
    }
    
    @Test
    public void bishopMovementUpRight() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        assertTrue(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementUpRightPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        game.setPieceAt(3, 4, whitePawn);
        assertFalse(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementUpRightSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        game.setPieceAt(2, 5, blackPawn);
        assertFalse(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementDownRight() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        assertTrue(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownRightPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        game.setPieceAt(4, 3, whitePawn);
        assertFalse(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownRightSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        game.setPieceAt(5, 4, blackPawn);
        assertFalse(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownLeft() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        assertTrue(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementDownLeftPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        game.setPieceAt(4, 3, whitePawn);
        assertFalse(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementDownLeftSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        game.setPieceAt(5, 2, blackPawn);
        assertFalse(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeft() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        assertTrue(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeftPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        game.setPieceAt(4, 4, whitePawn);
        assertFalse(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeftSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        game.setPieceAt(2, 2, blackPawn);
        assertFalse(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopInvalidMovement() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        assertFalse(game.checkMove(5, 5, 5, 2, bishop));
    }
    
}
