package chess.objects;

import javax.swing.Icon;

import chess.main.Chess;

public class Piece implements java.io.Serializable {
	/** The color of the Piece in an Enum */
	protected PColor color;
	/** The name of the Piece */
	protected String name;
	/** The unicode character String for the Piece */
	protected String icon;
	/** Whether the Piece has moved yet */
	protected boolean hasMoved;
	/** An arbitrary score for each Piece, used by AI */
	protected int score;

	/*******************************************************************
	 * Constructor for Piece with one parameter for the Piece's color
	 * 
	 * @param color is the PColor of the Piece
	 ******************************************************************/
	public Piece(PColor color) {
		this.setColor(color);
		hasMoved = false;
		name = "";
		icon = "";
		score = 0;
	}

	/*******************************************************************
	 * Third constructor that takes three parameters for the Piece
	 * 
	 * @param color is the PColor of the Piece
	 * @param name is the name of the Piece
	 ******************************************************************/
	public Piece(PColor color, String name) {
		this.setColor(color);
		this.setName(name);
		this.setHasMoved(false);
		icon = "";
		score = 0;
	}

	/*******************************************************************
	 * Fourth constructor for the Piece
	 * 
	 * @param color is the PColor of the Piece
	 * @param name is the name of the Piece
	 * @param hasMoved is whether the Piece has moved yet
	 ******************************************************************/
	public Piece(PColor color, String name,
			boolean hasMoved) {
		this.setColor(color);
		this.setName(name);
		this.setHasMoved(hasMoved);
		icon = "";
		score = 0;
		/** Prevents previously set icon sets from being reset */
	}

	/*******************************************************************
	 * Constructor that takes another Piece and copies its info to the
	 * current Piece
	 * 
	 * @param otherPiece is the Piece to copy
	 ******************************************************************/
	public Piece(Piece otherPiece) {
		this.color = otherPiece.color;
		this.name = otherPiece.name;
		this.hasMoved = otherPiece.hasMoved;
		this.icon = otherPiece.icon;
		this.score = otherPiece.score;
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
	public boolean hasMoved() {
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
				+ "\nhasMoved: " + hasMoved + "\nIcon: " + icon;

	}

	/*******************************************************************
	 * Gets the score of the Piece
	 * 
	 * @return the score
	 ******************************************************************/
	public int getScore() {
		return score;
	}

	/*******************************************************************
	 * Sets the score of the Piece 
	 *
	 * @param score the score to set
	 ******************************************************************/
	public void setScore(int score) {
		this.score = score;
	}

	public boolean checkMovement(int r1, int c1, int r2, int c2,
			Chess chess) {
		return false;
	}

}
