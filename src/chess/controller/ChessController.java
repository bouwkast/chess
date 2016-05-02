package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.gui.ChessGUI;
import chess.main.Chess;

public class ChessController {

    /**
     * Is the ChessGUI to add to the Controller
     */
    private ChessGUI gui;
    /**
     * Is the Chess to add to the controller
     */
    private Chess game;

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
    }

    /*******************************************************************
     * Inner class for the ActionListener
     ******************************************************************/
    class ChessListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
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
    }


}