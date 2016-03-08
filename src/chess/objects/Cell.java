package chess.objects;

public class Cell implements java.io.Serializable{

	/** Is the Piece in the Cell */
	private Piece piece;
	/** Is whether the Cell is considered for En Passant */
	private boolean isPassant;

	/*******************************************************************
	 * Constructor for the Cell class, sets the piece to be null and
	 * sets isPassant to be false by default.
	 ******************************************************************/
	public Cell() {
		piece = null;
		isPassant = false;
	}

	/*******************************************************************
	 * Sets the Piece that is in the Cell, isPassant is set to false by
	 * default and only is true when a pawn moves past the cell due to
	 * its first move.
	 * 
	 * @param piece is the Piece to set
	 ******************************************************************/
	public void setChessPiece(Piece piece) {
		this.piece = piece;
	}

	/*******************************************************************
	 * Gets the Piece that is in the Cell
	 * 
	 * @return the Piece that is in the Cell
	 ******************************************************************/
	public Piece getChessPiece() {
		return piece;
	}

	/*******************************************************************
	 * Gets the actual name of the piece
	 * 
	 * @return a String value containing the name of the piece
	 ******************************************************************/
	public String getPieceName() {
		return piece.getName();
	}

	/*******************************************************************
	 * Gets the pieces unicode character to display the appropriate
	 * icon.
	 * 
	 * @return a string value containing the piece's unicode value
	 ******************************************************************/
	public String getPieceIcon() {
		return piece.getIcon();

	}

	/*******************************************************************
	 * Returns whether the cell is a passant square.
	 * 
	 * If it is a passant square an enemy pawn may move to that square
	 * and capture that pawn even though it isn't on the square.
	 * 
	 * @return the isPassant is whether the cell is a passant cell
	 ******************************************************************/
	public boolean isPassant() {
		return isPassant;
	}

	/*******************************************************************
	 * Sets whether the cell is a passant square.
	 *
	 * @param isPassant the isPassant to set
	 ******************************************************************/
	public void setPassant(boolean isPassant) {
		this.isPassant = isPassant;
	}

}
