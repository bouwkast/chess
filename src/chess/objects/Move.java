package chess.objects;

public class Move implements java.io.Serializable {

	/** Locations of the selPiece and tarPiece */
	private int r1, c1, r2, c2;
	/** Piece that was selected */
	private Piece selPiece;
	/** Piece that is targeted, can be null */
	private Piece tarPiece;
	
	/*******************************************************************
	 * Move constructor to create a new move based on the selected row
	 * and column and the targeted row and column. Each move contains a
	 * copy of two new Pieces, different IDs, to be able to undo the
	 * move.
	 * 
	 * @param r1 is the selected row
	 * @param c1 is the selected col
	 * @param r2 is the targeted row
	 * @param c2 is the targeted col
	 * @param selPiece is the selected Piece
	 * @param tarPiece is the targeted Piece
	 ******************************************************************/
	public Move(int r1, int c1, int r2, int c2, Piece selPiece,
			Piece tarPiece) {
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
		this.selPiece = selPiece;
		this.tarPiece = tarPiece;
	}

	/*******************************************************************
	 * Simple copy method to create a deep copy of a pre-existing move
	 * with a new ID along with new IDs for both Pieces
	 * 
	 * @return the cloned Move
	 ******************************************************************/
	public Move clone() {
		Piece toClone1 = null, toClone2 = null;
		if(selPiece != null) {
			switch(selPiece.getName()) {
			case "Pawn": toClone1 = new Pawn((Pawn) selPiece);
			break;
			case "Knight": toClone1 = new Knight((Knight)selPiece);
			break;
			case "Bishop": toClone1 = new Bishop((Bishop)selPiece);
			break;
			case "Rook": toClone1 = new Rook((Rook)selPiece);
			break;
			case "Queen": toClone1 = new Queen((Queen)selPiece);
			break;
			case "King": toClone1 = new King((King)selPiece);
			break;
			}
		}
		if(tarPiece != null) {
			switch(tarPiece.getName()) {
			case "Pawn": toClone2 = new Pawn((Pawn)tarPiece);
			break;
			case "Knight": toClone2 = new Knight((Knight)tarPiece);
			break;
			case "Bishop": toClone2 = new Bishop((Bishop)tarPiece);
			break;
			case "Rook": toClone2 = new Rook((Rook)tarPiece);
			break;
			case "Queen": toClone2 = new Queen((Queen)tarPiece);
			break;
			case "King": toClone2 = new King((King)tarPiece);
			break;
			}
		} else {
			toClone2 = null;
		}
		return new Move(r1, c1, r2, c2, toClone1, toClone2);
	}

	/*******************************************************************
	 * Gets the selected row
	 * 
	 * @return the r1
	 ******************************************************************/
	public int getR1() {
		return r1;
	}

	/*******************************************************************
	 * Sets the selected row 
	 *
	 * @param r1 the r1 to set
	 ******************************************************************/
	public void setR1(int r1) {
		this.r1 = r1;
	}

	/*******************************************************************
	 * Gets the selected col
	 * 
	 * @return the c1
	 ******************************************************************/
	public int getC1() {
		return c1;
	}

	/*******************************************************************
	 * Sets the selected col 
	 *
	 * @param c1 the c1 to set
	 ******************************************************************/
	public void setC1(int c1) {
		this.c1 = c1;
	}

	/*******************************************************************
	 * Gets the targeted row
	 * 
	 * @return the r2
	 ******************************************************************/
	public int getR2() {
		return r2;
	}

	/*******************************************************************
	 * Sets the targeted row 
	 *
	 * @param r2 the r2 to set
	 ******************************************************************/
	public void setR2(int r2) {
		this.r2 = r2;
	}

	/*******************************************************************
	 * Gets the targeted col
	 * 
	 * @return the c2
	 ******************************************************************/
	public int getC2() {
		return c2;
	}

	/*******************************************************************
	 * Sets the targeted col 
	 *
	 * @param c2 the c2 to set
	 ******************************************************************/
	public void setC2(int c2) {
		this.c2 = c2;
	}

	/*******************************************************************
	 * Gets the selected Piece
	 * 
	 * @return the selPiece
	 ******************************************************************/
	public Piece getSelPiece() {
		return selPiece;
	}

	/*******************************************************************
	 * Sets the selected Piece 
	 *
	 * @param selPiece the selPiece to set
	 ******************************************************************/
	public void setSelPiece(Piece selPiece) {
		this.selPiece = selPiece;
	}

	/*******************************************************************
	 * Gets the targeted Piece
	 * 
	 * @return the tarPiece
	 ******************************************************************/
	public Piece getTarPiece() {
		return tarPiece;
	}

	/*******************************************************************
	 * Sets the targeted Piece 
	 *
	 * @param tarPiece the tarPiece to set
	 ******************************************************************/
	public void setTarPiece(Piece tarPiece) {
		this.tarPiece = tarPiece;
	}
	
}
