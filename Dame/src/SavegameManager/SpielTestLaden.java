package SavegameManager;

import GameLogic.*;

public class SpielTestLaden {

	public static void main(String[] args) {

		Spiel game = new Spiel();
		game.load("./", "dame");
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
		game.outputGameboardCSV();
		game.getCurrentGamer().move(game, null);
	}
}
