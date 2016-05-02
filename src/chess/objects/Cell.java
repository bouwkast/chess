package chess.objects;

public class Cell {

    private int row, col;
    private boolean isPassant;
    private Piece piece;

    public Cell(Piece piece, int row, int col, boolean isPassant) {
        this.piece = piece;
        this.row = row;
        this.col = col;
        this.isPassant = isPassant;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isPassant() {
        return isPassant;
    }

    public void setPassant(boolean passant) {
        isPassant = passant;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
