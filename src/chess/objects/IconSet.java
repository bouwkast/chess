package chess.objects;

import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconSet implements java.io.Serializable {
	/** Set of icons with piece names as the key */
	private HashMap<String,Icon> iconSet;
	/** Default path for icons */
	private final static String defaultPath = "icons/";
	
	/*******************************************************************
	 * Constructor for IconSet that takes a string argument for the name
	 * of the set to be loaded
	 * 
	 * @param set is the name of the set to be loaded
	 ******************************************************************/
	public IconSet (String set) {
		iconSet = new HashMap<String, Icon>();
		setIconSet(set);
	}
	
	/*******************************************************************
	 * Setter for IconSet that takes a string argument for the name
	 * of the set to be loaded
	 * 
	 * @param set is the name of the set to be loaded
	 ******************************************************************/
	public void setIconSet (String set) {	
		iconSet.clear();
		
		iconSet.putIfAbsent("Pawn", new ImageIcon(this.getClass().getResource(defaultPath + set + "_p-0.png")));
		iconSet.putIfAbsent("Bishop", new ImageIcon(this.getClass().getResource(defaultPath + set + "_b-0.png")));
		iconSet.putIfAbsent("Rook", new ImageIcon(this.getClass().getResource(defaultPath + set + "_r-0.png")));
		iconSet.putIfAbsent("Knight", new ImageIcon(this.getClass().getResource(defaultPath + set + "_n-0.png")));
		iconSet.putIfAbsent("Queen", new ImageIcon(this.getClass().getResource(defaultPath + set + "_q-0.png")));
		iconSet.putIfAbsent("King", new ImageIcon(this.getClass().getResource(defaultPath + set + "_k-0.png")));
	}
	
	/*******************************************************************
	 * Getter for an individual icon from the icon set based on a key
	 * that is the name of a piece
	 * 
	 * @param key is the name of a piece
	 ******************************************************************/
	public Icon getIcon (String key) {
		return iconSet.get(key);
	}
}
