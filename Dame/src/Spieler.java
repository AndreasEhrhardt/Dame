//###########################################################
//## Imports

import java.util.Scanner;
import java.awt.*;

//###########################################################
//## Class

public class Spieler {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	String name;
	FarbEnum color;
	KI ki_player = null;
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Spieler(){
		this.setName("");
	}

	/**
	 * @param name
	 */
	public Spieler(KI ki_player, FarbEnum color){
		setKI(ki_player);
		this.setColor(color);
	}

	/**
	 * @param name
	 * @param color
	 */
	public Spieler(String name, FarbEnum color){
		this.setName(name);
		this.setColor(color);
	}



	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	protected void move(Spiel game){	
		System.out.println("");
		System.out.println("Spieler " + "" + " (" + FarbEnum.getColorName(this.color) + ") ist am Zug");
		
		Point fromPoint = null;
		Point toPoint = null;
		
		do{
			try{
				System.out.print("Bitte Spielfigur auswählen: ");
				fromPoint = inputPosition();
			}
			catch(eInvalidPointException e){
				System.out.println("Ungültige Positions-Eingab");
				continue;
			}
		}while(game.checkMove(fromPoint, toPoint));
	}
	
	private Point inputPosition() throws eInvalidPointException{
		// Create keyboard reader
		Scanner scanner = new Scanner(System.in);
		
		// Read next line
		String point = scanner.nextLine();
		
		// Input to upper
		point = point.toUpperCase();
		
		// Check if 3 chars are typed in
		if(point.length() != 3){
			// Not enough or to many chars -> Invalid input
			throw new eInvalidPointException();
		}
		else{
			// Check if first char is a letter
			if(point.charAt(0) >= 65 && point.charAt(0) <= 90){
				
			}
			else{
				throw new eInvalidPointException();
			}
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public KI getKi(){
		return this.ki_player;
	}

	/**
	 * @return
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * @return
	 */
	public FarbEnum getColor(){
		return this.color;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setKI(KI ki_player){
		this.ki_player = ki_player;
	}
	/**
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @param color
	 */
	public void setColor(FarbEnum color){
		this.color = color;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}