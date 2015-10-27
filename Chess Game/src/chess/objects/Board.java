package chess.objects;

public class Board {

	/** Is the 2D array that will contain the pieces */
	private Cell[][] board;

	/*******************************************************************
	 * Constructor for the board class, going to be changing this one
	 * soon by removing the parameter. Just waiting to ensure that
	 * everything works.
	 ******************************************************************/
	public Board() {
		board = new Cell[8][8];
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
		int[] coords = {-1, -1};
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(getCellAt(row, col).getChessPiece() != null) {
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

	public int[] findWKing() {
		int[] coords = {-1, -1};
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(getCellAt(row, col).getChessPiece() != null) {
					if (getCellAt(row, col).getChessPiece().getName()
							.equals("King")
							&& getCellAt(row, col).getChessPiece()
									.getColor() == PColor.White) {
						coords[0] = row;
						coords[1] = col;
						return coords;
					}
				}
			}
		}
		return coords;
	}
	
	public int[] findBKing() {
		int[] coords = {-1, -1};
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(getCellAt(row, col).getChessPiece() != null) {
					if (getCellAt(row, col).getChessPiece().getName()
							.equals("King")
							&& getCellAt(row, col).getChessPiece()
									.getColor() == PColor.Black) {
						coords[0] = row;
						coords[1] = col;
						return coords;
					}
				}
			}
		}
		return coords;
	}
}
