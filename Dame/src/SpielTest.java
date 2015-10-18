/**
 *
 */
import java.util.Scanner;

public class SpielTest implements iBediener {

	public static void main(String[] args) {

		Spiel spiel = new Spiel();
		Spieler gamerName = new Spieler();
		Spielfeld[][] felder = new Spielfeld[8][8];

		int zeile;
		int spalte;

		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < felder.length; i++) {
			try {
				System.out.println(" ");
				System.out.println("Spieler" + gamerName.name);
				System.out.println("Zeile eingeben: ");
				zeile = sc.nextInt();

				System.out.println("Spalte eingeben: ");
				spalte = sc.nextInt();
				System.out.println("Spieler" + gamerName.name + " hat Zeile "
						+ zeile + " ,Spalte " + spalte + " ausgewählt !");
			} catch (Exception e) {
				System.out.println("");
				sc.nextLine();

			}
		}
	}

	@Override
	public void nextMove() {

	}

	@Override
	public void outputGameboardCSV() {

	}

	@Override
	public int getGameboardSize() {

		return 0;

	}

	@Override
	public Spieler getPlayer(int playerNumber) {

		return new Spieler();
	}

}

