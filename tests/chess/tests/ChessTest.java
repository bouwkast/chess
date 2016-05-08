package chess.tests;

import chess.main.Chess;
import chess.objects.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessTest {

    Chess game;

    @Before
    public void setupGame() {
       game = new Chess();
    }
    @Test
    public void getPieceAt() throws Exception {

    }

    @Test
    public void getBoard() throws Exception {

    }

    @Test
    public void movePiece() throws Exception {

    }

    @Test
    public void checkPawnMove() throws Exception {
        blackPawnRowMovement();

        whitePawnRowMovement();

        pawnInvalidDiagonalMovement();

        pawnCaptureMovement();


        pawnInvalidCaptureMovementVertical();

        pawnInvalidCaptureMovementDiagonal();

        // en passant testing

        pawnEnPassantCapture();


    }

    private void pawnEnPassantCapture() {
        game = new Chess();
        game.getCellAt(3, 1).setPiece(new Pawn(1));
        assertFalse(game.getPieceAt(1, 0).isHasMoved());
        assertTrue(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(3, 0)));
        assertTrue(game.movePiece(game.getCellAt(3, 1), game.getCellAt(2, 0)));
    }

    private void pawnInvalidCaptureMovementDiagonal() {
        game.getCellAt(3, 2).setPiece(new Pawn(1));
        game.getCellAt(4, 2).setPiece(new Pawn(1));

        assertFalse(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(3, 2))); // piece of diff color in way
        assertFalse(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(4, 2))); // piece of diff color in way
    }

    private void pawnInvalidCaptureMovementVertical() {
        game.getCellAt(2, 0).setPiece(new Pawn(1));
        game.getCellAt(5, 0).setPiece(new Pawn(0));

        assertFalse(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(2, 0))); // piece of diff color in way
        assertFalse(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(5, 0))); // piece of diff color in way
    }

    private void pawnCaptureMovement() {
        game.getCellAt(2, 1).setPiece(new Pawn(1)); // create white pawn at previously tested cell
        game.getCellAt(5, 1).setPiece(new Pawn(0)); // create black pawn at previously tested cell

        assertTrue(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(2, 1))); // black piece valid diagonal
        assertTrue(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(5, 1))); // white piece valid diagonal

        assertTrue(game.movePiece(game.getCellAt(1, 0), game.getCellAt(2, 1))); // move black piece valid diagonal
        assertTrue(game.movePiece(game.getCellAt(6, 0), game.getCellAt(5, 1))); // move white piece valid diagonal

        assertTrue(game.getPieceAt(5, 1).isHasMoved() && game.getPieceAt(5, 1).getColor() == 1); // check white cap
        assertTrue(game.getPieceAt(6, 0) == null);

        assertTrue(game.getPieceAt(2, 1).isHasMoved() && game.getPieceAt(2, 1).getColor() == 0); // check black cap
        assertTrue(game.getPieceAt(1, 0) == null);
    }

    private void pawnInvalidDiagonalMovement() {
        assertFalse(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(2, 1))); // black piece invalid diagonal
        assertFalse(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(5, 1))); // white piece invalid diagonal
    }

    private void whitePawnRowMovement() {
        assertTrue(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(5, 0))); // white piece valid 1 row
        assertTrue(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(4, 0))); // white piece valid 2 row
        assertFalse(game.isValidMove(game.getCellAt(6, 0), game.getCellAt(3, 0))); // white piece invalid 3 rows
    }

    private void blackPawnRowMovement() {
        assertTrue(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(2, 0))); // black piece valid 1 row
        assertTrue(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(3, 0))); // black piece valid 2 row
        assertFalse(game.isValidMove(game.getCellAt(1, 0), game.getCellAt(4, 0))); // black piece invalid 3 rows
    }

    @Test
    public void checkKnightMove() throws Exception {
        //start with default board layout
        assertTrue(game.isValidMove(game.getCellAt(0, 1), game.getCellAt(2, 2)));
        game = new Chess();
        assertFalse(game.isValidMove(game.getCellAt(0, 1), game.getCellAt(2, 1)));


    }

    @Test
    public void checkBishopMove() throws Exception {

    }

    @Test
    public void checkRookMove() throws Exception {

    }

    @Test
    public void checkQueenMove() throws Exception {

    }

    @Test
    public void checkKingMove() throws Exception {

    }

    @Test
    public void isValidMove() throws Exception {

    }

}