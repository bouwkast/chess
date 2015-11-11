package chess.objects;

import javax.swing.Icon;

import chess.main.Chess;

public class Piece {
    /** The color of the Piece in an Enum */
    protected PColor color;
    /** Whether the Piece is alive, used for Kings */
    private boolean isAlive;
    /** The name of the Piece */
    protected String name;
    /** The unicode character String for the Piece */
    protected String icon;
    /** Whether the Piece has moved yet */
    protected boolean hasMoved;
    /** The set of icons to use for black pieces */
	private static IconSet blackIconSet;
	/** The set of icons to use for white pieces */
	private static IconSet whiteIconSet;
    
    /*******************************************************************
     * Constructor for Piece with one parameter for the Piece's color
     * 
     * @param color is the PColor of the Piece
     ******************************************************************/
    public Piece(PColor color) {
        this.setColor(color);
        setAlive(true);
        hasMoved = false;
        name = "";
        icon = "";
        
        /** Prevents previously set icon sets from being reset */
        if(blackIconSet == null)
        	blackIconSet = new IconSet("black");
        if(whiteIconSet == null)
        	whiteIconSet = new IconSet("white");
    }
    
    /*******************************************************************
     * Second constructor that requires both color and whether it is
     * alive
     * 
     * @param color is the PColor of the Piece
     * @param isAlive is whether the Piece is alive
     ******************************************************************/
    public Piece(PColor color, boolean isAlive) {
        this.setColor(color);
        this.setAlive(isAlive);
        hasMoved = false;
        name = "";
        icon = "";
        
        /** Prevents previously set icon sets from being reset */
        if(blackIconSet == null)
        	blackIconSet = new IconSet("black");
        if(whiteIconSet == null)
        	whiteIconSet = new IconSet("white");
    }
    
    /*******************************************************************
     * Third constructor that takes three parameters for the Piece
     * 
     * @param color is the PColor of the Piece
     * @param isAlive is whether the Piece is alive
     * @param name is the name of the Piece
     ******************************************************************/
    public Piece(PColor color, boolean isAlive, String name) {
        this.setColor(color);
        this.setAlive(isAlive);
        this.setName(name);
        this.setHasMoved(false);
        icon = "";
        
        /** Prevents previously set icon sets from being reset */
        if(blackIconSet == null)
        	blackIconSet = new IconSet("black");
        if(whiteIconSet == null)
        	whiteIconSet = new IconSet("white");
    }
    
    /*******************************************************************
     * Fourth constructor for the Piece
     * 
     * @param color is the PColor of the Piece
     * @param isAlive is whether the Piece is alive
     * @param name is the name of the Piece
     * @param hasMoved is whether the Piece has moved yet
     ******************************************************************/
    public Piece(PColor color, boolean isAlive, String name,
            boolean hasMoved) {
        this.setColor(color);
        this.setAlive(isAlive);
        this.setName(name);
        this.setHasMoved(hasMoved);
        icon = "";
        
        /** Prevents previously set icon sets from being reset */
        if(blackIconSet == null)
        	blackIconSet = new IconSet("black");
        if(whiteIconSet == null)
        	whiteIconSet = new IconSet("white");
    }
    
    /*******************************************************************
     * Constructor that takes another Piece and copies its info to the
     * current Piece
     * 
     * @param otherPiece is the Piece to copy
     ******************************************************************/
    public Piece(Piece otherPiece) {
        this.color = otherPiece.color;
        this.isAlive = otherPiece.isAlive;
        this.name = otherPiece.name;
        this.hasMoved = otherPiece.hasMoved;
        this.icon = otherPiece.icon;
        blackIconSet = otherPiece.blackIconSet;
		whiteIconSet = otherPiece.whiteIconSet;
    }
    
    /*******************************************************************
     * Getter for whether the Piece is alive
     * 
     * @return a boolean value of the Piece's life status
     ******************************************************************/
    public boolean isAlive() {
        return isAlive;
    }
    
    /*******************************************************************
     * Setter for whether the Piece is alive or not
     * 
     * @param isAlive is the value to set
     ******************************************************************/
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    /*******************************************************************
     * Gets the PColor of the Piece
     * 
     * @return color a PColor Enum of the Pieces color
     ******************************************************************/
    public PColor getColor() {
        return color;
    }
    
    /*******************************************************************
     * Setter for the PColor of the Piece
     * 
     * @param color is the PColor to set
     ******************************************************************/
    public void setColor(PColor color) {
        this.color = color;
    }
    
    /*******************************************************************
     * Gets the name of the Piece, not it's icon
     * 
     * @return a String value of the Piece's name
     ******************************************************************/
    public String getName() {
        return name;
    }
    
    /*******************************************************************
     * Setter for the name of the Piece
     * 
     * @param name is the name to set
     ******************************************************************/
    public void setName(String name) {
        this.name = name;
    }
    
    /*******************************************************************
     * Gets the Unicode String Icon of the Piece
     * 
     * @return a String value containing the Piece's unicode icon
     ******************************************************************/
    public String getIcon() {
        return icon;
    }
    
    /*******************************************************************
     * Return whether the Piece has moved or not
     * 
     * @return a boolean value of if the Piece has moved
     ******************************************************************/
    public boolean isHasMoved() {
        return hasMoved;
    }
    
    /*******************************************************************
     * Setter for whether the Piece has moved or not
     * 
     * @param hasMoved is the boolean value to set
     ******************************************************************/
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    /*******************************************************************
     * Returns all the information about the piece as a string
     * 
     * @return a string containing all information about the piece
     ******************************************************************/
    public String toString() {
        return "Piece name: " + name + "\nPiece color: " + color.name()
                + "\nisAlive: " + isAlive + "\nhasMoved: " + hasMoved
                + "\nIcon: " + icon;
                
    }
    
    /*******************************************************************
	 * Gets the Image Icon that corresponds to the Piece
	 * 
	 * @return an Icon object corresponding to the piece's name and
	 * color
	 ******************************************************************/
	public Icon getImageIcon() {
		if ( color == PColor.Black)
			return blackIconSet.getIcon(name);
		else
			return whiteIconSet.getIcon(name);
	}
	
	/*******************************************************************
	 * Setter for the icon set for black pieces
	 * 
	 * @param set is the string name of the set
	 ******************************************************************/
	public static void setBlackIconSet ( String set ) {
		blackIconSet.setIconSet(set);
	}
	
	/*******************************************************************
	 * Setter for the icon set for white pieces
	 * 
	 * @param set is the string name of the set
	 ******************************************************************/
	public static void setWhiteIconSet ( String set ) {
		whiteIconSet.setIconSet(set);
	}
    
    public boolean checkMovement(int r1, int c1, int r2, int c2,
            Chess chess) {
        return false;
    }
    
}
