/**
 * 
 */
package chess.objects;

public class CastlingMove extends Move implements java.io.Serializable {


    
    int rowOfRook1;
    int colOfRook1;
    int rowOfRook2;
    int colOfRook2;
    Piece rookPiece;
    Piece targetLocation; // TODO appears that this is useless
    boolean isKingside;
    Cell current, targeted;
    
    /*******************************************************************
     * Constructor to create a new CastlingMove based on the selected
     * row and column and the targeted row and column. Mainly here as a
     * super constructor for the move class. Each CastlingMove contains
     * a copy of the King and the Rook with different IDs to be able to
     * undo the Castling move.
     *
     * @param selPiece is the selected Piece
     * @param tarPiece is the targeted Piece
     ******************************************************************/
    public CastlingMove(Cell current, Cell targeted, Piece selPiece,
                        Piece tarPiece) {
        super(current, targeted, selPiece, tarPiece);
        if(current.getCol() < targeted.getCol())
            isKingside = true;
        else
            isKingside = false;
        setParameters(current, targeted);
        this.current = current;
        this.targeted = targeted;
    }

    private void setParameters(Cell current, Cell targeted) {
        int xDir = isKingside ? -1 : 3; // direction of the rook to move
        colOfRook1 = isKingside ? 7 : 0; // if it is kingside its in col 7 else its 0
        colOfRook2 = colOfRook1 + xDir;
        if(current.getChessPiece().getColor() == PColor.Black)
            rowOfRook1 = 0;
        else
            rowOfRook1 = 7;
        rowOfRook2 = rowOfRook1; // TODO, seriously....... it can only go horizontally need to fix
        if(current.getChessPiece().getColor() == PColor.Black)
            rookPiece = new Rook(PColor.Black);
        else
            rookPiece = new Rook(PColor.White);

    }
    // TODO temporary comment out before deletion
//
//    /*******************************************************************
//     * Constructor to create a new CastlingMove based on the selected
//     * King's row and column, the targeted row and column for the King,
//     * the selected Rook's row and column, and the Rook's targeted row
//     * and column.
//     *
//     * @param kingPiece contains the King piece
//     * @param tarPiece contains the piece at the targeted location
//     * @param rookRow1 contains the row of the selected Rook
//     * @param rookCol1 contains the column of the selected Rook
//     * @param rookRow2 contains the row where the Rook will go
//     * @param rookCol2 contains the column where the Rook will go
//     * @param rookSelect contains the Rook piece
//     * @param rookTarPiece contains the piece at the targted location
//     ******************************************************************/
//    public CastlingMove(Cell current, Cell targeted, Piece kingPiece, Piece tarPiece, int rookRow1,
//                        int rookCol1, int rookRow2, int rookCol2, Piece rookSelect,
//                        Piece rookTarPiece) {
//
//        super(current, targeted, kingPiece,
//                tarPiece);
//
//        rowOfRook1 = rookRow1;
//        colOfRook1 = rookCol1;
//        rowOfRook2 = rookRow2;
//        colOfRook2 = rookCol2;
//        rookPiece = rookSelect;
//        targetLocation = rookTarPiece;
//    }
    
    /*******************************************************************
     * Method to set the row of the selected Rook to a given value.
     * 
     * @param value that contains the row to be assigned
     *******************************************************************/
    public void setRowOfRook1(int value) {
        rowOfRook1 = value;
    }
    
    /******************************************************************
     * Method to get the row of the selected Rook
     * 
     * @return rowOfRook1 that contains the row of the selected Rook
     ******************************************************************        
     */
    
    public int getRowOfRook1() {
        return rowOfRook1;
    }
    
    /*******************************************************************
     * Method to set the column of the selected Rook
     * 
     * @param value that contains the column to be assigned
     *******************************************************************/
    public void setColOfRook1(int value) {
        colOfRook1 = value;
    }
    
    /*******************************************************************
     * Method to get the column of the selected Rook
     * 
     * @return colOfRook1 that contains the column of the selected Rook
     *******************************************************************/
    public int getColOfRook1() {
        return colOfRook1;
    }
    
    /******************************************************************
     * Method to set the row of where the Rook shall move
     * 
     * @param value that contains the row to be assigned
     *******************************************************************/
    public void setRowOfRook2(int value) {
        rowOfRook2 = value;
    }
    
    /*******************************************************************
     * Method to get the row of where the rook shall be moved to
     * 
     * @return rowOfRook2 that contains the row of where the rook shall
     *         be
     
     ******************************************************************/
    public int getRowOfRook2() {
        return rowOfRook2;
    }
    
    /******************************************************************
     * Method to set the column of where the Rook shall move
     * 
     * @param value that contains the column to be assigned
     *******************************************************************/
    public void setRowOfCol2(int value) {
        colOfRook2 = value;
    }
    
    /*******************************************************************
     * Method to get the column of where the Rook shall be
     * 
     * @return colOfRook2 that has the column of where the rook will be
    *******************************************************************/
    public int getColOfRook2() {
        return colOfRook2;
    }
    
    /*******************************************************************
     * Method to set the Rook piece. Acts similarly to the selected 
     * Piece variable from the Move class
     * 
     * @param theRook that contains the Rook piece
    ******************************************************************/
    public void setRookPiece(Piece theRook) {
        rookPiece = theRook;
    }
    
    /*******************************************************************
     * Method to get the Rook Piece. rookPiece variable acts similarly
     * to selected Piece variable from Move class in which it contains
     * the Rook in it's original location before movement
     * 
     * @return rookPiece that contains the Rook Piece
    *******************************************************************/
    public Piece getRookPiece() {
        return rookPiece;
    }
    
    /*******************************************************************
     * Method to set a piece to the targetLocation variable in which
     * it contains the piece that is in the cell located at rowOfRook2
     * and colOfRook2
     * 
     * @param target
    *******************************************************************/
    
    public void setTargetPiece(Piece target) {
        targetLocation = target;
    }
    
    /*******************************************************************
     * Method to get the piece from the variable targetLocation which
     * contains the piece that is in the cell located at rowOfRook2 and
     * colOfRook2
     * 
     * @return targetLocation containing the Piece at the target cell
    *******************************************************************/
    
    public Piece getTargetPiece() {
        return targetLocation;
    }
    
    /*******************************************************************
     * Copy method to create a deep copy of a pre-existing 
     * CastlingMove with a new ID along with new IDs for all four
     * pieces
     * 
     * @return clonedCastle which is the cloned CastlingMove object
    *******************************************************************/
    public CastlingMove cloneCastling() {
        Piece cloneKingStart;
        Piece cloneKingEnd;
        
        Piece cloneRookStart;
        Piece cloneRookEnd;

        Cell cCurrent = current.cloneCell();
        Cell cTargeted = targeted.cloneCell();
        
        cloneRookStart = new Rook((Rook) rookPiece);
        cloneRookEnd = null;
        
        cloneKingStart = new King((King) super.getSelPiece());
        cloneKingEnd = null;

        return new CastlingMove(cCurrent, cTargeted, cloneKingStart, null);
//        CastlingMove clonedCastle = new CastlingMove(current, targeted, cloneKingStart,
//                cloneKingEnd, rowOfRook1, colOfRook1, rowOfRook2,
//                colOfRook2, cloneRookStart, cloneRookEnd);
//
//        return clonedCastle;
    }
    
}
