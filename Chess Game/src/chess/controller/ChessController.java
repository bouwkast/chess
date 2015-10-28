package chess.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chess.gui.ChessGUI;
import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.King;
import chess.objects.Knight;
import chess.objects.PColor;
import chess.objects.Piece;
import chess.objects.Queen;
import chess.objects.Rook;

public class ChessController {

	/** Is the ChessGUI to add to the Controller */
	private ChessGUI gui;

	/** Is the Chess to add to the controller */
	private Chess game;

	/** These are the row values for first and second click */
	private int r1 = -1, c1 = -1, r2 = -1, c2 = -1;

	/** Track whether it is the first click for that player's turn */
	private boolean firstClick;

	/** If it is the white player's turn */
	private boolean whiteTurn;

	/** Border for the selected piece */
	private final Border BORDER = new LineBorder(Color.BLUE, 2);

	private int timeRemainingP1;
	private int timeRemainingP2;

	private Timer countdownTimerP1;
	private Timer countdownTimerp2;

	private boolean playerTimer;

	/*******************************************************************
	 * Constructor for the Controller part of the MVC Takes in both the
	 * GUI and the game as parameters and adds the ChessListener onto
	 * the GUI.
	 * 
	 * The design works like so:
	 * 
	 * Chess.java and anything in chess.objects holds all logic
	 * 
	 * ChessController.java communicates between Chess and GUI
	 * 
	 * ChessGUI simply displays the view, the controller updates it
	 * 
	 * @param gui is the ChessGUI to add
	 * @param game is the Chess to add
	 ******************************************************************/
	public ChessController(ChessGUI gui, Chess game) {
		this.gui = gui;
		this.game = game;
		this.firstClick = true;
		whiteTurn = true;
		playerTimer = true;
		setTimers();

		this.gui.addChessListener(new ChessListener());
	}
	
	/*******************************************************************
	 * Inner class for the ActionListener
	 ******************************************************************/
	class ChessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int Winner = -1;
			if (e.getSource() == gui.getNewItem()) {
				startNewGame();
			} else if (e.getSource() == gui.getExitItem()) {
				System.exit(0);
			}

			if (firstClick) { // Stores pieces location or resets turn
				findCell(e);
				executeFirstClick();
			} else { // It is the second click of the Player's turn
				findCell(e);
				executeSecondClick();
			}
		}
	}

	/*******************************************************************
	 * Helper method to find the cell that is pressed
	 * 
	 * @param e is the ActionEvent that occurs
	 ******************************************************************/
	private void findCell(ActionEvent e) {
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (e.getSource() == gui.getBoard()[row][col]) {
					if (firstClick) {
						r1 = row;
						c1 = col;
						gui.getBoard()[row][col]
								.setBorderPainted(true);
						gui.getBoard()[row][col].setBorder(BORDER);
						firstClick = false;

					} else {
						r2 = row;
						c2 = col;
						gui.getBoard()[r1][c1].setBorderPainted(false);
						firstClick = true;

					}
				}
			}
		}
	}

//	/*******************************************************************
//	 * Method that checks to see if the game is over Then offers the
//	 * player a choice to restart or exit
//	 * 
//	 * @return an int value of the user who won
//	 ******************************************************************/
//	public int CheckWin() {
//		boolean WhiteKingAlive = false;
//		boolean BlackKingAlive = false;
//		String Options[] = new String[3];
//		Options[0] = "New Game";
//		Options[1] = "Quit";
//		Options[2] = "Cancel";
//		int choice = -1;
//		int Winner = -1;
//		for (int x = 0; x < 8; x++) {
//			for (int y = 0; y < 8; y++) {
//				if (game.getPieceAt(x, y) != null) {
//					if (game.getPieceAt(x, y) instanceof King) {
//						if ((game.getPieceAt(x, y).getColor()
//								.equals(PColor.White))) {
//							WhiteKingAlive = true;
//						} else
//							BlackKingAlive = true;
//					}
//				}
//			}
//		}
//
//		if (!BlackKingAlive) {
//			Winner = 0;
//			declareWinner(true);
//		} else if (!WhiteKingAlive) {
//			Winner = 1;
//			declareWinner(false);
//		}
//		return Winner;
//	}

	/***************************************************************
	 * Executes the second click of the Player's turn
	 **************************************************************/
	private void executeSecondClick() {
		Piece first = game.getPieceAt(r1, c1);
		if (game.checkMove(r1, c1, r2, c2, first)) {
			// Castling is a unique king move
			if (first instanceof King) {
				if (((King) first).checkCastling(r1, c1, r2, c2,
						(King) first, game)) {
					updateCastlePieces(first);
				} else {
					game.movePieceTo(r1, c1, r2, c2, first);
					updateMovedPieceButtons();
				}
			} else {
				// It is a valid move, tell the game to move the
				// piece
				game.movePieceTo(r1, c1, r2, c2, first);

				if (game.checkPawnPromotion(r2, c2, first)) {
					pawnPromotion(r2, c2, first);
				}
			}
			whiteTurn = !whiteTurn;
			TurnChange(whiteTurn);

			updateMovedPieceButtons();
			if (game.isGameOver() != -1) {
				winner(game.isGameOver());
			}
		}
	}

	/*******************************************************************
	 * Display who is the winner and ask to start a new game or to exit
	 * 
	 * @param player is the player who won (0 is black) (1 is white)
	 ******************************************************************/
	private void winner(int player) {
		String options[] = new String[2];
		options[0] = "New Game";
		options[1] = "Quit";
		String winner = player == 0 ? "Black" : "White";
		String message =
				"Congratulations to " + winner + " for winning!";
		int result = JOptionPane.showOptionDialog(gui, message,
				winner + " Wins!", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options,
				options[0]);
		if (result == 0) {
			startNewGame();
		} else {
			System.exit(0);
		}
	}

	/*******************************************************************
	 * Updates the buttons for the pieces that are castled
	 * 
	 * @param first is the first piece selected
	 ******************************************************************/
	private void updateCastlePieces(Piece first) {
		game.executeCastle(r1, c1, r2, c2, (King) first);
		if (c1 < c2) {
			updateMovedPieceButtons();
			Piece toCastle = game.getPieceAt(r2, c2 - 1);
			gui.getButtonAt(r2, c2 + 1).setText("");
			gui.getButtonAt(r2, c2 - 1).setText(toCastle.getIcon());
		} else {
			updateMovedPieceButtons();
			Piece toCastle = game.getPieceAt(r2, c2 + 1);
			gui.getButtonAt(r2, c2 - 2).setText("");
			gui.getButtonAt(r2, c2 + 1).setText(toCastle.getIcon());
		}
	}

	/***************************************************************
	 * Updates the two buttons' icons after moving a piece
	 **************************************************************/
	private void updateMovedPieceButtons() {
		gui.getButtonAt(r1, c1).setText("");
		if (game.getPieceAt(r2, c2) != null) {
			gui.getButtonAt(r2, c2)
					.setText(game.getPieceAt(r2, c2).getIcon());
			gui.revalidate();
		}
	}

	/***************************************************************
	 * Executes the first click for either Player's turn. This method
	 * checks whether the piece selected is valid for the Player and if
	 * so it will allow the Player to make a second click. If the click
	 * is invalid, it will restart the Player's turn.
	 **************************************************************/
	private void executeFirstClick() {
		if (game.getPieceAt(r1, c1) != null) {
			checkForCorrectColor();
		} else
			emptyCellSelected();
	}

	/***************************************************************
	 * Checks to make sure that the Player's first click is on one of
	 * their pieces.
	 **************************************************************/
	private void checkForCorrectColor() {
		Piece selected = game.getPieceAt(r1, c1);
		if (whiteTurn) {
			if (selected.getColor().equals(PColor.Black)) {
				incorrectColorPiece();
			}
		} else if (!whiteTurn) {
			if (selected.getColor().equals(PColor.White)) {
				incorrectColorPiece();
			}
		}
	}

	/***************************************************************
	 * Displays a message box informing the Player that they selected an
	 * empty cell and what color piece they should select for their
	 * first turn.
	 **************************************************************/
	private void emptyCellSelected() {
		String color = (whiteTurn) ? "white" : "black";
		String result = "You selected an empty square."
				+ " Please select a square with a " + color
				+ " piece in it for your first move.";
		JOptionPane.showMessageDialog(gui, result);
		gui.getBoard()[r1][c1].setBorderPainted(false);
		firstClick = true;
	}

	/***************************************************************
	 * Displays a message box informing the Player that they selected
	 * the wrong color piece and what color piece they should select for
	 * their first turn.
	 **************************************************************/
	private void incorrectColorPiece() {
		int turn = (whiteTurn) ? 1 : 2;
		String color = (whiteTurn) ? "white" : "black";
		String result = "It is Player " + turn + "'s turn."
				+ "\n Please select a " + color + " piece.";
		JOptionPane.showMessageDialog(gui, result);
		gui.getBoard()[r1][c1].setBorderPainted(false);
		firstClick = true;
	}

	/***************************************************************
	 * Starts a new game if the new game option is selected
	 **************************************************************/
	private void startNewGame() {
		game.reset(); // resets the board in Chess.java
		gui.resetBoard(); // resets the buttons
		gui.revalidate();
		gui.repaint();

		firstClick = true;
		whiteTurn = true;
		
		playerTimer = true; // TODO what does this mean???
		setTimers();

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				gui.getButtonAt(x, y).setEnabled(true);
			}
		}
	}
	
	public Piece getPieceAt(int row, int col) {
		return game.getPieceAt(row, col);
	}

	// TODO while it works, this method is confusing
	public void TurnChange(boolean TurnPlayer) {
		playerTimer = TurnPlayer;
	}

//	private void declareWinner(boolean victory) {
//
//		int choice = -1;
//		String Options[] = new String[3];
//		Options[0] = "New Game";
//		Options[1] = "Quit";
//		Options[2] = "Cancel";
//
//		if (victory) {
//			choice = JOptionPane.showOptionDialog(gui,
//					"Game Over" + "Player 1 Wins!", "Player 1 Wins", 0,
//					JOptionPane.INFORMATION_MESSAGE, null, Options,
//					null);
//		} else {
//			choice = JOptionPane.showOptionDialog(gui,
//					"Game Over" + "Player 2 Wins!", "Player 2 Wins", 0,
//					JOptionPane.INFORMATION_MESSAGE, null, Options,
//					null);
//		}
//
//		if (choice == 0) {
//			game.reset();
//			gui.resetBoard();
//			gui.revalidate();
//			gui.repaint();
//			firstClick = true;
//			whiteTurn = true;
//			whiteKingAlive = true;
//			blackKingAlive = true;
//			playerTimer = true;
//			whitePlayerWins = false;
//
//			for (int x = 0; x < 8; x++) {
//				for (int y = 0; y < 8; y++) {
//					gui.getButtonAt(x, y).setEnabled(true);
//				}
//			}
//		} else if (choice == 1) {
//			gui.dispose();
//		} else if (choice == 2) {
//			for (int x = 0; x < 8; x++) {
//				for (int y = 0; y < 8; y++) {
//					gui.getButtonAt(x, y).setEnabled(false);
//				}
//			}
//		}
//	}

	public void pawnPromotion(int row, int col, Piece piece) {
		Piece toPromote = game.getPieceAt(row, col);
		Object[] choices = { "Queen", "Bishop", "Knight", "Rook" };
		String input = (String) JOptionPane.showInputDialog(null,
				"Choose now...", "The Choice of a Lifetime",
				JOptionPane.QUESTION_MESSAGE, null, choices,
				choices[0]);

		if (input.equals("Queen") || input.equals(null)) {
			Queen promotedQueen = new Queen(toPromote.getColor());
			game.setPieceAt(row, col, promotedQueen);
		}

		else if (input.equals("Bishop")) {
			Bishop promotedBishop = new Bishop(toPromote.getColor());
			game.setPieceAt(row, col, promotedBishop);
		}

		else if (input.equals("Knight")) {
			Knight promotedKnight = new Knight(toPromote.getColor());
			game.setPieceAt(row, col, promotedKnight);
		} else if (input.equals("Rook")) {
			Rook promotedRook = new Rook(toPromote.getColor());
			game.setPieceAt(row, col, promotedRook);
		}
	}

	ActionListener timer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int p1minutes = timeRemainingP1 / 60;
			int p1seconds = timeRemainingP1 % 60;
			int p2minutes = timeRemainingP2 / 60;
			int p2seconds = timeRemainingP2 % 60;
			String p1Time = String.format("%d", p1minutes) + ":"
					+ String.format("%02d", p1seconds);

			String p2Time = String.format("%d", p2minutes) + ":"
					+ String.format("%02d", p2seconds);
			if (playerTimer) {
				if (--timeRemainingP1 > 0) {

					p1minutes = timeRemainingP1 / 60;
					p1seconds = timeRemainingP1 % 60;
					p1Time = String.format("%d", p1minutes) + ":"
							+ String.format("%02d", p1seconds);
					gui.UpdateTimer(p1Time, p2Time);

				} else {
					countdownTimerP1.stop();
					int winner = 0;
					winner(winner);
//					declareWinner(whitePlayerWins);

				}
			} else if (!playerTimer) {
				if (--timeRemainingP2 > 0) {

					p2minutes = timeRemainingP2 / 60;
					p2seconds = timeRemainingP2 % 60;
					p2Time = String.format("%d", p2minutes) + ":"
							+ String.format("%02d", p2seconds);
					gui.UpdateTimer(p1Time, p2Time);

				} else {
					countdownTimerP1.stop();
					int winner = 1;
					winner(winner);
//					declareWinner(whitePlayerWins);

				}
			}
		}
	};

	 
	
	private void setTimers() {
		timeRemainingP1 = 300;
		timeRemainingP2 = 300;
		countdownTimerP1 = new Timer(1000, timer);
		countdownTimerP1.start();
	}
}