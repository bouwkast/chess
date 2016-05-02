package chess.gui;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT;
import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import java.awt.Insets;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import chess.main.Chess;

public class ChessGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Is the 2D array of JButtons to use for the board
     */
    private JButton[][] board;
    /**
     * Is the panel that contains the JButtons
     */
    private JPanel gridPanel;
    /**
     * Is the instance of the chess game
     */
    private Chess chess;
    /**
     * Menu Bar for the game
     */
    private JMenuBar menuBar;
    /**
     * File menu
     */
    private JMenu menu;
    /**
     * Menu to enable/disable AI
     */
    private JMenu enableMenu;
    /**
     * Option to start a new game
     */
    private JMenuItem newItem;
    /**
     * Option to exit the game
     */
    private JMenuItem exitItem;
    /**
     * Option to enable the AI
     */
    private JMenuItem enableItem;
    /**
     * Option to undo previous move(s)
     */
    private JMenuItem undoItem;
    /**
     * Option to save the game
     */
    private JMenuItem saveItem;
    /**
     * Option to load the game
     */
    private JMenuItem loadItem;
    /**
     * Font for the JButtons
     */
    private final Font FONT = new Font("Arial Unicode MS", Font.BOLD,
            24);
    /**
     * Screen dimension
     */
    private Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
    private final int BHEIGHT = (screenSize.height - screenSize.height / 3);
    private final int BWIDTH = (screenSize.height - screenSize.height / 3);
    /**
     * Dimension for board to make square
     */
    private Dimension boardSize = new Dimension((int) BHEIGHT, (int) BWIDTH);
    /**
     * Main JPanel for the entire GUI
     */
    private JPanel mainPanel;


    /*******************************************************************
     * Constructor for the View
     ******************************************************************/
    public ChessGUI() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        this.setLocation(screenSize.width - 2 * BWIDTH, 0);
        this.setLayout(layout);
        chess = new Chess();
        board = new JButton[8][8];
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit Game");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        enableItem = new JMenuItem("Enable AI");
        undoItem = new JMenuItem("Undo");
        enableMenu = new JMenu("AI");


        mainPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(8, 8));
        gridPanel.setPreferredSize(boardSize);

        add(menuBar);
        menuBar.add(menu);
        menu.add(newItem);
        menu.add(exitItem);
        menu.add(undoItem);
        menu.add(saveItem);
        menu.add(loadItem);
        enableMenu.add(enableItem);
        menuBar.add(enableMenu);

        setJMenuBar(menuBar);


        mainPanel.add(gridPanel);


        createButtons();
        resetBoard();

        this.add(mainPanel);
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

    /******************************************************************
     * Creates all of the buttons and creates an ImageIcon in each
     *****************************************************************/
    public void createButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new JButton(new ImageIcon());
                board[row][col].setFont(FONT);
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
        gridPanel.removeAll();
        resetButtons();
        setCheckers();
    }

    /*******************************************************************
     * Resets each button and puts the proper icon in place
     ******************************************************************/
    public void resetButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chess.getPieceAt(row, col) != null) {
                    board[row][col].setText(chess.getPieceAt(row, col).getIcon());
                } else {
                    board[row][col].setText("");
                }
                board[row][col].getModel().setPressed(false);
                board[row][col].setMargin(new Insets(0, 0, 0, 0));
                board[row][col].setBorderPainted(false);
                board[row][col].setFocusPainted(false);
                gridPanel.add(board[row][col]);
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
     * Gets the save item to save the current game
     *
     * @return a JMenuItem of icon set
     ******************************************************************/
    public JMenuItem getSaveItem() {
        return saveItem;
    }

    /*******************************************************************
     * Gets the load item to load a previously saved game
     *
     * @return a JMenuItem of icon set
     ******************************************************************/
    public JMenuItem getLoadItem() {
        return loadItem;
    }

    /*******************************************************************
     * Gets the JMenuItem that allows the user to enable the AI
     *
     * @return a JMenuItem to enable the AI
     ******************************************************************/
    public JMenuItem getEnableItem() {
        return enableItem;
    }

    /*******************************************************************
     * Gets the JMenuItem that allows the user to undo a move
     *
     * @return a JMenuItem to undo a previous move
     ******************************************************************/
    public JMenuItem getUndoItem() {
        return undoItem;
    }

    /*******************************************************************
     * Adds the Controller to the JButtons
     *
     * @param listener is the ActionListener to add
     ******************************************************************/
    public void addChessListener(ActionListener listener) {
        exitItem.addActionListener(listener);
        newItem.addActionListener(listener);
        saveItem.addActionListener(listener);
        loadItem.addActionListener(listener);

        enableItem.addActionListener(listener);
        undoItem.addActionListener(listener);


        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                board[row][col].addActionListener(listener);
            }
        }
    }


    /*******************************************************************
     * Gets the enableMenu to turn the AI on/off
     *
     * @return JMenu the enableMenu
     ******************************************************************/
    public JMenu getEnableMenu() {
        return enableMenu;
    }


}
