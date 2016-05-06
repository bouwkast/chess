package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.gui.ChessGUI;
import chess.main.Chess;
import chess.objects.Cell;

public class ChessController {

    /**
     * Is the ChessGUI to add to the Controller
     */
    private ChessGUI gui;
    /**
     * Is the Chess to add to the controller
     */
    private Chess game;

    private Cell initial, targeted;

    private boolean firstClick;

    /*******************************************************************
     * Constructor for the Controller part of the MVC Takes in both the
     * GUI and the game as parameters and adds the ChessListener onto
     * the GUI.
     * <p>
     * The design works like so:
     * <p>
     * Chess.java and anything in chess.objects holds all logic
     * <p>
     * ChessController.java communicates between Chess and GUI
     * <p>
     * ChessGUI simply displays the view, the controller updates it
     *
     * @param gui  is the ChessGUI to add
     * @param game is the Chess to add
     ******************************************************************/
    public ChessController(ChessGUI gui, Chess game) {
        this.gui = gui;
        this.game = game;
        firstClick = true;
    }

    /*******************************************************************
     * Inner class for the ActionListener
     ******************************************************************/
    class ChessListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            findCell(e);
            sendOffToGame(initial, targeted);


        }
    }

    private void sendOffToGame(Cell initial, Cell targeted) {

    }

    /***************************************************************
     * Starts a new game if the new game option is selected
     **************************************************************/
    private void startNewGame() {

    }

    /*******************************************************************
     * Helper method to find the cell that is pressed
     *
     * @param e is the ActionEvent that occurs
     ******************************************************************/
    private void findCell(ActionEvent e) {

        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                if(gui.getButtonAt(row, col).equals(e)) {
                    if(firstClick)
                        initial = game.getBoard().getCellAt(row, col);
                    else
                        targeted = game.getBoard().getCellAt(row, col);
                }
            }
        }

    }


}