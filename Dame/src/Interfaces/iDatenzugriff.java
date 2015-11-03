package Interfaces;

import java.io.File;

import GameLogic.Spiel;

public interface iDatenzugriff {
	public String saveGameName = "";
	
	void saveGame(Spiel game);
	void loadGame(Spiel game);
	public static boolean haveSaveGame(){
		File f = new File(saveGameName);
		if(!f.exists() || f.isDirectory()){
			return false;
		}
		return true;
	}
}
