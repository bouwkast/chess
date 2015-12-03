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
    public void checkWhiteKnightsPosition() {
        // White Knights are at row 7, col 1 and 6
        game = new Chess();
        boolean isLeftKnight = game.getPieceAt(7, 1) instanceof Knight;
        boolean isRightKnight = game.getPieceAt(7, 6) instanceof Knight;
        boolean isLeftWhite = game.getPieceAt(7, 1)
                .getColor() == PColor.White;
        boolean isRightWhite = game.getPieceAt(7, 6)
                .getColor() == PColor.White;
        assertTrue(isLeftKnight && isRightKnight && isLeftWhite
                && isRightWhite);
    }
    
    @Test
    public void checkWhiteBishopsPosition() {
        // White Bishops are at row 7, col 2 and 5
        game = new Chess();
        boolean isLeftBishop = game.getPieceAt(7, 2) instanceof Bishop;
        boolean isRightBishop = game.getPieceAt(7, 5) instanceof Bishop;
        boolean isLeftWhite = game.getPieceAt(7, 2)
                .getColor() == PColor.White;
        boolean isRightWhite = game.getPieceAt(7, 5)
                .getColor() == PColor.White;
        assertTrue(isLeftBishop && isRightBishop && isLeftWhite
                && isRightWhite);
    }
    
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
    public void checkWhiteQueenPosition() {
        // White Queen is located at row 7, col 3
        game = new Chess();
        boolean isQueen = game.getPieceAt(7, 3) instanceof Queen;
        boolean isQueenWhite = game.getPieceAt(7, 3)
                .getColor() == PColor.White;
        assertTrue(isQueen && isQueenWhite);
    }
    
    @Test
    public void checkWhiteKingPosition() {
        // White King is located at row 7, col 4
        game = new Chess();
        boolean isKing = game.getPieceAt(7, 4) instanceof King;
        boolean isKingWhite = game.getPieceAt(7, 4)
                .getColor() == PColor.White;
        assertTrue(isKing && isKingWhite);
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
    public void checkBlackKnightsPositions() {
        // Black Knights are at row 0, col 1 and 6
        game = new Chess();
        boolean isLeftKnight = game.getPieceAt(0, 1) instanceof Knight;
        boolean isRightKnight = game.getPieceAt(0, 6) instanceof Knight;
        boolean isLeftBlack = game.getPieceAt(0, 1)
                .getColor() == PColor.Black;
        boolean isRightBlack = game.getPieceAt(0, 6)
                .getColor() == PColor.Black;
                
        assertTrue(isLeftKnight && isRightKnight && isLeftBlack
                && isRightBlack);
    }
    
    @Test
    public void checkBlackBishopsPositions() {
        // Black Bishops are at row 0, col 2 and 5
        game = new Chess();
        boolean isLeftBishop = game.getPieceAt(0, 2) instanceof Bishop;
        boolean isRightBishop = game.getPieceAt(0, 5) instanceof Bishop;
        boolean isLeftBlack = game.getPieceAt(0, 2)
                .getColor() == PColor.Black;
        boolean isRightBlack = game.getPieceAt(0, 5)
                .getColor() == PColor.Black;
        assertTrue(isLeftBishop && isRightBishop && isLeftBlack
                && isRightBlack);
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
    public void checkBlackQueenPosition() {
        // Black Queen is located at row 0, col 3
        game = new Chess();
        boolean isQueen = game.getPieceAt(0, 3) instanceof Queen;
        boolean isQueenBlack = game.getPieceAt(0, 3)
                .getColor() == PColor.Black;
        assertTrue(isQueen && isQueenBlack);
    }
    
    @Test
    public void checkBlackKingPosition() {
        // White King is located at row 0, col 4
        game = new Chess();
        boolean isKing = game.getPieceAt(0, 4) instanceof King;
        boolean isKingBlack = game.getPieceAt(0, 4)
                .getColor() == PColor.Black;
        assertTrue(isKing && isKingBlack);
    }
    
    @Test
    public void movePieceThatHasMoved() {
        game = new Chess();
        whitePawn.setHasMoved(true);
        game.movePieceTo(6, 1, 5, 1, whitePawn);
        assertTrue(whitePawn.hasMoved());
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
        game.movePieceTo(6, 1, 5, 1,game.getPieceAt(6, 1));
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
    
    /**
     * Still need some testing for Pawn, it is a unique move class
     */
    
    // Testing the Knight's Movement
    @Test
    public void knightMovementUpLeftTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 3, 2, knight));
    }
    
    @Test
    public void knightMovementUpLeftOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightMovementUpRightTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 3, 6, knight));
    }
    
    @Test
    public void knightMovementUpRightOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 2, 5, knight));
    }
    
    @Test
    public void knightMovementDownRightTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 5, 6, knight));
    }
    
    @Test
    public void knightMovementDownRightOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 6, 5, knight));
    }
    
    @Test
    public void knightMovementDownLeftTwoCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 5, 2, knight));
    }
    
    @Test
    public void knightMovementDownLeftOneCol() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertTrue(game.checkMove(4, 4, 6, 3, knight));
    }
    
    @Test
    public void knightCantMoveToSameColorPieceBlack() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, blackPawn);
        assertFalse(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightCantMoveToSameColorPieceWhite() {
        game = new Chess();
        Knight knight = new Knight(PColor.White);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, whitePawn);
        assertFalse(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightCanCapture() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        game.setPieceAt(2, 3, whitePawn);
        assertTrue(game.checkMove(4, 4, 2, 3, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooManyRows() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 7, 3, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooManyCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 2, 1, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewRows() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 4, 2, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewCols() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 2, 4, knight));
    }
    
    @Test
    public void knightInvalidMovePatternTooFewCols2() {
        game = new Chess();
        Knight knight = new Knight(PColor.Black);
        game.setPieceAt(4, 4, knight);
        assertFalse(game.checkMove(4, 4, 3, 4, knight));
    }
    
    /**
     * End of Knight Testing
     */
    
    // Begin Bishop Testing
    @Test
    public void bishopMovementUpRight() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        assertTrue(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementUpRightPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        game.setPieceAt(3, 4, whitePawn);
        assertFalse(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementUpRightSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 2, bishop);
        game.setPieceAt(2, 5, blackPawn);
        assertFalse(game.checkMove(5, 2, 2, 5, bishop));
    }
    
    @Test
    public void bishopMovementDownRight() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        assertTrue(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownRightPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        game.setPieceAt(4, 3, whitePawn);
        assertFalse(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownRightSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 1, bishop);
        game.setPieceAt(5, 4, blackPawn);
        assertFalse(game.checkMove(2, 1, 5, 4, bishop));
    }
    
    @Test
    public void bishopMovementDownLeft() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        assertTrue(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementDownLeftPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        game.setPieceAt(4, 3, whitePawn);
        assertFalse(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementDownLeftSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(2, 5, bishop);
        game.setPieceAt(5, 2, blackPawn);
        assertFalse(game.checkMove(2, 5, 5, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeft() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        assertTrue(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeftPieceBlocking() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        game.setPieceAt(4, 4, whitePawn);
        assertFalse(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopMovementUpLeftSameColorInPlace() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        game.setPieceAt(2, 2, blackPawn);
        assertFalse(game.checkMove(5, 5, 2, 2, bishop));
    }
    
    @Test
    public void bishopInvalidMovement() {
        game = new Chess();
        Bishop bishop = new Bishop(PColor.Black);
        game.setPieceAt(5, 5, bishop);
        assertFalse(game.checkMove(5, 5, 5, 2, bishop));
    }
    
    /**
     * End of Bishop Testing
     */
    
    // Begin Rook Testing
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
    
    /**
     * End of Testing Rook
     */
    
    // Begin Testing Queen
    
    @Test
    public void queenMovementUp() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 2, 4, queen));
    }
    
    @Test
    public void queenMovementUpRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 2, 6, queen));
    }
    
    @Test
    public void queenMovementRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(4, 4, queen);
        assertTrue(game.checkMove(4, 4, 4, 7, queen));
    }
    
    @Test
    public void queenMovementDownRight() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertTrue(game.checkMove(2, 2, 5, 5, queen));
    }
    
    @Test
    public void queenMovementDown() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertTrue(game.checkMove(2, 2, 5, 2, queen));
    }
    
    @Test
    public void queenMovementDownLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 5, queen);
        assertTrue(game.checkMove(2, 5, 5, 2, queen));
    }
    
    @Test
    public void queenMovementLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(5, 7, queen);
        assertTrue(game.checkMove(5, 7, 5, 0, queen));
    }
    
    @Test
    public void queenMovementUpLeft() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(5, 7, queen);
        assertTrue(game.checkMove(5, 7, 2, 4, queen));
    }
    
    @Test
    public void queenInvalidMovement() {
        game = new Chess();
        Queen queen = new Queen(PColor.Black);
        game.setPieceAt(2, 2, queen);
        assertFalse(game.checkMove(2, 2, 4, 3, queen));
    }
    
    /**
     * End Queen Testing
     */
    
    // Begin King Testing
    
    @Test
    public void kingMovementUp() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 3, 4, king));
    }
    
    @Test
    public void kingMovementUpRight() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 3, 5, king));
    }
    
    @Test
    public void kingMovementRight() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(4, 4, king);
        assertTrue(game.checkMove(4, 4, 4, 5, king));
    }
    
    @Test
    public void kingMovementDownRight() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 2, king);
        assertTrue(game.checkMove(2, 2, 3, 3, king));
    }
    
    @Test
    public void kingMovementDown() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 2, king);
        assertTrue(game.checkMove(2, 2, 3, 2, king));
    }
    
    @Test
    public void kingMovementDownLeft() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(2, 5, king);
        assertTrue(game.checkMove(2, 5, 3, 4, king));
    }
    
    @Test
    public void kingMovementLeft() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertTrue(game.checkMove(5, 7, 5, 6, king));
    }
    
    @Test
    public void kingMovementUpLeft() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertTrue(game.checkMove(5, 7, 4, 6, king));
    }
    
    @Test
    public void kingInvalidMovement() {
        game = new Chess();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 7, king);
        assertFalse(game.checkMove(5, 7, 5, 0, king));
    }
    
    @Test
    public void kingInvalidMovementDiagCols() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 5, king);
        assertFalse(king.checkMovement(5, 5, 4, 7, game));
    }
    
    @Test
    public void kingInvalidMovementDiagCols2() {
        game = new Chess();
        game.getBoard().reset();
        King king = new King(PColor.Black);
        game.setPieceAt(5, 5, king);
        assertFalse(king.checkMovement(5, 5, 4, 7, game));
    }
    /**
     * End of King testing
     */
    
    /**
     * Beginning of Advanced Movement Checking
     */
    
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
    
    /**
     * Beginning of Check & Checkmate Tests
     */
    
    @Test
    public void kingInCheckTrue() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Pawn blackPawn = new Pawn(PColor.Black);
        
        game.setPieceAt(6, 3, whiteKing);
        game.setPieceAt(4, 4, blackPawn);
        
        assertTrue(game.isFutureCheck(6, 3, 5, 3, whiteKing));
    }
    
    @Test
    public void kingInCheckFalse() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Pawn blackPawn = new Pawn(PColor.Black);
        
        game.setPieceAt(6, 3, whiteKing);
        game.setPieceAt(4, 4, blackPawn);
        
        assertFalse(game.isFutureCheck(6, 3, 6, 2, whiteKing));
    }
    
    @Test
    public void whiteKingInCheckmateTrue() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Queen blackQueen1 = new Queen(PColor.Black);
        Queen blackQueen2 = new Queen(PColor.Black);
        Queen blackQueen3 = new Queen(PColor.Black);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 3, blackQueen1);
        game.setPieceAt(7, 5, blackQueen2);
        game.setPieceAt(6, 3, blackQueen3);
        
        assertEquals(game.isGameOver(), 0);
    }
    
    @Test
    public void whiteKingInCheckmateTrue2() {
        game = new Chess();
        game.movePieceTo(6, 6, 4, 6, game.getPieceAt(6, 6));
        game.movePieceTo(1, 4, 3, 4, game.getPieceAt(1, 4));
        game.movePieceTo(6, 5, 5, 5, game.getPieceAt(6, 5));
        game.movePieceTo(0, 3, 4, 7, game.getPieceAt(0, 3));
        assertTrue(game.isGameOver() == 0);
    }
    
    @Test
    public void whiteKingInCheckmateFalse() {
        game = new Chess();
        King whiteKing = new King(PColor.White);
        Queen blackQueen1 = new Queen(PColor.Black);
        
        game.setPieceAt(7, 4, whiteKing);
        game.setPieceAt(7, 3, blackQueen1);
        
        assertEquals(game.isGameOver(), -1);
    }
    
    @Test
    public void blackKingInCheckmateTrue() {
        game = new Chess();
        King blackKing = new King(PColor.Black);
        Queen whiteQueen1 = new Queen(PColor.White);
        
        game.setPieceAt(0, 4, blackKing);
        game.setPieceAt(0, 3, whiteQueen1);
        
        assertEquals(game.isGameOver(), -1);
    }
    
    @Test
    public void blackKingInCheckmateFalse() {
        game = new Chess();
        King blackKing = new King(PColor.Black);
        Queen whiteQueen1 = new Queen(PColor.White);
        Queen whiteQueen2 = new Queen(PColor.White);
        Queen whiteQueen3 = new Queen(PColor.White);
        
        game.setPieceAt(0, 4, blackKing);
        game.setPieceAt(0, 3, whiteQueen1);
        game.setPieceAt(0, 5, whiteQueen2);
        game.setPieceAt(1, 4, whiteQueen3);
        
        assertEquals(game.isGameOver(), 1);
    }
    
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
    
    @Test
    public void gameOverByStalemateBlackNoMoves() {
        game = new Chess();
        game.getBoard().reset();
        game.setPieceAt(0, 7, new King(PColor.Black));
        game.setPieceAt(2, 6, new Queen(PColor.White));
        game.setPieceAt(1, 5, new King(PColor.White));
        equals(game.isGameOver() == 2);
    }
    
    @Test
    public void gameOverByStalemateWhiteNoMoves() {
        game = new Chess();
        game.getBoard().reset();
        game.setPieceAt(0, 7, new King(PColor.White));
        game.setPieceAt(2, 6, new Queen(PColor.Black));
        game.setPieceAt(1, 5, new King(PColor.Black));
        equals(game.isGameOver() == 2);
    }
    
    @Test
    public void checkDefaultPieceCantMove() {
        game = new Chess();
        Piece toCheck = new Piece(PColor.Black);
        game.setPieceAt(4, 5, toCheck);
        assertFalse(game.checkMove(4, 5, 4, 3, toCheck));
    }
    
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
        ;
    }
    
    @Test
    public void checkCantFindWhiteKing() {
        game = new Chess();
        game.setPieceAt(7, 4, null);
        int[] kCoords = game.getBoard().findKing(PColor.White);
        int[] coords = { -1, -1 };
        equals(coords == kCoords);
    }
    
    @Test
    public void checkBoardGetPiece() {
        game = new Chess();
        assertTrue(game.getBoard().getPieceAt(7, 4) instanceof King);
    }
    
    @Test
    public void shouldNotHasMoved() {
        game = new Chess();
        game.isFutureCheck(6, 6, 4, 6, game.getPieceAt(6, 6));
        assertFalse(game.getPieceAt(6, 6).hasMoved());
    }
    
    @Test
    public void intialValidMoves() {
        game = new Chess();
        System.out.println(game.generateMoves(PColor.Black).size());
        assertTrue(game.generateMoves(PColor.Black).size() == 20);
    }
    
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
    
    // @Test
    // public void checkChessGame() {
    // ChessGame game = new ChessGame();
    // game.main(null);
    // }
    
    @Test
    public void validMovesToMake() {
        game = new Chess();
        List<Move> test = new ArrayList();
        test = game.generatePossibleMoves(6, 0);
        List<Move> validMoves = new ArrayList();
        
        Move pawnTemp1 = new Move(6, 0, 5, 0, game.getPieceAt(6, 0),
                game.getPieceAt(5, 0));
                
        Move pawnTemp2 = new Move(6, 0, 4, 0, game.getPieceAt(6, 0),
                game.getPieceAt(4, 0));
                
        validMoves.add(pawnTemp2);
        validMoves.add(pawnTemp1);
        
        assertEquals(validMoves.get(0).getR2(),test.get(0).getR2());
        assertEquals(validMoves.get(0).getC2(),test.get(0).getC2());
        assertEquals(validMoves.get(1).getR2(),test.get(1).getR2());
        assertEquals(validMoves.get(1).getC2(),test.get(1).getC2());

        
    }
    
}
