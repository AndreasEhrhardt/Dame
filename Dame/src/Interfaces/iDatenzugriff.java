package Interfaces;

import java.io.File;

import GameLogic.Spiel;

public interface iDatenzugriff {

	boolean saveGame(String path, String filename, Spiel game);
	boolean loadGame(String path, String filename, Spiel game);
	boolean haveSaveGame(String path, String filename);
}
