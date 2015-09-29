package chess.objects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board {

	/** Is the 2D array that will contain the pieces */
	private Cell[][] ChessBoard;

	/*******************************************************************
	 * Constructor for the board class, going to be changing this one
	 * soon by removing the parameter. Just waiting to ensure that
	 * everything works.
	 ******************************************************************/
	public Board() {
		ChessBoard = new Cell[8][8];
		reset();
		setBoard();

	}
	
	/*******************************************************************
	 * Create a whole new board and initializes each Cell to a new Cell
	 ******************************************************************/
	public void reset() {
		ChessBoard = new Cell[8][8];

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				ChessBoard[x][y] = new Cell();
			}
		}
	}

	/*******************************************************************
	 * Sets the pawns on the board in the correct location and color
	 ******************************************************************/
	public void setPawns() {
		// Sets the pawns for the black side;
		for (int x = 0; x < 8; x++) {
			ChessBoard[1][x].SetChessPiece(new Pawn(PColor.Black, true));
		}
		// Sets the pawns for the white Side
		for (int y = 0; y < 8; y++) {
			ChessBoard[6][y].SetChessPiece(new Pawn(PColor.White, true));
		}
	}

	/**
	 * Sets the knights on the board
	 */
	public void setKnights() {
		// Sets the knights for the black side
		ChessBoard[0][1].SetChessPiece(new Knight(PColor.Black));
		ChessBoard[0][6].SetChessPiece(new Knight(PColor.Black));

		// Sets the knights for the white side
		ChessBoard[7][1].SetChessPiece(new Knight(PColor.White));
		ChessBoard[7][6].SetChessPiece(new Knight(PColor.White));
	}

	/**
	 * Sets the bishops on the board
	 */
	public void setBishops() {
		// Sets the bishops for the black side
		ChessBoard[0][2].SetChessPiece(new Bishop(PColor.Black));
		ChessBoard[0][5].SetChessPiece(new Bishop(PColor.Black));

		// Sets the bishops for the white side
		ChessBoard[7][2].SetChessPiece(new Bishop(PColor.White));
		ChessBoard[7][5].SetChessPiece(new Bishop(PColor.White));
	}

	/**
	 * Sets the Rooks on the board
	 */
	public void setRooks() {
		// Sets the rooks for the black side
		ChessBoard[0][0].SetChessPiece(new Rook(PColor.Black));
		ChessBoard[0][7].SetChessPiece(new Rook(PColor.Black));

		// Sets the rooks for the white side
		ChessBoard[7][0].SetChessPiece(new Rook(PColor.White));
		ChessBoard[7][7].SetChessPiece(new Rook(PColor.White));
	}

	/**
	 * Sets the Queens on the board
	 */
	public void setQueens() {
		// Sets the queen for the black side
		ChessBoard[0][3].SetChessPiece(new Queen(PColor.Black));

		// Sets the queen for the white side
		ChessBoard[7][3].SetChessPiece(new Queen(PColor.White));
	}

	/**
	 * Sets the Kings on the board
	 */
	public void setKings() {
		// Sets the king for the black side
		ChessBoard[0][4].SetChessPiece(new King(PColor.Black));

		// Sets the king for the white side
		ChessBoard[7][4].SetChessPiece(new King(PColor.White));
	}

	private void setBoard() {
		setKings();
		setQueens();
		setBishops();
		setKnights();
		setRooks();
		setPawns();
	}

	public Cell getCellAt(int row, int col) {
		return ChessBoard[row][col];
	}

	/***
	 * Prints the board in characters. Used to make sure the set methods
	 * for the board are working properly. Returns a string of the
	 * board*
	 * 
	 * @return String result containing the string representation of the
	 *         board
	 */
	public String printBoard() {
		String result = "";
		if (ChessBoard[1][0].getChessPiece() == null)
			result = "null";
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (ChessBoard[row][col].getChessPiece() == null) {
					result += "E ";
				} else {
					result +=
							ChessBoard[row][col].getPieceName() + " ";
				}
			}
			result += "\n";
		}

		return result;
	}
}
