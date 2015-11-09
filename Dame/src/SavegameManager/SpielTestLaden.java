package SavegameManager;

import GameLogic.*;
import Interfaces.iDatenzugriff;

public class SpielTestLaden {

	public static void main(String[] args) {

		Spiel game = new Spiel();
		iDatenzugriff serialisiert = new DatenzugriffSerialisiert();
		game.load("./", "dame");
		
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		System.out.println("serialisiert");
		serialisiert.loadGame("./", "dameSerialisiert", game);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
	}
}
