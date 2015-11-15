//###########################################################
//## Package

package GUI;

import java.awt.Color;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class GameboardSettings extends MainPanelComponent{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	JSpinner boardSize;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public GameboardSettings(){
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel background = new JPanel();
		background.setPreferredSize(new Dimension(400,150));
		background.setBackground(new Color(230,230,230));
		background.setLayout(new GridBagLayout());
		
		c.gridx = 0; c.gridy = 0;
		JLabel label = new JLabel("Gameboard size:");
		label.setPreferredSize(new Dimension(150,30));
		label.setHorizontalAlignment(JLabel.CENTER);
		background.add(label, c);
		
		c.gridx = 1; c.gridy = 0;
		boardSize = new JSpinner(new SpinnerNumberModel(8, 8, 20, 1));
		background.add(boardSize, c);
		
		this.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0;
		this.add(background,c);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class
	
	private class gameboardDesign{
		
	}

}