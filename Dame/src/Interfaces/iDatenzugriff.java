package Interfaces;

import java.io.File;

import GameLogic.Spiel;

public interface iDatenzugriff {
	
	void saveGame(Object game);
	void loadGame(Spiel game);
	boolean haveSaveGame();
}
