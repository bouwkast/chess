package chess.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerGUI extends JPanel {
	private JPanel whiteTimerPanel;
	private JPanel blackTimerPanel;
	private final Font FONT = new Font("Arial Unicode MS", Font.BOLD,
            30);
	private JLabel whiteTime;
	private JLabel blackTime;
	private Dimension screenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
	private Dimension boardSize = new Dimension(screenSize.height - 150,
            screenSize.height - 150);
	private static final long serialVersionUID = 1L;
	public TimerGUI() {
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
//		layout.setHgap(0);
//		layout.setVgap(0);
//		BorderLayout layout = new BorderLayout(0, 0);
		
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(boardSize.width, 50));
        whiteTimerPanel = new JPanel();
        whiteTimerPanel.setBackground(Color.WHITE);
        whiteTimerPanel.setPreferredSize(new Dimension(this.getPreferredSize().width / 2, this.getPreferredSize().height));

        blackTimerPanel = new JPanel();
        blackTimerPanel.setBackground(Color.BLACK);
        blackTimerPanel.setPreferredSize(new Dimension(this.getPreferredSize().width / 2, this.getPreferredSize().height));

        JLabel timeForWhite = new JLabel("WHITE: ");
        timeForWhite.setFont(FONT);
        JLabel timeForBlack = new JLabel("BLACK: ");
        timeForBlack.setFont(FONT);
        timeForBlack.setForeground(Color.WHITE);
        

        whiteTime = new JLabel("5:00");
        whiteTime.setFont(FONT);

        blackTime = new JLabel("5:00");
        blackTime.setFont(FONT);
        
        blackTime.setForeground(Color.WHITE);

        whiteTimerPanel.add(timeForWhite);
        whiteTimerPanel.add(whiteTime);
        blackTimerPanel.add(timeForBlack);
        blackTimerPanel.add(blackTime);
        JPanel dummy = new JPanel();
        
        
        this.add(whiteTimerPanel, 0);
//        this.add(dummy, BorderLayout.CENTER);
        this.add(blackTimerPanel, 1);
//        this.setPreferredSize(new Dimension(800, 75));
	}
	
	public void updateTimer(String p1TimeUpdate, String p2TimeUpdate) {
        whiteTime.setText(p1TimeUpdate);
        blackTime.setText(p2TimeUpdate);
        
    }
}
