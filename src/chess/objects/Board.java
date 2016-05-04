package chess.objects;

public class Board {

    private Cell[][] grid;
    private final int NUM_ROWS = 8;
    private final int NUM_COLS = 8;

    public Board() {
        createGrid();
        deployPieces();
    }

    private void createGrid() {
        grid = new Cell[NUM_ROWS][NUM_COLS];
        for(int row = 0; row < NUM_ROWS; ++row) {
            for(int col = 0; col < NUM_COLS; ++col) {
                grid[row][col] = new Cell(null, row, col, false);
            }
        }
    }

    private void deployPieces() {
        // set up pawns
        for(int col = 0; col < NUM_COLS; ++col) {
            grid[1][col].setPiece(new Pawn(0)); // black
            grid[NUM_ROWS - 2][col].setPiece(new Pawn(1));
        }

        // rooks
        grid[0][0].setPiece(new Rook(0)); // black
        grid[0][7].setPiece(new Rook(0));

        grid[7][0].setPiece(new Rook(1));
        grid[7][7].setPiece(new Rook(1));

        // knights
        grid[0][1].setPiece(new Knight(0)); // black
        grid[0][6].setPiece(new Knight(0));

        grid[7][1].setPiece(new Knight(1));
        grid[7][6].setPiece(new Knight(1));

        // bishops
        grid[0][2].setPiece(new Bishop(0)); // black
        grid[0][5].setPiece(new Bishop(0));

        grid[7][2].setPiece(new Bishop(1));
        grid[7][5].setPiece(new Bishop(1));

        // queens
        grid[0][3].setPiece(new Queen(0)); // black

        grid[7][3].setPiece(new Queen(1));

        // kings
        grid[0][4].setPiece(new King(0)); // black

        grid[7][4].setPiece(new King(1));
    }

    public Cell getCellAt(int row, int col) {
        return grid[row][col];
    }

}
