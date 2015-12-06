/**
 * 
 */
package tester;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.Board;
import chess.objects.King;
import chess.objects.PColor;
import chess.objects.Pawn;
import chess.objects.Rook;

/**
 * @author andy
 *
 */
public class CastlingTester {
    
    Chess game = new Chess();
    Board boardTest = new Board();
    
    Pawn whitePawn = (Pawn) game.getPieceAt(6, 1);
    Pawn blackPawn = (Pawn) game.getPieceAt(1, 1);
    
    @Test
    public void castlingQueensideValidMovement() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.getBoard().getCellAt(7, 3).setChessPiece(null);
        game.getBoard().getCellAt(7, 2).setChessPiece(null);
        game.getBoard().getCellAt(7, 1).setChessPiece(null);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        
        assertTrue(
                whiteKing.checkCastling(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void castlingKingValidMovement() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.getBoard().getCellAt(7, 5).setChessPiece(null);
        game.getBoard().getCellAt(7, 6).setChessPiece(null);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        
        assertTrue(
                whiteKing.checkCastling(7, 4, 7, 6, whiteKing, game));
    }
    
    @Test
    public void castlingQueenInvalidMovement() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        
        assertFalse(
                whiteKing.checkCastling(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void castlingKingInvalidMovement() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        
        assertFalse(
                whiteKing.checkCastling(7, 4, 7, 6, whiteKing, game));
    }
    
    @Test
    public void checkExecuteCastleRight() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.getBoard().getCellAt(7, 5).setChessPiece(null);
        game.getBoard().getCellAt(7, 6).setChessPiece(null);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        game.executeCastle(7, 4, 7, 6, whiteKing);
        assertTrue(game.getPieceAt(7, 6) instanceof King
                && game.getPieceAt(7, 5) instanceof Rook);
    }
    
    @Test
    public void checkExecuteCastleLeft() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        
        game.getBoard().getCellAt(7, 3).setChessPiece(null);
        game.getBoard().getCellAt(7, 2).setChessPiece(null);
        game.getBoard().getCellAt(7, 1).setChessPiece(null);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        game.executeCastle(7, 4, 7, 2, whiteKing);
        assertTrue(game.getPieceAt(7, 2) instanceof King
                && game.getPieceAt(7, 3) instanceof Rook);
    }
    
    @Test
    public void invalidCastleKingHasMoved() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        whiteKing.setHasMoved(true);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        assertFalse(
                whiteKing.checkCastling(7, 4, 7, 6, whiteKing, game));
    }
    
    @Test
    public void invalidRightCastle() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 7, whiteKing,
                game));
                
    }
    
    @Test
    public void invalidRightCastleNoRook() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Bishop whiteBishop = new Bishop(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteBishop);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 6, whiteKing,
                game));
    }
    
    @Test
    public void invalidRightCastleRookHasMoved() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        whiteRook.setHasMoved(true);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 6, whiteKing,
                game));
    }
    
    @Test
    public void invalidRightCastleEndCellOccupied() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        game.setPieceAt(7, 6, whiteRook);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 6, whiteKing,
                game));
    }
    
    @Test
    public void invalidRightCastleFirstCellOcc() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        game.setPieceAt(7, 5, whiteRook);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 6, whiteKing,
                game));
    }
    
    @Test
    public void invalidRightCastleFirstCellInCheck() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        Rook blackRook = new Rook(PColor.Black);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 7, whiteRook);
        game.setPieceAt(6, 5, blackRook);
        assertFalse(whiteKing.castleCheckRight(7, 4, 7, 6, whiteKing,
                game));
    }
    
    @Test
    public void invalidLeftCastle() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 1, whiteKing, game));
    }
    
    @Test
    public void invalidLeftCastleNoRook() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Bishop whiteBishop = new Bishop(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteBishop);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void invalidLeftCastleRookHasMoved() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        whiteRook.setHasMoved(true);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void invalidLeftCastleEndCellOccupied() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        game.setPieceAt(7, 2, whiteRook);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void invalidLeftCastleFirstCellOcc() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        game.setPieceAt(7, 3, whiteRook);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 2, whiteKing, game));
    }
    
    @Test
    public void invalidLeftCastleFirstCellInCheck() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Rook whiteRook = new Rook(PColor.White);
        Rook blackRook = new Rook(PColor.Black);
        game.getBoard().reset();
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 0, whiteRook);
        game.setPieceAt(6, 3, blackRook);
        assertFalse(
                whiteKing.castleCheckLeft(7, 4, 7, 2, whiteKing, game));
    }
}
