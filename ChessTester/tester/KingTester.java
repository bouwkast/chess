/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.King;
import chess.objects.PColor;
import chess.objects.Pawn;

/**
 * @author andy
 *
 */
public class KingTester {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);

    @Test
    public void checkWhiteKingPosition() {
        // White King is located at row 7, col 4
        game = new Chess();
        boolean isKing = game.getPieceAt(7, 4) instanceof King;
        boolean isKingWhite = game.getPieceAt(7, 4)
                .getColor() == PColor.White;
        assertTrue(isKing && isKingWhite);
    }
 
    
    @Test
    public void checkBlackKingPosition() {
        // White King is located at row 0, col 4
        game = new Chess();
        boolean isKing = game.getPieceAt(0, 4) instanceof King;
        boolean isKingBlack = game.getPieceAt(0, 4)
                .getColor() == PColor.Black;
        assertTrue(isKing && isKingBlack);
    }
    
    @Test
    public void kingMovementUp() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 3, 4, king));
    }
    
    @Test
    public void kingMovementUpRight() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 3, 5, king));
    }
    
    @Test
    public void kingMovementRight() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 4, 5, king));
    }
    
    @Test
    public void kingMovementDownRight() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 2, king);
        assertTrue(game.checkMove(2, 2, 3, 3, king));
    }
    
    @Test
    public void kingMovementDown() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 2, king);
        assertTrue(game.checkMove(2, 2, 3, 2, king));
    }
    
    @Test
    public void kingMovementDownLeft() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 5, king);
        assertTrue(game.checkMove(2, 5, 3, 4, king));
    }
    
    @Test
    public void kingMovementLeft() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertTrue(game.checkMove(5, 7, 5, 6, king));
    }
    
    @Test
    public void kingMovementUpLeft() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertTrue(game.checkMove(5, 7, 4, 6, king));
    }
    
    @Test
    public void kingInvalidMovement() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertFalse(game.checkMove(5, 7, 5, 0, king));
    }
    
    @Test
    public void kingInvalidMovementDiagCols() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 5, king);
        assertFalse(king.checkMovement(5, 5, 4, 7, game));
    }
    
    @Test
    public void kingInvalidMovementDiagCols2() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 5, king);
        assertFalse(king.checkMovement(5, 5, 4, 7, game));
    }
    
}
