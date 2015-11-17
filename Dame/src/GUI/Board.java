//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Events.EventHandler;
import GameLogic.Spiel;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class Board extends JPanel {	

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private ArrayList< FieldButton > pressedButton = new ArrayList<>();
	public static Board globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Board() {
		// Set global pointer
		Board.globalPointer = this;

		this.setOpaque(false);

		this.addComponentListener(new EventHandler().new eBoard());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void createField(){
		// Remove all components
		this.removeAll();

		// Get game
		Spiel game = MainFrame.globalPointer.getGame();

		// Set layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,0,0,0);

		// Create fields
		for(int i = 0; i < game.getGameboardSize(); i++){
			for(int j = 0; j < game.getGameboardSize(); j++){
				c.gridx = i + 1; c.gridy = game.getGameboardSize() - j - 1;
				FieldButton field = new FieldButton(i,j);
				field.addActionListener(new EventHandler().new eFieldButton());
				this.add(field, c);
			}
		}

		// Numbers
		for(int i = 0; i < game.getGameboardSize(); i++){
			c.gridx = 0; c.gridy = game.getGameboardSize() - i - 1;
			JLabel label = new JLabel(String.format("%02d", i + 1));
			label.setFont(new Font("Gill Sans",Font.BOLD,20));
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label, c);
		}

		// Letters
		for(int i = 0; i < game.getGameboardSize(); i++){
			c.gridx = i + 1; c.gridy = game.getGameboardSize();
			JLabel label = new JLabel(String.valueOf((char)(i + 65)));
			label.setFont(new Font("Gill Sans",Font.BOLD,20));
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label, c);
		}

		setSize();
	}

	public void setSize(){
		// Get game
		Spiel game = MainFrame.globalPointer.getGame();

		// Get all components
		Component components [] = this.getComponents();

		// Detect size of button
		int sizeW = this.getWidth() / (game.getGameboardSize() + 1);
		int sizeH = this.getHeight() / (game.getGameboardSize() + 1);
		int size;

		// Check which size should be use
		if(sizeW < sizeH) size = sizeW;
		else size = sizeH;

		// Set pref size for every button
		for(Component c: components){
			c.setPreferredSize(new Dimension(size,size));
		}
		
		this.updateUI();
	}

	public void fieldPressed(FieldButton button) {
		if(pressedButton.contains(button)) {
			this.resetPressedButtons();
		} 
		else{
			pressedButton.add(button);
			if(pressedButton.size() == 2){
				Control.globalPointer.startMove(pressedButton.get(0).getLocation(), pressedButton.get(1).getLocation());
				this.resetPressedButtons();
			}
			else{
				button.setSelected(true);
			}
		}
	}
	
	public void resetPressedButtons(){
		for(FieldButton button: pressedButton){
			button.setSelected(false);
		}
		
		this.pressedButton.clear();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
	

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}
