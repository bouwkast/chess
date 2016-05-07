package chess.tests;

import chess.main.Chess;
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