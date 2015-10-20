/**
 *
 */

import java.util.Scanner;

public class SpielTest implements iBediener {

	public static void main(String[] args) {

		Spiel spiel = new Spiel();

		Spielfeld[][] felder = new Spielfeld[8][8];

		// Attribute

		int zeile;
		int spalte;

		// create Scanner
		Scanner sc = new Scanner(System.in);

		// Get gamer position

		for (int i = 0; i < felder.length; i++) {
			for(int j=0;j<felder[i].length;j++){
			try {
				System.out.println(" ");
				System.out.println("Spieler");
				System.out.print("Zeile eingeben: ");
				zeile = sc.nextInt();

				System.out.print("Spalte eingeben: ");
				spalte = sc.nextInt();
				System.out.println("Spieler hat Zeile " + zeile + " ,Spalte "
						+ spalte + " ausgewählt !");
			} catch (Exception e) {
				System.out.println("");
				sc.nextLine();

			}

		}
		}
	}

	/**
	 * @param
	 */
	@Override
	public void nextMove() {

	}

	/**
	 * @param
	 */
	@Override
	public void outputGameboardCSV() {

	}

	/**
	 * @return
	 */
	@Override
	public int getGameboardSize() {

		return 0;

	}

	/**
	 * @return
	 */
	@Override
	public Spieler getPlayer(int playerNumber) {

		return new Spieler();
	}

	@Override
	public void loadingScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean gameFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean askNewGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
