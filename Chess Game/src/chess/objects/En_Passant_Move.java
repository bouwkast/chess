/**
 * 
 */
package chess.objects;

public class En_Passant_Move extends Move {
    
    private Piece capturedPiece;
    
    /******************************************************************
     * Constructor for the En Passant Move. 
     * Creates a new En Passant move based on the selected row
     * and column and the targeted row and column. Here to act as a 
     * super constructor for the move class 
     * and contains all of its variables
     * 
     *@param r1 is the selected row
     *@param c1 is the selected col
     *@param r2 is the targeted row
     *@param c2 is the targeted col
     *@param selPiece is the selected Piece
     *@param tarPiece is the targeted Piece
    ******************************************************************/
    public En_Passant_Move(int r1, int c1, int r2, int c2,
            Piece selPiece, Piece tarPiece) {
        super(r1, c1, r2, c2, selPiece, tarPiece);
        // TODO Auto-generated constructor stub
    }
    
    /******************************************************************
     * Constructor for the En Passant Move. 
     * Creates a new En Passant move based on the selected row
     * and column and the targeted row and column. Each En Passant Move
     * object contains all the variables of the Move object as well
     * as a third piece variable that stores the 
     * captured piece that results from this movement
     * 
     *@param r1 is the selected row
     *@param c1 is the selected col
     *@param r2 is the targeted row
     *@param c2 is the targeted col
     *@param selPiece is the selected Piece
     *@param tarPiece is the targeted Piece
     *@param capturedPassant is the captured piece 
    ******************************************************************/
    
    public En_Passant_Move(int r1, int c1, int r2, int c2,
            Piece selPiece, Piece tarPiece, Piece capturedPassant) {
            
        super(r1, c1, r2, c2, selPiece, tarPiece);
        
        capturedPiece = capturedPassant;
        
    }
    
    /******************************************************************
     * Method for getting the captured piece that results from
     * En Passant move
     * 
     * @return capturedPiece that contains the captured piece
    *****************************************************************
     */
    public Piece getCapturedPiece() {
        return capturedPiece;
    }
    
    /*******************************************************************
     * Method for assigning a piece to the capturedPiece variable
     * 
     * @param piece containing the piece to be assigned
    *****************************************************************
    */
    public void setCapturedPiece(Piece piece) {
        capturedPiece = piece;
    }
    
    /*******************************************************************
     * Copy method to create a deep copy of a pre-existing En Passant
     * move with a new ID along with new IDs for all three pieces
     * 
     * @return the cloned En Passant Move
     ******************************************************************/
    public En_Passant_Move cloneMove() {
        Piece selectClone;
        Piece targetClone;
        Piece capturedPieceCopy;
        
        int r1 = super.getR1();
        int c1 = super.getC1();
        int r2 = super.getR2();
        int c2 = super.getC2();
        
        selectClone = new Pawn((Pawn) super.getSelPiece());
        targetClone = null;
        
        capturedPieceCopy = new Pawn((Pawn) capturedPiece);
        
        En_Passant_Move clonedMove = new En_Passant_Move(r1, c1, r2, c2,
                selectClone, targetClone, capturedPieceCopy);
                
        return clonedMove;
    }
    
}
