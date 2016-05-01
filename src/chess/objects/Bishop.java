package chess.objects;

import chess.main.Chess;

public class Bishop extends Piece implements java.io.Serializable {

    private Cell current, targeted;

    /*******************************************************************
     * Constructor for the Bishop piece with its specified PColor
     *
     * @param color is the PColor of the Bishop
     ******************************************************************/
    public Bishop(PColor color) {
        super(color, "Bishop"); // Set the color and make it alive
        if (color.equals(PColor.White))
            this.icon = "\u2657"; // white
        else
            this.icon = "\u265d"; // black
        this.score = 30;
    }


    /*******************************************************************
     * Copy constructor for the Bishop
     *
     * @param other is the Bishop to copy
     ******************************************************************/
    public Bishop(Bishop other) {
        super(other);
    }

    @Override
    public boolean checkMovement(Cell current, Cell targeted, Chess chess) {
        this.current = current;
        this.targeted = targeted;
        // Bishops row and col must change by same amount
        if (Math.abs(current.getRow() - targeted.getRow()) == Math.abs(current.getCol() - targeted.getCol())) {
            Movement move = new Movement(current, targeted, this, chess);
            return move.checkDiagonal();

        } else { // Not a diagonal move
            return false;
        }
    }
}
