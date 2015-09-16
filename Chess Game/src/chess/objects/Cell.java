package chess.objects;

public class Cell {
	
	private Piece ChessPiece;
	
	/*A constructor for the Cell class
	 * @param N/A
	 * @return N/A */
	public Cell(){
		ChessPiece = null;
	}
	
	/*A method to set the Piece to a Cell
	 * @param Pieces with an appropriate piece type
	 * @return N/A */
	public void SetChessPiece(Piece piece) {
		// TODO Auto-generated method stub
		ChessPiece = piece;
	}

	/*A method to return the Piece that a Cell contains
	 * @param N/A
	 * @return ChessPiece*/
	public Piece getChessPiece(){
		return ChessPiece;
	}
	
	public String getPieceName() {
		return ChessPiece.getName();
	}



	


	

}
