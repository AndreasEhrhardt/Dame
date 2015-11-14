//###########################################################
//## Package

package GUI;	

//###########################################################
//## Imports

import javax.swing.*;

import Events.*;
import Events.EventHandler.*;

import java.awt.*;
import GameLogic.*;

//###########################################################
//## Class

public class MainFrame extends JFrame {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties
	
	private Spiel game;
	private MainPanel mp;
	
	public static MainFrame globalPointer = null;
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	private MainFrame(){
		// Set global pointer
		MainFrame.globalPointer = this;
		
		// Set close handling
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Create game
		this.game = new Spiel();
		
		// Create main panel
		mp = new MainPanel(this);
		this.setContentPane(mp);
		
		// Set size of game
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.setSize(rec.width,rec.height);
		this.setLocation(0,0);
		
		// Undecorate the windows
		this.setUndecorated(true);

		// Show the window
		this.setVisible(true);
	}
	
	public MainFrame(Spiel game){
		this();
		
		this.setGame(game);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods
	

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public MainPanel getMainPanel(){
		return this.mp;
	}
	
	public Spiel getGame(){
		return this.game;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setGame(Spiel game){
		if(game == null) throw new RuntimeException();
		
		this.game = game;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
}