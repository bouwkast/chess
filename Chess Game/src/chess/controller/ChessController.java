package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.gui.ChessGUI;
import chess.main.Chess;
import chess.objects.King;
import chess.objects.PColor;

public class ChessController {

	/** Is the ChessGUI to add to the Controller */
	private ChessGUI gui;

	/** Is the Chess to add to the controller */
	private Chess game;

	/** These are the row values for first and second click */
	private int r1 = -1, c1 = -1, r2 = -1, c2 = -1;

	/** Track whether it is the first click for that player's turn */
	private boolean firstClick;
	

	private boolean WhiteTurn;

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
		WhiteTurn = true;

		this.gui.addChessListener(new ChessListener());
	}

	private void findCell(ActionEvent e) {
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (e.getSource() == gui.getBoard()[row][col]) {
					if (firstClick) {
						r1 = row;
						c1 = col;
						firstClick = false;
					} else {
						r2 = row;
						c2 = col;
						firstClick = true;
					}
					//System.out.println("The Row is: " + (row + 1)
							//+ " and the Column is: " + (col + 1));
				}
			}
		}
	}

	private int CheckWin(){
		boolean WhiteKingAlive = false;
		boolean BlackKingAlive = false;
		
		int Winner = -1;
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if (game.getPieceAt(x, y) != null){
					if (game.getPieceAt(x, y) instanceof King){
						if ((game.getPieceAt(x, y).getColor().equals(PColor.White))){
							WhiteKingAlive = true;
						}
						else
							BlackKingAlive = true;
					}
				}
			}
				
		}
		
		if (!BlackKingAlive){
			Winner = 0;
		}
		else if (!WhiteKingAlive){
			Winner = 1;
		}
		
		return Winner;
	}
	
	class ChessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int Winner = -1;
			if(e.getSource() == gui.getNewItem()) {
				game.reset();
				gui.resetBoard();
				gui.revalidate();
				gui.repaint();
				
			} else if(e.getSource() == gui.getExitItem()) {
				System.exit(0);
			}
			
			
				// Very basic method to find the button and piece and checks
				// if it can move there
				if (firstClick) {
					findCell(e);
					if(game.getPieceAt(r1, c1) != null){
						if (WhiteTurn){
							if (!game.getPieceAt(r1, c1).getColor().equals(PColor.White)){
								System.out.println("BLACK PIECE WAS CLICKED WHEN IT WAS SUPPOSED TO BE WHITE");
								firstClick = true;
						}
					}
						else if (!WhiteTurn){
							if (game.getPieceAt(r1, c1).getColor().equals(PColor.White)){
								System.out.println("WHITE PIECE WAS CLICKED WHEN IT WAS SUPPOSED TO BE BLACK");
								firstClick = true;
						}
					}
				}
					else
						firstClick = true;
			} else {
					System.out.println("Second Click and WhiteTurn is " + WhiteTurn);
					findCell(e);
					//System.out.println(r2 + 1 + " " + (c2 + 1));
					if (game.checkMove(r1, c1, r2, c2,
							game.getPieceAt(r1, c1))) {
						WhiteTurn = !WhiteTurn;
								gui.getButtonAt(r1, c1).setText("");
									if (game.getPieceAt(r2, c2) != null) {
										gui.getButtonAt(r2, c2).setText(
											game.getPieceAt(r2, c2).getIcon());
										gui.revalidate();
									}
				}
			}
			Winner = CheckWin();
			
		}
		//Insert Check King Here
		
		
	}
	
	
}