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

	private Cell[][] ChessBoard;
	// private JButton[][] BoardBackground;
	// private JFrame Frame;
	// private JPanel Grid;

	// /**
	// * A constructor for the Board class
	// */
	// public Board(){
	// ChessBoard = new Cell[8][8];
	// BoardBackground = new JButton[8][8];
	// Frame = new JFrame("Chess");
	// Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// Frame.setSize(800,800);
	// Frame.setVisible(true);
	//
	// }

	/*******************************************************************
	 * Constructor for the board class, going to be changing this one
	 * soon by removing the parameter. Just waiting to ensure that
	 * everything works.
	 * 
	 * @param i
	 ******************************************************************/
	public Board(int i) {
		ChessBoard = new Cell[8][8];
		reset();
		setBoard();

	}

	/**
	 * Resets the board and populates the 2D array with blank cells
	 */

	public void reset() {
		ChessBoard = new Cell[8][8];

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				ChessBoard[x][y] = new Cell();
			}
		}

	}

	/**
	 * Sets the pawns on board*
	 */
	public void setPawns() {

		Pawn pawnB = new Pawn(PColor.Black, true);
		Pawn pawnW = new Pawn(PColor.White, true);

		// Sets the pawns for the black side;
		for (int x = 0; x < 8; x++) {
			ChessBoard[1][x].SetChessPiece(pawnB);

		}

		// Sets the pawns for the white Side
		for (int y = 0; y < 8; y++) {
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

//	/**
//	 * Generates the buttons on the Frame CURRENTLY UNFINISHED
//	 */
//
//	public void GenerateBackground() {
//		Grid = new JPanel();
//		Grid.setLayout(new GridLayout(8, 8));
//		Grid.setPreferredSize(new Dimension(800, 800));
//		for (int row = 0; row < 8; row++) {
//			for (int col = 0; col < 8; col++) {
//				if (ChessBoard[row][col].getChessPiece() == (null)) {
//					BoardBackground[row][col] = new JButton("");
//					BoardBackground[row][col].getModel()
//							.setPressed(true);
//					BoardBackground[row][col]
//							.addActionListener(new ButtonListener());
//				} else {
//					BoardBackground[row][col] = new JButton(
//							ChessBoard[row][col].getPieceName());
//					BoardBackground[row][col].getModel()
//							.setPressed(true);
//					BoardBackground[row][col]
//							.addActionListener(new ButtonListener());
//
//				}
//				Grid.add(BoardBackground[row][col]);
//			}
//		}
//		Grid.setVisible(true);
//		Frame.add(Grid, BorderLayout.CENTER);
//		Frame.revalidate();
//	}
//
//	/**
//	 * A button Listener method as a placeholder. Allows the buttons to
//	 * perform certain actions. Currently it's used as a test to make
//	 * sure buttons are working properly*
//	 *
//	 */
//	private class ButtonListener implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent a) {
//			// TODO Auto-generated method stub
//			DepressButtons();
//			for (int row = 0; row < 8; row++) {
//				for (int col = 0; col < 8; col++) {
//					if (BoardBackground[row][col] == a.getSource()) {
//						System.out.println("The Row is: " + (row + 1)
//								+ " and the Column is: " + (col + 1));
//					}
//				}
//			}
//
//		}
//
//	}
//
//	/****
//	 * Sets all the buttons on the 2D Array to look like it's pressed.
//	 * There was an issue where once you pressed a button, it would
//	 * "reset" the button so that it looks like it can be pushed again.
//	 */
//	public void DepressButtons() {
//		for (int row = 0; row < 8; row++) {
//			for (int col = 0; col < 8; col++) {
//				BoardBackground[row][col].getModel().setPressed(true);
//			}
//		}
//	}
//
//	/***
//	 * A method to set colors to the buttons. UNFINISHED
//	 */
//	public void SetColorToButtons() {
//		boolean FlipColors = false;
//		for (int row = 0; row < 8; row++) {
//			for (int col = 0; col < 8; col++) {
//				if (!FlipColors) {
//					// BoardBackground[row][col].setBackground(Color.White);
//
//				}
//			}
//		}
//	}
}
