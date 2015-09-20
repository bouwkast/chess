package chess.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.main.Chess;

public class ChessGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton[][] board;
	private JPanel grid;
	private Chess chess;
	
	public ChessGUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		chess = new Chess();
		board = new JButton[8][8];
		
		grid = new JPanel(new GridLayout(8,8));
		for(int row = 0; row < 8; ++row) {
			for(int col = 0; col < 8; ++col) {
				board[row][col] = new JButton("");
				if(chess.getBoard().getCellAt(row, col).getChessPiece() != null) {
					board[row][col].setText(chess.getBoard().getCellAt(row, col).getPieceName());
				} 
				board[row][col].getModel().setPressed(true);
				/*For now just making the colors black/white, not doing a checkered pattern yet*/
				if(row == 0 || row == 1) {
					board[row][col].setBackground(Color.GRAY);
				} else if(row == 6 || row ==7) {
					board[row][col].setBackground(Color.WHITE);
				} else {
					board[row][col].setBackground(Color.LIGHT_GRAY);
				}
//				board[row][col].setContentAreaFilled(false);
				board[row][col].setMargin(new Insets(0,0,0,0));
				board[row][col].setBorderPainted(false);
				board[row][col].setFocusPainted(false);;
				grid.add(board[row][col]);
			}
		}
		this.add(grid);
		this.setVisible(true);
	}
	
	public JButton[][] getBoard() {
		return board;
	}
	
	public void addChessListener(ActionListener listener) {
		
		for(int row = 0; row < 8; ++row) {
			for(int col = 0; col < 8; ++col) {
				board[row][col].addActionListener(listener);
			}
		}

	}

}