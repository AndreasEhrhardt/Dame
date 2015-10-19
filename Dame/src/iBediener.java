public interface iBediener {
	void nextMove();
	
	void outputGameboardCSV();
	
	int getGameboardSize();
	
	void loadingScreen();
	void loadGame();
	void saveGame();
	
	boolean gameFinished();
	boolean askNewGame();
	
	Spieler getPlayer(int playerNumber);
}
