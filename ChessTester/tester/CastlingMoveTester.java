/**
 * 
 */
package tester;

import static org.junit.Assert.assertEquals;

import chess.objects.*;
import org.junit.Test;

import chess.main.Chess;
import chess.objects.CastlingMove;

/**
 * @author andy
 *
 */
public class CastlingMoveTester {

    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void setRookRow1Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        test.setR1(0);
        
        assertEquals(0, test.getR1());
    }
    
    @Test
    public void setRookCol1Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        test.setC1(5);
        
        assertEquals(5, test.getC1());
    }
    
    @Test
    public void setRookRow2Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        test.setR2(0);
        
        assertEquals(0, test.getR2());
    }
    
    @Test
    public void setRookCol2Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        test.setR1(6);
        
        assertEquals(6, test.getR1());
    }
    
    @Test
    public void setRookPieceSuccess() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        Rook piece = (Rook) game.getPieceAt(7, 7);
        
        test.setRookPiece(piece);
        
        assertEquals(piece, test.getRookPiece());
    }
    
    @Test
    public void setTargetLocationSuccess() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        Piece piece = game.getPieceAt(7, 2);
        
        test.setTargetPiece(piece);
        
        assertEquals(piece, test.getTargetPiece());
    }
    
    @Test
    public void getRookRow1Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        assertEquals(7, test.getRowOfRook1());
    }
    
    @Test
    public void getRookCol1Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        assertEquals(0, test.getColOfRook1());
    }
    
    @Test
    public void getRookRow2Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        assertEquals(7, test.getRowOfRook2());
    }
    
    @Test
    public void getRookCol2Success() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        assertEquals(3, test.getColOfRook2());
    }
    
    @Test
    public void getRookPieceSuccess() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        Piece piece = game.getPieceAt(7, 0);
        
        assertEquals(piece, test.getRookPiece());
    }
    
    @Test
    public void getTargetLocationSuccess() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        Piece piece = game.getPieceAt(7, 3);
        
        assertEquals(piece, test.getTargetPiece());
    }
    
    @Test
    public void cloneCastlingMoveSuccess() {
        game = new Chess();
        game.setPieceAt(7, 1, null);
        game.setPieceAt(7, 2, null);
        game.setPieceAt(7, 3, null);
        CastlingMove test = new CastlingMove(7, 4, 7, 2,
                game.getPieceAt(7, 4), game.getPieceAt(7, 2), 7, 0, 7,
                3, game.getPieceAt(7, 0), game.getPieceAt(7, 3));
                
        CastlingMove cloned = test.cloneCastling();
        
        assertEquals(test.getRowOfRook1(), cloned.getRowOfRook1());
        assertEquals(test.getColOfRook1(), cloned.getColOfRook1());
        assertEquals(test.getRowOfRook2(), cloned.getRowOfRook2());
        assertEquals(test.getColOfRook2(), cloned.getColOfRook2());
        
        assertEquals(test.getRookPiece().getName(),
                cloned.getRookPiece().getName());
                
        assertEquals(test.getTargetPiece(), cloned.getTargetPiece());
    }
    
}
