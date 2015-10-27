//###########################################################
//## Imports

import java.util.Scanner;
import java.awt.*;

//###########################################################
//## Class

public class Spieler {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	String name;
	FarbEnum color;
	KI ki_player = null;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	public Spieler() {
		this.setName("");
	}

	/**
	 * Constructor for the KI
	 * 
	 * @param ki_player
	 * @param color
	 */
	public Spieler(KI ki_player, FarbEnum color) {
		this.setName("NPC");
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
	protected void move(Spiel game) {
		System.out.println("");
		System.out.println("Spieler '" + this.getName() + "' ("
				+ FarbEnum.getColorName(this.color) + ") ist am Zug");

		Point fromPoint = null;
		Point toPoint = null;

		boolean success;
		do {
			success = false;
			try {
				System.out.print("Bitte Spielfigur eingeben: ");
				fromPoint = inputPosition();

				System.out.print("Bitte neues Spielfeld eingeben: ");
				toPoint = inputPosition();

				game.move(fromPoint, toPoint);
				
				success = true;
			}catch (Spiel.eInvalidPointException e) {
				System.out.println("Ungültige Positions-Eingabe");
			}catch (Spiel.eSomeOtherMoveErrors e) {
				System.out.println("Unbekannter Fehler. sorry");
			}catch (Spiel.eDestinationPointIsBlockedException e) {
				System.out.println("Ziel-Feld ist blockiert");
			}catch (Spiel.eNoDiagonalMoveException e) {
				System.out.println("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
			}catch (Spiel.eNoFigurFoundOnFieldException e) {
				System.out.println("Feld hat keine gültige Spielfigur");
			}catch (Spiel.eOutOfGameboardException e) {
				System.out.println("Position ist außerhalb des Spielfeldes");
			}catch (Spiel.eSamePositionException e) {
				System.out.println("Spielfigur-Feld und Ziel-Feld sind identisch");
			}catch (Spiel.eDistanceToFarException e){
				System.out.println("Bauern dürfen nur 1 Feld weit springen");
			}catch(Spiel.eEnemyFigurSelectedException e){
				System.out.println("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
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
	private Point inputPosition() throws Spiel.eInvalidPointException {
		// Create keyboard reader
		Scanner scanner = new Scanner(System.in);

		// Read next line
		String sPoint = scanner.nextLine();

		// Input to upper
		sPoint = sPoint.toUpperCase();

		// Check if 3 chars are typed in
		if (sPoint.length() != 3) {
			// Not enough or to many chars -> Invalid input
			throw new Spiel.eInvalidPointException();
		} else {
			// Check if first char is a letter
			if (sPoint.charAt(0) >= 65 && sPoint.charAt(0) <= 90) {
				Point point = new Point();
				int x = sPoint.charAt(0) - 65;

				if (sPoint.charAt(1) >= 48 && sPoint.charAt(1) <= 57) {
					int y = -1;
					y = (sPoint.charAt(1) - 48) * 10;

					if (sPoint.charAt(2) >= 48 && sPoint.charAt(2) <= 57) {
						y += (sPoint.charAt(2) - 48) - 1;

						if (x < 0 || y < 0)
							throw new Spiel.eInvalidPointException();

						point.setLocation(x, y);

						return point;
					} else {
						throw new Spiel.eInvalidPointException();
					}

				} else {
					throw new Spiel.eInvalidPointException();
				}

			} else {
				throw new Spiel.eInvalidPointException();
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
	public void setKI(KI ki_player) {
		this.ki_player = ki_player;
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

}