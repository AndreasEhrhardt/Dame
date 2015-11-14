package GUI;

import java.awt.*;
import javax.swing.*;

import GameLogic.Spiel;

public class Board extends JPanel {

	public Board() {				
		Spiel game = MainFrame.globalPointer.getGame();
		int gameboardSize = game.getGameboardSize();
		JButton[][] buttons = new JButton[gameboardSize][gameboardSize];
		int buttonSize = (int)((double)60 - ((gameboardSize - 8) * 2.5));
		Dimension dimension = new Dimension(buttonSize,buttonSize);
		this.setLayout(new GridLayout(gameboardSize + 1, gameboardSize + 1));
		
		// Colour of buttons
		for (int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				JButton button = new JButton();
				if ((j % 2 == 0 || !(i % 2 == 0)) && ((i % 2 == 0 || !(j % 2 == 0)))) {
					button.setBackground(Color.white);
					button.setEnabled(false);
				} else {
					button.setBackground(Color.gray);
				}
				button.setPreferredSize(dimension);
				buttons[i][j] = button;
			}
		}
		
		
		this.add(new JLabel(""));
		// Letters of board
		for (int k = 0; k < buttons.length; k++) {
			this.add(new JLabel(String.valueOf((char) (65 + (buttons.length - 1) - k))), SwingConstants.CENTER);
		}
		
		// Numbers of board
		for (int k = 0; k < buttons.length; k++) {
			for (int j = 0; j < buttons.length; j++) {
				if (j == 0) {
					this.add(new JLabel("" + (gameboardSize - k), SwingConstants.CENTER));
				}
				this.add(buttons[k][j]);
			}
		}
	}
}
