package chess.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chess.controller.ChessController;
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
    /** Option to set the icon set */
	private JMenuItem iconSetItem;
    /** Font for the JButtons */
    private final Font FONT = new Font("Arial Unicode MS", Font.BOLD,
            24);
    /** Screen dimension */
    private Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
    /** Dimension for board to make square */
    private Dimension boardSize = new Dimension(screenSize.height / 2,
            screenSize.height / 2);
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
        int width = screenSize.height - (screenSize.height / 3);
        int xLoc = ((screenSize.width - 100) - screenSize.height) / 2; 
        // center
        setLocation(xLoc, 0);
        chess = new Chess();
        board = new JButton[8][8];
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit Game");
        iconSetItem = new JMenuItem("Select Icon Set");
        add(menuBar);
        menuBar.add(menu);
        menu.add(newItem);
        menu.add(exitItem);
        menu.add(iconSetItem);
        setJMenuBar(menuBar);
        
        grid = new JPanel(new GridLayout(8, 8));
        grid.setPreferredSize(boardSize);
        createButtons();
        resetBoard();
        addPanel();
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
    
    public void createButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
            	board[row][col] = new JButton(new ImageIcon());
                //board[row][col].setFont(FONT);
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
        // setTimer();
        resetButtons();
        setCheckers();
    }
    
    /*******************************************************************
     * Resets each button and puts the proper icon in place
     ******************************************************************/
    public void resetButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
            	if(chess.getPieceAt(row, col) != null) {
					setCellIcon(board[row][col], (ImageIcon)
							chess.getPieceAt(row, col).getImageIcon());
				} else {
					board[row][col].setIcon(new ImageIcon());
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
	 * Gets the icon set item to change the icon set
	 * 
	 * @return a JMenuItem of icon set
	 ******************************************************************/
	public JMenuItem getIconSetItem() {
		return iconSetItem;
	}
    
    /*******************************************************************
     * Adds the Controller to the JButtons
     * 
     * @param listener is the ActionListener to add
     ******************************************************************/
    public void addChessListener(ActionListener listener) {
        exitItem.addActionListener(listener);
        newItem.addActionListener(listener);
        iconSetItem.addActionListener(listener);
        
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
        menuPanel.setPreferredSize(
                new Dimension(boardSize.width / 3, boardSize.height));
        JLabel timeForP1 = new JLabel("P1's Time");
        timeForP1.setVerticalAlignment(SwingConstants.BOTTOM);
        JLabel timeForP2 = new JLabel("P2's Time");
        timeForP2.setVerticalAlignment(SwingConstants.TOP);
        p1Time = new JLabel("");
        p1Time.setHorizontalAlignment(SwingConstants.CENTER);
        p1Time.setVerticalAlignment(SwingConstants.BOTTOM);
        p2Time = new JLabel("");
        p2Time.setVerticalAlignment(SwingConstants.TOP);
        p2Time.setHorizontalAlignment(SwingConstants.CENTER);
        
        menuPanel.add(timeForP2);
        menuPanel.add(p2Time);
        menuPanel.add(timeForP1);
        menuPanel.add(p1Time);
        
        mainPanel.add(menuPanel);
    }
    
    public void UpdateTimer(String p1TimeUpdate, String p2TimeUpdate) {
        p1Time.setText(p1TimeUpdate);
        p2Time.setText(p2TimeUpdate);
        
    }
    
    /*******************************************************************
	 * Sets the icon to be displayed by a JButton
	 * 
	 * @param button is the the JButton to modify
	 * @param ico is the icon to set button to display
	 ******************************************************************/
	public void setCellIcon(JButton button, ImageIcon ico) {
		int h = (int) (boardSize.getHeight() / 8);
		int w = (int) (boardSize.getWidth() / 8);
		ImageIcon scaled = new ImageIcon(getScaledIcon(ico.getImage(), w, h));
		button.setIcon(scaled);
	}
	
	/*******************************************************************
	 * Returns a scaled image from the icon passed to it, scaled to the
	 * passed height and width
	 * 
	 * @param src is the icon from which the image will be scaled
	 * @param w is the width to scale the image to
	 * @param h is the height to scale the image to
	 * @return resize is the scaled image
	 ******************************************************************/
	private Image getScaledIcon( Image src, int w, int h) {
		BufferedImage resize = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resize.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(src, 0, 0, w, h, null);
		g.dispose();
		
		return resize;
	}
    
}
