/**
 * 
 */
package tester;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Board;
import chess.objects.En_Passant_Move;
import chess.objects.Pawn;

/**
 * @author andy
 *
 */
public class EnPassantMoveTester {

    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void getCapturedPieceSuccess() {
        game = new Chess();
        game.setPieceAt(1, 3, blackPawn);
        
        game.setPieceAt(1, 2, whitePawn);
        
        En_Passant_Move test = new En_Passant_Move(1, 2, 0, 3,
                game.getPieceAt(1, 2), game.getPieceAt(0, 3),
                game.getPieceAt(1, 3));
                
        Pawn piece = (Pawn) game.getPieceAt(1, 3);
        
        assertEquals(piece, test.getCapturedPiece());
        
    }
    
    @Test
    public void setCapturedPieceSuccess() {
        game = new Chess();
        game.setPieceAt(1, 3, blackPawn);
        
        game.setPieceAt(1, 2, whitePawn);
        
        En_Passant_Move test = new En_Passant_Move(1, 2, 0, 3,
                game.getPieceAt(1, 2), game.getPieceAt(0, 3),
                game.getPieceAt(1, 3));
                
        Pawn piece = (Pawn) game.getPieceAt(1, 4);
        
        test.setCapturedPiece(piece);
        
        assertEquals(piece, test.getCapturedPiece());
        
    }
    
    @Test
    public void cloneEnPassantMoveSuccess() {
        game = new Chess();
        game.setPieceAt(1, 3, blackPawn);
        
        game.setPieceAt(1, 2, whitePawn);
        
        En_Passant_Move test = new En_Passant_Move(1, 2, 0, 3,
                game.getPieceAt(1, 2), game.getPieceAt(0, 3),
                game.getPieceAt(1, 3));
                
        En_Passant_Move clonedMove = test.cloneMove();
        
        assertEquals(test.getCapturedPiece().getName(),
                clonedMove.getCapturedPiece().getName());
    }
}
