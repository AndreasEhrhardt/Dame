package Interfaces;
import GameLogic.Spieler;

public interface iBediener {
	
	void outputGameboardCSV();
	
	int getGameboardSize();
	
	void loadingScreen();
	
	boolean gameFinished();
	boolean askNewGame();
	
	Spieler getPlayer(int playerNumber);
}
