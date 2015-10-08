package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
    
    /** If it is the white player's turn */
    private boolean whiteTurn;
    
    /** check to see if the white king is alive */
    private boolean whiteKingAlive = false;
    
    /** check to see if the black king is alive */
    private boolean blackKingAlive = false;
    
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
        
        this.gui.addChessListener(new ChessListener());
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
                        firstClick = false;
                    } else {
                        r2 = row;
                        c2 = col;
                        firstClick = true;
                    }
                }
            }
        }
    }
    
    /*******************************************************************
     * Method that checks to see if the game is over Then offers the
     * player a choice to restart or exit
     * 
     * @return an int value of the users
     ******************************************************************/
    public int CheckWin() {
        boolean WhiteKingAlive = false;
        boolean BlackKingAlive = false;
        String Options[] = new String[3];
        Options[0] = "New Game";
        Options[1] = "Quit";
        Options[2] = "Cancel";
        int choice = -1;
        int Winner = -1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (game.getPieceAt(x, y) != null) {
                    if (game.getPieceAt(x, y) instanceof King) {
                        if ((game.getPieceAt(x, y).getColor()
                                .equals(PColor.White))) {
                            WhiteKingAlive = true;
                        } else
                            BlackKingAlive = true;
                    }
                }
            }
            
        }
        
        if (!BlackKingAlive) {
            Winner = 0;
            choice = JOptionPane.showOptionDialog(gui,
                    "Game Over! " + "Player 1 Wins!", "Player 1 Wins",
                    0, JOptionPane.INFORMATION_MESSAGE, null, Options,
                    null);
            if (choice == 0) {
                game.reset();
                gui.resetBoard();
                gui.revalidate();
                gui.repaint();
                firstClick = true;
                whiteTurn = true;
                WhiteKingAlive = true;
                BlackKingAlive = true;
                
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        gui.getButtonAt(x, y).setEnabled(true);
                    }
                }
            } else if (choice == 1) {
                gui.dispose();
            } else if (choice == 2) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        gui.getButtonAt(x, y).setEnabled(false);
                    }
                }
            }
        } else if (!WhiteKingAlive) {
            Winner = 1;
            choice = JOptionPane.showOptionDialog(gui,
                    "Game Over! " + "Player 2 Wins!", "Player 2 Wins",
                    0, JOptionPane.INFORMATION_MESSAGE, null, Options,
                    null);
            if (choice == 0) {
                game.reset();
                gui.resetBoard();
                gui.revalidate();
                gui.repaint();
                firstClick = true;
                whiteTurn = true;
                WhiteKingAlive = true;
                BlackKingAlive = true;
                
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        gui.getButtonAt(x, y).setEnabled(true);
                    }
                }
            } else if (choice == 1) {
                gui.dispose();
            } else if (choice == 2) {
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        gui.getButtonAt(x, y).setEnabled(false);
                    }
                }
            }
            
        }
        
        return Winner;
    }
    
    /*******************************************************************
     * Inner class for the ActionListener
     ******************************************************************/
    class ChessListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            int Winner = -1;
            if (e.getSource() == gui.getNewItem()) {
                game.reset();
                gui.resetBoard();
                gui.revalidate();
                gui.repaint();
                
                firstClick = true;
                whiteTurn = true;
                whiteKingAlive = true;
                blackKingAlive = true;
                
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        gui.getButtonAt(x, y).setEnabled(true);
                    }
                }
                
            } else if (e.getSource() == gui.getExitItem()) {
                System.exit(0);
            }
            
            // Very basic method to find the button and piece and checks
            // if it can move there
            if (firstClick) {
                findCell(e);
                if (game.getPieceAt(r1, c1) != null) {
                    if (whiteTurn) {
                        if (!game.getPieceAt(r1, c1).getColor()
                                .equals(PColor.White)) {
                            JOptionPane.showMessageDialog(gui,
                                    "Invalid selection. It is player"
                                            + "1's turn.");
                            firstClick = true;
                        }
                    } else if (!whiteTurn) {
                        if (game.getPieceAt(r1, c1).getColor()
                                .equals(PColor.White)) {
                            JOptionPane.showMessageDialog(gui,
                                    "Invalid selection. It is player"
                                            + " 2's turn.");
                            firstClick = true;
                        }
                    }
                } else
                    firstClick = true;
            } else {
                System.out.println(
                        "Second Click and WhiteTurn is " + whiteTurn);
                findCell(e);
                // System.out.println(r2 + 1 + " " + (c2 + 1));
                if (game.checkMove(r1, c1, r2, c2,
                        game.getPieceAt(r1, c1))) {
                    whiteTurn = !whiteTurn;
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
    }
    
}