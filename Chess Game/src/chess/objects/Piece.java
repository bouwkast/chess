package chess.objects;

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

}
