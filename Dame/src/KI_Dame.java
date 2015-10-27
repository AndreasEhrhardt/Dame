import java.awt.*;

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
	//pusten
	//public 
	//check if move is valid
	public boolean isFree(){
		
		return false;
	}
	
	//move to empty space
	public void move(Spiel game){
		//exception abfangen
		//if position != free -> nextPosition
		//if position == free -> setze stein 
		//spiel.move
		
	}
}
