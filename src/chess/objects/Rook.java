package chess.objects;

import chess.main.Chess;

public class Rook extends Piece implements java.io.Serializable {

    /*******************************************************************
     * Constructor for the Rook that sets its PColor
     *
     * @param color is the PColor to set the Rook to
     ******************************************************************/
    public Rook(PColor color) {
        super(color, "Rook");
        this.color = color;
        if (color.equals(PColor.White))
            this.icon = "\u2656"; // white
        else
            this.icon = "\u265c"; // black
        this.score = 50;
    }

    /*******************************************************************
     * Copy constructor for the Rook
     *
     * @param other is the Rook to copy
     ******************************************************************/
    public Rook(Rook other) {
        super(other);
    }

    @Override
    public boolean checkMovement(Cell current, Cell targeted, Chess chess) {

        if ((current.getRow() != targeted.getRow() && current.getCol() == targeted.getCol()) ||
                (current.getRow() == targeted.getRow() && current.getCol() != targeted.getCol())) {
            Movement move = new Movement(current, targeted, this, chess);
            return move.checkLateral();
        }
        return false;
    }
}
