package chess.objects;

public class Piece {
	protected PColor color;
	private boolean isAlive;
	protected String name;
	protected String icon;
	protected boolean hasMoved;
	
	
	int one = 1;
	
	public Piece(){
		one = 2;
	}
	
	/**
	 * First constructor with one parameter which is the pieces color
	 * 
	 * @param color
	 */
	public Piece(PColor color) {
		this.setColor(color);
		setAlive(true); // First creation of object, it will be alive
		hasMoved = false;
		name = "";
		icon = "";
	}
	
	
	 
	/**
	 * Second constructor that requires both color and parameter
	 *  
	 * @param color
	 * @param isAlive
	 */
	public Piece(PColor color, boolean isAlive) {
		this.setColor(color);
		this.setAlive(isAlive);
		hasMoved = false;
		name = "";
		icon = "";
	}
	
	/**
	 * Third constructor that has a name
	 * @param color
	 * @param isAlive
	 * @param name
	 */
	public Piece(PColor color, boolean isAlive, String name) {
		this.setColor(color);
		this.setAlive(isAlive);
		this.setName(name);
		this.setHasMoved(false);
		icon = "";
	}
	
	/**
	 * Fourth constructor that takes all parameters
	 * @param color
	 * @param isAlive
	 * @param name
	 * @param hasMoved
	 */
	public Piece(PColor color, boolean isAlive, String name, boolean hasMoved) {
		this.setColor(color);
		this.setAlive(isAlive);
		this.setName(name);
		this.setHasMoved(hasMoved);
		icon = "";
	}
	
	/**
	 * Fifth constructor that takes another piece
	 * @param otherPiece
	 */
	public Piece (Piece otherPiece) {
		this.color = otherPiece.color;
		this.isAlive = otherPiece.isAlive;
		this.name = otherPiece.name;
		this.hasMoved = otherPiece.hasMoved;
		this.icon = otherPiece.icon;
	}
	
	/**
	 * Getter for isAlive
	 * 
	 * @return a boolean value of whether the piece is alive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Setter for whether the piece is alive or not
	 * 
	 * @param isAlive
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Gets the color of the piece
	 * 
	 * @return the color
	 */
	public PColor getColor() {
		return color;
	}

	/**
	 * Sets the color of the piece
	 * 
	 * @param color the color to set
	 */
	public void setColor(PColor color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * @return the unicode icon for the piece (set in child classes)
	 */
	public String getIcon(){
		return icon;
	}
	
	/**
	 * Return whether the Piece has moved or not
	 * 
	 * @return the hasMoved
	 */
	public boolean isHasMoved() {
		return hasMoved;
	}

	/**
	 * Set whether the Piece has moved or not
	 * @param hasMoved the hasMoved to set
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	/*******************************************************************
	 * Returns all the information about the piece as a string
	 * 
	 * @return a string containing all information about the piece
	 ******************************************************************/
	public String toString() {
		return "Piece name: " + name
				+ "\nPiece color: " + color.name()
				+ "\nisAlive: " + isAlive
				+ "\nhasMoved: " + hasMoved
				+ "\nIcon: " + icon;
		
	}
	
}
