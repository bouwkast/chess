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
        for(int col = 0; col < NUM_COLS; ++col) {
            grid[0][col].setPiece(new Pawn());
        }
    }

}
