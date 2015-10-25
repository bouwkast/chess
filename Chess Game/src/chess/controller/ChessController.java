package chess.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import chess.gui.ChessGUI;
import chess.main.Chess;
import chess.objects.Bishop;
import chess.objects.King;
import chess.objects.Knight;
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
    
    /** check to see if the white king is alive */
    private boolean whiteKingAlive = false;
    
    /** check to see if the black king is alive */
    private boolean blackKingAlive = false;
    
    /**
     * A boolean value determining if the white player won or not if
     * false, it indicates the black player won instead
     */
    private boolean whitePlayerWins;
    
    private JLabel p1Time;
    private JLabel p2Time;
    
    private int timeRemainingP1;
    private int timeRemainingP2;
    
    private Timer countdownTimerP1;
    private Timer countdownTimerp2;
    
    private boolean playerTimer;
    
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
        playerTimer = true;
        setTimers();
        
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
     * @return an int value of the user who won
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
            declareWinner(true);
        } else if (!WhiteKingAlive) {
            Winner = 1;
            declareWinner(false);
            
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
                findCell(e);
                if (game.checkMove(r1, c1, r2, c2,
                        game.getPieceAt(r1, c1))) {
                    //System.out.println("Move is Valid");
                    game.movePieceTo(r1, c1, r2, c2,
                            game.getPieceAt(r1, c1));
                    if (game.getPieceAt(r2, c2) instanceof Pawn) {
                        if (r2 == 0 || r2 == 7) {
                            pawnPromotion(r2, c2,
                                    game.getPieceAt(r2, c2));
                        }
                        
                    }
                    
                    whiteTurn = !whiteTurn;
                    TurnChange(whiteTurn);
                    gui.getButtonAt(r1, c1).setText("");
                    if (game.getPieceAt(r2, c2) != null) {
                        gui.getButtonAt(r2, c2).setText(
                                game.getPieceAt(r2, c2).getIcon());
                        gui.revalidate();
                    }
                } else if (checkCastling(r1, c1, r2, c2)) {
                    castlingMove(r1, c1, r2, c2);
                    whiteTurn = !whiteTurn;
                    TurnChange(whiteTurn);
                    gui.revalidate();
                    gui.repaint();
                    System.out.println("Phase 11");
                    
                }
            }
            Winner = CheckWin();
        }
    }
    
    private void setTimers() {
        
        timeRemainingP1 = 600;
        timeRemainingP2 = 600;
        countdownTimerP1 = new Timer(1000, new Listener());
        countdownTimerP1.start();
        
    }
    
    class Listener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            int p1minutes = timeRemainingP1 / 60;
            int p1seconds = timeRemainingP1 % 60;
            int p2minutes = timeRemainingP2 / 60;
            int p2seconds = timeRemainingP2 % 60;
            String p1Time = String.format("%d", p1minutes) + ":"
                    + String.format("%02d", p1seconds);
                    
            String p2Time = String.format("%d", p2minutes) + ":"
                    + String.format("%02d", p2seconds);
            if (playerTimer) {
                if (--timeRemainingP1 > 0) {
                    
                    p1minutes = timeRemainingP1 / 60;
                    p1seconds = timeRemainingP1 % 60;
                    p1Time = String.format("%d", p1minutes) + ":"
                            + String.format("%02d", p1seconds);
                    gui.UpdateTimer(p1Time, p2Time);
                    
                } else {
                    whitePlayerWins = false;
                    declareWinner(whitePlayerWins);
                    
                }
            } else if (!playerTimer) {
                if (--timeRemainingP2 > 0) {
                    
                    p2minutes = timeRemainingP2 / 60;
                    p2seconds = timeRemainingP2 % 60;
                    p2Time = String.format("%d", p2minutes) + ":"
                            + String.format("%02d", p2seconds);
                    gui.UpdateTimer(p1Time, p2Time);
                    
                } else {
                    whitePlayerWins = true;
                    declareWinner(whitePlayerWins);
                    
                }
            }
        }
        
    }
    
    public void TurnChange(boolean TurnPlayer) {
        
        playerTimer = TurnPlayer;
    }
    
    private void declareWinner(boolean victory) {
        
        int choice = -1;
        String Options[] = new String[3];
        Options[0] = "New Game";
        Options[1] = "Quit";
        Options[2] = "Cancel";
        
        if (victory) {
            choice = JOptionPane.showOptionDialog(gui,
                    "Game Over" + "Player 1 Wins!", "Player 1 Wins", 0,
                    JOptionPane.INFORMATION_MESSAGE, null, Options,
                    null);
        } else {
            choice = JOptionPane.showOptionDialog(gui,
                    "Game Over" + "Player 2 Wins!", "Player 2 Wins", 0,
                    JOptionPane.INFORMATION_MESSAGE, null, Options,
                    null);
        }
        
        if (choice == 0) {
            game.reset();
            gui.resetBoard();
            gui.revalidate();
            gui.repaint();
            firstClick = true;
            whiteTurn = true;
            whiteKingAlive = true;
            blackKingAlive = true;
            playerTimer = true;
            whitePlayerWins = false;
            
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
    
    public void pawnPromotion(int row, int col, Piece piece) {
        
        Object[] choices = { "Queen", "Bishop", "Knight", "Rook" };
        String input = (String) JOptionPane.showInputDialog(null,
                "Choose now...", "The Choice of a Lifetime",
                JOptionPane.QUESTION_MESSAGE, null, choices,
                choices[0]);
                
        if (input.equals("Queen") || input.equals(null)) {
            Queen promotedQueen = new Queen(
                    game.getPieceAt(row, col).getColor());
            game.getBoard().getCellAt(row, col)
                    .setChessPiece(promotedQueen);
        }
        
        else if (input.equals("Bishop")) {
            Bishop promotedBishop = new Bishop(
                    game.getPieceAt(row, col).getColor());
            game.getBoard().getCellAt(row, col)
                    .setChessPiece(promotedBishop);
        }
        
        else if (input.equals("Knight")) {
            Knight promotedKnight = new Knight(
                    game.getPieceAt(row, col).getColor());
            game.getBoard().getCellAt(row, col)
                    .setChessPiece(promotedKnight);
        } else if (input.equals("Rook")) {
            Rook promotedRook = new Rook(
                    game.getPieceAt(row, col).getColor());
            game.getBoard().getCellAt(row, col)
                    .setChessPiece(promotedRook);
        }
        
    }
    
    /***
     * ****************************************************************
     * This method checks to see if the castling move, that is requested
     * by a user, is possible
     * 
     * @param r1 Row of the first piece that was clicked
     * @param c1 Column of the first piece that was clicked
     * @param r2 Row of the second piece that was clicked
     * @param c2 Column of the second piece that was clicked
     * @return True if castling can be done, false otherwise
     *****************************************************************         
     */
    
    public boolean checkCastling(int r1, int c1, int r2, int c2) {
        boolean castling = true;
        boolean matchColors = true;
        boolean leftRook = false;
        int rowOfKing = -1;
        int colOfKing = -1;
        int rowOfRook = -1;
        int colOfRook = -1;
        
        // Checks to see if the two pieces are a King and a Rook
        if (!(game.getPieceAt(r1, c1) instanceof King
                || game.getPieceAt(r1, c1) instanceof Rook)) {
                
            System.out.println("Phase 1");
            castling = false;
            return castling;
        }
        
        if (!(game.getPieceAt(r2, c2) instanceof King
                || game.getPieceAt(r2, c2) instanceof Rook)) {
                
            System.out.println("Phase 2");
            castling = false;
            return castling;
        }
        
        // Determines which of the two pieces is the King and Rook
        if (game.getPieceAt(r1, c1) instanceof King) {
            rowOfKing = r1;
            colOfKing = c1;
            rowOfRook = r2;
            colOfRook = c2;
        } else {
            rowOfKing = r2;
            colOfKing = c2;
            rowOfRook = r1;
            colOfRook = c1;
        }
        
        // Checks to see if
        if (game.getPieceAt(rowOfKing, colOfKing).getColor().equals(
                game.getPieceAt(rowOfRook, colOfRook).getColor())) {
            matchColors = false;
        } else {
            System.out.println("Phase 3");
            castling = false;
            return castling;
        }
        
        if (!(rowOfKing == 0) || (rowOfKing == 7)) {
            if (colOfKing != 4) {
                System.out.println("Phase 4");
                castling = false;
                return castling;
            }
        }
        
        if (rowOfRook == 0 || rowOfRook == 7) {
            if (!(colOfRook == 0 || colOfRook == 7)) {
                System.out.println("Phase 5");
                castling = false;
                return castling;
            }
        }
        
        if (colOfRook == 0) {
            // System.out.println("Marvel's The Avengers");
            leftRook = true;
        }
        
        if (leftRook) {
            if (game.getPieceAt(rowOfKing, 1) != null) {
                System.out.println("Phasre 6");
                castling = false;
                return castling;
            }
            if (game.getPieceAt(rowOfKing, 2) != null) {
                System.out.println("Phase 7");
                castling = false;
                return castling;
            }
            
            if (game.getPieceAt(rowOfKing, 3) != null) {
                System.out.println("Phase 8");
                castling = false;
                return castling;
            }
        } else {
            if (game.getPieceAt(rowOfKing, 6) != null) {
                System.out.println("Phase 9");
                castling = false;
                return castling;
            }
            
            if (game.getPieceAt(rowOfKing, 5) != null) {
                System.out.println("Phase 10");
                castling = false;
                return castling;
            }
            
        }
        
        return castling;
        
    }
    
    public void castlingMove(int r1, int c1, int r2, int c2) {
        
        int rowOfKing = -1;
        int colOfKing = -1;
        int rowOfRook = -1;
        int colOfRook = -1;
        
        if (game.getPieceAt(r1, c1) instanceof King) {
            rowOfKing = r1;
            colOfKing = c1;
            rowOfRook = r2;
            colOfRook = c2;
        } else {
            rowOfKing = r2;
            colOfKing = c2;
            rowOfRook = r1;
            colOfRook = c1;
        }
        
        if (colOfRook == 0) {
            game.movePieceTo(rowOfKing, colOfKing, rowOfKing,
                    colOfKing - 2,
                    game.getPieceAt(rowOfKing, colOfKing));
                    
            game.movePieceTo(rowOfRook, colOfRook, rowOfRook,
                    colOfRook + 3,
                    game.getPieceAt(rowOfRook, colOfRook));
                    
            System.out.println(
                    "Pieces have been Castled from the leftmost Rook");
        } else if (colOfRook == 7) {
            game.movePieceTo(rowOfKing, colOfKing, rowOfKing,
                    colOfKing + 2,
                    game.getPieceAt(rowOfKing, colOfKing));
                    
            game.movePieceTo(rowOfRook, colOfRook, rowOfRook,
                    colOfRook - 2,
                    game.getPieceAt(rowOfRook, colOfRook));
                    
            System.out.println(
                    "Pieces have been Castled from the rightmost Rook");
        }
        
    }
}