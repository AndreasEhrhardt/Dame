//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;
import Events.EventHandler;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class GameGUI extends MainPanelComponent{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	Board gameboard;
	Logging log;
	Control control;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public GameGUI(){		
		// Create graphic elements
		log = new Logging();
		gameboard = new Board();
		control = new Control();

		// Set layout maanger
		this.setLayout(new BorderLayout());
		
		// Set prefered size
		this.log.setPreferredSize(new Dimension(400,50));
		
		// Disable logging
		this.log.setEnabled(false);;

		// Add widgets to layout
		this.add(gameboard,BorderLayout.CENTER);
		this.add(log,BorderLayout.SOUTH);
		this.add(control,BorderLayout.EAST);

		this.addComponentListener(new EventHandler().new eGameGUI());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public Board getGameboard(){
		return this.gameboard;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}