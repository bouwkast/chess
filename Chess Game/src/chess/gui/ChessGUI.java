package chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import chess.main.Chess;

public class ChessGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    /** Is the 2D array of JButtons to use for the board */
    private JButton[][] board;
    /** Is the panel that contains the JButtons */
    private JPanel grid;
    /** Is the instance of the chess game */
    private Chess chess;
    /** Menu Bar for the game */
    private JMenuBar menuBar;
    /** File menu */
    private JMenu menu;
    /** Option to start a new game */
    private JMenuItem newItem;
    /** Option to exit the game */
    private JMenuItem exitItem;
    /** Font for the JButtons */
    private final Font FONT = new Font("Arial Unicode MS", Font.BOLD,
            48);
    /** Screen dimension */
    private Dimension screenSize = Toolkit.getDefaultToolkit()
    .getScreenSize();
    /** Dimension for board to make square */
    private Dimension boardSize = new Dimension(screenSize.height / 2, screenSize.height / 2);
    private JPanel mainPanel;
    
    private JPanel menuPanel;
    
    private JLabel p1Time;
    private JLabel p2Time;
    
    
            
    /*******************************************************************
     * Constructor for the View
     ******************************************************************/
    public ChessGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Makes a square based on screen size, (x, y, size.x, size.y
        int x = 50;
        int width = screenSize.height - (screenSize.height/3);
//        setBounds(0, 0, width, boardSize.height);
//        setBounds(0, 0, screenSize.height - x, screenSize.height - x);
        int xLoc = ((screenSize.width - 100) - screenSize.height) / 2; // center
        setLocation(xLoc, 0);
        chess = new Chess();
        board = new JButton[8][8];
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit Game");
        add(menuBar);
        menuBar.add(menu);
        menu.add(newItem);
        menu.add(exitItem);
        setJMenuBar(menuBar);
        
        grid = new JPanel(new GridLayout(8, 8));
//        grid.setPreferredSize(screenSize);
//        grid.setBounds(0, 0, screenSize.height - x, screenSize.height - x);
        grid.setPreferredSize(boardSize);
//        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createButtons();
        resetBoard();
        addPanel();
        // this.add(grid);
        this.add(mainPanel);
        //setTimer();
        this.pack();
        this.setVisible(true);
        
        
    }
    
    /******************************************************************
     * Gets the board in the form of a 2D array of JButtons
     * 
     * @return a 2D array of JButton
     *****************************************************************/
    public JButton[][] getBoard() {
        return board;
    }
    
    public void createButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new JButton("");
            }
        }
    }
    
    /*******************************************************************
     * Gets the JButton at a specified location
     * 
     * @param row is the row of the JButton to get
     * @param col is the col of the JButton to get
     * @return a JButton at the specified location
     ******************************************************************/
    public JButton getButtonAt(int row, int col) {
        return board[row][col];
    }
    
    /*******************************************************************
     * Resets the Board that the user sees
     ******************************************************************/
    public void resetBoard() {
        grid.removeAll();
        //setTimer();
        resetButtons();
        setCheckers();
    }
    
    /*******************************************************************
     * Resets each button and puts the proper icon in place
     ******************************************************************/
    private void resetButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].setFont(FONT);
                if (chess.getPieceAt(row, col) != null) {
                    board[row][col].setText(
                            chess.getPieceAt(row, col).getIcon());
                } else {
                    board[row][col].setText("");
                }
                board[row][col].getModel().setPressed(false);
                board[row][col].setMargin(new Insets(0, 0, 0, 0));
                board[row][col].setBorderPainted(false);
                board[row][col].setFocusPainted(false);
                grid.add(board[row][col]);
            }
        }
    }
    
    /*******************************************************************
     * Creates the checkerboard pattern for the board
     ******************************************************************/
    private void setCheckers() {
        boolean isWhite = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (isWhite) {
                    board[row][col].setBackground(Color.WHITE);
                    if (col != 7) {
                        isWhite = false;
                    }
                } else {
                    board[row][col].setBackground(Color.GRAY);
                    if (col != 7) {
                        isWhite = true;
                    }
                }
            }
        }
    }
    
    /*******************************************************************
     * Gets the menu item to start a new game
     * 
     * @return a JMenuItem of new game
     ******************************************************************/
    public JMenuItem getNewItem() {
        return newItem;
    }
    
    /*******************************************************************
     * Gets the menu item to exit the game
     * 
     * @return a JMenuItem of exit game
     ******************************************************************/
    public JMenuItem getExitItem() {
        return exitItem;
    }
    
    /*******************************************************************
     * Adds the Controller to the JButtons
     * 
     * @param listener is the ActionListener to add
     ******************************************************************/
    public void addChessListener(ActionListener listener) {
        exitItem.addActionListener(listener);
        newItem.addActionListener(listener);
        
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                board[row][col].addActionListener(listener);
            }
        }
        
    }
    
    private void addPanel() {
        mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        mainPanel.add(grid);
        
        
        menuPanel = new JPanel(new GridLayout(2, 2, 0, 25));
        menuPanel.setPreferredSize(new Dimension(boardSize.width / 3, boardSize.height));
        JLabel timeForP1 = new JLabel("Player 1's Time");
        timeForP1.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel timeForP2 = new JLabel("Player 2's Time");
        timeForP2.setVerticalAlignment(SwingConstants.TOP);
        p1Time = new JLabel("10:00");
        p1Time.setHorizontalAlignment(SwingConstants.CENTER);
        p1Time.setVerticalAlignment(SwingConstants.BOTTOM);
        p2Time = new JLabel("10:00");
        p2Time.setVerticalAlignment(SwingConstants.TOP);
        p2Time.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        menuPanel.add(timeForP2);
        menuPanel.add(p2Time); 
        menuPanel.add(timeForP1);
        menuPanel.add(p1Time);
//        menuPanel = new JPanel(new GridBagLayout());
        
        
        
//        Container contentPane = this.getContentPane();
//        SpringLayout layout = new SpringLayout();
//        
//        contentPane.setLayout(layout);
//        contentPane.add(mainPanel);
        
        mainPanel.add(menuPanel);
        
//        JLabel timeForP1 = new JLabel("Player 1's Time");
//        JLabel timeForP2 = new JLabel("Player 2's Time");
        
//        p1Time = new JLabel("10:00");
//        p2Time = new JLabel("10:00");
        
//        contentPane.add(timeForP1);
//        contentPane.add(timeForP2);
//        
//        contentPane.add(p1Time);
//        contentPane.add(p2Time);
//        
//        layout.putConstraint(SpringLayout.WEST, timeForP2, 485,
//                SpringLayout.NORTH, contentPane);
//                
//        layout.putConstraint(SpringLayout.SOUTH, p2Time, 35,
//                SpringLayout.NORTH, timeForP2);
//                
//        layout.putConstraint(SpringLayout.WEST, p2Time, 515,
//                SpringLayout.NORTH, timeForP2);
//                
//        layout.putConstraint(SpringLayout.SOUTH, timeForP1, 540,
//                SpringLayout.NORTH, contentPane);
//                
//        layout.putConstraint(SpringLayout.WEST, timeForP1, 485,
//                SpringLayout.NORTH, contentPane);
//                
//        layout.putConstraint(SpringLayout.SOUTH, p1Time, 560,
//                SpringLayout.NORTH, contentPane);
//                
//        layout.putConstraint(SpringLayout.WEST, p1Time, 515,
//                SpringLayout.NORTH, contentPane);
                
    }
    
    public void UpdateTimer(String p1TimeUpdate, String p2TimeUpdate){
        p1Time.setText(p1TimeUpdate);
        p2Time.setText(p2TimeUpdate);
        
    }
    
//    private void setTimer() {
//        
//        TimeRemainingP1 = 600;
//        TimeRemainingP2 = 600;
//        countdownTimerP1 = new Timer(1000, new Listener());
//        countdownTimerP1.start();
//    }
//    
//    class Listener implements ActionListener {
//        
//        @Override
//        public void actionPerformed(ActionEvent arg0) {
//            // TODO Auto-generated method stub
//          
//            if (PlayerTimer) {
//                if (--TimeRemainingP1 > 0) {
//                    
//                    int minutes = TimeRemainingP1 / 60;
//                    int seconds = TimeRemainingP1 % 60;
//                    P1Time.setText(
//                            String.valueOf(minutes) + ":" + seconds);
//                            
//                } else {
//                    P1Time.setText("TIME'S UP!");
//                    
//                    
//                }
//            } else if (!PlayerTimer) {
//                if (--TimeRemainingP2 > 0) {
//                    
//                    int minutes = TimeRemainingP2 / 60;
//                    int seconds = TimeRemainingP2 % 60;
//                    P2Time.setText(
//                            String.valueOf(minutes) + ":" + seconds);
//                            
//                } else {
//                    P2Time.setText("TIME'S UP!");
//                    
//                    
//                }
//            }
//        }
//        
//    }
//    
//    public void TurnChange(boolean TurnPlayer) {
//    
//        PlayerTimer = TurnPlayer;
//    }
    
//    private void WinnerByTimeUp(boolean victory){
//        
//        int choice = -1;
//        String Options[] = new String[3];
//        Options[0] = "New Game";
//        Options[1] = "Quit";
//        Options[2] = "Cancel";
//        
//        if (victory){
//                choice = JOptionPane.showOptionDialog(this,
//                "Time Up! " + "Player 1 Wins!", "Player 1 Wins",
//                0, JOptionPane.INFORMATION_MESSAGE, null, Options,
//                null);
//        }
//        else{
//            choice = JOptionPane.showOptionDialog(this,
//                    "Game Over! " + "Player 2 Wins!", "Player 2 Wins",
//                    0, JOptionPane.INFORMATION_MESSAGE, null, Options,
//                    null);
//        }
//        
//    }  
}
