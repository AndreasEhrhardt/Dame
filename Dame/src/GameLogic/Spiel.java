package GameLogic;

//###########################################################
//## Imports

import java.io.*;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JFrame;

import Enumerations.FarbEnum;
import GUI.MainFrame;
import Interfaces.iBediener;
import Interfaces.iDatenzugriff;
import KI.KI_Dame;
import SavegameManager.DatenzugriffCSV;
import SavegameManager.DatenzugriffSerialisiert;

import java.awt.*;

//###########################################################
//## Class

public class Spiel implements iBediener, Serializable {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Exceptions

	public static class eNoDiagonalMoveException extends Exception {}
	public static class eInvalidPointException extends Exception {}
	public static class eSamePositionException extends Exception {}
	public static class eOutOfGameboardException extends Exception {}
	public static class eDestinationPointIsBlockedException extends Exception {}
	public static class eNoFigureFoundOnFieldException extends Exception {}
	public static class eSomeOtherMoveErrorsException extends Exception {}
	public static class eEnemyFigureSelectedException extends Exception {}
	public static class eDistanceToFarException extends Exception {}
	public static class eNoBackJumpExcpetion extends Exception {}
	public static class eWayIsBlockedException extends Exception {}
	public static class eOwnFigureIsBlockingException extends Exception {}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private Spielbrett gameboard;
	private Spieler gamer[];
	private Spieler currentGamer;
	static int maxLoopCount = 10;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	/**
	 * Constructor
	 */
	public Spiel() {
	}

	/**
	 * @param gameboard
	 * @param gamer
	 */
	public Spiel(Spielbrett gameboard, Spieler gamer[]) {
		// Set gameboard
		this.setGameboard(gameboard);

		// Set gamer
		if (gamer == null || gamer.length != 2 || gamer[0] == null || gamer[1] == null) {
			throw new RuntimeException();
		}
		this.gamer = gamer;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	/**
	 * Initializes new game and creates a new gameboard and two new players.
	 */
	public void initialize() {
		gamer = new Spieler[2];

		// Create gameboard
		this.gameboard = new Spielbrett();

		// Ask if new game, restore last game or load CSV-File
		int newGameState = askNewGame();

		// Loading success
		boolean loadingSuccess = false;

		// If not new game, try to load
		if(newGameState == 2){
			DatenzugriffSerialisiert serial = new DatenzugriffSerialisiert();
			loadingSuccess = serial.loadGame(this);
		} else if(newGameState == 3){
			// Get filepath
			String path = getFilePath();

			// Get path and name
			String pathAndName[] = this.getPathAndName(path);
			if(pathAndName.length != 2) loadingSuccess = false;
			else{
				// Load from CSV
				loadingSuccess = load(pathAndName[0], pathAndName[1]);
			}
		}

		if (!loadingSuccess) {
			if(newGameState != 1) System.out.println("Fehler beim laden! (Existiert die Datei?)");
			
			// Lets create a new gameboard
			this.createGameBoard();

			// Create gamer 1
			gamer[0] = createNewPlayer(1);

			// Create gamer 2
			gamer[1] = createNewPlayer(2);
		}

		// Set start player
		this.currentGamer = gamer[0];
	}

	/**
	 * The game-loop is the main loop of the application. The loop checks for
	 * finished
	 */
	public void gameLoop() {
		// Output start gameboard
		this.outputGameboardCSV();

		// Set info for first run
		boolean firstRun = true;

		while (!gameFinished()) {
			if (!firstRun) {
				// Save serialized
				DatenzugriffSerialisiert serial = new DatenzugriffSerialisiert();
				serial.saveGame(this);

				// Ask for other save-methods
				this.askForSaving();

			} else firstRun = false;

			// Current player have to move
			this.currentGamer.move(this, null);

			// Output current gameboard
			this.outputGameboardCSV();
		}

	}

	/**
	 * 
	 */
	private void askForSaving() {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i <= maxLoopCount; i++) {
			try {
				// Output information
				System.out.print("Wollen Sie das Spiel speichern (J / N): ");

				// Read result
				String status = sc.next();
				status = status.toUpperCase();
				if (status.equals("J")) {
					// Get filepath
					String path = getFilePath();

					// Get path and name
					String pathAndName[] = this.getPathAndName(path);
					if(pathAndName.length != 2) continue;

					// Save as csv
					if(this.save(pathAndName[0], pathAndName[1])){
						System.out.println("Speichern erfolgreich!");
						return;
					}
					else System.out.println("Fehler beim speichern!");
				} else if (status.equals("N")) {
					return;
				}
			} catch (NoSuchElementException | IllegalStateException e) {
				// Clear input buffer
				sc.nextLine();

				// Continue loop
				continue;
			} finally {
				// Check if endless loop
				if (i == Spiel.maxLoopCount) return;
			}
		}
	}

	private String[] getPathAndName(String filePath){
		// Get file path
		String filePathSplittet[] = filePath.split("/");

		// Check if splittet path has values
		if(filePathSplittet.length <= 0) return new String[0];

		// Remove filename to get path
		filePath = filePath.replace(filePathSplittet[filePathSplittet.length - 1], "");

		return new String[]{filePath,filePathSplittet[filePathSplittet.length - 1]};
	}

	private String getFilePath(){
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i <= maxLoopCount; i++) {
			try {
				// Output information
				System.out.print("Path: ");

				// Read result
				String path = sc.next();
				System.out.println("");
				return path;
			} catch (NoSuchElementException | IllegalStateException e) {
			} finally {
				// Check if endless loop
				if (i == Spiel.maxLoopCount) break;
			}
		}
		return "";
	}

	/**
	 * 
	 */
	private void checkForNewDames() {
		Spielfeld felder[][] = this.gameboard.getFields();

		// Check if field is valid
		if (felder.length > 2) {

			// Check for new black dames
			for (int i = 0; i < felder.length; i++) {
				Spielfigur currentFigure = felder[i][0].getFigure();
				if (currentFigure != null && currentFigure.getColor() == FarbEnum.schwarz && !currentFigure.isDame())
					currentFigure.setDame(true);

				currentFigure = felder[i][felder[i].length - 1].getFigure();
				if (currentFigure != null && currentFigure.getColor() == FarbEnum.wei� && !currentFigure.isDame())
					currentFigure.setDame(true);
			}
		}
	}

	/**
	 * Checks if move is valid else it will throw exceptions
	 * 
	 * @param fromPoint
	 * @param toPoint
	 * @return True if move is valid
	 * @throws Spiel.eSamePositionException
	 * @throws Spiel.eNoDiagonalMoveException
	 * @throws Spiel.eOutOfGameboardException
	 * @throws Spiel.eNoFigureFoundOnFieldException
	 * @throws Spiel.eDestinationPointIsBlockedException
	 */
	public boolean moveIsValid(Point fromPoint, Point toPoint)
			throws Spiel.eSamePositionException, Spiel.eNoDiagonalMoveException, Spiel.eOutOfGameboardException,
			Spiel.eNoFigureFoundOnFieldException, Spiel.eDestinationPointIsBlockedException,
			Spiel.eDistanceToFarException, Spiel.eEnemyFigureSelectedException, Spiel.eNoBackJumpExcpetion,
			eOwnFigureIsBlockingException, eWayIsBlockedException {
		int diffX = (int) (toPoint.getX() - fromPoint.getX());
		int diffY = (int) (toPoint.getY() - fromPoint.getY());

		Spielfeld fromField = this.gameboard.getField((int) fromPoint.getX(), (int) fromPoint.getY());
		Spielfeld toField = this.gameboard.getField((int) toPoint.getX(), (int) toPoint.getY());

		// Check if both fields are the same
		if (fromPoint.equals(toPoint))
			throw new Spiel.eSamePositionException();

		// Check if move is diagonal
		if (!(diffX == diffY || (diffX * (-1) == diffY)))
			throw new Spiel.eNoDiagonalMoveException();

		// Check if toPoint and fromPoint are valid fields
		if (!this.isValidField(fromPoint, toPoint))
			throw new Spiel.eOutOfGameboardException();

		// Check if field have figure
		Spielfigur gameFigure = fromField.getFigure();
		if (gameFigure == null)
			throw new Spiel.eNoFigureFoundOnFieldException();

		// Check if destination have already a figure
		Spielfigur destinationfigure = toField.getFigure();
		if (destinationfigure != null)
			throw new Spiel.eDestinationPointIsBlockedException();

		// Check if figure is jumping to far or in wrong way
		if (!gameFigure.isDame()) {
			if (diffX > 1 || (diffX * (-1)) > 1) {
				if (!((diffX == 2 || (diffX * (-1)) == 2))) {
					throw new Spiel.eDistanceToFarException();
				} else {
					Spielfigur midfigure = this.gameboard
							.getField((int) fromPoint.getX() + (diffX / 2), (int) fromPoint.getY() + (diffY / 2))
							.getFigure();
					if (midfigure == null) {
						throw new Spiel.eDistanceToFarException();
					} else if (midfigure.getColor() == this.currentGamer.getColor()) {
						throw new Spiel.eOwnFigureIsBlockingException();
					}

				}
			} else {
				if (gameFigure.getColor() == FarbEnum.schwarz && diffY > 0)
					throw new Spiel.eNoBackJumpExcpetion();
				else if (gameFigure.getColor() == FarbEnum.wei� && diffY < 0)
					throw new Spiel.eNoBackJumpExcpetion();
			}
		} else {
			// Check for double stones
			if (doubleFiguresFound(fromPoint, toPoint))
				throw new Spiel.eWayIsBlockedException();

			// Check if blocked by own figure
			if (this.blockedByOwnFigure(fromPoint, toPoint))
				throw new Spiel.eOwnFigureIsBlockingException();
		}

		// Check if figure is from enemy team
		if (gameFigure.getColor() != this.currentGamer.getColor())
			throw new Spiel.eEnemyFigureSelectedException();

		return true;
	}

	/**
	 * @param fromPoint
	 * @param toPoint
	 * @return
	 */
	private boolean doubleFiguresFound(Point fromPoint, Point toPoint) {
		// Check if move-range is only one
		int diffX = (int) fromPoint.getX() - (int) toPoint.getX();
		if (diffX == 1 || (diffX * (-1)) == 1)
			return false;

		// Set help variables
		int moveX, moveY, currentX = (int) fromPoint.getX(), currentY = (int) fromPoint.getY();

		if (fromPoint.getX() < toPoint.getX())
			moveX = 1;
		else
			moveX = -1;

		if (fromPoint.getY() < toPoint.getY())
			moveY = 1;
		else
			moveY = -1;

		do {
			currentX += moveX;
			currentY += moveY;

			Spielfeld currentField = this.gameboard.getField(currentX, currentY);
			Spielfigur currentFigure = currentField.getFigure();

			Spielfeld nextField = this.gameboard.getField(currentX + moveX, currentY + moveY);
			Spielfigur nextFigure = nextField.getFigure();

			if (currentFigure != null && nextFigure != null) {
				return true;
			}
		} while (currentX != toPoint.getX() - moveX & currentY != toPoint.getY() - moveY);

		return false;
	}

	/**
	 * @param fromPoint
	 * @param toPoint
	 * @return
	 */
	private boolean blockedByOwnFigure(Point fromPoint, Point toPoint) {
		int moveX, moveY, currentX = (int) fromPoint.getX(), currentY = (int) fromPoint.getY();

		if (fromPoint.getX() < toPoint.getX())
			moveX = 1;
		else
			moveX = -1;

		if (fromPoint.getY() < toPoint.getY())
			moveY = 1;
		else
			moveY = -1;

		do {
			currentX += moveX;
			currentY += moveY;

			Spielfeld currentField = this.gameboard.getField(currentX, currentY);
			Spielfigur currentFigure = currentField.getFigure();

			if (currentFigure != null && currentFigure.getColor() == this.currentGamer.getColor()) {
				return true;
			}
		} while (currentX != toPoint.getX() & currentY != toPoint.getY());

		return false;
	}

	/**
	 * @param fromPoint
	 * @param toPoint
	 * @return
	 */
	private boolean isValidField(Point fromPoint, Point toPoint) {
		int boardSize = this.gameboard.getFields().length;

		if (fromPoint.getX() < 0 || fromPoint.getX() >= boardSize || fromPoint.getY() < 0
				|| fromPoint.getY() >= boardSize || toPoint.getX() < 0 || toPoint.getX() >= boardSize
				|| toPoint.getY() < 0 || toPoint.getY() >= boardSize) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.iBediener#move(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void move(Point fromPoint, Point toPoint)
			throws Spiel.eSamePositionException, Spiel.eNoDiagonalMoveException, Spiel.eOutOfGameboardException,
			Spiel.eNoFigureFoundOnFieldException, Spiel.eDestinationPointIsBlockedException,
			Spiel.eSomeOtherMoveErrorsException, Spiel.eDistanceToFarException, Spiel.eEnemyFigureSelectedException,
			Spiel.eNoBackJumpExcpetion, Spiel.eOwnFigureIsBlockingException, Spiel.eWayIsBlockedException {
		if (this.moveIsValid(fromPoint, toPoint)) {
			// Get fields
			Spielfeld fromField = this.gameboard.getField((int) fromPoint.getX(), (int) fromPoint.getY());
			Spielfeld toField = this.gameboard.getField((int) toPoint.getX(), (int) toPoint.getY());

			// Get moveable figure
			Spielfigur gameFigure = fromField.getFigure();

			// Remove every figure on the move-line
			boolean removed = this.removeFigures(fromPoint, toPoint);

			// Check for the "blowing"-rule
			if (!removed)
				this.checkForBlowing();

			// Check if figure is after blowing already exist
			if (fromField.getFigure() == gameFigure) {
				// Set new coordinations to figure
				gameFigure.setPoint(toPoint);

				// Remove figure from old field and set to new field
				toField.setFigure(gameFigure);
				fromField.removeFigure();

				// Check for new dame
				checkForNewDames();

				// Check if figure can jump again
				if (removed && this.canDestroyOtherFigures(toPoint).size() > 0) {
					// Do next jump
					currentGamer.move(this, toPoint);
				}
			}

			// Set next player
			if (this.currentGamer == this.gamer[0])
				this.currentGamer = this.gamer[1];
			else
				this.currentGamer = this.gamer[0];
		} else {
			// Some strange errors appears
			throw new eSomeOtherMoveErrorsException();
		}
	}

	/**
	 * @param point
	 * @return
	 */
	public ArrayList<Point> canDestroyOtherFigures(Point point) {
		int xCurrent = (int) point.getX(), yCurrent = (int) point.getY();

		ArrayList<Point> blowable = new ArrayList<>();

		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur figure = felder[i][j].getFigure();
				if (figure != null) {
					if (figure.getColor() != this.currentGamer.getColor()) {
						Point movePoint = new Point(i, j);
						if (xCurrent < (int) movePoint.getX())
							movePoint.setLocation(movePoint.getX() + 1, movePoint.getY());
						else
							movePoint.setLocation(movePoint.getX() - 1, movePoint.getY());

						if (yCurrent < (int) movePoint.getY())
							movePoint.setLocation(movePoint.getX(), movePoint.getY() + 1);
						else
							movePoint.setLocation(movePoint.getX(), movePoint.getY() - 1);

						try {
							if (moveIsValid(point, movePoint))
								blowable.add(movePoint);
						} catch (Exception e) {
						}
					}
				}
			}
		}

		return blowable;
	}

	private boolean canMove(FarbEnum color) {
		// Collect all available figures
		Spielfeld felder[][] = this.gameboard.getFields();
		ArrayList<Point> validFigures = new ArrayList<>();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur currentFigure = felder[i][j].getFigure();
				if (currentFigure != null && currentFigure.getColor() == color) {
					Point currentPosition = new Point(i, j);
					validFigures.add(currentPosition);
				}
			}
		}

		// Take first figure and try to move the figure
		for (int p = 0; p < validFigures.size(); p++) {
			// Take first figure
			Point fromPoint = validFigures.get(p);

			// Check for valid fields
			for (int i = 0; i < felder.length; i++) {
				for (int j = 0; j < felder[i].length; j++) {
					Point currentPoint = new Point(i, j);
					try {
						if (moveIsValid(fromPoint, currentPoint))
							return true;
					} catch (Exception e) {
					}
				}
			}
		}

		return false;
	}

	/*
	 * private int figureCount(Point fromPoint, Point toPoint) throws
	 * eOwnFigureIsBlockingException{ int moveX, moveY, currentX =
	 * (int)fromPoint.getX(), currentY = (int)fromPoint.getY(); int figures = 0;
	 * 
	 * if(fromPoint.getX() < toPoint.getX()) moveX = 1; else moveX = -1;
	 * 
	 * if(fromPoint.getY() < toPoint.getY()) moveY = 1; else moveY = -1;
	 * 
	 * do{ currentX += moveX; currentY += moveY;
	 * 
	 * Spielfeld currentField = this.gameboard.getField(currentX, currentY);
	 * Spielfigur currentFigure = currentField.getFigure();
	 * 
	 * if(currentFigure != null){ if(currentFigure.getColor() ==
	 * this.currentGamer.getColor()) throw new eOwnFigureIsBlockingException();
	 * 
	 * figures++; } }while(currentX != toPoint.getX() & currentY !=
	 * toPoint.getY());
	 * 
	 * return figures; }
	 */

	private void checkForBlowing() {
		// Create blowing list
		ArrayList<Spielfigur> figures = new ArrayList<>();

		// Detect every stone
		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur currentFigure = felder[i][j].getFigure();
				if (currentFigure != null && currentFigure.getColor() == this.currentGamer.getColor()) {
					if (canDestroyOtherFigures(currentFigure.getPosiiton()).size() > 0) {
						if (!figures.contains(currentFigure))
							figures.add(currentFigure);
					}
				}
			}
		}

		Point removePosition = null;

		if (figures.size() > 0) {
			System.out.println("");
			System.out.println("Pusten-Regel tritt in Kraft!");
			if (figures.size() == 1) {
				removePosition = figures.get(0).getPosiiton();
			} else {
				Point position = null;
				do {
					removePosition = null;

					System.out.println("Es stehen folgende Spielfiguren zur Auswahl:");
					for (int i = 0; i < figures.size(); i++) {
						if (i != 0)
							System.out.print("; ");
						System.out.print(i + ". " + this.currentGamer.posToString(figures.get(i).getPosiiton()));
					}
					System.out.println("");
					System.out.print("Bitte Spielfigur eingeben: ");

					try {
						position = this.currentGamer.inputPosition();

						for (Spielfigur figure : figures) {
							if (figure.getPosiiton() == position)
								removePosition = position;
						}

						if (removePosition == null)
							System.out.println("Fehler bei der Eingabe!");
					} catch (Exception e) {
						position = null;
					}
				} while (position == null && removePosition == null);
			}
		}

		if (removePosition != null) {
			int currentX = (int) removePosition.getX();
			int currentY = (int) removePosition.getY();

			this.gameboard.getField(currentX, currentY).removeFigure();

			System.out.println(
					"[Pusten] Folgende Spielfigur wird entfern: " + this.currentGamer.posToString(removePosition));
		}
	}

	private boolean removeFigures(Point fromPoint, Point toPoint) {
		int moveX, moveY, currentX = (int) fromPoint.getX(), currentY = (int) fromPoint.getY();
		boolean removed = false;

		if (fromPoint.getX() < toPoint.getX())
			moveX = 1;
		else
			moveX = -1;

		if (fromPoint.getY() < toPoint.getY())
			moveY = 1;
		else
			moveY = -1;

		do {
			currentX += moveX;
			currentY += moveY;

			Spielfeld currentField = this.gameboard.getField(currentX, currentY);
			Spielfigur currentFigure = currentField.getFigure();

			if (currentFigure != null) {
				if (currentFigure.getColor() == this.currentGamer.getColor())
					throw new RuntimeException();

				currentField.removeFigure();

				removed = true;
			}
		} while (currentX != toPoint.getX() & currentY != toPoint.getY());

		return removed;
	}

	/**
	 * Method for creating variable sized board. Gets the size and then creates
	 * the gameboard.
	 */
	private Spielbrett createGameBoard() {
		// Get gameboard size
		int fieldCount = getGameboardSize();

		// Create gameboard
		return new Spielbrett(fieldCount);
	}

	/**
	 * Method to convert the current gaming state into a String to save in CSV
	 * 
	 * @return returns a String of the current gaming state
	 */
	public String csvString() {
		// Get gameboard fields
		Spielfeld felder[][] = this.gameboard.getFields();
		String gameString = "";
		
		// first row: information of player 1
		gameString += this.getPlayer(1).getName() + ";" + this.getPlayer(1).getColor() + ";";
		if (this.getPlayer(1).getKi() == null)
			gameString += "null" + "\n";
		else
			gameString += "KI" + "\n";
		
		// second row: information of player 2
		gameString += this.getPlayer(2).getName() + ";" + this.getPlayer(2).getColor() + ";";
		if (this.getPlayer(2).getKi() == null)
			gameString += "null" + "\n";
		else
			gameString += "KI" + "\n";
		
		// third row: saves who is the current Player and the game size
		gameString += this.getCurrentGamer().getColor() + ";" + this.getGameboard().getFields().length + "\n";
		
		// fourth row.. saves the current board state
		for (int i = felder.length - 1; i >= 0; i--) {
			// For every column
			for (int j = 0; j < felder.length; j++) {
				// Get figure of field
				Spielfigur currentFigure = felder[j][i].getFigure();
				// Write seperator
				
				// Check if field has figure or not
				if (currentFigure == null) {
					// e for empty
					gameString += "e";
				} else {
					if (currentFigure.getColor() == FarbEnum.wei�) {
						if (currentFigure.isDame()) {
							gameString += "W+";
						} else {
							gameString += "W ";
						}
					} else if (currentFigure.isDame()) {
						gameString += "S+";
					} else {
						gameString += "S ";
					}
				}
				if(j != felder.length - 1) gameString += ";";
			}
			// End of line
			gameString += "\n";
		}
		return gameString;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	/**
	 * @return
	 */
	public Spielbrett getGameboard() {
		return this.gameboard;
	}

	/**
	 * @return
	 */
	public Spieler getPlayer(int playerID) {
		// Check if playerID and gamer is valid
		if (!(playerID >= 1 && playerID <= 2))
			throw new RuntimeException();
		if (gamer == null)
			throw new NullPointerException();
		if (gamer.length != 2)
			throw new RuntimeException();

		// Get gamer
		return this.gamer[playerID - 1];
	}

	public Spieler getCurrentGamer() {
		if (this.currentGamer == null)
			throw new RuntimeException();

		return this.currentGamer;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	public void setGameboard(Spielbrett gameboard) {
		if (gameboard != null)
			this.gameboard = gameboard;
		else
			throw new NullPointerException();
	}

	public void setPlayer(int playerID, Spieler gamer) {
		// Check if playerID and gamer is valid
		if (!(playerID >= 1 && playerID <= 2))
			throw new RuntimeException();
		if (gamer == null)
			throw new NullPointerException();

		// Set new gamer
		this.gamer[playerID - 1] = gamer;
	}

	public void setCurrentGamer(FarbEnum color) {
		// Check for errors
		if (this.gamer == null || this.gamer[0] == null || this.gamer[1] == null)
			throw new NullPointerException();

		// Detect new current player
		Spieler newCurrentGamer;
		if (this.gamer[0].getColor() == color)
			newCurrentGamer = this.gamer[0];
		else if (this.gamer[1].getColor() == color)
			newCurrentGamer = this.gamer[1];
		else
			throw new RuntimeException();

		// Set new current player
		this.currentGamer = newCurrentGamer;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.iBediener#load()
	 */
	public boolean load(String path, String name) {
		// Check if file name is valid
		if(!name.endsWith(".csv")) name += ".csv";

		// Check if path ends with "/"-tag
		if(!path.endsWith("/")) path += "/";

		// Check if path is valid dir
		File f = new File(path);
		if(!(f.exists() && f.isDirectory())) return false;

		// Load from CSV
		iDatenzugriff csv = new DatenzugriffCSV();
		return csv.loadGame(path,name,this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.iBediener#save() 
	 */
	public boolean save(String path, String name) {
		// Check if file name is valid
		if(!name.endsWith(".csv")) name += ".csv";

		// Check if path ends with "/"-tag
		if(!path.endsWith("/")) path += "/";

		// Check if path is valid dir
		File f = new File(path);
		if(!(f.exists() && f.isDirectory())) return false;

		// Safe as CSV
		iDatenzugriff csv = new DatenzugriffCSV();
		return csv.saveGame(path,name,this);
	}

	/**
	 * Forces the user to enter game board size and checks if number is even. If
	 * user fails to enter valid size multiple times, the size is set to 8x8
	 */
	@Override
	public int getGameboardSize() {
		// Create scanner
		Scanner sc = new Scanner(System.in);

		// Get field size
		int maxField = 20, minField = 8, fieldCount = 8;
		for (int i = 0; i <= maxLoopCount; i++) {
			try {
				// Output information
				System.out.print("Bitte Spielfeldgr��e angeben (" + minField + "-" + maxField + "):");
				// Read next field size
				fieldCount = sc.nextInt();
				// If size is valid, leave loop
				if (fieldCount >= minField && fieldCount <= maxField) {
					if (fieldCount % 2 == 0)
						break;
					else
						System.out.println("Nur gerade Spielfeldgr��en sind erlaubt!");
				}
			} catch (NoSuchElementException | IllegalStateException e) {
				// Clear input buffer
				sc.nextLine();

				// Continue loop
				continue;
			} finally {
				// Check if endless loop
				if (i == this.maxLoopCount) {
					System.out.println("No valid number detected, we will choose the value 8");
					fieldCount = 8;
				}
			}
		}
		return fieldCount;
	}

	/**
	 * This method is used to check if players are human or KIs and asks for the
	 * name of human players. If all entry is valid, it starts the game with the
	 * given data. If user fails to properly decide if player is human or KI
	 * multiple times, it is set to KI automatically If user fails to enter a
	 * proper name multiple times, it is set to Peter automatically
	 * 
	 * @param playerNumber
	 * @return
	 * 
	 */
	@Override
	public Spieler createNewPlayer(int playerNumber) {
		// create Scanner
		Scanner sc = new Scanner(System.in);

		// create Gamer
		int gamerID = 0;
		for (int i = 0; i <= maxLoopCount; i++) {
			try {
				// Get current color name
				String colorName;
				if (playerNumber == 1)
					colorName = "white";
				else
					colorName = "black";

				// Get gamer type
				System.out.println("Spieler " + playerNumber + " (" + colorName + "): Spieler (1) oder KI(2)?");
				System.out.print("Ihre Eingabe: ");
				gamerID = sc.nextInt();
				System.out.println("");

				// Check if type is valid
				if (gamerID == 1 || gamerID == 2)
					break;
			} catch (NoSuchElementException | IllegalStateException e) {
				// Clear input buffer
				sc.nextLine();
			} finally {
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
		if (playerNumber == 1)
			color = FarbEnum.wei�;
		else
			color = FarbEnum.schwarz;

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
			newGamer = new Spieler(gamerName, color);
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

	/**
	 * This method is used to save the game to a CSV file
	 */
	@Override
	public void outputGameboardCSV() {
		// Get gameboard fields
		Spielfeld felder[][] = this.gameboard.getFields();


		// Define start variable
		char currentRow = (char) (65 + felder.length - 1);
		int currentColumn = 1;

		// Create empty line
		System.out.println("");

		// For every row - DESC
		for (int i = felder.length - 1; i >= 0; i--) {
			currentColumn = 1;

			// Write colum name
			System.out.print(currentRow);

			// For every column
			for (int j = 0; j < felder.length; j++) {
				// Get figure of field
				Spielfigur currentFigure = felder[j][i].getFigure();

				// Write seperator
				System.out.print(";");

				// Check if field have figure or not
				if (currentFigure == null) {
					System.out.print("  ");
				} else {
					if (currentFigure.getColor() == FarbEnum.wei�) {
						if (currentFigure.isDame()) {
							System.out.print("W+");
						} else {
							System.out.print("W ");
						}
					} else if (currentFigure.isDame()) {
						System.out.print("S+");
					} else {
						System.out.print("S ");
					}
				}

				// Increase column value
				currentColumn++;
			}

			// End of line
			System.out.println("");

			// Increase row value
			int ascii = currentRow;
			currentRow = (char) --ascii;
		}

		// Draw column names
		for (int i = 0; i <= felder.length; i++) {
			if (i == 0)
				System.out.print(" ");
			else {
				System.out.print(";");
				System.out.print(String.format("%02d", i));
			}
		}
		System.out.println("");

		// Create empty line
		System.out.println("");

	}

	/**
	 * This checks if game is finished
	 */
	@Override
	public boolean gameFinished() {
		int whiteFigures = 0, blackFigures = 0;

		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur figure = felder[i][j].getFigure();
				if (figure != null) {
					if (figure.getColor() == FarbEnum.schwarz)
						blackFigures++;
					else if (figure.getColor() == FarbEnum.wei�)
						whiteFigures++;
				}
			}
		}

		String winName;
		if (whiteFigures == 0 || blackFigures == 0) {
			if (whiteFigures == 0)
				winName = FarbEnum.getColorName(FarbEnum.schwarz);
			else
				winName = FarbEnum.getColorName(FarbEnum.wei�);
		} else {
			boolean canMoveValue;
			if (this.currentGamer.getColor() == FarbEnum.schwarz)
				canMoveValue = canMove(FarbEnum.schwarz);
			else
				canMoveValue = canMove(FarbEnum.wei�);

			if (!canMoveValue) {
				if (this.currentGamer.getColor() == FarbEnum.schwarz)
					winName = FarbEnum.getColorName(FarbEnum.wei�);
				else
					winName = FarbEnum.getColorName(FarbEnum.schwarz);
			} else
				return false;
		}

		System.out.println("Herzlichen Gl�ckwunsch " + winName + "! Sie haben gewonnen");
		return true;
	}

	/**
	 * This method is used to ask if user wants to start a new game or continue
	 * playing from a saved game
	 */
	@Override
	public int askNewGame() {
		// Create help variables
		int gameType = 0;
		Scanner sc = new Scanner(System.in);

		// Get gametype
		for (int i = 0; i <= maxLoopCount; i++) {
			try {
				// Ask for new game / load game
				System.out.print("Create new game (1), restore last game (2) or load game (3): ");

				// Get result
				gameType = sc.nextInt();

				// Go to next line
				System.out.println("");

				// Check if result is valid
				if (gameType == 1 || gameType == 2 || gameType == 3) break;

			} catch (NoSuchElementException | IllegalStateException e) {
				// Clear input buffer
				sc.nextLine();
			} finally {
				// Check if endless loop
				if (i == maxLoopCount) {
					System.out.println("No valid number detected, we will choose 'new game'");
					gameType = 1;
				}
			}
		}

		// Return result
		return gameType;
	}
}