package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.gui.ChessGUI;
import chess.main.Chess;

public class ChessController {
	
	private ChessGUI gui;
	private Chess game;

	public ChessController(ChessGUI gui, Chess game) {
		this.gui = gui;
		this.game = game;
		
		this.gui.addChessListener(new ChessListener());
		// TODO Auto-generated constructor stub
	}
	
	class ChessListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// Simply going to search through the 2D array of buttons and
			// print out the location of them
			for(int row = 0; row < 8; ++row) {
				for(int col = 0; col < 8; ++col) {
					if(e.getSource() == gui.getBoard()[row][col]) {
						System.out.println("The Row is: " + (row + 1) + " and the Column is: " + (col + 1));
					}
				}
			}
			
		}
		
	}

}
