package chess.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import chess.objects.*;
import chess.objects.CastlingMove;

public class Chess implements java.io.Serializable {

    /**
     * This is the board that will be used within the chess game
     */
    private Board board;
    /**
     * Stack of Moves to undo previous moves
     */
    private Stack<Move> moves;
    /**
     * Boolean to tell if a move is en passant
     */
    private boolean enPassCap;
    private boolean isWhiteTurn;
    /**
     * ArrayList of Moves to keep track of movements made in the game
     */
    private List<Move> historyOfMoves;

    /*******************************************************************
     * Default constructor - in the future we may add some parameters
     * like opening from a new board, number of players, level of AI,
     * ect.
     ******************************************************************/
    public Chess() {
        board = new Board();
        moves = new Stack<Move>();
        enPassCap = false;
        historyOfMoves = new ArrayList();
        isWhiteTurn = true;
    }

    // TODO delete, this serves no purpose Chess() is the same
    public void reset() {
        board = new Board();
    }

    /*******************************************************************
     * Returns the board object
     *
     * @return the board that is within the game
     ******************************************************************/
    public Board getBoard() {
        return board;
    }

    /*******************************************************************
     * Sets the board to the specified parameter
     *
     * @param board is the board to set
     ******************************************************************/
    public void setBoard(Board board) {
        this.board = board;
    }

    /*******************************************************************
     * Method that will set the specified Cell to the specified Piece
     *
     * @param row   is the row of the Cell
     * @param col   is the col of the Cell
     * @param piece is the Piece to set
     ******************************************************************/
    public void setPieceAt(int row, int col, Piece piece) {
        getBoard().getCellAt(row, col).setChessPiece(piece);
    }

    /*******************************************************************
     * Makes it easier to get the piece
     *
     * @param row the row of the piece
     * @param col the col of the piece
     * @return the piece at the specified cell
     ******************************************************************/
    public Piece getPieceAt(int row, int col) {
        return this.getBoard().getCellAt(row, col).getChessPiece();
    }

    /*******************************************************************
     * Simply moves one piece from one cell to the one specified. No
     * checking should be done in this function, it is a basic helper
     * method, so all checking if the piece can go in that cell should
     * be done outside.
     * <p>
     * This will remove any piece in the second cell and replace it with
     * the piece that was in the first cell.
     *
     * @param piece is the piece to move
     ******************************************************************/
    public void movePieceTo(Cell current, Cell targeted,
                            Piece piece) {
        makeMove(current, targeted);
        // Set the second cell to the pawn
        setPieceAt(targeted.getRow(), targeted.getCol(), piece);
        // Set the previous cell to null
        setPieceAt(current.getRow(), current.getCol(), null);
    }

    /*******************************************************************
     * Performs the En Passant Move with the given rows and columns.
     * Also creates the appropriate EnPassantMove object to be stored
     * in the stack
     *
     * @param pawn which contains the Pawn piece
     *******************************************************************/
    public void moveEnPassant(Cell current, Cell targeted, Pawn pawn) {

        Piece selection = current.getChessPiece();
//        Piece selection = getPieceAt(r1, c1);
//        Piece target = getPieceAt(r2, c2);
        Piece target = targeted.getChessPiece();

        Piece piece = getPieceAt(current.getRow(), targeted.getCol());

        EnPassantMove passantMove = new EnPassantMove(current, targeted, selection, target, piece);

        moves.add(passantMove.cloneMove());
        historyOfMoves.add(passantMove.cloneMove());

        // Set the second cell to the pawn
        setPieceAt(targeted.getRow(), targeted.getCol(), pawn);
        // Set the previous cell to null
        setPieceAt(current.getRow(), current.getCol(), null);
    }

    //TODO terrible....

    /******************************************************************
     * Performs the Castling Move with the given rows and columns. Also
     * creates the appropriate CastlingMove object to be stored in the
     * stack
     *
     * @param kingRow1  contains the row of the selected King
     * @param kingCol1  contains the column of the selected King
     * @param kingRow2  contains the row where the King will go
     * @param kingCol2  contains the column where the King will go
     * @param kingPiece contains the King piece
     * @param rookRow1  contains the row of the selected Rook
     * @param rookCol1  contains the column of the selected Rook
     * @param rookRow2  contains the row where the Rook will go
     * @param rookCol2  contains the column where the Rook will go
     * @param rookPiece contains the Rook piece
     *                  ****************************************************************
     */
    public void moveCastlingPieces(int kingRow1, int kingCol1,
                                   int kingRow2, int kingCol2, Piece kingPiece, int rookRow1,
                                   int rookCol1, int rookRow2, int rookCol2, Piece rookPiece) {

        Piece kingTarget = getPieceAt(kingRow2, kingCol2);
        Piece rookTarget = getPieceAt(rookRow2, rookCol2);

//        CastlingMove castlingMove = new CastlingMove(kingRow1,
//                kingCol1, kingRow2, kingCol2, kingPiece, kingTarget,
//                rookRow1, rookCol1, rookRow2, rookCol2, rookPiece,
//                rookTarget);
        CastlingMove castlingMove = new CastlingMove(board.getCellAt(kingRow1, kingCol1), board.getCellAt(kingRow2, kingCol2), board.getPieceAt(kingRow1, kingCol1), null);

        moves.add(castlingMove.cloneCastling());
        historyOfMoves.add(castlingMove.cloneCastling());

        // Set the second cell to the King
        setPieceAt(kingRow2, kingCol2, kingPiece);
        // Set the previous cell to null
        setPieceAt(kingRow1, kingCol1, null);

        // Set the second cell to the King
        setPieceAt(rookRow2, rookCol2, rookPiece);
        // Set the previous cell to null
        setPieceAt(rookRow1, rookCol1, null);
    }

    /*******************************************************************
     * Makes a copy of a move that is about to be made
     *
     * @param current  is the currently selected piece to be move
     * @param targeted is the targeted Cell to move the current piece to
     ******************************************************************/
    public void makeMove(Cell current, Cell targeted) {
//        Piece selPiece = getPieceAt(r1, c1); // shouldn't be null
        Piece selPiece = current.getChessPiece();
//        Piece tarPiece = getPieceAt(r2, c2); // can be null
        Piece tarPiece = targeted.getChessPiece();
        Move toMake = new Move(current, targeted, selPiece, tarPiece);
        moves.add(toMake.clone());
        historyOfMoves.add(toMake.clone());
    }

    //TODO could be a lot better

    /*******************************************************************
     * Undo the previously made move
     ******************************************************************/
    public void unMakeMove() {
        Move toUnMake = moves.pop();
        historyOfMoves.remove(historyOfMoves.size() - 1);

        if (toUnMake instanceof CastlingMove) {
            CastlingMove temp = (CastlingMove) toUnMake;

            int kingRow1 = temp.getR1();
            int kingCol1 = temp.getC1();
            int kingRow2 = temp.getR2();
            int kingCol2 = temp.getC2();

            int rookRow1 = temp.getRowOfRook1();
            int rookCol1 = temp.getColOfRook1();
            int rookRow2 = temp.getRowOfRook2();
            int rookCol2 = temp.getColOfRook2();

            setPieceAt(kingRow1, kingCol1,
                    createCopy(temp.getSelPiece()));

            setPieceAt(kingRow2, kingCol2,
                    createCopy(temp.getTarPiece()));

            setPieceAt(rookRow1, rookCol1,
                    createCopy(temp.getRookPiece()));

            setPieceAt(rookRow2, rookCol2,
                    createCopy(temp.getTargetPiece()));

        } else if (toUnMake instanceof EnPassantMove) {
            EnPassantMove passant = (EnPassantMove) toUnMake;

            int passantR1 = passant.getR1();
            int passantC1 = passant.getC1();
            int passantR2 = passant.getR2();
            int passantC2 = passant.getC2();

            setPieceAt(passantR1, passantC1,
                    createCopy(passant.getSelPiece()));

            setPieceAt(passantR2, passantC2,
                    createCopy(passant.getTarPiece()));

            setPieceAt(passantR1, passantC2,
                    createCopy(passant.getCapturedPiece()));

            getBoard().getCellAt(passantR2, passantC2).setPassant(true);

        } else {
            int r1 = toUnMake.getR1();
            int c1 = toUnMake.getC1();
            int r2 = toUnMake.getR2();
            int c2 = toUnMake.getC2();
            setPieceAt(r1, c1, createCopy(toUnMake.getSelPiece()));
            setPieceAt(r2, c2, createCopy(toUnMake.getTarPiece()));
        }
    }

    /*******************************************************************
     * Allows for an easy way to copy the appropriate Piece
     *
     * @param piece is the Piece to create a copy of
     * @return a copy of the Piece
     ******************************************************************/
    private Piece createCopy(Piece piece) {
        if (piece == null)
            return null;
        switch (piece.getName()) {
            case "Pawn":
                return new Pawn((Pawn) piece);
            case "Rook":
                return new Rook((Rook) piece);
            case "Bishop":
                return new Bishop((Bishop) piece);
            case "Knight":
                return new Knight((Knight) piece);
            case "Queen":
                return new Queen((Queen) piece);
            default:
                return new King((King) piece);
        }
    }

    /*******************************************************************
     * Gets the stack of Moves that have been currently made
     *
     * @return a Stack of Move
     ******************************************************************/
    public Stack<Move> getMoves() {
        return moves;
    }

    public Move getPreviousMove() {
        Move temp = moves.lastElement();
        return temp;

    }

    // TODO move to the pawn class

    /*******************************************************************
     * Checks to see if a pawn is up for promotion
     *
     * @param row   is the row of the Piece to check
     * @param col   is the col of the Piece to check
     * @param piece is the Piece to check
     * @return a boolean value whether the Piece can be promoted
     ******************************************************************/
    public boolean checkPawnPromotion(int row, int col, Piece piece) {
        if (row == 0 || row == 7) {
            if (piece instanceof Pawn)
                return true;

        }

        return false;
    }

    /*******************************************************************
     * Executes the appropriate castling move
     *
     * @param king is the King to castle
     ******************************************************************/
    public void executeCastle(Cell current, Cell targeted, Piece king) {
        King toCheck = (King) king;

        if(current.getRow() < targeted.getRow()) {
            // kingside
            // movePieceTo(r1, c1, r2, c2, king);
            Piece toCastle = getPieceAt(targeted.getRow(), targeted.getCol() + 1);
            // movePieceTo(r1, c2 + 1, r1, c2 - 1, toCastle);
            moveCastlingPieces(current.getRow(), current.getCol(), targeted.getRow(), targeted.getCol(), king, current.getRow(), targeted.getCol() + 1, current.getRow(), targeted.getCol() - 1, toCastle);

        } else { // queenside
            // movePieceTo(r1, c1, r2, c2, king);
            Piece toCastle = getPieceAt(targeted.getRow(), targeted.getCol() - 2);
            // movePieceTo(r1, c2 - 2, r1, c2 + 1, toCastle);

            moveCastlingPieces(current.getRow(), current.getCol(), targeted.getRow(), targeted.getCol(), king, current.getRow(), targeted.getCol() + 1, current.getRow(), targeted.getCol() - 1, toCastle);
        }
    }

    /*******************************************************************
     * Executes an en Passant move
     *

     * @param pawn is the Pawn we are moving
     ******************************************************************/
    public void executeEnPassant(Cell current, Cell targeted, Pawn pawn) {
        moveEnPassant(current, targeted, pawn);
        setPieceAt(current.getRow(), targeted.getCol(), null);
    }

    /*******************************************************************
     * Determines whether the specified King is in check
     *
     * @param color is the color of the King
     * @return a boolean value whether the king is in check
     ******************************************************************/
    private boolean isKingInCheck(PColor color) {
        int[] location = board.findKing(color);
        int kRow = location[0];
        int kCol = location[1];
        Cell targeted = board.getCellAt(kRow, kCol);
        // Want to try to move opposite colors to the king specified
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell current = board.getCellAt(row, col);
                if (getPieceAt(row, col) != null) {
                    Piece temp = getPieceAt(row, col);
                    if (temp.getColor() != color) {
                        // Try to move the piece to king
                        if (checkMove(current, targeted, temp)) {
                            return true; // white king is in check
                        }
                    }
                }
            }
        }
        return false;
    }

    /*******************************************************************
     * Checks to see if the specified king is in checkmate, it does this
     * by having the king in check and seeing if there are any valid
     * moves that can get the king out of the check. If not, then it is
     * considered to be a checkmate and the game is over.
     *
     * @param color is the PColor to check
     * @return a boolean value whether color is in checkmate
     ******************************************************************/
    private boolean isKingInCheckmate(PColor color) {
        if (isKingInCheck(color)) {
            // If the king is in check try to see if it can get out
            // check every move of their color to see if they can get
            // out

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Cell current = board.getCellAt(row, col);
                    if (getPieceAt(row, col) != null) {
                        Piece toCheck = getPieceAt(row, col);
                        if (toCheck.getColor() == color) {
                            for (int r = 0; r < 8; r++) {
                                for (int c = 0; c < 8; c++) {
                                    Cell targeted = board.getCellAt(r, c);
                                    if (checkMove(current, targeted, toCheck)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /*******************************************************************
     * Checks to see if the specified player, by color, is out of valid
     * moves.
     *
     * @param color is the PColor to check
     * @return a boolean value whether the player has any valid moves
     ******************************************************************/
    private boolean isOutOfMoves(PColor color) {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell current = board.getCellAt(row, col);
                if (getPieceAt(row, col) != null) {
                    Piece toCheck = getPieceAt(row, col);
                    if (toCheck.getColor() == color) {
                        for (int r = 0; r < 8; r++) {
                            for (int c = 0; c < 8; c++) {
                                Cell targeted = board.getCellAt(r, c);
                                if (checkMove(current, targeted, toCheck)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    /*******************************************************************
     * Checks to see if there is a stalemate in the game
     *
     * @param color is the PColor to check is there is a stalemate
     * @return a boolean value whether there is a stalemate
     ******************************************************************/
    private boolean isStalemate(PColor color) {
        if (!isKingInCheck(color)) { // king can't be in check
            // check to see if any valid moves are left for the player
            if (generateMoves(color).size() == 0)
                return true;
            // return isOutOfMoves(color);
        }
        return false;
    }

    /*******************************************************************
     * Checks to see if the game is over, either by a player winning or
     * by stalemate.
     * <p>
     * If result is 0 it means that the white king is in checkmate and
     * Black wins.
     * <p>
     * If result is 1 it means that the black king is in checkmate and
     * White wins.
     * <p>
     * If result is 2 it means that there is a stalemate, nobody wins.
     * <p>
     * If result is -1 it means that the game is still going.
     *
     * @return an integer value of whether the game is over
     ******************************************************************/
    public int isGameOver() {
        int result = -1; // default assumption game is still going
        if (isKingInCheckmate(PColor.White))
            result = 0; // white in checkmate, black wins
        else if (isKingInCheckmate(PColor.Black))
            result = 1; // black in checkmate, white wins
        else if (isStalemate(PColor.White) || isStalemate(PColor.Black))
            result = 2; // stalemate, no winner
        return result; // whether the game is over
    }

    /*******************************************************************
     * Checks to see if a piece is moved to a cell if it will put their
     * king at risk. This is done before you move the piece.
     *
     * @param piece is the Piece to check
     * @return a boolean value whether it puts the King at risk
     ******************************************************************/
    public boolean isFutureCheck(Cell current, Cell targeted,
                                 Piece piece) {

        Piece old = current.getChessPiece();
        boolean moved = old.hasMoved();
        Piece pCellToCheck;
        /* If result is 1 it means the move puts your king in check */
        int result = -1; // 0 is false, 1 is true
        if (targeted.getChessPiece() != null) {
            pCellToCheck = targeted.getChessPiece();
        } else {
            pCellToCheck = null;
        }

        movePieceTo(current, targeted, piece);

        if (piece.getColor() == PColor.White) {
            if (isKingInCheck(PColor.White)) {
                unMakeMove();
                return true;
            } else {
                if (isKingInCheckStill(PColor.White)) {
                    unMakeMove();
                    return true;
                }
                unMakeMove();
                return false;
            }
        } else {
            if (isKingInCheck(PColor.Black)) {

                unMakeMove();
                return true;
            } else {
                if (isKingInCheckStill(PColor.Black)) {
                    unMakeMove();
                    return true;
                }
                unMakeMove();
                return false;
            }
        }

    }

    public boolean checkMoveNoCheck(Cell current, Cell targeted, Piece piece) {
        if (piece.checkMovement(current, targeted, this)) {
            return true;
        }

        return false;
    }

    public boolean isKingInCheckStill(PColor color) {
        int[] location = board.findKing(color);
        int kRow = location[0];
        int kCol = location[1];
        Cell targeted = board.getCellAt(kRow, kCol);
        // Want to try to move opposite colors to the king specified
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell current = board.getCellAt(row, col);
                if (getPieceAt(row, col) != null) {
                    Piece temp = getPieceAt(row, col);
                    if (temp.getColor() != color) {
                        // Try to move the piece to king
                        if (checkMoveNoCheck(current, targeted, temp)) {
                            return true; // white king is in check
                        }
                    }
                }
            }
        }
        return false;
    }

    /*******************************************************************
     * Generates all of the possible moves for the specified color. TODO
     * Maybe store all alive pieces in a map for quicker lookup
     *
     * @param color is the PColor to search
     * @return a List of all valid Moves
     ******************************************************************/
    public List<Move> generateMoves(PColor color) {
        if (!isKingInCheckmate(color)) {
            List<Move> validMoves = new ArrayList<>();
            int direction = color == PColor.White ? 1 : -1;
            boolean passVal = false;
            for (int r1 = 0; r1 < 8; r1++) {
                for (int c1 = 0; c1 < 8; c1++) {
                    Cell current = board.getCellAt(r1, c1);
                    // First two loops go through board looking for
                    // color
                    if (getPieceAt(r1, c1) != null
                            && getPieceAt(r1, c1).getColor() == color) {
                        // it is a black piece
                        for (int r2 = 0; r2 < 8; r2++) {
                            for (int c2 = 0; c2 < 8; c2++) {
                                Cell targeted = board.getCellAt(r2, c2);
                                if (r1 - direction > -1 && r1 - direction < 8) {
                                    passVal = getBoard().getCellAt(r1 - direction, c2).isPassant();
                                }
                                if (checkMove(current, targeted, getPieceAt(r1, c1))) {
                                    if (r1 - direction > -1 && r1 - direction < 8) {
                                        getBoard().getCellAt(r1 - direction, c2).setPassant(passVal);
                                    }
                                    Move toAdd = new Move(current, targeted, getPieceAt(r1, c1), getPieceAt(r2, c2));
                                    validMoves.add(toAdd.clone());

                                }
                            }
                        }
                    }
                }
            }
            return validMoves;
        } else {
            return null;
        }
    }

    /*******************************************************************
     * From the list of all valid moves, this method utilizes a negaMax
     * algorithm to determine what the best possible move it can make
     * with the specified depth.
     *
     * @param color is the PColor we are checking
     * @return the best possible Move to be made
     ******************************************************************/
    public Move getBestMove(PColor color) {
        List<Move> validMoves = generateMoves(color);
        if (validMoves == null)
            return null;
        int bestResult = Integer.MIN_VALUE;
        Move bestMove = null;
        int depth = 2;

        for (Move move : validMoves) {
            int r1 = move.getR1();
            int c1 = move.getC1();
            int r2 = move.getR2();
            int c2 = move.getC2();

            movePieceTo(board.getCellAt(r1, c1), board.getCellAt(r2, c2), createCopy(move.getSelPiece()));

            int result = negaMax(depth, color);
            unMakeMove();
            if (result > bestResult) {
                bestResult = result;
                bestMove = move;
            }
        }
        System.out.println(bestResult);
        return bestMove;
    }

    /*******************************************************************
     * A negaMax algorithm that determines what the best possible move
     * will be for the specified color.
     * <p>
     * For more information, Wikipedia and some chess wikis explain it
     * really well.
     *
     * @param depth is how many plies to search
     * @param color is what PColor we are looking at
     * @return an integer value of the best score.
     ******************************************************************/
    public int negaMax(int depth, PColor color) {
        if (depth <= 0 || isGameOver() != -1)
            return evauluateScores();

        List<Move> moves = generateMoves(color);
        int currentMax = Integer.MIN_VALUE;

        for (Move currentMove : moves) {

            int r1 = currentMove.getR1();
            int c1 = currentMove.getC1();
            int r2 = currentMove.getR2();
            int c2 = currentMove.getC2();

            movePieceTo(board.getCellAt(r1, c1), board.getCellAt(r2, c2), createCopy(currentMove.getSelPiece()));
            int score = negaMax(depth - 1, color);

            unMakeMove();
            if (score > currentMax) {
                currentMax = score;
            }
        }
        return -currentMax;
    }

    /*******************************************************************
     * Evaluates the "score" of either side by adding together each
     * colors remaining Pieces' score values
     * <p>
     * TODO only coded for the black side right now
     *
     * @return an int containing the score
     ******************************************************************/
    private int evauluateScores() {
        int scoreWhite = 0;
        int scoreBlack = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (getPieceAt(row, col) != null) {
                    if (getPieceAt(row, col)
                            .getColor() == PColor.Black) {
                        scoreBlack += getPieceAt(row, col).getScore();
                        scoreBlack += getPositionScore(row, col);
                    } else {
                        scoreWhite += getPieceAt(row, col).getScore();
                        scoreWhite += getPositionScore(row, col);
                    }
                }
            }
        }
        // Each side has a score for remaining pieces now
        // System.out.println(scoreBlack - scoreWhite);

        return scoreBlack - scoreWhite;
    }

    /*******************************************************************
     * This makes the AI favor the middle of the board
     *
     * @param row is the row
     * @param col is the col
     * @return the score of that position on the board
     ******************************************************************/
    private int getPositionScore(int row, int col) {
        byte[][] positionWeight = {{1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 3, 3, 3, 3, 2, 2},
                {2, 2, 3, 4, 4, 3, 2, 2}, {2, 2, 3, 4, 4, 3, 2, 2},
                {2, 2, 3, 3, 3, 3, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2},
                {1, 1, 1, 1, 1, 1, 1, 1}};
        return positionWeight[row][col];
    }

    /*******************************************************************
     * DESCRIPTION
     *
     * @return the enPassCap
     ******************************************************************/
    public boolean isEnPassCap() {
        return enPassCap;
    }

    /*******************************************************************
     * Set method that sets a boolean value to the global boolean
     * variable enPassCap
     *
     * @param enPassCap the enPassCap to set
     ******************************************************************/
    public void setEnPassCap(boolean enPassCap) {
        this.enPassCap = enPassCap;
    }

    /*******************************************************************
     * Check the Piece's possible move, and move it if necessary
     *
     * @param piece is the Piece that we are checking
     * @return a boolean value whether the Piece was moved
     ******************************************************************/
    public boolean checkMove(Cell current, Cell targeted,
                             Piece piece) {

        if (piece.checkMovement(current, targeted, this)) {

            if (isFutureCheck(current, targeted, piece)) {
                return false;
            }
            if (!piece.hasMoved())
                piece.setHasMoved(true);

            return true;
        }

        return false;
    }

    /******************************************************************
     * Method to produce an ArrayList containing all possible moves with
     * the given piece at the row and column, if there is a piece at
     * that location
     *
     * @return ArrayList containing all possible moves with given piece
     *******************************************************************/
    public List<Move> generatePossibleMoves(Cell current) {
        List<Move> possibleMoves = new ArrayList<Move>();

        Move temp;
        Piece selected = current.getChessPiece();
        boolean movedStatus = selected.hasMoved();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (checkMove(current, board.getCellAt(r, c), selected)) {
                    temp = new Move(current, board.getCellAt(r, c), selected, getPieceAt(r, c));
                    possibleMoves.add(temp.clone());
                    // System.out.println("Row: " + r + "\tColumn: " +
                    // c);
                }

                selected.setHasMoved(movedStatus);

            }
        }

        selected.setHasMoved(movedStatus);
        return possibleMoves;

    }

    /*******************************************************************
     * Method to convert all the moves in an array list to specific
     * Strings for printing on the list object in the GUI
     *
     * @return ArrayList of strings
     ******************************************************************/

    public List<String> getHistoryArrayList() {

        List<String> contentToDisplay = new ArrayList();
        String temp;
        int tempRow1;
        int tempCol1;
        int tempRow2;
        int tempCol2;
        char letterCol1;
        char letterCol2;

        for (int x = 0; x < historyOfMoves.size(); x++) {

            if (historyOfMoves.get(x) instanceof CastlingMove) {
                CastlingMove castles = (CastlingMove) historyOfMoves
                        .get(x);

                tempRow1 = castles.getR1() + 1;
                tempCol1 = castles.getC1() + 1;
                tempRow2 = castles.getR2() + 1;
                tempCol2 = castles.getC2() + 1;

                letterCol1 = (char) (tempCol1 + 96);
                letterCol2 = (char) (tempCol2 + 96);

                temp = letterCol1 + "" + tempRow1 + " to " + letterCol2
                        + tempRow2;

                contentToDisplay.add(x, temp);

                tempRow1 = castles.getRowOfRook1() + 1;
                tempCol1 = castles.getColOfRook1() + 1;
                tempRow2 = castles.getRowOfRook2() + 1;
                tempCol2 = castles.getColOfRook2() + 1;

                letterCol1 = (char) (tempCol1 + 96);
                letterCol2 = (char) (tempCol2 + 96);

                temp = letterCol1 + "" + tempRow1 + " to " + letterCol2
                        + tempRow2;

                contentToDisplay.add(x, temp);
            } else {
                tempRow1 = historyOfMoves.get(x).getR1() + 1;
                tempCol1 = historyOfMoves.get(x).getC1() + 1;
                tempRow2 = historyOfMoves.get(x).getR2() + 1;
                tempCol2 = historyOfMoves.get(x).getC2() + 1;

                letterCol1 = (char) (tempCol1 + 96);
                letterCol2 = (char) (tempCol2 + 96);

                temp = letterCol1 + "" + tempRow1 + " to " + letterCol2
                        + tempRow2;

                contentToDisplay.add(x, temp);
            }
        }

        return contentToDisplay;
    }

    /**
     * Used to check for whose turn it is
     *
     * @return a boolean whether it is the white side's turn
     */
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    /**
     * Used to set whether it is the white side's turn or not - use changeTurn() for normal turn changing
     *
     * @param whiteTurn is a boolean for whether to set it to be the white side's turn or not
     */
    public void setWhiteTurn(boolean whiteTurn) {
        isWhiteTurn = whiteTurn;
    }

    /**
     * Used to change the turn to the next side
     *
     * @return a boolean for whether it is the white side's turn
     */
    public boolean changeTurn() {
        return isWhiteTurn = !isWhiteTurn;
    }
}
