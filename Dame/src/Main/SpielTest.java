package Main;
import GameLogic.Spiel;

public class SpielTest {
	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Spiel game = new Spiel();
		game.initialize();
		game.gameLoop();
	}
}
