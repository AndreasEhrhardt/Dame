//###########################################################
//## Imports

import java.util.*;

//###########################################################
//## Class


public class Spiel implements iBediener{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private Spielbrett gameboard;
	private Spieler gamer [];
	private Spieler currentGamer;
	static int maxLoopCount = 10;
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Spiel(){
		gamer = new Spieler[2];

		// Create gameboard
		this.gameboard = this.createGameBoard();

		// Create gamer 1
		gamer[0] = createGamer();

		// Create gamer 2
		gamer[1] = createGamer();

	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * 
	 */
	private Spielbrett createGameBoard()
	{
		// Create scanner
		Scanner sc = new Scanner(System.in);

		// Get field size
		int maxField = 20, minField = 4, fieldCount = 8;
		for(int i = 0; i <= maxLoopCount; i++){
			// Output information
			System.out.print("Bitte Spielfeldgröße angeben (" + minField + "-" + maxField + "):");
			// Read next field size
			fieldCount = sc.nextInt();
			// If size is valid, leave loop
			if(fieldCount >= minField && fieldCount <= maxField) break;
			// Check if endless loop
			if(i == 1000){ 
				System.out.println("No valid number detected, we will choose the value 8");
				fieldCount = 8;
			}
			
			System.out.println(i);
		}

		// Create gameboard
		return new Spielbrett(fieldCount);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private Spieler createGamer() {
		//create Scanner
		Scanner sc = new Scanner(System.in);

		//create Gamer
		int gamerID = 0;
		for(int i = 0; i <= maxLoopCount; i++){
			// Get gamer type
			System.out.println("Spieler oder KI?");
			System.out.println("Spieler = 1, KI = 2");
			System.out.print("Ihre Eingabe: ");
			gamerID = sc.nextInt();
			System.out.println("");

			// Check if type is valid
			if(gamerID == 1 || gamerID == 2) break;

			// Check if endless loop
			if(i == maxLoopCount){
				System.out.println("No valid number detected, we will choose KI for you");
				gamerID = 2;
			}
		}
		if(gamerID == 1){
			String gamerName = "";
			for(int i = 0; i <= maxLoopCount; i++){
				System.out.print("Bitte Spielername eingeben:");
				gamerName = sc.next();
				System.out.println("");

				if(!gamerName.isEmpty()) break;

				if(i == maxLoopCount){
					System.out.println("No name insert, we will call you Peter");
					gamerName = "Peter";
				}
			}
			return new Spieler(gamerName);
		}else if(gamerID == 2){
			return new KI();
		}
		else{
			return new Spieler();
		}


	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}