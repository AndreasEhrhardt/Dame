//###########################################################
//## Imports

import java.io.*;
import java.util.*;
import java.awt.*;

//###########################################################
//## Class

public class Spiel implements iBediener {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private String saveGameName = "./savegame.data";
	private Spielbrett gameboard;
	private Spieler gamer[];
	private Spieler currentGamer;
	static int maxLoopCount = 10;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	public Spiel() {
		// Initialize game
		this.initialize();

		// Start game-loop
		this.gameLoop();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	private void initialize(){
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

		// Set start player
		this.currentGamer = gamer[0];
	}

	/**
	 * The game-loop is the mein loop of the application.
	 * The loop checks for finished
	 */
	private void gameLoop(){
		int loopBreak = 5;
		while(!gameFinished() && loopBreak > 0){
			loopBreak--;

			// Output current gameboard
			this.outputGameboardCSV();

			// Current player have to move
			this.currentGamer.move(this);

			// Set next player
			if(this.currentGamer == this.gamer[0])
				this.currentGamer = this.gamer[1];
			else
				this.currentGamer = this.gamer[0];


		}
	}
	
	/**
	 * @param from
	 * @param to
	 */
	public boolean checkMove(Point fromPoint, Point toPoint){
		return true;
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
		int maxField = 20, minField = 8, fieldCount = 8;
		for (int i = 0; i <= maxLoopCount; i++) {
			try{
				// Output information
				System.out.print("Bitte Spielfeldgröße angeben (" + minField + "-" + maxField + "):");
				// Read next field size
				fieldCount = sc.nextInt();
				// If size is valid, leave loop
				if (fieldCount >= minField && fieldCount <= maxField){
					if(fieldCount % 2 == 0) break;
					else System.out.println("Nur gerade Spielfeldgrößen sind erlaubt!");
				}
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
				// Get current color name
				String colorName;
				if(playerNumber == 1) colorName = "white";
				else colorName = "black";

				// Get gamer type
				System.out.println("Spieler " + playerNumber + " (" + colorName + "): Spieler (1) oder KI(2)?");
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

		// Set player color
		FarbEnum color;
		if(playerNumber == 1) color = FarbEnum.weiß;
		else color = FarbEnum.schwarz;

		// Create player
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
			newGamer =  new Spieler(gamerName, color);
		} else if (gamerID == 2) {
			// Create new KI-Player
			newGamer = new Spieler(new KI_Dame(), color);
		} else {
			// Create a default player
			newGamer = new Spieler();
		}

		// Return new gamer
		return newGamer;
	}


	@Override
	public void nextMove()	{

	}

	@Override
	public void outputGameboardCSV(){
		// Get gameboard fields
		Spielfeld felder[][] = this.gameboard.getFelder();

		// Define start variable
		char currentRow = (char)(65 + felder.length - 1);
		int currentColumn = 1;

		// Create empty line
		System.out.println("");

		// For every row - DESC
		for(int i = felder.length - 1; i >= 0; i--){
			currentColumn = 1;

			// Write colum name
			System.out.print(currentRow);

			// For every column
			for(int j = 0; j < felder.length; j++){
				// Get figur of field
				Spielfigur currentFigur = felder[i][j].getFigur();

				// Write seperator
				System.out.print(";");

				// Check if field have figur or not
				if(currentFigur == null) System.out.print("  ");
				else{ 
					if(currentFigur.getColor() == FarbEnum.weiß) 
						System.out.print(" W");
					else
						System.out.print(" S");
				}

				// Increase column value
				currentColumn++;
			}

			// End of line
			System.out.println("");

			// Increase row value
			int ascii = currentRow;
			currentRow = (char)--ascii;
		}

		// Draw column names
		for(int i = 0; i <= felder.length; i++){
			if(i == 0) System.out.print(" ");
			else{
				System.out.print(";");
				System.out.print(String.format("%02d",i));
			}
		}
		System.out.println("");

		// Create empty line
		System.out.println("");
	}

	@Override
	public void loadingScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadGame() {
		try{
			// Open file stream
			FileInputStream f_in = new FileInputStream(saveGameName);
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
			FileOutputStream game = new FileOutputStream(saveGameName);
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

		// Check if savegame avaiable
		File f = new File(saveGameName);
		if(!f.exists() || f.isDirectory()) { 
			return true;
		}

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