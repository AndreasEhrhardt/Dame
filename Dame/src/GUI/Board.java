package GUI;

import java.awt.*;
import javax.swing.*;

import GameLogic.Spiel;
public class Board extends MainPanelComponent {

	public Board(MainPanel mp) {
		super(mp);
		
		JPanel jPanel = new JPanel();
		
		Spiel game = mp.getMainFrame().getGame();
		int size = game.getGameboardSize();
		JButton[][] buttons = new JButton[size][size];
		Dimension dimension;
		
		//size of buttons
		switch(size) {
		case 8: 
			jPanel.setLayout(new GridLayout(0, 9));
			dimension = new Dimension(60,60);
			break;
		case 10: 
			jPanel.setLayout(new GridLayout(0,11));
			dimension = new Dimension(55,55);
			break;
		case 12: 
			jPanel.setLayout(new GridLayout(0, 13));
			dimension = new Dimension(50,50);
			break;
		case 14: 
			jPanel.setLayout(new GridLayout(0, 15));
			dimension = new Dimension(45,45);
			break;
		case 16: 
			jPanel.setLayout(new GridLayout(0, 17));
			dimension = new Dimension(40,40);
			break;
		case 18: 
			jPanel.setLayout(new GridLayout(0, 19));
			dimension = new Dimension(35,35);
			break;
		case 20: 
			jPanel.setLayout(new GridLayout(0, 21));
			dimension = new Dimension(30,30);
			break;	
		default: 
			jPanel.setLayout(new GridLayout(0, 13));
			dimension = new Dimension(12,12);
		}
		
		//colour of buttons
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
		
		
		jPanel.add(new JLabel(""));
		//letters of board
		for (int k = 0; k < buttons.length; k++) {
			jPanel.add(new JLabel(String.valueOf((char) (65 + (buttons.length - 1) - k))), SwingConstants.CENTER);
		}
		
		//numbers of board
		for (int k = 0; k < buttons.length; k++) {
			for (int j = 0; j < buttons.length; j++) {
				if (j == 0) {
					jPanel.add(new JLabel("" + (size - k), SwingConstants.CENTER));
				}
				jPanel.add(buttons[k][j]);
			}
		}
	}
	
	
	

}
