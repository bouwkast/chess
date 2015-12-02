package chess.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import chess.gui.ChessGUI;
import chess.gui.IconSetDialog;
import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.Castling_Move;
import chess.objects.En_Passant_Move;
import chess.objects.King;
import chess.objects.Knight;
import chess.objects.Move;
import chess.objects.PColor;
import chess.objects.Pawn;
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
    
    private Timer countdownTimer;
    // private Timer countdownTimerp2;
    
    /** Boolean to indicate which player's timer should go down */
    private boolean whitePlayerTimer;
    
    /** Boolean to determine whether the timer is enabled or disabled*/
    private boolean timerSwitch;
    
    /**Int to store the current time limit selected by the player*/
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
        whiteTurn = true;
        whitePlayerTimer = true;
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
            } else if (e.getSource() == gui.getIconSetItem()) {
                setIconSets();
            } else if (e.getSource() == gui.getEnableItem()) {
                startStopAI();
            } else if (e.getSource() == gui.getUndoItem()) {
 
                Move temp = game.getPreviousMove();
                
                if (temp instanceof Castling_Move) {
                    updateUndoMoveOnBoard(game.getPreviousMove());
                    whiteTurn = !whiteTurn;
                    turnChange(whiteTurn);
                }
                
                else if (aiEnabled) {
                    updateUndoMoveOnBoard(game.getPreviousMove());
                    updateUndoMoveOnBoard(game.getPreviousMove());
                } else {
                    updateUndoMoveOnBoard(game.getPreviousMove());
                    whiteTurn = !whiteTurn;
                    turnChange(whiteTurn);
                }
                
                updateHistory();
                gui.revalidate();
                gui.repaint();
            }
            
            else if (e.getSource() == gui.getRestTimerItem()){
                resetTimers(currentTimeLimit);
            }
            
            else if (e.getSource() == gui.getTwoMinutesItem()){
                currentTimeLimit = 120;
                resetTimers(currentTimeLimit);

            }
            
            else if (e.getSource() == gui.getFiveMinutesItem()){
                currentTimeLimit = 300;
                resetTimers(currentTimeLimit);
            }
            
            else if (e.getSource() == gui.getTenMinutesItem()){
                currentTimeLimit = 600;
                resetTimers(currentTimeLimit);

            }
            
            else if (e.getSource() == gui.getEnableTimerItem()){
                switchTimerOnOff(true);
                
            }
            
            else if (e.getSource() == gui.getDisableTimerItem()){
                switchTimerOnOff(false);
            }
            
            else if (firstClick) { // Stores pieces location or resets
                findCell(e);
                executeFirstClick();
                if (!firstClick){
                   highlightPossibleMoves(game.getPieceAt(r1, c1));
                }
            } else { // It is the second click of the Player's turn
                findCell(e);
                
                executeSecondClick();
                
//                 if (resetPassant == 1) {
//                 game.getBoard().resetPassant();
//                 resetPassant = 0;
//                 } else {
//                 resetPassant++;
//                 }
            }
            //unHighlightCells();
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
        whiteTurn = true;
        
        // By default, the white player's time goes down first
        whitePlayerTimer = true;
        resetTimers();
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                gui.getButtonAt(x, y).setEnabled(true);
            }
        }
    }
    
    /***************************************************************
     * Calls dialog box to select the icon sets for black and white
     * pieces
     **************************************************************/
    private void setIconSets() {
        String message = "Changing icon sets will start a new game. "
                + "Continue?";
        String options[] = new String[2];
        options[0] = "Continue";
        options[1] = "Cancel";
        
        int result = JOptionPane.showOptionDialog(gui, message,
                "Game Over!", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options,
                options[0]);
        if (result == 0) {
            IconSetDialog iconDialogWhite = new IconSetDialog();
            JDialog dialogWhite = iconDialogWhite.createDialog(null,
                    "Select White Icon Set");
            dialogWhite.setVisible(true);
            if (!iconDialogWhite.getSelected().equals("")) {
                Piece.setWhiteIconSet(iconDialogWhite.getSelected());
            }
            
            IconSetDialog iconDialogBlack = new IconSetDialog();
            JDialog dialogBlack = iconDialogBlack.createDialog(null,
                    "Select Black Icon Set");
            dialogBlack.setVisible(true);
            if (!iconDialogBlack.getSelected().equals("")) {
                Piece.setBlackIconSet(iconDialogBlack.getSelected());
            }
            
            startNewGame();
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
                
                if (game.checkPawnPromotion(r2, c2, first)) {
                    pawnPromotion(r1, c1, first);
                }
                
                else if (game.isEnPassCap()) {
                    updatePassantPieces(first);
                    game.setEnPassCap(false);
                } else {
                    game.movePieceTo(r1, c1, r2, c2, first);
                    updateMovedPieceButtons();
                }
                
              
                
            } else {
                // It is a valid move, tell the game to move the
                // piece
                game.movePieceTo(r1, c1, r2, c2, first);
                
               
            }
            
            if (!first.hasMoved()){
                first.setHasMoved(true);
            }
            
            whiteTurn = !whiteTurn;
            turnChange(whiteTurn);
            updateMovedPieceButtons();

            unHighlightCells();
            updateHistory();

            if (resetPassant == 1) {
              game.getBoard().resetPassant();
              resetPassant = 0;
              } else {
              resetPassant++;
             }
            
        }
    }
    
    /*******************************************************************
     * Executes the AI's turn
     * 
     * @param color is the PColor of the AI
     ******************************************************************/
    private void executeAITurn(PColor color) {
        Move move = game.getBestMove(color);
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
                if (game.isEnPassCap()) {
                    updatePassantPieces(first);
                    game.setEnPassCap(false);
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
            // // Set the appropriate Passant Information
            // game.getBoard().setcurrentPassantRow(
            // game.getBoard().getnewPassantRow());
            //
            // game.getBoard().setcurrentPassantCol(
            // game.getBoard().getnewPassantCol());
            //
            // game.getBoard().setCurrentPassantMove(
            // game.getBoard().getnewPassantMove());
            //
            // game.getBoard().resetnewPassantVal();
            
            whiteTurn = !whiteTurn;
            turnChange(whiteTurn);
            updateMovedPieceButtons();
            if (resetPassantB == 1) {
                game.getBoard().resetPassant();
                resetPassantB = 0;
                } else {
                resetPassantB++;
               }
            
            // // Checks to see if there was a Passant Capture
            // if (game.getBoard().getpassantCapture()) {
            // game.getBoard().setpassantCapture(false);
            //
            // int direction =
            // first.getColor() == PColor.White ? 1 : -1;
            // int PassRow = r2 + direction;
            // if(PassRow > -1) {
            // System.out.println(PassRow);
            // updatePassantPieces(PassRow, c2);
            // }
            //
            //
            // }
            if (game.isGameOver() != -1) {
                displayWinner(game.isGameOver());
            }
            // gui.updateButtons();
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
        game.executeCastle(r1, c1, r2, c2, (King) first);
        if (c1 < c2) {
            updateMovedPieceButtons();
            gui.getButtonAt(r2, c2 + 1).setIcon(new ImageIcon());
            gui.setCellIcon(gui.getButtonAt(r2, c2 - 1),
                    (ImageIcon) game.getPieceAt(r2, c2 - 1)
                            .getImageIcon());
        } else {
            updateMovedPieceButtons();
            gui.getButtonAt(r2, c2 - 2).setIcon(new ImageIcon());
            gui.setCellIcon(gui.getButtonAt(r2, c2 + 1),
                    (ImageIcon) game.getPieceAt(r2, c2 + 1)
                            .getImageIcon());
        }
    }
    
    /***************************************************************
     * Updates the two buttons' icons after moving a piece
     **************************************************************/
    private void updateMovedPieceButtons() {
        gui.getButtonAt(r1, c1).setIcon(new ImageIcon());
        if (game.getPieceAt(r2, c2) != null) {
            gui.setCellIcon(gui.getButtonAt(r2, c2),
                    (ImageIcon) game.getPieceAt(r2, c2).getImageIcon());
            gui.revalidate();
        }
    }
    
    /***************************************************************
     * Updates the two buttons' icons after moving a piece for the case
     * of a en Passant move
     **************************************************************/
    private void updatePassantPieces(Piece piece) {
        game.executeEnPassant(r1, c1, r2, c2, (Pawn) piece);
        gui.setCellIcon(gui.getButtonAt(r2, c2),
                (ImageIcon) game.getPieceAt(r2, c2).getImageIcon());
        gui.getButtonAt(r1, c2).setIcon(new ImageIcon());
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
    
    /***
     * ****************************************************************
     * A method to toggle which timer should go down. If
     * whitePlayerTimer is true, it is Player 1's turn and their timer
     * should go down. If whitePlayerTime is false, it is Player two's
     * turn and their timer should go down instead
     * 
     * @param TurnPlayer
     *****************************************************************            
     */
    public void turnChange(boolean TurnPlayer) {
        whitePlayerTimer = TurnPlayer;
    }
    
    // TODO comments and more concise.
    public void pawnPromotion(int row, int col, Piece piece) {
        
        // Gets the pawn that is going to be promoted
        Piece toPromote = game.getPieceAt(row, col);
        
        // Array of objects to be used by the Option Pane
        Object[] choices = { "Queen", "Bishop", "Knight", "Rook" };
        
        // Reads the user's input to determine what piece they want
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
    
    // TODO comments and refractoring of code to make more concise
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
            if (whitePlayerTimer && timerSwitch) {
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
            } else if (!whitePlayerTimer && timerSwitch) {
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
     * TODO needs some work for pluggability, different times etc.
     * MAY NOT BE NEEDED
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
    private void resetTimers(int time){
        timeRemainingP1 = time;
        timeRemainingP2 = time;
    }
    
    /*******************************************************************
     * Enables or disables the timer with the given boolean. True is
     * when the timer is enabled and false is when the timer is 
     * disabled
     * 
     * @param OnorOff a boolean value to enable or disable the timer
    ******************************************************************/
    
    private void switchTimerOnOff(boolean OnorOff){
        timerSwitch = OnorOff;
    }
    
    /******************************************************************
     * Method to update the board with the appropriate icons 
     * once a move is undone by a player
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
        
        //If move was a castling move, update accordingly
        if (moves instanceof Castling_Move) {
            
            Castling_Move castlingTemp = (Castling_Move) temp;
            
            r1 = castlingTemp.getR1();
            c1 = castlingTemp.getC1();
            r2 = castlingTemp.getR2();
            c2 = castlingTemp.getC2();
            
            int r1Rook = castlingTemp.getRowOfRook1();
            int c1Rook = castlingTemp.getColOfRook1();
            int r2Rook = castlingTemp.getRowOfRook2();
            int c2Rook = castlingTemp.getColOfRook2();
            
            gui.setCellIcon(gui.getButtonAt(r1, c1),
                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
                    
            Piece kingTemp = (King) game.getPieceAt(r1, c1);
            
            kingTemp.setHasMoved(false);
            
            Piece rookTemp = (Rook) game.getPieceAt(r1Rook, c1Rook);
            
            rookTemp.setHasMoved(false);
            
            gui.setCellIcon(gui.getButtonAt(r2, c2), new ImageIcon());
            
            gui.setCellIcon(gui.getButtonAt(r1Rook, c1Rook),
                    (ImageIcon) game.getPieceAt(r1Rook, c1Rook)
                            .getImageIcon());
                            
            gui.setCellIcon(gui.getButtonAt(r2Rook, c2Rook),
                    new ImageIcon());
            
            
        } 
        
        //If the move was an En Passant move, undo accordingly
        else if (moves instanceof En_Passant_Move) {
            En_Passant_Move passant = (En_Passant_Move) moves;
            
            r1 = passant.getR1();
            c1 = passant.getC1();
            r2 = passant.getR2();
            c2 = passant.getC2();
            
            gui.setCellIcon(gui.getButtonAt(r1, c1),
                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
            gui.setCellIcon(gui.getButtonAt(r2, c2),
                   new ImageIcon());
            gui.setCellIcon(gui.getButtonAt(r1, c2),
                    (ImageIcon) game.getPieceAt(r1, c2).getImageIcon());
        } 
        
        //If not castling or En Passant, update similar to regular move
        else {
            
            r1 = temp.getR1();
            c1 = temp.getC1();
            r2 = temp.getR2();
            c2 = temp.getC2();
            
            gui.setCellIcon(gui.getButtonAt(r1, c1),
                    (ImageIcon) game.getPieceAt(r1, c1).getImageIcon());
                    
            if (game.getPieceAt(r2, c2) != null)
                gui.setCellIcon(gui.getButtonAt(r2, c2),
                        (ImageIcon) game.getPieceAt(r2, c2)
                                .getImageIcon());
                                
            else
                gui.getButtonAt(r2, c2).setIcon(new ImageIcon());
        }
    }
   
    
    /******************************************************************
     * Method to highlight the spaces in which the selected piece can
     * move to
     * 
     * @param piece which contains the selected piece
    ******************************************************************/
    
    public void highlightPossibleMoves(Piece piece){
        List<Move> possibleMoves = game.generatePossibleMoves(r1, c1);
        Move temp;
        int row;
        int col;
        for (int x = 0; x<possibleMoves.size(); x++){
            temp = possibleMoves.get(x);
            row = temp.getR2();
            col = temp.getC2();
            gui.getBoard()[row][col].setBorderPainted(true);
            gui.getBoard()[row][col].setBorder(BORDER);      
        }
    }

    /******************************************************************
     * Method to "unhighlight" the spaces that were highlighted from 
     * the highlightPossibleMoves method
     * 
    ******************************************************************/
    public void unHighlightCells(){
        for (int x = 0; x<8; x++){
            for (int y = 0; y<8; y++){
                gui.getBoard()[x][y].setBorderPainted(false);
                
            }
        }
    }
    
    public void updateHistory(){
      List<String> temp = game.getHistoryArrayList();
      gui.updateHistory(temp);
    }
    
   
    
}