/**
 *
 */
public class SpielTest implements iBediener {

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
	
	public int 
}

