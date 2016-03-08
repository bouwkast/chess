/**
 * 
 */
package chess.objects;

public class Castling_Move extends Move implements java.io.Serializable {
    
    int rowOfRook1;
    int colOfRook1;
    int rowOfRook2;
    int colOfRook2;
    Piece rookPiece;
    Piece targetLocation;
    
    /*******************************************************************
     * Constructor to create a new Castling_Move based on the selected
     * row and column and the targeted row and column. Mainly here as a
     * super constructor for the move class. Each Castling_Move contains
     * a copy of the King and the Rook with different IDs to be able to
     * undo the Castling move.
     * 
     * @param r1 is the selected row
     * @param c1 is the selected col
     * @param r2 is the targeted row
     * @param c2 is the targeted col
     * @param selPiece is the selected Piece
     * @param tarPiece is the targeted Piece
     ******************************************************************/
    public Castling_Move(int r1, int c1, int r2, int c2, Piece selPiece,
            Piece tarPiece) {
        super(r1, c1, r2, c2, selPiece, tarPiece);
    }
    
    /*******************************************************************
     * Constructor to create a new Castling_Move based on the selected
     * King's row and column, the targeted row and column for the King,
     * the selected Rook's row and column, and the Rook's targeted row
     * and column.
     * 
     * @param kingRow1 contains the row of the selected King
     * @param kingCol1 contains the column of the selected King
     * @param kingRow2 contains the row where the King will go
     * @param kingCol2 contains the column where the King will go
     * @param kingPiece contains the King piece
     * @param tarPiece contains the piece at the targeted location
     * @param rookRow1 contains the row of the selected Rook
     * @param rookCol1 contains the column of the selected Rook
     * @param rookRow2 contains the row where the Rook will go
     * @param rookCol2 contains the column where the Rook will go
     * @param rookSelect contains the Rook piece
     * @param rookTarPiece contains the piece at the targted location
     ******************************************************************/
    public Castling_Move(int kingRow1, int kingCol1, int kingRow2,
            int kingCol2, Piece kingPiece, Piece tarPiece, int rookRow1,
            int rookCol1, int rookRow2, int rookCol2, Piece rookSelect,
            Piece rookTarPiece) {
            
        super(kingRow1, kingCol1, kingRow2, kingCol2, kingPiece,
                tarPiece);
                
        rowOfRook1 = rookRow1;
        colOfRook1 = rookCol1;
        rowOfRook2 = rookRow2;
        colOfRook2 = rookCol2;
        rookPiece = rookSelect;
        targetLocation = rookTarPiece;
    }
    
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
     * Castling_Move with a new ID along with new IDs for all four
     * pieces
     * 
     * @return clonedCastle which is the cloned Castling_Move object
    *******************************************************************/
    public Castling_Move cloneCastling() {
        Piece cloneKingStart;
        Piece cloneKingEnd;
        
        Piece cloneRookStart;
        Piece cloneRookEnd;
        
        int rowOfKing1 = super.getR1();
        int colOfKing1 = super.getC1();
        int rowOfKing2 = super.getR2();
        int colOfKing2 = super.getC2();
        
        cloneRookStart = new Rook((Rook) rookPiece);
        cloneRookEnd = null;
        
        cloneKingStart = new King((King) super.getSelPiece());
        cloneKingEnd = null;
        
        Castling_Move clonedCastle = new Castling_Move(rowOfKing1,
                colOfKing1, rowOfKing2, colOfKing2, cloneKingStart,
                cloneKingEnd, rowOfRook1, colOfRook1, rowOfRook2,
                colOfRook2, cloneRookStart, cloneRookEnd);
                
        return clonedCastle;
    }
    
}
