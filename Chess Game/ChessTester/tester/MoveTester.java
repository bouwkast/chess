/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.Movement;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Rook;

/**
 * @author andy
 *
 */
public class MoveTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void checkBasicInvalidLateralMovement() {
        game = new Chess();
        Rook toCheck = new Rook(PColor.Black);
        game.setPieceAt(4, 4, toCheck);
        Movement move = new Movement(4, 4, 5, 5, toCheck, game);
        assertFalse(move.checkLateral());
    }
    
    @Test
    public void checkBasicInvalidLateralMovement2() {
        game = new Chess();
        Rook toCheck = new Rook(PColor.Black);
        game.setPieceAt(4, 4, toCheck);
        Movement move = new Movement(4, 4, 4, 4, toCheck, game);
        assertFalse(move.checkLateral());
        
    }
    
    
    
    
}
