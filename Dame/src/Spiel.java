
//###########################################################
//## Imports

import java.util.*;

//###########################################################
//## Class

public class Spiel implements iBediener {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private Spielbrett gameboard;
	private Spieler gamer[];
	private Spieler currentGamer;
	static int maxLoopCount = 10;
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	public Spiel() {
		gamer = new Spieler[2];

		// Create gameboard
		this.gameboard = this.createGameBoard();

		// Create gamer 1
		gamer[0] = getPlayer(1);

		// Create gamer 2
		gamer[1] = getPlayer(2);

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	/**
	 * 
	 */
	private Spielbrett createGameBoard() {
		// Get gameboard size
		int fieldCount = getGameboardSize();

		// Create gameboard
		return new Spielbrett(fieldCount);
	}


	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)
	@Override
	public int getGameboardSize() {
		// Create scanner
		Scanner sc = new Scanner(System.in);

		// Get field size
		int maxField = 20, minField = 4, fieldCount = 8;
		for (int i = 0; i <= maxLoopCount; i++) {
			try{
				// Output information
				System.out.print("Bitte Spielfeldgröße angeben (" + minField + "-" + maxField + "):");
				// Read next field size
				fieldCount = sc.nextInt();
				// If size is valid, leave loop
				if (fieldCount >= minField && fieldCount <= maxField)
					break;
			}
			catch(NoSuchElementException | IllegalStateException e ){
				// Clear input buffer
				sc.nextLine();

				// Continue loop
				continue;
			}
			finally{
				// Check if endless loop
				if (i == this.maxLoopCount) {
					System.out.println("No valid number detected, we will choose the value 8");
					fieldCount = 8;
				}
			}
		}
		return fieldCount;
	}

	@Override
	/**
	 * @return
	 */
	public Spieler getPlayer(int playerNumber) {
		// create Scanner
		Scanner sc = new Scanner(System.in);

		// create Gamer
		int gamerID = 0;
		for (int i = 0; i <= maxLoopCount; i++) {
			try{
				// Get gamer type
				System.out.println("Spieler " + playerNumber + ": Spieler oder KI?");
				System.out.println("Spieler = 1, KI = 2");
				System.out.print("Ihre Eingabe: ");
				gamerID = sc.nextInt();
				System.out.println("");

				// Check if type is valid
				if (gamerID == 1 || gamerID == 2)
					break;
			}catch(NoSuchElementException | IllegalStateException e ){
				// Clear input buffer
				sc.nextLine();
			} finally{
				// Check if endless loop
				if (i == maxLoopCount) {
					System.out.println("No valid number detected, we will choose KI for you");
					gamerID = 2;
				}
			}

		}
		if (gamerID == 1) {
			String gamerName = "";
			for (int i = 0; i <= maxLoopCount; i++) {
				System.out.print("Bitte Spielername eingeben:");
				gamerName = sc.next();
				System.out.println("");

				if (!gamerName.isEmpty())
					break;

				if (i == maxLoopCount) {
					System.out.println("No name insert, we will call you Peter");
					gamerName = "Peter";
				}
			}
			return new Spieler(gamerName);
		} else if (gamerID == 2) {
			return new KI();
		} else {
			return new Spieler();
		}

	}


	@Override
	public void nextMove()
	{

	}

	@Override
	public void outputGameboardCSV()
	{

	}
}