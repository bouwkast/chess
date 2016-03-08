package tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.Board;
import chess.objects.Castling_Move;
import chess.objects.En_Passant_Move;
import chess.objects.King;
import chess.objects.Knight;
import chess.objects.Move;
import chess.objects.Movement;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Piece;
import chess.objects.Queen;
import chess.objects.Rook;

public class ChessTester {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
     
   
    
    @Test
    public void checkDefaultPieceCantMove() {
        game = new Chess();
        Piece toCheck = new Piece(PColor.Black);
        game.setPieceAt(4, 5, toCheck);
        assertFalse(game.checkMove(4, 5, 4, 3, toCheck));
    }
    
    @Test
    public void intialValidMoves() {
        game = new Chess();
        System.out.println(game.generateMoves(PColor.Black).size());
        assertTrue(game.generateMoves(PColor.Black).size() == 20);
    }
    
    @Test
    public void validMovesToMake() {
        game = new Chess();
        List<Move> test = game.generatePossibleMoves(6, 0);
        List<Move> validMoves = new ArrayList();
        
        Move pawnTemp1 = new Move(6, 0, 5, 0, game.getPieceAt(6, 0),
                game.getPieceAt(5, 0));
                
        Move pawnTemp2 = new Move(6, 0, 4, 0, game.getPieceAt(6, 0),
                game.getPieceAt(4, 0));
                
        validMoves.add(pawnTemp2);
        validMoves.add(pawnTemp1);
        
        assertEquals(validMoves.get(0).getR2(), test.get(0).getR2());
        assertEquals(validMoves.get(0).getC2(), test.get(0).getC2());
        assertEquals(validMoves.get(1).getR2(), test.get(1).getR2());
        assertEquals(validMoves.get(1).getC2(), test.get(1).getC2());
    }
    
    @Test
    public void invalidMovesToMake() {
        game = new Chess();
        List<Move> test = game.generatePossibleMoves(6, 0);
        
        List<Move> invalidMoves = new ArrayList();
        
        Move pawnTemp1 = new Move(6, 0, 3, 1, game.getPieceAt(6, 0),
                game.getPieceAt(3, 0));
                
        Move pawnTemp2 = new Move(6, 0, 2, 1, game.getPieceAt(6, 0),
                game.getPieceAt(2, 0));
                
        invalidMoves.add(pawnTemp1);
        invalidMoves.add(pawnTemp2);
        
        boolean result1 = (test.get(0).getR2() == invalidMoves.get(0)
                .getR2());
                
        boolean result2 = (test.get(0).getC2() == invalidMoves.get(0)
                .getC2());
                
        boolean result3 = (test.get(1).getR2() == invalidMoves.get(1)
                .getR2());
                
        boolean result4 = (test.get(1).getC2() == invalidMoves.get(1)
                .getC2());
                
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
        
    }
    
    @Test
    public void highlightPossibleMovesSuccess(){
        game = new Chess();
        game.setPieceAt(3, 3, whitePawn);
        whitePawn.setHasMoved(false);
        List<Move> expected = game.generatePossibleMoves(3, 3);
        
        System.out.println(expected.size());
        
        Move temp1 = expected.get(0);
        
        assertEquals(2,temp1.getR2());
        
        assertEquals(3,temp1.getC2());
        
    }
}
