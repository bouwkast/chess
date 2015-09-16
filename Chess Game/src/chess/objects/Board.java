package chess.objects;

public class Board {
	
	private Cell[][] ChessBoard;
	
	/**
	 * A constructor for the Board class
	 */
	public Board(){
		ChessBoard = new Cell[8][8];
	}
	
	/**
	 * Resets the board and populates the 2D array with blank cells
	 */

	public void Reset(){
		ChessBoard = new Cell[8][8];
		
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				ChessBoard[x][y] = new Cell();
			}
		}
		
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
	
	/**
	 *Sets the pawns on board*
	 */
	public void setPawns(){
		
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
	public void setKnights() {
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
	public void setBishops() {
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
	public void setRooks() {
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
	public void setQueens() {
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
	public void setKings() {
		King kB = new King(PColor.Black);
		King kW = new King(PColor.White);
		
		// Sets the king for the black side
		ChessBoard[0][4].SetChessPiece(kB);
				
		// Sets the king for the white side
		ChessBoard[7][4].SetChessPiece(kW);
		
	}
	
	/***
	 * Prints the board in characters. Used to make sure the set methods for the board
	 * are working properly. Returns a string of the board*
	 * @return String result containing the string representation of the board
	 */
	public String printBoard() {
		String result = "";
		if(ChessBoard[1][0].getChessPiece() == null)
			result = "null";
		//result = ChessBoard[1][0].getPieceName();
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if(ChessBoard[row][col].getChessPiece() == null) {
					result += "E ";
				}
		else {
				result += ChessBoard[row][col].getPieceName() + " ";
				}
			}
			result += "\n";
		}
		
		return result;
	}
	


}
