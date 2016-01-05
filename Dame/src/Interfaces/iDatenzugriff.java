package Interfaces;

import java.io.File;

import GameLogic.SpielBean;

public interface iDatenzugriff {

	boolean saveGame(String path, String filename, SpielBean game);
	boolean loadGame(String path, String filename, SpielBean game);
	boolean haveSaveGame(String path, String filename);
}
