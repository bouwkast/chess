package chess.objects;

import chess.main.Chess;

public class Knight extends Piece implements java.io.Serializable {

    private Cell current, targeted;

    /*******************************************************************
     * Constructor for the Knight that takes it's PColor
     *
     * @param color is the PColor to set the Knight to
     ******************************************************************/
    public Knight(PColor color) {
        super(color, "Knight"); // Set the color and make it alive
        this.color = color;
        if (color.equals(PColor.White))
            this.icon = "\u2658"; // white
        else
            this.icon = "\u265e"; // black
        this.score = 30;
    }

    public Knight(Knight other) {
        super(other);
    }

    @Override
    public boolean checkMovement(Cell current, Cell targeted, Chess chess) {

        // First need to calc the difference for the rows and cols
        if ((Math.abs(current.getRow() - targeted.getRow()) == 2 && Math.abs(current.getCol() - targeted.getCol()) == 1) || (Math.abs(current.getRow() - targeted.getRow()) == 1 &&  Math.abs(current.getCol() - targeted.getCol()) == 2)) {
            // Need to check the cell it is moving to
            if (targeted.getChessPiece() == null || this.getColor() != targeted.getChessPiece().getColor()) {
                // Valid move, we can move the Knight
                return true;

            } else {
                // Invalid move, it contains the same color as Knight
                return false;
            }

        } else {
            // Invalid move pattern
            return false;
        }
    }

}
