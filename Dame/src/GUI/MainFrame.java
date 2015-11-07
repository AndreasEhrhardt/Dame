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
	
	Spiel game;
	MainPanel mp;
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	private MainFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		mp = new MainPanel();
		this.setContentPane(mp);
		
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.setSize(rec.width,rec.height);
		this.setLocation(0,0);
		
		//this.setUndecorated(true);

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

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setGame(Spiel game){
		if(game == null) throw new RuntimeException();
		
		this.game = game;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
}