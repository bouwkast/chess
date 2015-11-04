package chess.objects;

public class Board {
    
    /** Is the 2D array that will contain the pieces */
    private Cell[][] board;
    
    /** Stores the row of a space that is available for en Passant */
    private int currentPassantRow;
    
    /** Stores the column of a space that is available for en Passant */
    private int currentPassantCol;
    
    /** Determines if there's a space that's available for en Passant */
    private boolean currentPassantMove;
    
    /** Contains the new row for the new en Passant space */
    private int newPassantRow;
    
    /** Contains the new column for the new en Passant space */
    private int newPassantCol;
    
    /** Determines if a new space is available for en Passant **/
    private boolean newPassantMove;
    
    /** Determines if a piece has been captured via en Passant **/
    private boolean passantCapture;
    
    /*******************************************************************
     * Constructor for the board class, going to be changing this one
     * soon by removing the parameter. Just waiting to ensure that
     * everything works.
     ******************************************************************/
    public Board() {
        board = new Cell[8][8];
        currentPassantMove = false;
        newPassantMove = false;
        passantCapture = false;
        reset();
        setBoard();
    }
    
    /*******************************************************************
     * Goes through the Board and sets each Cell to be a new Cell, not
     * containing anything
     ******************************************************************/
    private void reset() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Cell();
            }
        }
    }
    
    /*******************************************************************
     * Sets the pawns on the board in the correct location and color
     ******************************************************************/
    private void setPawns() {
        // Sets the pawns for the black side;
        for (int x = 0; x < 8; x++) {
            board[1][x].setChessPiece(new Pawn(PColor.Black));
        }
        // Sets the pawns for the white Side
        for (int y = 0; y < 8; y++) {
            board[6][y].setChessPiece(new Pawn(PColor.White));
        }
    }
    
    /*******************************************************************
     * Sets the 2 black and 2 white Knights on the board.
     ******************************************************************/
    private void setKnights() {
        // Sets the knights for the black side
        board[0][1].setChessPiece(new Knight(PColor.Black));
        board[0][6].setChessPiece(new Knight(PColor.Black));
        
        // Sets the knights for the white side
        board[7][1].setChessPiece(new Knight(PColor.White));
        board[7][6].setChessPiece(new Knight(PColor.White));
    }
    
    /*******************************************************************
     * Sets the 2 black and the 2 white Bishops on the board.
     ******************************************************************/
    private void setBishops() {
        // Sets the bishops for the black side
        board[0][2].setChessPiece(new Bishop(PColor.Black));
        board[0][5].setChessPiece(new Bishop(PColor.Black));
        
        // Sets the bishops for the white side
        board[7][2].setChessPiece(new Bishop(PColor.White));
        board[7][5].setChessPiece(new Bishop(PColor.White));
    }
    
    /*******************************************************************
     * Sets the 2 black and the 2 white Rooks on the board
     ******************************************************************/
    private void setRooks() {
        // Sets the rooks for the black side
        board[0][0].setChessPiece(new Rook(PColor.Black));
        board[0][7].setChessPiece(new Rook(PColor.Black));
        
        // Sets the rooks for the white side
        board[7][0].setChessPiece(new Rook(PColor.White));
        board[7][7].setChessPiece(new Rook(PColor.White));
    }
    
    /*******************************************************************
     * Sets the black and white Queens on the board
     ******************************************************************/
    private void setQueens() {
        // Sets the queen for the black side
        board[0][3].setChessPiece(new Queen(PColor.Black));
        
        // Sets the queen for the white side
        board[7][3].setChessPiece(new Queen(PColor.White));
    }
    
    /*******************************************************************
     * Sets the black and white Kings on the board
     ******************************************************************/
    private void setKings() {
        // Sets the king for the black side
        board[0][4].setChessPiece(new King(PColor.Black));
        
        // Sets the king for the white side
        board[7][4].setChessPiece(new King(PColor.White));
    }
    
    /*******************************************************************
     * Simple helper method that sets all of the Pieces on the board
     ******************************************************************/
    private void setBoard() {
        setKings();
        setQueens();
        setBishops();
        setKnights();
        setRooks();
        setPawns();
    }
    
    /*******************************************************************
     * Getter method for a Cell at a specific location
     * 
     * @param row is the row of the Cell to get
     * @param col is the col of the Cell to get
     * @return the Cell at the location specified
     ******************************************************************/
    public Cell getCellAt(int row, int col) {
        return board[row][col];
    }
    
    /*******************************************************************
     * Gets the piece in the specified Cell
     * 
     * @param row is the row of the Cell
     * @param col is the col of the Cell
     * @return a Piece
     ******************************************************************/
    public Piece getPieceAt(int row, int col) {
        return board[row][col].getChessPiece();
    }
    
    /*******************************************************************
     * Finds the King of the specified color
     * 
     * @param color is the PColor of the King to find
     * @return the location of the King
     ******************************************************************/
    public int[] findKing(PColor color) {
        int[] coords = { -1, -1 };
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (getCellAt(row, col).getChessPiece() != null) {
                    if (getCellAt(row, col).getChessPiece().getName()
                            .equals("King")
                            && getCellAt(row, col).getChessPiece()
                                    .getColor() == color) {
                        coords[0] = row;
                        coords[1] = col;
                        return coords;
                    }
                }
            }
        }
        return coords;
    }
    
    /******************************************************************
    * Gets the boolean value to determine if there is a space
    * on the board that is available for en Passant movement
    * @return The boolean value currentPassantMove
    ******************************************************************/
    public boolean getCurrentPassantMove() {
        return currentPassantMove;
    }
    
    /******************************************************************
    * Toggles the boolean value for currentPassantMove to determine
    * if there is a space on the board that a pawn can move to for 
    * en Passant
    * @param val containing a boolean value for currentPassantMove
    ******************************************************************/
   
    public void setCurrentPassantMove(boolean val) {
        currentPassantMove = val;
    }
    
    /******************************************************************
     * Gets the boolean value to determine if there is a new space
     * on the board that is available for en Passant movement. 
      * @return The boolean value newPassantMove
     ******************************************************************/
     public boolean getnewPassantMove() {
         return newPassantMove;
     }
     
     /******************************************************************
      * Toggles the boolean value for newPassantMove to determine
      * if there is a new space on the board that a pawn can move to for 
      * en Passant
      * @param val containing a boolean value for newPassantMove
      ******************************************************************/
     public void setnewPassantMove(boolean val) {
         newPassantMove = val;
     }
    
    /******************************************************************
    * Gets the value of the current stored row for the space that is
    * available for a pawn to perform en Passant
    * @return Value containing the row 
    ******************************************************************/
    public int getcurrentPassantRow() {
        return currentPassantRow;
    }
    
    /******************************************************************
     * Gets the value of the current stored column for the space that is
     * available for a pawn to perform en Passant
     * @return Value containing the column
     *****************************************************************/
    public int getcurrentPassantCol() {
        return currentPassantCol;
    }
    
    /******************************************************************
     * Gets the value of the current stored row for the space that will
     * replace the currently stored row that is available for a pawn 
     * to perform en Passant
     * @return Value containing the row 
     *****************************************************************/
    public int getnewPassantRow() {
        return newPassantRow;
    }
    
    /******************************************************************
     * Gets the value of the current stored column for the space 
     * that will replace the currently stored row 
     * that is available for a pawn to perform en Passant
     * @return Value containing the column 
     *****************************************************************/
    public int getnewPassantCol() {
        return newPassantCol;
    }
    
    /******************************************************************
     * Sets the value for the currentPassantRow variable. Used to store
     * the row for a space that is currently available for a pawn
     * to perform en Passant
     * @param val that contains the row for currentPassantRow
    ******************************************************************/
     
    public void setcurrentPassantRow(int val) {
        currentPassantRow = val;
    }
    
    /******************************************************************
     * Sets the value for the currentPassantCol variable. Used to store
     * the column for a space that is currently available for a pawn
     * to perform en Passant
     * @param val that contains the column for currentPassantRow
    ******************************************************************/
    public void setcurrentPassantCol(int val) {
        currentPassantCol = val;
    }
    
    
    /******************************************************************
     * Sets the value for the newPassantRow variable. Used to store
     * the row for a space that is will be available for the next
     * player's turn to be able to use a pawn to perform en Passant
     * @param val that contains the row for newPassantRow
    ******************************************************************/
    public void setnewPassantRow(int val) {
        newPassantRow = val;
    }
    
    /******************************************************************
     * Sets the value for the newPassantCol variable. Used to store
     * the column for a space that is will be available for the next
     * player's turn to be able to use a pawn to perform en Passant
     * @param val that contains the row for newPassantCol
    ******************************************************************/
    
    public void setnewPassantCol(int val) {
        newPassantCol = val;
    }
    
    /******************************************************************
     * Resets the newPassant variables so they do not interfere with
     * the currentPassant variables
    ******************************************************************/
     
    public void resetnewPassantVal() {
        
        newPassantMove = false;
        newPassantRow = -1;
        newPassantCol = -1;
        
    }
    
    /******************************************************************
    * Sets the passantCapture variable to determine if a piece needs
    * to be removed from the board due to en Passant
    * @param val containing a boolean for passantCapture
    ******************************************************************/
    
    public void setpassantCapture(boolean val) {
        passantCapture = val;
    }
    
    /******************************************************************
    * Gets the passantCapture boolean value to deteminre if a piece
    * has been captured by an en Passant Movement
    * @return
    ******************************************************************/
  
    public boolean getpassantCapture() {
        return passantCapture;
    }
    
}
