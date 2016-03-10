package chess.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chess.gui.ChessGUI;
import chess.main.Chess;
import chess.objects.*;
import chess.objects.EnPassantMove;

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
    private final Border HIGHLIGHT = new LineBorder(
            new Color(124, 252, 0), 2);
    /** Tracks the amount of time each player has left */
    private int timeRemainingP1;
    private int timeRemainingP2;
    /** DESCRIPTION */
    private Timer countdownTimer;
    /** Boolean to determine whether the timer is enabled or disabled */
    private boolean timerSwitch;
    /** Int to store the current time limit selected by the player */
    private int currentTimeLimit;
    /** Whether the AI is enabled or disabled */
    private boolean aiEnabled;
    /** A simple counter to tell when to reset the Passant fields */
    private int resetPassant, resetPassantB;
    
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
        whiteTurn = game.isWhiteTurn();
        setTimers();
        aiEnabled = false;
        this.gui.addChessListener(new ChessListener());
        resetPassant = 0;
        timerSwitch = true;
        currentTimeLimit = 300;
        resetPassantB = 0;
    }
    
    /*******************************************************************
     * Inner class for the ActionListener
     ******************************************************************/
    class ChessListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (e.getSource() == gui.getNewItem()) {
                startNewGame();
            } else if (e.getSource() == gui.getExitItem()) {
                System.exit(0);
            } else if (e.getSource() == gui.getSaveItem()) {
            	JFileChooser fc = new JFileChooser();
            	int response = fc.showSaveDialog(null);
            	if (response == JFileChooser.APPROVE_OPTION) {
            		String filename = fc.getSelectedFile().toString();
            		save(filename);
            	}
            } else if (e.getSource() == gui.getLoadItem()) {
            	JFileChooser fc = new JFileChooser();
            	int response = fc.showOpenDialog(null);
            	if (response == JFileChooser.APPROVE_OPTION) {
            		String filename = fc.getSelectedFile().toString();
            		load(filename);
            	}
            	
            	int r1Temp = r1;
            	int r2Temp = r2;
            	int c1Temp = c1;
            	int c2Temp = c2;
            	for ( int i = 0; i < 8; ++i ) {
            		for ( int j = 0; j < 8; ++j ) {
            			r1 = r2 = i;
            			c1 = c2 = j;
            			updateMovedPieceButtons();
            		}
            	}
            	r1 = r1Temp;
            	r2 = r2Temp;
            	c1 = c1Temp;
            	c2 = c2Temp;
            	updateHistory();
            	
            } else if (e.getSource() == gui.getEnableItem()) {
                startStopAI();
            } else if (e.getSource() == gui.getUndoItem()) {
                
                if (game.getMoves().size() > 0){
                    
                    Move temp = game.getPreviousMove();
                    if (temp instanceof CastlingMove){
                        updateUndoMoveOnBoard(game.getPreviousMove());
                        whiteTurn = game.changeTurn();

                    } else if (aiEnabled) {
                        updateUndoMoveOnBoard(game.getPreviousMove());
                        updateUndoMoveOnBoard(game.getPreviousMove());
                    } else {
                        updateUndoMoveOnBoard(game.getPreviousMove());
                        whiteTurn = game.changeTurn();
                    }
                
                    updateHistory();
                    gui.revalidate();
                    gui.repaint();
                }
            }     
            
            else if (e.getSource() == gui.getRestTimerItem()) {
                resetTimers(currentTimeLimit);
            }
            
            else if (e.getSource() == gui.getTwoMinutesItem()) {
                currentTimeLimit = 120;
                resetTimers(currentTimeLimit);
                
            }
            
            else if (e.getSource() == gui.getFiveMinutesItem()) {
                currentTimeLimit = 300;
                resetTimers(currentTimeLimit);
            }
            
            else if (e.getSource() == gui.getTenMinutesItem()) {
                currentTimeLimit = 600;
                resetTimers(currentTimeLimit);
                
            }
            
            else if (e.getSource() == gui
                    .getEnableOrDisableTimerItem()) {
                switchTimerOnOff();
                
            }
            
            else if (firstClick) { // Stores pieces location or resets
                findCell(e);
                executeFirstClick();
                if (!firstClick) {
                    highlightPossibleMoves(game.getPieceAt(r1, c1));
                }
            } else { // It is the second click of the Player's turn
                findCell(e);
                
                executeSecondClick();
                
            }
            // unHighlightCells();
            printPassant();
            if (!whiteTurn && aiEnabled) {
                executeAITurn(PColor.Black);
            }
            if (game.isGameOver() != -1) {
                displayWinner(game.isGameOver());
            }
        }
    }
    
    private void printPassant() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (game.getBoard().getCellAt(row, col).isPassant()) {
                    System.out.println("(" + row + ", " + col + ") ");
                }
            }
        }
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
        whiteTurn = game.isWhiteTurn(); // should be true at the start of the game
        resetTimers();
        
        resetPassant = 0;
        resetPassantB = 0;
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gui.getButtonAt(x, y).setEnabled(true);
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
                        gui.getBoard()[row][col].setBorderPainted(true);
                        gui.getBoard()[row][col].setBorder(BORDER);
                        firstClick = false;
                        
                    } else {
                        r2 = row;
                        c2 = col;
                        gui.getBoard()[r1][c1].setBorderPainted(false);
                        unHighlightCells();
                        firstClick = true;
                        
                    }
                }
            }
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
     * Executes the second click of the Player's turn
     **************************************************************/
    private void executeSecondClick() {
        Piece first = game.getPieceAt(r1, c1);
        
        if (game.checkMove(r1, c1, r2, c2, first)) {
            // Castling is a unique king move
            if (first instanceof King) {
                if (r1 == r2 && Math.abs(c1 - c2) == 2) {
                    updateCastlePieces(first);
                } else {
                    game.movePieceTo(r1, c1, r2, c2, first);
                    updateMovedPieceButtons();
                }
            } else if (first instanceof Pawn) {
                if (game.getPieceAt(r2, c2) == null && c1 != c2) {
                    updatePassantPieces(first);
                    game.getBoard().resetPassant();
                } else {
                    game.movePieceTo(r1, c1, r2, c2, first);
                    updateMovedPieceButtons();
                }
                if (game.checkPawnPromotion(r2, c2, first)) {
                    pawnPromotion(r1, c1, r2, c2, first);
                }
            } else {
                // It is a valid move, tell the game to move the piece
                game.movePieceTo(r1, c1, r2, c2, first);
            }
            if (resetPassant == 2) {
                game.getBoard().resetPassant();
                resetPassant = 0;
            } else {
                resetPassant++;
            }
            
            whiteTurn = game.changeTurn();
            updateMovedPieceButtons();
            
            unHighlightCells();
            updateHistory();
            
        }
    }
    
    /*******************************************************************
     * Executes the AI's turn
     * 
     * @param color is the PColor of the AI
     ******************************************************************/
    private void executeAITurn(PColor color) {
        Move move = game.getBestMove(color);
        if (move != null) {
            r1 = move.getR1();
            c1 = move.getC1();
            r2 = move.getR2();
            c2 = move.getC2();
            Piece first = game.getPieceAt(r1, c1);
            
            if (game.checkMove(r1, c1, r2, c2, first)) {
                // Castling is a unique king move
                if (first instanceof King) {
                    if (r1 == r2 && Math.abs(c1 - c2) == 2) {
                        updateCastlePieces(first);
                    } else {
                        game.movePieceTo(r1, c1, r2, c2, first);
                        updateMovedPieceButtons();
                    }
                } else if (first instanceof Pawn) {
                    if (game.checkPawnPromotion(r2, c2, first)) {
                        pawnPromotion(r1,c1,r2, c2, first);
                    } else if (game.getPieceAt(r2, c2) == null
                            && c1 != c2) {
                        updatePassantPieces(first);
                        game.setEnPassCap(false);
                        game.getBoard().resetPassant();
                    } else {
                        game.movePieceTo(r1, c1, r2, c2, first);
                        updateMovedPieceButtons();
                    }
                } else {
                    // It is a valid move, tell the game to move the
                    // piece
                    game.movePieceTo(r1, c1, r2, c2, first);
                }
                whiteTurn = game.changeTurn();
                updateMovedPieceButtons();
                if (resetPassantB == 1) {
                    game.getBoard().resetPassant();
                    resetPassantB = 0;
                } else {
                    resetPassantB++;
                }
                if (game.isGameOver() != -1) {
                    displayWinner(game.isGameOver());
                }
            }
        } else {
            displayWinner(game.isGameOver());
        }
    }
    
    /*******************************************************************
     * Display who is the winner and ask to start a new game or to exit
     * 
     * @param player is the player who won (0 is black) (1 is white)
     ******************************************************************/
    private void displayWinner(int player) {
        String options[] = new String[2];
        options[0] = "New Game";
        options[1] = "Quit";
        String winner = player == 0 ? "Black" : "White";
        String message = "Congratulations to " + winner
                + " for winning!";
        if (player == 2)
            message = "Stalemate. Nobody wins.";
        int result = JOptionPane.showOptionDialog(gui, message,
                "Game Over!", JOptionPane.YES_NO_OPTION,
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
        game.executeCastle(r1, c1, r2, c2, first);
        if (c1 < c2) {
            updateMovedPieceButtons();
            gui.getButtonAt(r2, c2 + 1).setText("");
            gui.getButtonAt(r2, c2 - 1).setText(game.getPieceAt(r2, c2 - 1).getIcon());
//            gui.getButtonAt(r2, c2 + 1).setIcon(new ImageIcon());
//            gui.setCellIcon(gui.getButtonAt(r2, c2 - 1),
//                    (ImageIcon) game.getPieceAt(r2, c2 - 1)
//                            .getImageIcon());
        } else {
            updateMovedPieceButtons();
            gui.getButtonAt(r2, c2 - 2).setText("");
            gui.getButtonAt(r2, c2 +1).setText(game.getPieceAt(r2, c2 + 1).getIcon());
//            gui.getButtonAt(r2, c2 - 2).setIcon(new ImageIcon());
//            gui.setCellIcon(gui.getButtonAt(r2, c2 + 1),
//                    (ImageIcon) game.getPieceAt(r2, c2 + 1)
//                            .getImageIcon());
        }
    }
    
    /***************************************************************
     * Updates the two buttons' icons after moving a piece
     **************************************************************/
    private void updateMovedPieceButtons() {
//        gui.getButtonAt(r1, c1).setIcon(new ImageIcon());
        gui.getButtonAt(r1, c1).setText("");
        if (game.getPieceAt(r2, c2) != null) {
            gui.getButtonAt(r2, c2).setText(game.getPieceAt(r2, c2).getIcon());
//            gui.setCellIcon(gui.getButtonAt(r2, c2),
//                    (ImageIcon) game.getPieceAt(r2, c2).getImageIcon());
            gui.revalidate();
        }
    }
    
    /***************************************************************
     * Updates the two buttons' icons after moving a piece for the case
     * of a en Passant move
     **************************************************************/
    private void updatePassantPieces(Piece piece) {
        game.executeEnPassant(r1, c1, r2, c2, (Pawn) piece);
        gui.getButtonAt(r2, c2).setText(game.getPieceAt(r2, c2).getIcon());
        gui.getButtonAt(r1, c1).setText("");
//        gui.setCellIcon(gui.getButtonAt(r2, c2),
//                (ImageIcon) game.getPieceAt(r2, c2).getImageIcon());
//        gui.getButtonAt(r1, c2).setIcon(new ImageIcon());
    }
    
    /***************************************************************
     * Checks to make sure that the Player's first click is on one of
     * their pieces.
     **************************************************************/
    private void checkForCorrectColor() {
        Piece selected = game.getPieceAt(r1, c1);
        if (whiteTurn) {
            if (selected.getColor().equals(PColor.Black)) {
                incorrectColorPiece(); // wrong color chosen
            }
        } else if (!whiteTurn) {
            if (selected.getColor().equals(PColor.White)) {
                incorrectColorPiece(); // wrong color chosen
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
        int turn = (whiteTurn) ? 1 : 2; // white is 1, black is 2
        String color = (whiteTurn) ? "white" : "black";
        String result = "It is Player " + turn + "'s turn."
                + "\n Please select a " + color + " piece.";
        JOptionPane.showMessageDialog(gui, result); // show error msg
        gui.getBoard()[r1][c1].setBorderPainted(false);
        firstClick = true;
    }
    
    /*******************************************************************
     * Enables or disables the AI, also sets the menu item's text to be
     * the appropriate option
     ******************************************************************/
    private void startStopAI() {
        if (!aiEnabled) {
            aiEnabled = true;
            gui.getEnableItem().setText("Disable AI");
        } else {
            aiEnabled = false;
            gui.getEnableItem().setText("Enable AI");
        }
    }
    
    /*******************************************************************
     * Simple getter method to get the piece at the specified location.
     * 
     * This is only done so you don't have to keep typing "game."
     * 
     * @param row is the row of the Piece to get
     * @param col is the col of the Piece to get
     * @return a Piece at the specified location
     ******************************************************************/
    public Piece getPieceAt(int row, int col) {
        return game.getPieceAt(row, col);
    }
    
    public void pawnPromotion(int row1, int col1, int row2, int col2,
            Piece piece) {
            
        // Gets the pawn that is going to be promoted
        Piece toPromote = game.getPieceAt(row1, col1);
        
        
        // Array of objects to be used by the Option Pane
        Object[] choices = { "Queen", "Bishop", "Knight", "Rook" };
        
        // Reads the user's input to determine what piece they want
        String input = (String) JOptionPane.showInputDialog(null,
                "Choose now...", "The Choice of a Lifetime",
                JOptionPane.QUESTION_MESSAGE, null, choices,
                choices[0]);
                
        if (input.equals("Queen") || input.equals(null)) {
            Queen promotedQueen = new Queen(piece.getColor());
            game.setPieceAt(row2, col2, promotedQueen);
            game.setPieceAt(row1, col1, null);
        }
        
        else if (input.equals("Bishop")) {
            Bishop promotedBishop = new Bishop(piece.getColor());
            game.setPieceAt(row2, col2, promotedBishop);
            game.setPieceAt(row1, col1, null);        }
        
        else if (input.equals("Knight")) {
            Knight promotedKnight = new Knight(piece.getColor());
            game.setPieceAt(row2, col2, promotedKnight);
            game.setPieceAt(row1, col1, null);            
        } else if (input.equals("Rook")) {
            Rook promotedRook = new Rook(piece.getColor());
            game.setPieceAt(row2, col2, promotedRook);
            game.setPieceAt(row1, col1, null);
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
            if (whiteTurn && timerSwitch) {
                if (--timeRemainingP1 > 0) {
                    
                    p1minutes = timeRemainingP1 / 60;
                    p1seconds = timeRemainingP1 % 60;
                    p1Time = String.format("%d", p1minutes) + ":"
                            + String.format("%02d", p1seconds);
                    gui.getTimerGUI().updateTimer(p1Time, p2Time);
                    
                } else {
                    countdownTimer.stop();
                    int winner = 0;
                    displayWinner(winner);
                    
                }
            } else if (!whiteTurn && timerSwitch) {
                if (--timeRemainingP2 > 0) {
                    
                    p2minutes = timeRemainingP2 / 60;
                    p2seconds = timeRemainingP2 % 60;
                    p2Time = String.format("%d", p2minutes) + ":"
                            + String.format("%02d", p2seconds);
                    gui.getTimerGUI().updateTimer(p1Time, p2Time);
                    
                } else {
                    countdownTimer.stop();
                    int winner = 1;
                    displayWinner(winner);
                }
            }
        }
    };
    
    /*******************************************************************
     * Sets some default values for the timers and their speeds.
     * 
     * TODO needs some work for pluggability, different times etc.
     ******************************************************************/
    private void setTimers() {
        timeRemainingP1 = 300;
        timeRemainingP2 = 300;
        countdownTimer = new Timer(1000, timer);
        countdownTimer.start();
    }
    
    /******************************************************************
     * Resets the timers to their initial values
     * 
     * 
     * 
     * TODO needs some work for pluggability, different times etc. MAY
     * NOT BE NEEDED
     *****************************************************************/
    private void resetTimers() {
        timeRemainingP1 = 300;
        timeRemainingP2 = 300;
    }
    
    /******************************************************************
     * Resets the timers to given amount
     * 
     * @param time which contains the amount of time to reset to
     ******************************************************************/
    private void resetTimers(int time) {
        timeRemainingP1 = time;
        timeRemainingP2 = time;
    }
    
    /*******************************************************************
     * Enables or disables the timer with the given boolean. True is
     * when the timer is enabled and false is when the timer is disabled
     *
     * What parameter???
//     * @param OnorOff a boolean value to enable or disable the timer
     ******************************************************************/
    
    private void switchTimerOnOff() {
        if (!timerSwitch){
            timerSwitch = true;
            gui.getEnableOrDisableTimerItem().setText("Disable Timer");
        }
        else {
        timerSwitch = false;
        gui.getEnableOrDisableTimerItem().setText("Enable Timer");
        }
    }
    
    /******************************************************************
     * Method to update the board with the appropriate icons once a move
     * is undone by a player
     * 
     * @param moves that contains a performed move from a player
     ******************************************************************/
    private void updateUndoMoveOnBoard(Move moves) {
        
        Move temp;
        int r1 = -1;
        int c1 = -1;
        int r2 = -1;
        int c2 = -1;
        
        temp = moves;
        game.unMakeMove();
        
        // If move was a castling move, update accordingly
        if (moves instanceof CastlingMove) {
            
            CastlingMove castlingTemp = (CastlingMove) temp;
            
            r1 = castlingTemp.getR1();
            c1 = castlingTemp.getC1();
            r2 = castlingTemp.getR2();
            c2 = castlingTemp.getC2();
            
            int r1Rook = castlingTemp.getRowOfRook1();
            int c1Rook = castlingTemp.getColOfRook1();
            int r2Rook = castlingTemp.getRowOfRook2();
            int c2Rook = castlingTemp.getColOfRook2();
//
            gui.getButtonAt(r1, c1).setText(game.getPieceAt(r1, c1).getIcon());
//            gui.setCellIcon(gui.getButtonAt(r1, c1),
//                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
                    
            Piece kingTemp = game.getPieceAt(r1, c1);
            
            kingTemp.setHasMoved(false);
            
            Piece rookTemp = game.getPieceAt(r1Rook, c1Rook);
            
            rookTemp.setHasMoved(false);
            
//            gui.setCellIcon(gui.getButtonAt(r2, c2), new ImageIcon());

            gui.getButtonAt(r1, c1).setText(game.getPieceAt(r2, c2).getIcon());
//            gui.setCellIcon(gui.getButtonAt(r1Rook, c1Rook),
//                    (ImageIcon) game.getPieceAt(r1Rook, c1Rook)
//                            .getImageIcon());
            gui.getButtonAt(r1Rook, c1Rook).setText(game.getPieceAt(r1Rook, c1Rook).getIcon());
                            
//            gui.setCellIcon(gui.getButtonAt(r2Rook, c2Rook),
//                    new ImageIcon());
            gui.getButtonAt(r2Rook, c2Rook).setText("");
                    
        }
        
        // If the move was an En Passant move, undo accordingly
        else if (moves instanceof EnPassantMove) {
            EnPassantMove passant = (EnPassantMove) moves;
            
            r1 = passant.getR1();
            c1 = passant.getC1();
            r2 = passant.getR2();
            c2 = passant.getC2();

            gui.getButtonAt(r1, c1).setText(game.getPieceAt(r1, c1).getIcon());
            gui.getButtonAt(r2, c2).setText("");
            gui.getButtonAt(r1, c2).setText(game.getPieceAt(r1, c2).getIcon());

//            gui.setCellIcon(gui.getButtonAt(r1, c1),
//                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
//            gui.setCellIcon(gui.getButtonAt(r2, c2), new ImageIcon());
//            gui.setCellIcon(gui.getButtonAt(r1, c2),
//                    (ImageIcon) game.getPieceAt(r1, c2).getImageIcon());
        }
        
        // If not castling or En Passant, update similar to regular move
        else {
            
            r1 = temp.getR1();
            c1 = temp.getC1();
            r2 = temp.getR2();
            c2 = temp.getC2();
            
//            gui.setCellIcon(gui.getButtonAt(r1, c1),
//                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
            gui.getButtonAt(r1, c1).setText(game.getPieceAt(r1, c1).getIcon());
                    
            if (game.getPieceAt(r2, c2) != null)
                    gui.getButtonAt(r2, c2).setText(game.getPieceAt(r2, c2).getIcon());
//                gui.setCellIcon(gui.getButtonAt(r2, c2),
//                        (ImageIcon) game.getPieceAt(r2, c2)
//                                .getImageIcon());

                                
            else
                gui.getButtonAt(r2, c2).setText("");
        }
    }
    
    /******************************************************************
     * Method to highlight the spaces in which the selected piece can
     * move to
     * 
     * @param piece which contains the selected piece
     ******************************************************************/
    
    public void highlightPossibleMoves(Piece piece) {
        List<Move> possibleMoves = game.generatePossibleMoves(r1, c1);
        Move temp;
        int row;
        int col;
        for (Move possibleMove : possibleMoves) {
            temp = possibleMove;
            row = temp.getR2();
            col = temp.getC2();
            gui.getBoard()[row][col].setBorderPainted(true);
            gui.getBoard()[row][col].setBorder(HIGHLIGHT);
        }
    }
    
    /******************************************************************
     * Method to "unhighlight" the spaces that were highlighted from the
     * highlightPossibleMoves method
     * 
     ******************************************************************/
    public void unHighlightCells() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gui.getBoard()[x][y].setBorderPainted(false);
                
            }
        }
    }
    
    /*******************************************************************
     * Method to obtain the array list of strings and then send them to
     * the GUI to print them on the list object
     * 
     *******************************************************************/
    
    public void updateHistory() {
        List<String> temp = game.getHistoryArrayList();
        gui.updateHistory(temp);
    }
    
    /*******************************************************************
     * Method to save the current game as a serialized file.
     * 
     * @param filename the path to save the file to
     *******************************************************************/
    private void save(String filename) {
    	try {
    		FileOutputStream fileOut = new FileOutputStream(filename);
    		ObjectOutputStream out = new ObjectOutputStream(fileOut);
    		
    		out.writeObject(game);
    		out.writeInt(r1);
    		out.writeInt(c1);
    		out.writeInt(r2);
    		out.writeInt(c2);
    		out.writeBoolean(firstClick);
    		out.writeBoolean(whiteTurn);
    		out.writeInt(timeRemainingP1);
    		out.writeInt(timeRemainingP2);
    		out.writeBoolean(timerSwitch);
    		out.writeInt(currentTimeLimit);
    		out.writeBoolean(aiEnabled);
    		out.writeInt(resetPassant);
    		out.writeInt(resetPassantB);
    		
    		out.close();
    		fileOut.close();
    	} catch(IOException i) {
    		i.printStackTrace();
    	}
    }
    
    /*******************************************************************
     * Method to load a game from a previously saved serialized file
     * 
     * @param filename the path to file to be loaded
     *******************************************************************/
    private void load(String filename) {
    	try {
    		FileInputStream fileIn = new FileInputStream(filename);
    		ObjectInputStream in = new ObjectInputStream(fileIn);
    		
    		game = (Chess) in.readObject();
    		r1 = in.readInt();
    		c1 = in.readInt();
    		r2 = in.readInt();
    		c2 = in.readInt();
    		firstClick = in.readBoolean();
    		whiteTurn = in.readBoolean();
    		timeRemainingP1 = in.readInt();
    		timeRemainingP2 = in.readInt();
    		timerSwitch = in.readBoolean();
    		currentTimeLimit = in.readInt();
    		aiEnabled = in.readBoolean();
    		resetPassant = in.readInt();
    		resetPassantB = in.readInt();
    		
    		in.close();
    	} catch(IOException i) {
            System.err.println("IOException");
    		i.printStackTrace();
    	} catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException");
            e.printStackTrace();
        }
    }
}