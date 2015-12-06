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
import chess.objects.Pawn;

/**
 * @author andy
 *
 */
public class CellTester {
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    
    @Test
    public void getCellNameOfPiece() {
        game = new Chess();
        String name = game.getBoard().getCellAt(1, 1).getPieceName();
        assertEquals(name, "Pawn");
    }
    
    @Test
    public void getCellIconOfPiece() {
        game = new Chess();
        String icon = game.getBoard().getCellAt(1, 1).getPieceIcon();
        assertEquals(icon, "\u265f");
    }
    
    @Test
    public void getCellIfPassant() {
        game = new Chess();
        boolean result = game.getBoard().getCellAt(1, 1).isPassant();
        assertFalse(result);
    }
    
    @Test
    public void setCellPassantStatus() {
        game = new Chess();
        game.getBoard().getCellAt(1, 1).setPassant(true);
        boolean result = game.getBoard().getCellAt(1, 1).isPassant();
        assertTrue(result);
    }
}
