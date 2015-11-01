package KI;
import java.awt.*;

import GameLogic.Spiel;
import GameLogic.Spieler;

public class KI_Dame extends KI{
	//Properties
	private Spieler kiPlayer;
	//Constructor
	public KI_Dame(){
		setKiPlayer();
	}
	//getter/Setter
	private void setKiPlayer(){
		this.kiPlayer = new Spieler();
	}
	
	//methods
	//take start stone
	//pusten
	//check if move is valid
	public boolean isFree(){
		
		return false;
	}
	
	//move to empty space
	public void move(Spiel game, Point pos){
		//exception abfangen
		//if position != free -> nextPosition
		//if position == free -> setze stein 
		//spiel.move
		
		
	}
}
