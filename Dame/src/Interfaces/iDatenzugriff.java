package Interfaces;

import java.io.File;

import GameLogic.Spiel;

public interface iDatenzugriff {
	
	void saveGame(Spiel game);
	void loadGame(Spiel game);
	boolean haveSaveGame();
}
