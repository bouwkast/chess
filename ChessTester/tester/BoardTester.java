/**
 * 
 */
package tester;

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
public class BoardTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void setBoard() {
        game = new Chess();
        boardTest.getCellAt(4, 4).setChessPiece(blackPawn);
        game.setBoard(boardTest);
        assertTrue(game.getPieceAt(4, 4) != null);
    }
    
    @Test
    public void resetBoard() {
        game = new Chess();
        boardTest.getCellAt(4, 4).setChessPiece(blackPawn);
        game.setBoard(boardTest);
        game.reset();
        assertTrue(game.getPieceAt(4, 4) == null);
    }
    
    @Test
    public void checkBoardGetPiece() {
        game = new Chess();
        assertTrue(game.getBoard().getPieceAt(7, 4) instanceof King);
    }
    
    @Test
    public void checkCantFindWhiteKing() {
        game = new Chess();
        game.setPieceAt(7, 4, null);
        int[] kCoords = game.getBoard().findKing(PColor.White);
        int[] coords = { -1, -1 };
        equals(coords == kCoords);
    }
}
