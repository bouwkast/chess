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
import chess.objects.Rook;

/**
 * @author andy
 *
 */
public class RookTester {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkWhiteRooksPosition() {
        // White Rooks are at row 7, col 0 and 7
        game = new Chess();
        boolean isLeftRook = game.getPieceAt(7, 0) instanceof Rook;
        boolean isRightRook = game.getPieceAt(7, 7) instanceof Rook;
        boolean isLeftWhite = game.getPieceAt(7, 0)
                .getColor() == PColor.White;
        boolean isRightWhite = game.getPieceAt(7, 7)
                .getColor() == PColor.White;
        assertTrue(isLeftRook && isRightRook && isLeftWhite
                && isRightWhite);
    }
    
    
    @Test
    public void checkBlackRooksPositions() {
        // Black Rooks are at row 0, col 0 and 7
        game = new Chess();
        boolean isLeftRook = game.getPieceAt(0, 0) instanceof Rook;
        boolean isRightRook = game.getPieceAt(0, 7) instanceof Rook;
        boolean isLeftBlack = game.getPieceAt(0, 0)
                .getColor() == PColor.Black;
        boolean isRightBlack = game.getPieceAt(0, 7)
                .getColor() == PColor.Black;
        assertTrue(isLeftRook && isRightRook && isLeftBlack
                && isRightBlack);
    }

    @Test
    public void rookMovementUp() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 3, rook);
        assertTrue(game.checkMove(5, 3, 2, 3, rook));
    }
    
    @Test
    public void rookMovementUpPieceBlocking() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 3, rook);
        game.setPieceAt(3, 3, whitePawn);
        assertFalse(game.checkMove(5, 3, 2, 3, rook));
    }
    
    @Test
    public void rookMovementUpSameColorInPlace() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 3, rook);
        game.setPieceAt(2, 3, blackPawn);
        assertFalse(game.checkMove(5, 3, 2, 3, rook));
    }
    
    @Test
    public void rookMovementRight() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 0, rook);
        assertTrue(game.checkMove(5, 0, 5, 7, rook));
    }
    
    @Test
    public void rookMovementRightPieceBlocking() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 0, rook);
        game.setPieceAt(5, 6, whitePawn);
        assertFalse(game.checkMove(5, 0, 5, 7, rook));
    }
    
    @Test
    public void rookMovementRightSameColorInPlace() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 0, rook);
        game.setPieceAt(5, 7, blackPawn);
        assertFalse(game.checkMove(5, 0, 5, 7, rook));
    }
    
    @Test
    public void rookMovementDown() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(2, 0, rook);
        assertTrue(game.checkMove(2, 0, 5, 0, rook));
    }
    
    @Test
    public void rookMovementDownPieceBlocking() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(2, 0, rook);
        game.setPieceAt(4, 0, whitePawn);
        assertFalse(game.checkMove(2, 0, 5, 0, rook));
    }
    
    @Test
    public void rookMovementDownSameColorInPlace() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(2, 0, rook);
        game.setPieceAt(5, 0, blackPawn);
        assertFalse(game.checkMove(2, 0, 5, 0, rook));
    }
    
    @Test
    public void rookMovementLeft() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 7, rook);
        assertTrue(game.checkMove(5, 7, 5, 0, rook));
    }
    
    @Test
    public void rookMovementLeftPieceBlocking() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 7, rook);
        game.setPieceAt(5, 1, whitePawn);
        assertFalse(game.checkMove(5, 7, 5, 0, rook));
    }
    
    @Test
    public void rookMovementLeftSameColorInPlace() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 7, rook);
        game.setPieceAt(5, 0, blackPawn);
        assertFalse(game.checkMove(5, 7, 5, 0, rook));
    }
    
    @Test
    public void rookInvalidMovement() {
        game = new Chess();
        Rook rook = new Rook(PColor.Black);
        game.setPieceAt(5, 7, rook);
        assertFalse(game.checkMove(5, 7, 4, 0, rook));
    }
}
