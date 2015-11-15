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
	Graphics playerOne;
	Graphics playerTwo;
	Graphics damePlayerOne;
	Graphics damePlayerTwo;

	public Board() {

		int buttonSize = (int) ((double) 60 - ((gameboardSize - 8) * 2.5));
		Dimension dimension = new Dimension(buttonSize, buttonSize);
		this.setLayout(new GridLayout(gameboardSize + 1, gameboardSize + 1));

		// Colour of buttons
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
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

	public void getImagesForFigures() {


	}

	public void addFigures() {
		Object[][] fields = new Object[gameboardSize][gameboardSize];
		boolean[][] isDame = new boolean[gameboardSize][gameboardSize];
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				// was wenn keine figur? NullPointerException?
				try{
				fields[i][j] = game.getGameboard().getField(i, j).getFigure().getColor();
				isDame[i][j] = game.getGameboard().getField(i, j).getFigure().isDame();
				} catch (NullPointerException e) {
					//was wenn keine figur?
				}
				// kann ich aufs farbEnum zugreifen?
				if (fields[i][j].equals(FarbEnum.schwarz) && isDame[i][j] == false) {
					buttons[i][j].paintComponents(playerTwo);
				} else if (fields[i][j].equals(FarbEnum.schwarz) && isDame[i][j] == true) {
					buttons[i][j].paintComponents(damePlayerTwo);
				} else if (fields[i][j].equals(FarbEnum.weiß) && isDame[i][j] == false) {
					buttons[i][j].paintComponents(playerOne);
				} else if (fields[i][j].equals(FarbEnum.weiß) && isDame[i][j] == true) {
					buttons[i][j].paintComponents(damePlayerOne);
				} else buttons[i][j].paintComponents(null);
			}
		}
	}
}
