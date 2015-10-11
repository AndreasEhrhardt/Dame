/**
 * 
 *
 */

public class SpielTest implements Bedienung {

	public static void main(String[] args) {

		Spiel game = new Spiel();
		Spielbrett gameboard = null;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (gameboard != null) {
					System.out.println("Zeile" + i + ", Spalte " + j
							+ "geklickt ");
				}
			}
		}
	}

	@Override
	public String iBediener() {

		return null;
	}

}

