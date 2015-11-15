//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import Events.EventHandler;
import Events.EventHandler.eShowGameboardSettingsButton;
import Events.EventHandler.eShowLoadingMenuButton;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class PlayerSettings extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public PlayerSettings(){
		// Prepare grid-layout
		this.setLayout(new GridBagLayout());

		// Create gui elements
		JLabel head = new JLabel("Player settings");
		head.setHorizontalAlignment(JLabel.CENTER);
		head.setFont(new Font("Gill Sans", Font.BOLD, 40));
		
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		this.add(head, c);
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

}