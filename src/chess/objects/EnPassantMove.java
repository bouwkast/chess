/**
 * 
 */
package chess.objects;

public class EnPassantMove extends Move implements java.io.Serializable {
    
    private Piece capturedPiece;
    private Cell current, targeted;
    
    /******************************************************************
     * Constructor for the En Passant Move. 
     * Creates a new En Passant move based on the selected row
     * and column and the targeted row and column. Here to act as a 
     * super constructor for the move class 
     * and contains all of its variables
     *
     *@param selPiece is the selected Piece
     *@param tarPiece is the targeted Piece
    ******************************************************************/
    public EnPassantMove(Cell current, Cell targeted,
                         Piece selPiece, Piece tarPiece) {
        super(current, targeted, selPiece, tarPiece);
        this.current = current;
        this.targeted = targeted;
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
     *@param selPiece is the selected Piece
     *@param tarPiece is the targeted Piece
     *@param capturedPassant is the captured piece 
    ******************************************************************/
    
    public EnPassantMove(Cell current, Cell targeted,
                         Piece selPiece, Piece tarPiece, Piece capturedPassant) {
            
        super(current, targeted, selPiece, tarPiece);
        
        capturedPiece = capturedPassant;
        this.current = current;
        this.targeted = targeted;
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
    public EnPassantMove cloneMove() {
        Piece selectClone;
        Piece targetClone;
        Piece capturedPieceCopy;
        
        int r1 = super.getR1();
        int c1 = super.getC1();
        int r2 = super.getR2();
        int c2 = super.getC2();

        Cell cCurrent = current.cloneCell();
        Cell cTargeted = targeted.cloneCell();
        
        selectClone = new Pawn((Pawn) super.getSelPiece());
        targetClone = null;
        
        if(capturedPiece != null)
        	capturedPieceCopy = new Pawn((Pawn) capturedPiece);
        else
        	capturedPieceCopy = null;
        
        EnPassantMove clonedMove = new EnPassantMove(cCurrent, cTargeted,
                selectClone, targetClone, capturedPieceCopy);
                
        return clonedMove;
    }
    
}
