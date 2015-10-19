
//###########################################################
//## Imports

import java.io.*;
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

		if(askNewGame()){
			// Create gamer 1
			gamer[0] = getPlayer(1);

			// Create gamer 2
			gamer[1] = getPlayer(2);
		}
		else{
			this.loadGame();
		}
		
		// Start game-loop
		this.gameLoop();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods
	
	/**
	 * 
	 */
	private void gameLoop(){
		while(!gameFinished()){
			
		}
	}

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
		
		// Create temp-reference
		Spieler newGamer;
		
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
			
			// Create new normal player
			newGamer =  new Spieler(gamerName);
		} else if (gamerID == 2) {
			// Create new KI-Player
			newGamer = new KI();
		} else {
			// Create a default player
			newGamer = new Spieler();
		}
		
		// Set player color
		if(playerNumber == 1) newGamer.setColor(FarbEnum.weiß);
		else newGamer.setColor(FarbEnum.schwarz);
		
		// Return new gamer
		return newGamer;
	}


	@Override
	public void nextMove()
	{

	}

	@Override
	public void outputGameboardCSV()
	{

	}

	@Override
	public void loadingScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame() {
		try{
			// Open file stream
			FileInputStream f_in = new FileInputStream("./savegame.data");
			ObjectInputStream obj_in = new ObjectInputStream (f_in);

			// Read object
			Object obj = obj_in.readObject();

			// Check if object is from same class
			if(obj.getClass() == Spiel.class){
				// Parse object
				Spiel lastGame = (Spiel)obj;
				
				// Get game-data
				this.gamer[0] = lastGame.gamer[0];
				this.gamer[1] = lastGame.gamer[1];
				this.gameboard = lastGame.gameboard;
				this.currentGamer = lastGame.currentGamer;
			}
		}
		catch(IOException | ClassNotFoundException e){
			// Output error message
			System.out.println("Savegame is corrupt");

			// Exit game
			System.exit(-1);
		}
	}

	@Override
	public void saveGame() {
		try{
			// Save game state
			FileOutputStream game = new FileOutputStream("savegame.data");
			ObjectOutputStream gameObjStream = new ObjectOutputStream (game);
			gameObjStream.writeObject(this);

			// Close file handle
			gameObjStream.close();
		}
		catch(IOException e){
			// File save error
			System.out.println("Cant save game - state");
		}
	}

	@Override
	public boolean gameFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean askNewGame() {
		// Create help variables
		int gameType = 0;
		Scanner sc = new Scanner(System.in);

		// Get gametype
		for (int i = 0; i <= maxLoopCount; i++) {
			try{
				// Ask for new game / load game
				System.out.print("Create nwe game (1) or load game (2): ");

				// Get result
				gameType = sc.nextInt();

				// Go to next line
				System.out.println("");

				// Check if result is valid
				if(gameType  == 1 || gameType == 2) break;

			}catch(NoSuchElementException | IllegalStateException e ){
				// Clear input buffer
				sc.nextLine();
			} finally{
				// Check if endless loop
				if (i == maxLoopCount) {
					System.out.println("No valid number detected, we will choose 'new game'");
					gameType = 1;
				}
			}
		}

		// Return result
		if(gameType == 1) return true;
		else return false;
	}
}