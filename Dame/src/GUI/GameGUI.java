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

	private Board gameboard;
	private Logging log;
	private Control control;
	
	private JPanel layoutPanel;
	
	public static GameGUI globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public GameGUI(){
		// Set global pointer
		GameGUI.globalPointer = this;
		
		// Add layout panel
		layoutPanel = new JPanel();
		layoutPanel.setOpaque(false);
		
		// Create graphic elements
		log = new Logging();
		gameboard = new Board();
		control = new Control();

		// Set layout maanger
		layoutPanel.setLayout(new BorderLayout());
		
		// Set prefered size
		this.log.setPreferredSize(new Dimension(400,50));

		// Add widgets to layout
		layoutPanel.add(gameboard,BorderLayout.CENTER);
		layoutPanel.add(log,BorderLayout.SOUTH);
		layoutPanel.add(control,BorderLayout.EAST);

		this.addComponentListener(new EventHandler().new eGameGUI());
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0; c.gridy = 0;
		c.insets = new Insets(20,20,20,20);
		this.add(layoutPanel,c);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void fitComponents() {
		this.layoutPanel.setPreferredSize(new Dimension(this.getWidth() - 40, this.getHeight() - 40));
	}
	
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