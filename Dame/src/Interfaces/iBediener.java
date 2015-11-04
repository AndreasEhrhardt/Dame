package Interfaces;
import GameLogic.Spieler;

public interface iBediener {
	
	String outputGameboardCSV();
	
	int getGameboardSize();
	
	void loadingScreen();
	
	boolean gameFinished();
	boolean askNewGame();
	
	Spieler createNewPlayer(int playerNumber);
}
