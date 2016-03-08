package chess.main;

import chess.controller.ChessController;
import chess.gui.ChessGUI;
/*
Just a note, this addition is just to make this comment.
Since this was for a class and done as a group project and has since
 been turned in no further changes will be made to this repository.

(Steven) With that I feel like this project is not complete and that
there are many things that I want to expand on, refactor, and implement still,
so I'll be simply copying this repo, with all current commits/contributors and
further work on it.
*/

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

		ChessController theController =
				new ChessController(theView, theModel);

		theView.setVisible(true);
	}

}
