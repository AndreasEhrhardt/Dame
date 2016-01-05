package GameLogic;
//###########################################################
//## Imports

import java.util.Scanner;

import Enumerations.FarbEnum;
import KI.KI;
import KI.KI_Dame;

import java.awt.*;
import java.io.Serializable;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class Spieler implements Serializable {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	String name;
	FarbEnum color;
	KI_Dame ki_player = null;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	/**
	 * 
	 */
	public Spieler() {
		this.setName("");
	}

	/**
	 * Constructor for the KI
	 * 
	 * @param ki_player
	 * @param color
	 */
	public Spieler(KI_Dame ki_player, FarbEnum color) {
		setKI(ki_player);
		this.setColor(color);
	}

	/**
	 * Constructor for a human player
	 * 
	 * @param name
	 * @param color
	 */
	public Spieler(String name, FarbEnum color) {
		this.setName(name);
		this.setColor(color);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods
	/**
	 * This method will move the tokens on the board and also checks
	 * if movement is valid.
	 * 
	 * @param game
	 */
	public void move(SpielBean game, Point fromPoint) {
		if(this.ki_player != null){
			this.ki_player.move(game, this);
			return;
		}
		
		System.out.println("");
		System.out.println("Spieler '" + this.getName() + "' ("
				+ FarbEnum.getColorName(this.color) + ") ist am Zug");

		boolean fromAlreadyExist = false;
		if(fromPoint != null) fromAlreadyExist = true;
		Point toPoint = null;

		boolean success;
		do {
			success = false;
			try {
				if(fromAlreadyExist == false){
					System.out.print("Bitte Spielfigur eingeben: ");
					fromPoint = inputPosition();
				}

				System.out.print("Bitte neues Spielfeld eingeben: ");
				toPoint = inputPosition();

				game.move(fromPoint, toPoint);
				
				success = true;
			}catch (SpielBean.eInvalidPointException e) {
				System.out.println("Ungültige Positions-Eingabe");
			}catch (SpielBean.eSomeOtherMoveErrorsException e) {
				System.out.println("Unbekannter Fehler. sorry");
			}catch (SpielBean.eDestinationPointIsBlockedException e) {
				System.out.println("Ziel-Feld ist blockiert");
			}catch (SpielBean.eNoDiagonalMoveException e) {
				System.out.println("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
			}catch (SpielBean.eNoFigureFoundOnFieldException e) {
				System.out.println("Feld hat keine gültige Spielfigur");
			}catch (SpielBean.eOutOfGameboardException e) {
				System.out.println("Position ist außerhalb des Spielfeldes");
			}catch (SpielBean.eSamePositionException e) {
				System.out.println("Spielfigur-Feld und Ziel-Feld sind identisch");
			}catch (SpielBean.eDistanceToFarException e){
				System.out.println("Bauern dürfen nur 1 Feld weit springen");
			}catch(SpielBean.eEnemyFigureSelectedException e){
				System.out.println("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
			}catch(SpielBean.eOwnFigureIsBlockingException e){
				System.out.println("Kann keine eigenen Steine überspringen");
			}catch(SpielBean.eNoBackJumpExcpetion e){
				System.out.println("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
			}catch(SpielBean.eWayIsBlockedException e){
				System.out.println("Es dürfen keine 2 Stein gleichzeitig übersürungen werden");
			}catch (Exception e){
				System.out.println("Sry, some other problems ");
			}			
		} while (!success);
	}

	/**
	 * This method is used for positioning the tokens and throws
	 * exception if position data is invalid
	 * 
	 * @return point 
	 */
	public Point inputPosition() throws SpielBean.eInvalidPointException {
		// Create keyboard reader
		Scanner scanner = new Scanner(System.in);

		// Read next line
		String sPoint = scanner.nextLine();

		// Input to upper
		sPoint = sPoint.toUpperCase();

		// Check if 3 chars are typed in
		if (sPoint.length() != 3) {
			// Not enough or to many chars -> Invalid input
			throw new SpielBean.eInvalidPointException();
		} else {
			// Check if first char is a letter
			if (sPoint.charAt(0) >= 65 && sPoint.charAt(0) <= 90) {
				Point point = new Point();
				int y = sPoint.charAt(0) - 65;

				if (sPoint.charAt(1) >= 48 && sPoint.charAt(1) <= 57) {
					int x = -1;
					x = (sPoint.charAt(1) - 48) * 10;

					if (sPoint.charAt(2) >= 48 && sPoint.charAt(2) <= 57) {
						x += (sPoint.charAt(2) - 48) - 1;
						
						if (x < 0 || y < 0)
							throw new SpielBean.eInvalidPointException();

						point.setLocation(x, y);

						return point;
					} else {
						throw new SpielBean.eInvalidPointException();
					}

				} else {
					throw new SpielBean.eInvalidPointException();
				}

			} else {
				throw new SpielBean.eInvalidPointException();
			}
		}
	}
	

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	/**
	 * Gets if it's a KI
	 * 
	 * @return
	 */
	public KI getKi() {
		return this.ki_player;
	}

	/**
	 * @return
	 * Gets the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the color
	 * 
	 * @return
	 */
	public FarbEnum getColor() {
		return this.color;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 * Sets the player as KI
	 * 
	 * @param ki_player
	 */
	public void setKI(KI_Dame ki_player) {
		this.ki_player = ki_player;
		if(this.ki_player != null) {
			this.ki_player.setPlayer(this);
		}
	}
	
	/**
	 * Sets the name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the color
	 * 
	 * @param color
	 */
	public void setColor(FarbEnum color) {
		this.color = color;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)

	public Spieler clone(){
		Spieler newObj = new Spieler();
		newObj.setColor(this.getColor());
		newObj.setKI(this.ki_player.clone());
		
		return newObj;
	}
}