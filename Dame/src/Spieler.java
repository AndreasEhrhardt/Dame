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
		System.out.println("Spieler '" + this.getName() + "' (" + FarbEnum.getColorName(this.color) + ") ist am Zug");

		Point fromPoint = null;
		Point toPoint = null;

		do{
			try{
				System.out.print("Bitte Spielfigur eingeben: ");
				fromPoint = inputPosition();
				
				System.out.print("Bitte neues Spielfeld eingeben: ");
				toPoint = inputPosition();
			}
			catch(eInvalidPointException e){
				System.out.println("Ungültige Positions-Eingabe");
				continue;
			}
		}while(!game.moveIsValid(fromPoint, toPoint));
	}

	private Point inputPosition() throws eInvalidPointException{
		// Create keyboard reader
		Scanner scanner = new Scanner(System.in);

		// Read next line
		String sPoint = scanner.nextLine();

		// Input to upper
		sPoint = sPoint.toUpperCase();

		// Check if 3 chars are typed in
		if(sPoint.length() != 3){
			// Not enough or to many chars -> Invalid input
			throw new eInvalidPointException();
		}
		else{
			// Check if first char is a letter
			if(sPoint.charAt(0) >= 65 && sPoint.charAt(0) <= 90){
				Point point = new Point();
				int x = sPoint.charAt(0) - 65;

				if(sPoint.charAt(1) >= 48 && sPoint.charAt(1) <= 57){
					int y = -1;
					y = (sPoint.charAt(1) - 48) * 10;

					if(sPoint.charAt(2) >= 48 && sPoint.charAt(2) <= 57){
						y += (sPoint.charAt(2) - 48) - 1;
						
						if(x < 0 || y < 0) throw new eInvalidPointException();
						
						point.setLocation(x, y);
						
						return point;
					}
					else{
						throw new eInvalidPointException();
					}

				}
				else{
					throw new eInvalidPointException();
				}

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