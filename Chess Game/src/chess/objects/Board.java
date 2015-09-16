package chess.objects;

public class Board {
	
	private Cell[][] ChessBoard;
	
	/*A constructor for the Board class
	 * @param N/A
	 * @return N/A */
	public Board(){
		ChessBoard = new Cell[8][8];
	}
	
	/*Resets the board and populates the 2D array with blank cells
	 * @param N/A
	 * @return N/A*/
	public void Reset(){
		ChessBoard = new Cell[8][8];
		
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				ChessBoard[x][y] = new Cell();
			}
		}
		
	}
	
	/*Set all the pawns on the board
	 * @param N/A
	 * @return N/A*/
	public void SetPawns(){
		
		Piece Pawns = new Piece();
		
		
		/*Sets the pawns for the first player*/
		for (int x = 0; x<8; x++){
			ChessBoard[6][x].SetChessPiece(Pawns);
			
		}
		
		/*Sets the pawns for the second player*/
		for (int y =0; y<8;y++){
			ChessBoard[1][y].SetChessPiece(Pawns);
		}
		
	}
	
	
	
	/*Set all the knights on the board
	 * @param N/A
	 * @return N/A*/
	public void SetKnights(){
		
		Piece Knights = new Piece();
		
		/*Set the Knights for the first player*/
		ChessBoard[7][1].SetChessPiece(Knights);
		ChessBoard[7][6].SetChessPiece(Knights);
		
		/*Set the Knights for the second player*/
		ChessBoard[0][1].SetChessPiece(Knights);
		ChessBoard[0][6].SetChessPiece(Knights);
	}
	
	/*Set all the bishops on the board
	 * @param N/A
	 * @return N/A*/
	public void SetBishops(){
		
		Piece Bishops = new Piece();
		
		/*Set the Bishops for the first player*/
		ChessBoard[7][2].SetChessPiece(Bishops);
		ChessBoard[7][5].SetChessPiece(Bishops);
		
		/*Set the Bishops for the second player*/
		ChessBoard[0][2].SetChessPiece(Bishops);
		ChessBoard[0][5].SetChessPiece(Bishops);
		
	}
	
	/*Set all the rooks on the board
	 * @param N/A
	 * @return N/A*/
	public void SetRook(){
		Piece Rooks = new Piece();
		
		/*Set the Rooks for the first player*/
		ChessBoard[7][0].SetChessPiece(Rooks);
		ChessBoard[7][7].SetChessPiece(Rooks);
		
		/*Set the Rooks for the second player*/
		ChessBoard[0][0].SetChessPiece(Rooks);
		ChessBoard[0][7].SetChessPiece(Rooks);
	}
	
	/*Set all the kings on the board
	 * @param N/A
	 * @return N/A*/
	public void SetKings(){
		Piece Kings = new Piece();
		
		/*Set the King for the first player*/
		ChessBoard[7][3].getClass();
		
		/*Set the King for the second player*/
		ChessBoard[0][3].SetChessPiece(Kings);
		
	}
	
	/*Set all the queens on the board
	 * @param N/A
	 * @return N/A*/
	public void SetQueens(){
		Piece Queens = new Piece();
		
		/*Set the Queen for the first player*/
		ChessBoard[7][4].SetChessPiece(Queens);
		
		/*Set the Queen for the second player*/
		ChessBoard[0][4].SetChessPiece(Queens);
		
	}
	
	
	/**
	 * Potentially new methods to set the board up upon creation.
	 * Based off of the new objects that are created and the current
	 * set methods.
	 */
	
	
	
	/**
	 * UNFINISHED - CURRENTLY IS NOT CALLED
	 * Just trying to set a row of each type Pawn on the board= "Hello";
	 */
	public void setPawns2(){
		
		Pawn pawnB = new Pawn(PColor.Black, true);
		Pawn pawnW = new Pawn(PColor.White, true);
		
		// Sets the pawns for the black side;
		for (int x = 0; x<8; x++){
			ChessBoard[1][x].SetChessPiece(pawnB);
			
		}
		
		
		// Sets the pawns for the white Side
		for (int y =0; y<8;y++){
			ChessBoard[6][y].SetChessPiece(pawnW);
		}
		
	}
	
	/**
	 * Sets the knights on the board
	 */
	public void setKnights2() {
		Knight kB = new Knight(PColor.Black);
		Knight kW = new Knight(PColor.White);
		
		// Sets the knights for the black side
		ChessBoard[0][1].SetChessPiece(kB);
		ChessBoard[0][6].SetChessPiece(kB);
		
		// Sets the knights for the white side
		ChessBoard[7][1].SetChessPiece(kW);
		ChessBoard[7][6].SetChessPiece(kW);

	}
	
	/**
	 * Sets the bishops on the board
	 */
	public void setBishops2() {
		Bishop bB = new Bishop(PColor.Black);
		Bishop bW = new Bishop(PColor.White);
		
		// Sets the bishops for the black side
		ChessBoard[0][2].SetChessPiece(bB);
		ChessBoard[0][5].SetChessPiece(bB);
		
		// Sets the bishops for the white side
		ChessBoard[7][2].SetChessPiece(bW);
		ChessBoard[7][5].SetChessPiece(bW);
	}
	
	/**
	 * Sets the Rooks on the board
	 */
	public void setRooks2() {
		Rook rB = new Rook(PColor.Black);
		Rook rW = new Rook(PColor.White);
		
		// Sets the rooks for the black side
		ChessBoard[0][0].SetChessPiece(rB);
		ChessBoard[0][7].SetChessPiece(rB);
		
		// Sets the rooks for the white side
		ChessBoard[7][0].SetChessPiece(rW);
		ChessBoard[7][7].SetChessPiece(rW);
	}
	
	/**
	 * Sets the Queens on the board
	 */
	public void setQueens2() {
		Queen qB = new Queen(PColor.Black);
		Queen qW = new Queen(PColor.White);
		
		// Sets the queen for the black side
		ChessBoard[0][3].SetChessPiece(qB);
		
		// Sets the queen for the white side
		ChessBoard[7][3].SetChessPiece(qW);
	}
	
	/**
	 * Sets the Kings on the board
	 */
	public void setKings2() {
		King kB = new King(PColor.Black);
		King kW = new King(PColor.White);
		
		// Sets the king for the black side
		ChessBoard[0][4].SetChessPiece(kB);
				
		// Sets the king for the white side
		ChessBoard[7][4].SetChessPiece(kB);
		
	}
	
	public String printBoard() {
		String result = "";
		if(ChessBoard[1][0].getChessPiece() == null)
			result = "null";
		result = ChessBoard[1][0].getPieceName();
//		for(int row = 0; row < 8; row++) {
//			for(int col = 0; col < 8; col++) {
//				if(ChessBoard[row][col].getChessPiece() == null) 
//					result += "E ";
//				result += ChessBoard[row][col].getPieceName() + " ";
//			}
//			result += "/n";
//		}
		
		return result;
	}
	


}
