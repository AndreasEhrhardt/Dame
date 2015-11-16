package GUI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Enumerations.FarbEnum;
import Events.EventHandler;
import GameLogic.Spiel;

public class Board extends JPanel {
	private Spiel game = MainFrame.globalPointer.getGame();
	private int gameboardSize = game.getGameboardSize();
	private JButton[][] buttons = new JButton[gameboardSize][gameboardSize];
	private ArrayList<Point> pressedButton = new ArrayList<Point>();
	public static Board globalPointer = null;
	
	

	public Board() {
		// Set global poitner
		Board.globalPointer = this;

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


	public void fieldPressed(FieldButton button) {
		if(!pressedButton.contains(button)) {
			pressedButton.add(button.getButtonId());
		} 
		if(pressedButton.size() == 2) {
			//startMove(pressedButton[0], pressedButton[1]);
		}
		pressedButton.remove(0);
		pressedButton.remove(1);
	}
}
