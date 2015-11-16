package GUI;

import java.awt.*;
import javax.swing.*;

import Enumerations.FarbEnum;
import Events.EventHandler;
import GameLogic.Spiel;

public class Board extends JPanel {
	private Spiel game = MainFrame.globalPointer.getGame();
	int gameboardSize = game.getGameboardSize();
	JButton[][] buttons = new JButton[gameboardSize][gameboardSize];
	globalPointer

	public Board() {
globalPoint
		int buttonSize = (int) ((double) 60 - ((gameboardSize - 8) * 2.5));
		Dimension dimension = new Dimension(buttonSize, buttonSize);
		this.setLayout(new GridLayout(gameboardSize + 1, gameboardSize + 1));

		// Colour of buttons
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				FieldButton button = new FieldButton(gameboardSize - i,gameboardSize - j);
				button.setPreferredSize(dimension);
				button.addActionListener(new EventHandler(). new eMoveFiguresBoard());
				buttons[gameboardSize - i][gameboardSize - j] = button;
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
	arraylist.remove point pressed
methode fieldPressed
zwei unterschiedliche koordinaten, dann move event
button state ändern!
}
