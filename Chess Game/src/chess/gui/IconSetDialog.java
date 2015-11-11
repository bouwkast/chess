package chess.gui;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class IconSetDialog extends JOptionPane implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	/** Base panel for dialog box */
	private JPanel dialogPanel;
	/** Buttons for each available icon set */
	private JButton white, black, clear, yellow, brown;
	/** The name of the currently selected icon set */
	private String selected;
	
	/*******************************************************************
	 * Constructor for the Icon Set Dialog Box
	 ******************************************************************/
	public IconSetDialog() {
		int height = 64;
		int width = 64;
		
		selected = "";
		
		dialogPanel = new JPanel();
		
		ImageIcon whiteIcon = new ImageIcon(this.getClass()
				.getResource("../objects/icons/white_k-0.png"));
		ImageIcon blackIcon = new ImageIcon(this.getClass()
				.getResource("../objects/icons/black_k-0.png"));
		ImageIcon clearIcon = new ImageIcon(this.getClass()
				.getResource("../objects/icons/clear_white_k-0.png"));
		ImageIcon yellowIcon = new ImageIcon(this.getClass()
				.getResource("../objects/icons/yellow_k-0.png"));
		ImageIcon brownIcon = new ImageIcon(this.getClass()
				.getResource("../objects/icons/brown_k-0.png"));
		
		white = new JButton(getScaledIcon(whiteIcon, height, width));
		black = new JButton(getScaledIcon(blackIcon, height, width));
		clear = new JButton(getScaledIcon(clearIcon, height, width));
		yellow = new JButton(getScaledIcon(yellowIcon, height, width));
		brown = new JButton(getScaledIcon(brownIcon, height, width));
		
		white.addActionListener(this);
		black.addActionListener(this);
		clear.addActionListener(this);
		yellow.addActionListener(this);
		brown.addActionListener(this);
		
		dialogPanel.setLayout(new GridLayout(1,5));
		dialogPanel.add(white);
		dialogPanel.add(black);
		dialogPanel.add(clear);
		dialogPanel.add(yellow);
		dialogPanel.add(brown);
		
		setMessage(dialogPanel);
		setOptionType(CANCEL_OPTION);
	}
	
	/*******************************************************************
	 * Getter for the name of the selected icon set
	 * 
	 * @return selected is the name of the selected icon set
	 ******************************************************************/
	public String getSelected() {
		return selected;
	}
	
	/*******************************************************************
	 * Returns a scaled ImageIcon from the icon passed to it, scaled to
	 * the passed height and width
	 * 
	 * @param src is the icon from which the image will be scaled
	 * @param w is the width to scale the image to
	 * @param h is the height to scale the image to
	 * @return resize is the scaled image
	 ******************************************************************/
	private ImageIcon getScaledIcon( ImageIcon src, int w, int h) {
		BufferedImage resize = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resize.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(src.getImage(), 0, 0, w, h, null);
		g.dispose();
		
		return new ImageIcon(resize);
	}
	
	/*******************************************************************
	 * Action listener for the buttons for each icon set
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object which = e.getSource();
		
		if(which == white){
			selected = "white";
		} else if(which == black){
			selected = "black";
		} else if(which == clear){
			selected = "clear_white";
		} else if(which == brown){
			selected = "brown";
		} else if(which == yellow){
			selected = "yellow";
		}
	}
}
