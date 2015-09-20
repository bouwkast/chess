package chess.main;

import chess.controller.ChessController;
import chess.gui.ChessGUI;

public class ChessGame {

	public static void main(String[] args) {
		ChessGUI theView = new ChessGUI();
		
		Chess theModel = new Chess();
		
		ChessController theController = new ChessController(theView, theModel);
		
		theView.setVisible(true);
	}

}
