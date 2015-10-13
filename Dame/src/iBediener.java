public interface iBediener {
	void nextMove();
	void outputGameboardCSV();
	int getGameboardSize();
	Spieler getPlayer(int playerNumber);
	
}
