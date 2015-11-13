package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import GameLogic.Spiel;


public class Board extends MainPanelComponent {

	public Board(MainPanel mp) {
		super(mp);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints coord = new GridBagConstraints();
		
		Spiel game = mp.getMainFrame().getGame();
		int size = game.getGameboardSize();
		JButton[][] buttons = new JButton[size][size];
		Dimension dimension;
		
		switch(size) {
		case 8: 
			dimension = new Dimension(60,60);
			break;
		case 10: 
			dimension = new Dimension(55,55);
			break;
		case 12: 
			dimension = new Dimension(50,50);
			break;
		case 14: 
			dimension = new Dimension(45,45);
			break;
		case 16: 
			dimension = new Dimension(40,40);
			break;
		case 18: 
			dimension = new Dimension(35,35);
			break;
		case 20: 
			dimension = new Dimension(30,30);
			break;	
		default: ??
		}
		
		for (int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				coord.gridx = i;
				coord.gridy = j;
				buttons[i][j] = new JButton();
				
				buttons[i][j].setPreferredSize(dimension);;
				jPanel.add(buttons[i][j], coord);
				if ((j % 2 == 0 || !(i % 2 == 0)) && ((i % 2 == 0 || !(j % 2 == 0)))) {
					buttons[i][j].setBackground(Color.white);
					buttons[i][j].setEnabled(false);
				} else {
					buttons[i][j].setBackground(Color.gray);
				}
				
			}
		}
	}
	
	
	

}
