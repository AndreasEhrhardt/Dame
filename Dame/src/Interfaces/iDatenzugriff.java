package Interfaces;

import GameLogic.Spiel;

public interface iDatenzugriff {
	void saveGame(Spiel game);
	abstract void loadGame(Spiel game);
}
