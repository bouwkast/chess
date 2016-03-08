package chess.gui;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT;
import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import chess.main.Chess;

public class ChessGUI extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    /** Is the 2D array of JButtons to use for the board */
    private JButton[][] board;
    /** Is the panel that contains the JButtons */
    private JPanel gridPanel;
    /** Is the instance of the chess game */
    private Chess chess;
    /** Menu Bar for the game */
    private JMenuBar menuBar;
    /** File menu */
    private JMenu menu;
    /** Menu to enable/disable AI */
    private JMenu enableMenu;
    /** Option to start a new game */
    private JMenuItem newItem;
    /** Option to exit the game */
    private JMenuItem exitItem;
    /** Option to set the icon set */
    private JMenuItem iconSetItem;
    /** Option to enable the AI */
    private JMenuItem enableItem;
    /** Option to undo previous move(s) */
    private JMenuItem undoItem;
    /** Option to save the game*/
    private JMenuItem saveItem;
    /** Option to load the game*/
    private JMenuItem loadItem;
    /** Font for the JButtons */
    private final Font FONT = new Font("Arial Unicode MS", Font.BOLD,
            24);
    /** Screen dimension */
    private Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
    private final int BHEIGHT = (screenSize.height - screenSize.height / 3);
    private final int BWIDTH = (screenSize.height - screenSize.height / 3);
    /** Dimension for board to make square */
    private Dimension boardSize = new Dimension((int)BHEIGHT, (int)BWIDTH);
    /** Main JPanel for the entire GUI */
    private JPanel mainPanel;
    /** GUI that has the timers in it */
    private TimerGUI timerGUI;
    /** Timer Menu */
    private JMenu timerMenu;
     /** Option to reset the Timer */
    private JMenuItem resetTimerItem;
    /** Option to change the time limit to Two Minutes */
    private JMenuItem twoMinutesItem;
    /** Option to change the time limit to Five Minutes */
    private JMenuItem fiveMinutesItem;
    /** Option to change the time limit to Ten Minutes */
    private JMenuItem tenMinutesItem;
    /** Option to enable/disable the timer */
    private JMenuItem enableOrDisableTimerItem;
    
    /** Panel to contain the list of moves made throughout the game */
    private JPanel historyPanel;
    
    /**List to display moves made throughout the game*/
    private JList ListBox;
    
    /**DESCRIPTION*/
    private DefaultListModel<String> listModel;
    
    /**Scroll pane to allow scrolling through the list of moves */
    private JScrollPane listScroll;
    
 // Determine what the default GraphicsDevice can support.
 	GraphicsEnvironment ge =
 	    GraphicsEnvironment.getLocalGraphicsEnvironment();
 	GraphicsDevice gd = ge.getDefaultScreenDevice();

 	boolean isUniformTranslucencySupported =
 	    gd.isWindowTranslucencySupported(TRANSLUCENT);
 	boolean isPerPixelTranslucencySupported =
 	    gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
 	boolean isShapedWindowSupported =
 	    gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT);
    
    
    
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
        JButton[][] highLightBoard = new JButton[8][8];
        chess = new Chess();
        board = new JButton[8][8];
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        timerMenu = new JMenu("Timer");
        newItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit Game");
        iconSetItem = new JMenuItem("Select Icon Set");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        enableItem = new JMenuItem("Enable AI");
        undoItem = new JMenuItem("Undo");
        enableMenu = new JMenu("AI");
        
        resetTimerItem = new JMenuItem("Reset");
        twoMinutesItem = new JMenuItem("2 Minutes");
        fiveMinutesItem = new JMenuItem("5 Minutes");
        tenMinutesItem = new JMenuItem("10 Minutes");
        enableOrDisableTimerItem = new JMenuItem("Disable Timer");

        
        timerGUI = new TimerGUI();
        
        mainPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(8, 8));
        gridPanel.setPreferredSize(boardSize);
        
        add(menuBar);
        menuBar.add(menu);
        menu.add(newItem);
        menu.add(exitItem);
        menu.add(iconSetItem);
        menu.add(undoItem);
        menu.add(saveItem);
        menu.add(loadItem);
        menuBar.add(timerMenu);
        timerMenu.add(resetTimerItem);
        timerMenu.add(twoMinutesItem);
        timerMenu.add(fiveMinutesItem);
        timerMenu.add(tenMinutesItem);
        timerMenu.add(enableOrDisableTimerItem);
        enableMenu.add(enableItem);
        menuBar.add(enableMenu);
        
        setJMenuBar(menuBar);

                
        mainPanel.add(timerGUI, BorderLayout.NORTH);
        mainPanel.add(gridPanel);
        
        
        addHistoryList();
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
                // board[row][col].setFont(FONT);
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
        listModel.clear();
    }
    
    /*******************************************************************
     * Resets each button and puts the proper icon in place
     ******************************************************************/
    public void resetButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chess.getPieceAt(row, col) != null) {
                    setCellIcon(board[row][col], (ImageIcon) chess
                            .getPieceAt(row, col).getImageIcon());
                } else {
                    board[row][col].setIcon(new ImageIcon());
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
     * Gets the icon set item to change the icon set
     * 
     * @return a JMenuItem of icon set
     ******************************************************************/
    public JMenuItem getIconSetItem() {
        return iconSetItem;
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
    
    public JMenuItem getRestTimerItem() {
        return resetTimerItem;
    }
    
    public JMenuItem getTwoMinutesItem() {
        return twoMinutesItem;
    }
    
    public JMenuItem getFiveMinutesItem() {
        return fiveMinutesItem;
    }
    
    public JMenuItem getTenMinutesItem() {
        return tenMinutesItem;
    }
    
    public JMenuItem getEnableOrDisableTimerItem() {
        return enableOrDisableTimerItem;
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
        saveItem.addActionListener(listener);
        loadItem.addActionListener(listener);
        
        enableItem.addActionListener(listener);
        undoItem.addActionListener(listener);
        resetTimerItem.addActionListener(listener);
        twoMinutesItem.addActionListener(listener);
        fiveMinutesItem.addActionListener(listener);
        tenMinutesItem.addActionListener(listener);
        enableOrDisableTimerItem.addActionListener(listener);

        
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                board[row][col].addActionListener(listener);
            }
        }
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
        ImageIcon scaled = new ImageIcon(
                getScaledIcon(ico.getImage(), w, h));
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
    private Image getScaledIcon(Image src, int w, int h) {
        BufferedImage resize = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resize.createGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        
        return resize;
    }
    
    /*******************************************************************
     * Gets the TimerGUI that holds both of the times for the players
     * 
     * @return the TimerGUI
     ******************************************************************/
    public TimerGUI getTimerGUI() {
        return timerGUI;
    }
    
    /*******************************************************************
     * Method to add the necessary components to the GUI to create
     * the history list of moves made throughout the game 
     * 
    *******************************************************************/
    
    public void addHistoryList(){
        
        historyPanel = new JPanel();
        listModel = new DefaultListModel();
        ListBox = new JList(listModel);
        listScroll = new JScrollPane(ListBox);
        
        ListBox.setPreferredSize(new Dimension(100, boardSize.height));
        ListBox.setSelectionMode(
                ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ListBox.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        ListBox.setVisibleRowCount(-1);
        ListBox.setVisible(true);
        
        listScroll = new JScrollPane(ListBox);
        listScroll.setPreferredSize(new Dimension(100, boardSize.height));
        
        historyPanel.add(listScroll);
        
        mainPanel.add(historyPanel,BorderLayout.WEST);
    }
    
   /********************************************************************
   * Method to update the history list of moves and display the 
   * appropriate Strings
   * 
   * @param historyList containing the Strings to display
  *********************************************************************/
  
  public void updateHistory(List<String> historyList){
      listModel.clear();
      for (int x = 0; x<historyList.size(); x++){
          listModel.addElement(historyList.get(x));
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
