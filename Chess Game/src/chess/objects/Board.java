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
	


}
