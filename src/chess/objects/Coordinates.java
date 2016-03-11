package chess.objects;

public class Coordinates {
    private int rowOne, colOne, rowTwo, colTwo;

    public Coordinates(int rowOne, int colOne, int rowTwo, int colTwo) {
        this.rowOne = rowOne;
        this.colOne = colOne;
        this.rowTwo = rowTwo;
        this.colTwo = colTwo;
    }

    public int getRowOne() {
        return rowOne;
    }

    public void setRowOne(int rowOne) {
        this.rowOne = rowOne;
    }

    public int getColOne() {
        return colOne;
    }

    public void setColOne(int colOne) {
        this.colOne = colOne;
    }

    public int getRowTwo() {
        return rowTwo;
    }

    public void setRowTwo(int rowTwo) {
        this.rowTwo = rowTwo;
    }

    public int getColTwo() {
        return colTwo;
    }

    public void setColTwo(int colTwo) {
        this.colTwo = colTwo;
    }
}
