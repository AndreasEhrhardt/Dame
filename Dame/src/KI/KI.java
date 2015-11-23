package KI;
import GameLogic.Spiel;
import GameLogic.Spieler;

import java.awt.*;

//###########################################################
//## Imports


//###########################################################
//## Class

public abstract class KI{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties
	
	Spieler player;	

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public KI(){
		player = null;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods
	
	public abstract void move(Spiel game, Spieler player);
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public Spieler getPlayer(){
		return this.player;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setPlayer(Spieler player){
		this.player = player;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
	
}