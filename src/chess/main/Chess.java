package chess.main;

import chess.objects.Board;
import chess.objects.Cell;
import chess.objects.Piece;

public class Chess implements java.io.Serializable {

    private Board board;
    public boolean passantJustSet;
    public int passantCounter;

    public Chess() {
        this.board = new Board();
        passantJustSet = false;
        passantCounter = 0;
    }

    public Piece getPieceAt(int row, int col) {
        return board.getCellAt(row, col).getPiece();
    }

    public Cell getCellAt(int row, int col) {
        return board.getCellAt(row, col);
    }

    public Board getBoard() {
        return board;
    }

    public boolean movePiece(Cell initial, Cell targeted) {
        if (isValidMove(initial, targeted)) {
            targeted.setPiece(initial.getPiece());
            initial.setPiece(null);
            targeted.getPiece().setHasMoved(true);
            if(passantJustSet) {
                passantCounter++;
            }
            if(passantCounter >= 2) {
                resetPassant();
            }
            return true;
        }
        return false;
    }

    private void resetPassant() {
        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                getCellAt(row, col).setPassant(false);
            }
        }
    }

    public boolean checkPawnMove(Cell initial, Cell targeted) {
        // pawn movement is color dependent
        int colorDir = initial.getPiece().getColor() == 0 ? 1 : -1; // if color is 0 it is black

        if (initial.getPiece().isHasMoved()) {
            if (Math.abs(initial.getRow() - targeted.getRow()) > 1 || Math.abs(initial.getCol() - targeted.getCol()) > 1)
                return false; // can only move two rows on first move, can never move 2 cols
        }

        // ensure moving in appropriate direction
        if (targeted.getRow() - initial.getRow() != colorDir && targeted.getRow() - initial.getRow() != colorDir * 2)
            return false;


        if (targeted.getPiece() == null && initial.getCol() != targeted.getCol() && !targeted.isPassant())
            return false; // can only move diagonal if attacking
        // if moving two rows check for piece in-between or at targeted
        if (targeted.getRow() - initial.getRow() == colorDir * 2) {
            if (board.getCellAt(initial.getRow() + colorDir, initial.getCol()).getPiece() != null || board.getCellAt(targeted.getRow(), initial.getCol()).getPiece() != null)
                return false;
            // TODO add in enpassant here
            if (adjacentPawn(initial, targeted)) {
                getCellAt(initial.getRow() + colorDir, initial.getCol()).setPassant(true);
                passantJustSet = true;
            }

        }

        return true;
    }

    private boolean adjacentPawn(Cell initial, Cell targeted) {

        Cell leftTar = null, rightTar = null;
        if (targeted.getCol() > 0)
            leftTar = getCellAt(targeted.getRow(), targeted.getCol() - 1);
        if (targeted.getCol() < 7)
            rightTar = getCellAt(targeted.getRow(), targeted.getCol() + 1);
        if (leftTar != null && leftTar.getPiece() != null) {
            if (leftTar.getPiece().getName().equals("Pawn") && leftTar.getPiece().getColor() != initial.getPiece().getColor()) {
                return true;
            }
        }

        if (rightTar != null && rightTar.getPiece() != null) {
            if (rightTar.getPiece().getName().equals("Pawn") && rightTar.getPiece().getColor() != initial.getPiece().getColor()) {
                return true;
            }
        }

        return false;
    }

    public boolean checkKnightMove(Cell initial, Cell targeted) {
        // move is 2 row 1 col or 1 row 2 col
        if (Math.abs(initial.getRow() - targeted.getRow()) == 2 && Math.abs(initial.getCol() - targeted.getCol())
                == 1 || Math.abs(initial.getRow() - targeted.getRow()) == 1 && Math.abs(initial.getCol() - targeted.getCol()) == 2) {
            return true;
        }
        return false;
    }

    public boolean checkBishopMove(Cell initial, Cell targeted) {
        if (initial.getCol() != targeted.getCol() && initial.getRow() != targeted.getRow()) {
            return checkDiagonalMovement(initial, targeted);
        } else {
            return false;
        }
    }

    private boolean checkDiagonalMovement(Cell initial, Cell targeted) {
        int colDir = initial.getCol() < targeted.getCol() ? 1 : -1; // one is some diagonal right
        int rowDir = initial.getRow() < targeted.getRow() ? 1 : -1; // one is some diagonal down
        // confusing for loop coming
        for (int row = initial.getRow() + rowDir, col = initial.getCol() + colDir; row != targeted.getRow(); row += rowDir, col += colDir) {
            if (board.getCellAt(row, col).getPiece() != null)
                return false;
        }
        return true;
    }

    public boolean checkRookMove(Cell initial, Cell targeted) {
        // rooks move vertical or horizontal

        if (initial.getCol() != targeted.getCol() && initial.getRow() != targeted.getRow())
            return false; // diagonal move
        if (initial.getRow() != targeted.getRow()) { // vertical
            return checkVerticalMovement(initial, targeted);
        } else { // horizontal
            return checkHorizontalMovement(initial, targeted);
        }
    }

    private boolean checkHorizontalMovement(Cell initial, Cell targeted) {
        int colDir = initial.getCol() < targeted.getCol() ? 1 : -1; // 1 is going right
        for (int col = initial.getCol() + colDir; col != targeted.getCol(); col += colDir) {
            if (board.getCellAt(initial.getRow(), col).getPiece() != null)
                return false; // piece in way
        }
        return true;
    }

    private boolean checkVerticalMovement(Cell initial, Cell targeted) {
        int rowDir = initial.getRow() < targeted.getRow() ? 1 : -1; // 1 is going down
        for (int row = initial.getRow() + rowDir; row != targeted.getRow(); row += rowDir) {
            if (board.getCellAt(row, initial.getCol()).getPiece() != null)
                return false; // piece in way
        }
        return true;
    }

    public boolean checkQueenMove(Cell initial, Cell targeted) {

        return false;
    }

    public boolean checkKingMove(Cell initial, Cell targeted) {

        return false;
    }


    public boolean isValidMove(Cell initial, Cell targeted) {

        if (initial.getPiece() == null)
            return false; // no piece to move
        if (targeted.getPiece() != null)
            if (initial.getPiece().getColor() == targeted.getPiece().getColor()) {
                return false;
            }
        switch (initial.getPiece().getName()) {
            case ("Pawn"):
                return checkPawnMove(initial, targeted);
            case ("Knight"):
                return checkKnightMove(initial, targeted);
            case ("Bishop"):
                return checkBishopMove(initial, targeted);
            case ("Rook"):
                return checkRookMove(initial, targeted);
            case ("Queen"):
                return checkQueenMove(initial, targeted);
            case ("King"):
                return checkKingMove(initial, targeted);
            default:
                return false;

        }
    }


}
