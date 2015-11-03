package Main;
import GameLogic.Spiel;

public class Main {

	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Spiel game = new Spiel();
		game.gameLoop();
	}
}
