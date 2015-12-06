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
public class PawnTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkPawnPColorWhite() {
        Pawn pW = new Pawn(PColor.White);
        assertTrue(pW.getColor() == PColor.White);
    }
    
    @Test
    public void checkPawnPColorBlack() {
        Pawn pB = new Pawn(PColor.Black);
        assertTrue(pB.getColor() == PColor.Black);
    }
    
    @Test
    public void checkPawnName() {
        Pawn p = new Pawn(PColor.Black);
        assertTrue(p.getName().equals("Pawn"));
    }
    
    @Test
    public void checkPawnIconWhite() {
        Pawn pW = new Pawn(PColor.White);
        assertTrue(pW.getIcon().equals("\u2659"));
    }
    
    @Test
    public void checkPawnIconBlack() {
        Pawn pB = new Pawn(PColor.Black);
        assertTrue(pB.getIcon().equals("\u265f"));
    }
    
    @Test
    public void checkPawnHasMovedInitialStatus() {
        Pawn p = new Pawn(PColor.Black);
        assertFalse(p.hasMoved());
    }
    
    @Test
    public void checkWhitePawnsPosition() {
        // White Pawns are in row 6, col 0-7
        game = new Chess();
        boolean isCorrect = false;
        for (int i = 0; i < 8; i++) {
            boolean isPawn = game.getPieceAt(6, i) instanceof Pawn;
            boolean isWhite = game.getPieceAt(6, i)
                    .getColor() == PColor.White;
            isCorrect = (isPawn && isWhite);
            if (!isCorrect)
                return;
        }
        
        assertTrue(isCorrect);
    }
    
    @Test
    public void checkBlackPawnsPositions() {
        // Black Pawns are in row 0, col 0-7
        game = new Chess();
        boolean isCorrect = false;
        for (int i = 0; i < 8; i++) {
            boolean isPawn = game.getPieceAt(1, i) instanceof Pawn;
            boolean isWhite = game.getPieceAt(1, i)
                    .getColor() == PColor.Black;
            isCorrect = (isPawn && isWhite);
            if (!isCorrect)
                return;
        }
        assertTrue(isCorrect);
    }
    
    @Test
    public void whitePawnDoesntMoveIfRowGreaterThanTwo() {
        game = new Chess();
        assertFalse(game.checkMove(6, 1, 3, 2, (whitePawn)));
    }
    
    @Test
    public void blackPawnDoesntMoveIfRowGreaterThanTwo() {
        game = new Chess();
        assertFalse(game.checkMove(6, 1, 3, 2, (blackPawn)));
    }
    
    @Test
    public void whitePawnShouldMoveOneRow() {
        game = new Chess();
        assertTrue(game.checkMove(6, 1, 5, 1, (whitePawn)));
    }
    
    @Test
    public void blackPawnShouldMoveOneRow() {
        game = new Chess();
        assertTrue(game.checkMove(1, 1, 2, 1, (blackPawn)));
    }
    
    @Test
    public void whitePawnHasMovedShouldBeTrue() {
        game = new Chess();
        game.checkMove(6, 1, 5, 1, (whitePawn));
        assertTrue(whitePawn.hasMoved());
    }
    
    @Test
    public void blackPawnHasMovedShouldBeTrue() {
        game = new Chess();
        game.checkMove(1, 1, 2, 1, (blackPawn));
        game.movePieceTo(1, 1, 2, 1, (blackPawn));
        
        assertTrue(blackPawn.hasMoved());
    }
    
    @Test
    public void whitePawnHasNotMoved() {
        game = new Chess();
        assertFalse(whitePawn.hasMoved());
    }
    
    @Test
    public void blackPawnHasNotMoved() {
        game = new Chess();
        assertFalse(blackPawn.hasMoved());
    }
    
    @Test
    public void whitePawnHasNotMoved2() {
        game = new Chess();
        game.checkMove(6, 1, 1, 1, whitePawn);
        assertFalse(whitePawn.hasMoved());
    }
    
    @Test
    public void blackPawnHasNotMoved2() {
        game = new Chess();
        game.checkMove(1, 1, 6, 1, blackPawn);
        assertFalse(blackPawn.hasMoved());
    }
    
    @Test
    public void whitePawnMoveTwoRows() {
        game = new Chess();
        assertTrue(game.checkMove(6, 1, 4, 1, whitePawn)
                && whitePawn.hasMoved());
    }
    
    @Test
    public void blackPawnMoveTwoRows() {
        game = new Chess();
        assertTrue(game.checkMove(1, 1, 3, 1, blackPawn)
                && blackPawn.hasMoved());
    }
    
    @Test
    public void whitePawnCantMoveOneThenTwo() {
        game = new Chess();
        game.checkMove(6, 1, 5, 1, whitePawn);
        game.movePieceTo(6, 1, 5, 1, game.getPieceAt(6, 1));
        assertFalse(game.checkMove(5, 1, 3, 1, whitePawn));
    }
    
    @Test
    public void blackPawnCantMoveOneThenTwo() {
        game = new Chess();
        game.checkMove(1, 1, 2, 1, whitePawn);
        assertFalse(game.checkMove(2, 1, 4, 1, whitePawn));
    }
    
    @Test
    public void whitePawnCaptureUpLeft() {
        game = new Chess();
        game.getBoard().getCellAt(5, 0).setChessPiece(blackPawn);
        boolean capture = game.checkMove(6, 1, 5, 0, whitePawn);
        boolean isCellEmpty = false;
        if (game.getPieceAt(6, 1) == null)
            isCellEmpty = true;
        assertTrue(capture && !isCellEmpty);
    }
    
    @Test
    public void whitePawnCantCaptureUpLeftTwoCells() {
        game = new Chess();
        game.getBoard().getCellAt(4, 0).setChessPiece(blackPawn);
        boolean capture = game.checkMove(6, 2, 4, 0, whitePawn);
        boolean isCellEmpty = true;
        if (game.getPieceAt(6, 1) != null)
            isCellEmpty = false;
        assertFalse(capture && isCellEmpty);
    }
    
    @Test
    public void whitePawnCaptureUpLeftSameColor() {
        game = new Chess();
        game.getBoard().getCellAt(5, 0)
                .setChessPiece(new Pawn(PColor.White));
        boolean capture = game.checkMove(6, 1, 5, 0, whitePawn);
        boolean isCellEmpty = true;
        if (game.getPieceAt(6, 1) != null)
            isCellEmpty = false;
        assertFalse(capture && isCellEmpty);
    }
    
    @Test
    public void whitePawnCaptureUpRight() {
        game = new Chess();
        game.getBoard().getCellAt(5, 2)
                .setChessPiece(new Pawn(PColor.Black));
        assertTrue(game.checkMove(6, 1, 5, 2, whitePawn));
    }
    
    @Test
    public void whitePawnCantCaptureUpRightTwoCells() {
        game = new Chess();
        game.getBoard().getCellAt(4, 3)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(6, 1, 4, 3, whitePawn));
    }
    
    @Test
    public void whitePawnCaptureUpRightSameColor() {
        game = new Chess();
        game.getBoard().getCellAt(5, 2)
                .setChessPiece(new Pawn(PColor.White));
        assertFalse(game.checkMove(6, 1, 5, 2, whitePawn));
    }
    
    @Test
    public void whitePawnCantCaptureUp() {
        game = new Chess();
        game.getBoard().getCellAt(5, 1)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(6, 1, 5, 1, whitePawn));
    }
    
    @Test
    public void whitePawnCantMoveThroughPiece() {
        game = new Chess();
        game.getBoard().getCellAt(5, 1)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(6, 1, 4, 1, whitePawn));
    }
    
    @Test
    public void blackPawnCaptureDownLeft() {
        game = new Chess();
        game.getBoard().getCellAt(2, 0)
                .setChessPiece(new Pawn(PColor.White));
        assertTrue(game.checkMove(1, 1, 2, 0, blackPawn));
    }
    
    @Test
    public void blackPawnCantCaptureDownLeftTwoCells() {
        game = new Chess();
        game.getBoard().getCellAt(3, 0)
                .setChessPiece(new Pawn(PColor.White));
        assertFalse(game.checkMove(1, 2, 3, 0, blackPawn));
    }
    
    @Test
    public void blackPawnCaptureDownLeftSameColor() {
        game = new Chess();
        game.getBoard().getCellAt(2, 0)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(1, 1, 2, 0, blackPawn));
    }
    
    @Test
    public void blackPawnCaptureDownRight() {
        game = new Chess();
        game.getBoard().getCellAt(2, 2)
                .setChessPiece(new Pawn(PColor.White));
        assertTrue(game.checkMove(1, 1, 2, 2, blackPawn));
    }
    
    @Test
    public void blackPawnCantCaptureDownRightTwoCells() {
        game = new Chess();
        game.getBoard().getCellAt(3, 3)
                .setChessPiece(new Pawn(PColor.White));
        assertFalse(game.checkMove(1, 1, 3, 3, blackPawn));
    }
    
    @Test
    public void blackPawnCaptureDownRightSameColor() {
        game = new Chess();
        game.getBoard().getCellAt(2, 2)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(1, 1, 2, 2, blackPawn));
    }
    
    @Test
    public void blackPawnCantCaptureDown() {
        game = new Chess();
        game.getBoard().getCellAt(2, 1)
                .setChessPiece(new Pawn(PColor.White));
        assertFalse(game.checkMove(1, 1, 2, 1, blackPawn));
    }
    
    @Test
    public void blackPawnCantMoveToOccupied() {
        game = new Chess();
        game.getBoard().getCellAt(2, 1)
                .setChessPiece(new Pawn(PColor.Black));
        assertFalse(game.checkMove(1, 1, 2, 1, blackPawn));
    }
    
    @Test
    public void blackPawnCantMoveThroughPiece() {
        game = new Chess();
        game.getBoard().getCellAt(2, 1)
                .setChessPiece(new Pawn(PColor.White));
        assertFalse(game.checkMove(1, 1, 3, 1, blackPawn));
    }
    
    
    /**Start of Pawn Promotion Testing */
    
    @Test
    public void checkForPawnPromotionWhite() {
        game = new Chess();
        game.setPieceAt(0, 4, whitePawn);
        
        assertTrue(game.checkPawnPromotion(0, 4, whitePawn));
    }
    
    @Test
    public void checkForPawnPromotionBlack() {
        game = new Chess();
        game.setPieceAt(7, 4, blackPawn);
        
        assertTrue(game.checkPawnPromotion(7, 4, blackPawn));
    }
    
    @Test
    public void ensurePawnPromotionIvalid() {
        game = new Chess();
        game.setPieceAt(1, 4, whitePawn);
        
        assertFalse(game.checkPawnPromotion(1, 4, whitePawn));
    }
    
    @Test
    public void cantPromoteNonPawn() {
        game = new Chess();
        Knight toTest = new Knight(PColor.Black);
        game.setPieceAt(0, 4, toTest);
        
        assertFalse(game.checkPawnPromotion(0, 4, toTest));
    }
    
    /**Start of En Passant Checking*/
    
    @Test
    public void enPassantMiddle() {
        game = new Chess();
        game.getBoard().reset();
        game.setPieceAt(4, 4, whitePawn);
        game.setPieceAt(4, 3, blackPawn);
        game.getBoard().getCellAt(3, 3).setPassant(true);
        assertTrue(game.checkMove(4, 4, 3, 3, whitePawn));
    }
    
    @Test
    public void enPassantMiddleFull() {
        game = new Chess();
        Pawn pawn1 = new Pawn(PColor.Black);
        Pawn pawn2 = new Pawn(PColor.Black);
        game.setPieceAt(4, 4, pawn1);
        game.setPieceAt(4, 6, pawn2);
        game.setPieceAt(6, 5, whitePawn);
        assertTrue(game.checkMove(6, 5, 4, 5, whitePawn));
        game.movePieceTo(6, 5, 4, 5, whitePawn);
        assertTrue(game.getBoard().getCellAt(5, 5).isPassant());
    }
}
