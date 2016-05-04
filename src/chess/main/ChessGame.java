package chess.main;

import chess.controller.ChessController;
import chess.gui.ChessGUI;


/*******************************************************************
 * Not to be confused with Chess.java (we may need to change names
 * around if it does get confusing)
 * 
 * This is the initializer class the sets up the model, the view, and
 * the controller. That is the only purpose it does and should serve.
 *
 ******************************************************************/
public class ChessGame {

	public static void main(String[] args) {
		ChessGUI theView = new ChessGUI();
		
		Chess theModel = new Chess();

//		ChessController theController =
//				new ChessController(theView, theModel);

		theView.setVisible(true);
	}

}
