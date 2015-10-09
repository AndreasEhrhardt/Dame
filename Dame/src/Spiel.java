//###########################################################
//## Imports

import java.util.*;

//###########################################################
//## Class


public class Spiel{

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

		try{			
			// Create gameboard
			this.createGameBoard();

			// Create gamer 1
		}
		catch(Exception e){
			System.out.println("Some errors appear");

		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * 
	 */
	private void createGameBoard() throws Exception
	{
		// Create scanner
		Scanner sc = new Scanner(System.in);

		// Get field size
		int maxField = 20, minField = 4;
		System.out.print("Bitte Spielfeldgröße angeben (" + minField + "-" + maxField + "):");
		int fieldCount = 8;
		for(int i = 0; i <= maxLoopCount; i++){
			// Read next field size
			fieldCount = sc.nextInt();
			// If size is valid, leave loop
			if(fieldCount >= minField && fieldCount <= maxField) break;
			// Check if endless loop
			if(i == 1000){ 
				System.out.println("No valid number detected, we will choose the value 8");
				fieldCount = 8;
			}
		}

		// Create gameboard
		this.gameboard = new Spielbrett(fieldCount);

		// Close scanner
		sc.close();
	}

	private Spieler createGamer(){
		//create Scanner
		Scanner sc = new Scanner(System.in);

		//create Gamer
		int gamerID = 0;
		for(int i = 0; i <= maxLoopCount; i++){
			System.out.println("Spieler oder KI?");
			System.out.println("Spieler = 1, KI = 2");

			gamerID = sc.nextInt();

			if(gamerID == 1 || gamerID == 2) break;

			if(i == maxLoopCount){
				System.out.println("No valid number detected, we will choose KI for you");
				gamerID = 2;
			}
		}
		if(gamerID == 1){
			String gamerName = "";
			System.out.println("Bitte Spielername eingeben:");
			for(int i = 0; i <= maxLoopCount; i++){
				gamerName = sc.nextLine();
				
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
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}