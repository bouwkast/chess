package chess.objects;

public class Piece {
	protected PColor color;
	private boolean isAlive;
	protected String name;
	protected String icon;
	
	
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
	 * @return the unicode icon for the piece
	 */
	public String getIcon(){
		return icon;
	}
	

}
