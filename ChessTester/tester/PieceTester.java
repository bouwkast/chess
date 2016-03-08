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
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Piece;

/**
 * @author andy
 *
 */
public class PieceTester {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    
    @Test
    public void pieceCopyConstructor() {
        Piece piece = new Piece(whitePawn);
        assertTrue(piece.getColor() == PColor.White
                && piece.getName().equals("Pawn")
                && piece.getIcon().equals("\u2659")
                && !piece.hasMoved());
    }
    
    @Test
    public void pieceToString() {
        String expected = "Piece name: Pawn" + "\nPiece color: White"
                + "\nhasMoved: false" + "\nIcon: \u2659";
        assertEquals(expected, whitePawn.toString());
    }
    
    @Test
    public void movePieceThatHasMoved() {
        game = new Chess();
        whitePawn.setHasMoved(true);
        game.movePieceTo(6, 1, 5, 1, whitePawn);
        assertTrue(whitePawn.hasMoved());
    }
    
    @Test
    public void shouldNotHasMoved() {
        game = new Chess();
        game.isFutureCheck(6, 6, 4, 6, game.getPieceAt(6, 6));
        assertFalse(game.getPieceAt(6, 6).hasMoved());
    }
}
