package GameLogic;

//###########################################################
//## Imports

import java.io.*;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import Enumerations.FarbEnum;
import Interfaces.iBediener;
import Interfaces.iDatenzugriff;
import SavegameManager.*;

import java.awt.*;

//###########################################################
//## Class

@XmlRootElement(namespace = "DameSpiel")
@SuppressWarnings("serial")
public class SpielBean implements iBediener, Serializable {

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
	private boolean isTemporary = false;
	private Point fieldClicked = null;
	private boolean lockFieldClicked = false;
	private int gameID;

	@SuppressWarnings("unused")
	transient Logging log = new Logging();

	static int maxLoopCount = 10;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	/**
	 * Constructor
	 */
	public SpielBean() {
		gamer = new Spieler[2];
		
		gameID = (new Random()).nextInt((99999999 - 1) + 1) + 1;

		this.gameboard = new Spielbrett();
	}

	/**
	 * @param gameboard
	 * @param gamer
	 */
	public SpielBean(Spielbrett gameboard, Spieler gamer[]) {
		// Call default constructor
		this();

		// Set gameboard
		this.setGameboard(gameboard);

		// Set gamer
		if (gamer == null || gamer.length != 2 || gamer[0] == null || gamer[1] == null) {
			throw new RuntimeException();
		}

		this.setPlayer(1, gamer[0]);
		this.setPlayer(2, gamer[1]);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	public void nextFieldClicked(String position){
		try{
			Point pos = stringToPoint(position);

			if(this.fieldClicked != null){
				try{
					move(fieldClicked,pos);
				}catch (SpielBean.eSomeOtherMoveErrorsException e) {
					Logging.globalPointer.addErrorMessage("Unbekannter Fehler. Sorry.");
				}catch (SpielBean.eDestinationPointIsBlockedException e) {
					Logging.globalPointer.addErrorMessage("Ziel-Feld ist blockiert");
				}catch (SpielBean.eNoDiagonalMoveException e) {
					Logging.globalPointer.addErrorMessage("Ung¸ltige Bewegungsrichtung (nur diagonal ist erlaubt)");
				}catch (SpielBean.eNoFigureFoundOnFieldException e) {
					Logging.globalPointer.addErrorMessage("Feld hat keine g¸ltige Spielfigur");
				}catch (SpielBean.eOutOfGameboardException e) {
					Logging.globalPointer.addErrorMessage("Position ist auﬂerhalb des Spielfeldes");
				}catch (SpielBean.eSamePositionException e) {
					Logging.globalPointer.addErrorMessage("Spielfigur-Feld und Ziel-Feld sind identisch");
				}catch (SpielBean.eDistanceToFarException e){
					Logging.globalPointer.addErrorMessage("Sorry, Steine d¸rfen nur 1 Feld weit springen");
				}catch(SpielBean.eEnemyFigureSelectedException e){
					Logging.globalPointer.addErrorMessage("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
				}catch(SpielBean.eOwnFigureIsBlockingException e){
					Logging.globalPointer.addErrorMessage("Kann keine eigenen Steine ¸berspringen");
				}catch(SpielBean.eNoBackJumpExcpetion e){
					Logging.globalPointer.addErrorMessage("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
				}catch(SpielBean.eWayIsBlockedException e){
					Logging.globalPointer.addErrorMessage("Es d¸rfen keine 2 Stein gleichzeitig ¸bers¸rungen werden");
				}catch (Exception e){
					Logging.globalPointer.addErrorMessage("Sry, some other problems");
				}finally{
					if(lockFieldClicked == false) this.fieldClicked = null;
				}
			}
			else{
				this.fieldClicked = pos;
			}
		}catch(eInvalidPointException e){
			Logging.globalPointer.addErrorMessage("Field is invalid");
		}
	}

	/**
	 * @param position
	 * @return
	 */
	public String posToString(Point position){
		char firstLetter = (char)(65 + position.getX());

		StringBuilder returnValue = new StringBuilder();
		returnValue.append(firstLetter);
		returnValue.append((int)position.getY() + 1);

		return returnValue.toString();
	}

	/**
	 * @param sPoint
	 * @return
	 * @throws Spiel.eInvalidPointException
	 */
	public Point stringToPoint(String sPoint) throws SpielBean.eInvalidPointException{
		if(sPoint.length() >= 2){
			if (sPoint.charAt(0) >= 65 && sPoint.charAt(0) <= 90) {
				Point point = new Point();
				int y = sPoint.charAt(0) - 65;

				if (sPoint.charAt(1) >= 48 && sPoint.charAt(1) <= 57) {
					int x = -1;
					x = (sPoint.charAt(1) - 48) - 1;

					
					if (sPoint.length() > 2 && sPoint.charAt(2) >= 48 && sPoint.charAt(2) <= 57) {
						
						x = x * 10;
						
						x += (sPoint.charAt(2) - 48) - 1;

						if (x < 0 || y < 0) throw new SpielBean.eInvalidPointException();
					} 
					
					point.setLocation(y, x);
					return point;

				} 
			}
		}

		throw new SpielBean.eInvalidPointException();
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
				if (currentFigure != null && currentFigure.getColor() == FarbEnum.weiﬂ && !currentFigure.isDame())
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
			throws SpielBean.eSamePositionException, SpielBean.eNoDiagonalMoveException, SpielBean.eOutOfGameboardException,
			SpielBean.eNoFigureFoundOnFieldException, SpielBean.eDestinationPointIsBlockedException,
			SpielBean.eDistanceToFarException, SpielBean.eEnemyFigureSelectedException, SpielBean.eNoBackJumpExcpetion,
			eOwnFigureIsBlockingException, eWayIsBlockedException {
		int diffX = (int) (toPoint.getX() - fromPoint.getX());
		int diffY = (int) (toPoint.getY() - fromPoint.getY());

		Spielfeld fromField = this.gameboard.getField((int) fromPoint.getX(), (int) fromPoint.getY());
		Spielfeld toField = this.gameboard.getField((int) toPoint.getX(), (int) toPoint.getY());

		// Check if both fields are the same
		if (fromPoint.equals(toPoint))
			throw new SpielBean.eSamePositionException();

		// Check if move is diagonal
		if (!(diffX == diffY || (diffX * (-1) == diffY)))
			throw new SpielBean.eNoDiagonalMoveException();

		// Check if toPoint and fromPoint are valid fields
		if (!this.isValidField(fromPoint) || !this.isValidField(toPoint))
			throw new SpielBean.eOutOfGameboardException();

		// Check if field have figure
		Spielfigur gameFigure = fromField.getFigure();
		if (gameFigure == null)
			throw new SpielBean.eNoFigureFoundOnFieldException();

		// Check if destination have already a figure
		Spielfigur destinationfigure = toField.getFigure();
		if (destinationfigure != null)
			throw new SpielBean.eDestinationPointIsBlockedException();

		// Check if figure is jumping to far or in wrong way
		if (!gameFigure.isDame()) {
			if (diffX > 1 || (diffX * (-1)) > 1) {
				if (!((diffX == 2 || (diffX * (-1)) == 2))) {
					throw new SpielBean.eDistanceToFarException();
				} else {
					Spielfigur midfigure = this.gameboard
							.getField((int) fromPoint.getX() + (diffX / 2), (int) fromPoint.getY() + (diffY / 2))
							.getFigure();
					if (midfigure == null) {
						throw new SpielBean.eDistanceToFarException();
					} else if (midfigure.getColor() == this.currentGamer.getColor()) {
						throw new SpielBean.eOwnFigureIsBlockingException();
					}

				}
			} else {
				if (gameFigure.getColor() == FarbEnum.schwarz && diffY > 0)
					throw new SpielBean.eNoBackJumpExcpetion();
				else if (gameFigure.getColor() == FarbEnum.weiﬂ && diffY < 0)
					throw new SpielBean.eNoBackJumpExcpetion();
			}
		} else {
			// Check for double stones
			if (doubleFiguresFound(fromPoint, toPoint))
				throw new SpielBean.eWayIsBlockedException();

			// Check if blocked by own figure
			if (this.blockedByOwnFigure(fromPoint, toPoint))
				throw new SpielBean.eOwnFigureIsBlockingException();
		}

		// Check if figure is from enemy team
		if (gameFigure.getColor() != this.currentGamer.getColor())
			throw new SpielBean.eEnemyFigureSelectedException();

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
	private boolean isValidField(Point point) {
		int boardSize = this.gameboard.getFields().length;

		if (point.getX() < 0 || point.getX() >= boardSize || 
				point.getY() < 0 || point.getY() >= boardSize) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 */
	public void switchCurrentPlayer(){
		if(this.currentGamer == this.gamer[0]) this.currentGamer = this.gamer[1];
		else this.currentGamer = this.gamer[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.iBediener#move(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void move(Point fromPoint, Point toPoint)
			throws SpielBean.eSamePositionException, SpielBean.eNoDiagonalMoveException, SpielBean.eOutOfGameboardException,
			SpielBean.eNoFigureFoundOnFieldException, SpielBean.eDestinationPointIsBlockedException,
			SpielBean.eSomeOtherMoveErrorsException, SpielBean.eDistanceToFarException, SpielBean.eEnemyFigureSelectedException,
			SpielBean.eNoBackJumpExcpetion, SpielBean.eOwnFigureIsBlockingException, SpielBean.eWayIsBlockedException {
		if (this.moveIsValid(fromPoint, toPoint)) {
			// Get fields
			Spielfeld fromField = this.gameboard.getField((int) fromPoint.getX(), (int) fromPoint.getY());
			Spielfeld toField = this.gameboard.getField((int) toPoint.getX(), (int) toPoint.getY());

			// Get moveable figure
			Spielfigur gameFigure = fromField.getFigure();

			// Remove every figure on the move-line
			boolean removed = this.removeFigures(fromPoint, toPoint);

			// Check for the "blowing"-rule
			if (!removed) this.checkForBlowing();

			// Check if figure is after blowing already exist
			if (fromField.getFigure() == gameFigure) {

				// Set new coordinations to figure
				gameFigure.setPoint(toPoint);

				// Remove figure from old field and set to new field
				toField.setFigure(gameFigure);
				fromField.removeFigure();

				// Check for new dame
				checkForNewDames();

				String startPos = posToString(fromPoint);
				String endPos = posToString(toPoint);
				if(!this.isTemporary){

					// Output status to logging
					Logging.globalPointer.addMessage(startPos + " -> " + endPos);
				}
			}

			// Check if figure can jump again
			if (removed && this.canDestroyOtherFigures(toPoint).size() > 0) {
				this.fieldClicked = toPoint;
				this.lockFieldClicked = true;
			}
			else{
				this.fieldClicked = null;
				this.lockFieldClicked = false;
				
				this.switchCurrentPlayer();
			}
		} else {
			// Some strange errors appears
			throw new eSomeOtherMoveErrorsException();
		}
	}

	public Point getNextFigure(Point currentPos, int xIncrease, int yIncrease){
		Spielfigur figure;
		do{
			currentPos.setLocation(currentPos.getX() + xIncrease, currentPos.getY() + yIncrease);

			if(this.isValidField(currentPos))
				figure = this.getGameboard().getField((int) currentPos.getX(), (int) currentPos.getY()).getFigure();
			else {
				figure = null;
				break;
			}

		}while(figure == null);

		if(figure == null) return null;
		else return currentPos;
	}

	public boolean willBeDestroyed(Point point){
		Point checkAblePositions[] = new Point[4];
		checkAblePositions[0] = getNextFigure(new Point((int)point.getX(),(int) point.getY()), -1,  1); // TopLeft
		checkAblePositions[1] = getNextFigure(new Point((int)point.getX(),(int) point.getY()),  1,  1); // TopRight
		checkAblePositions[2] = getNextFigure(new Point((int)point.getX(),(int) point.getY()),  1, -1); // BottomRight
		checkAblePositions[3] = getNextFigure(new Point((int)point.getX(),(int) point.getY()), -1, -1); // BottomLeft

		for(Point checkAblePoint : checkAblePositions){
			// Check if point is valid
			if(checkAblePoint == null) continue;

			if(this.isValidField(checkAblePoint)){
				ArrayList<Point> blowable = this.canDestroyOtherFigures(checkAblePoint);
				if(blowable.size() != 0){
					for(Point blowPoint : blowable){
						Point resultetPoint = new Point();

						if(blowPoint.getX() < checkAblePoint.getX()) resultetPoint.setLocation(blowPoint.getX() + 1, blowPoint.getY());
						else resultetPoint.setLocation(blowPoint.getX() - 1, resultetPoint.getY());

						if(blowPoint.getY() < checkAblePoint.getY()) resultetPoint.setLocation(resultetPoint.getX(), blowPoint.getY() + 1);
						else resultetPoint.setLocation(resultetPoint.getX(), blowPoint.getY() - 1);

						if(resultetPoint.getX() == point.getX() && 
								resultetPoint.getY() == point.getY()){
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * @param point
	 * @return
	 */
	public ArrayList<Point> canDestroyOtherFigures(Point point) {
		// Create new list with blowable move directions
		ArrayList<Point> blowable = new ArrayList<>();

		// Check if point is valid
		if(!this.isValidField(point)) return blowable;

		// Create current x and y coords
		int xCurrent = (int) point.getX(), yCurrent = (int) point.getY();

		// Detect moveable fields
		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur figure = felder[i][j].getFigure();
				if (figure != null) {
					if (!figure.getColor().equals(this.currentGamer.getColor())) {
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
							if (moveIsValid(point, movePoint)) blowable.add(movePoint);
						} catch (Exception e) {
						}
					}
				}
			}
		}

		return blowable;
	}

	/**
	 * @param color
	 * @return
	 */
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

	private void checkForBlowing() {
		// Create blowing list
		ArrayList<Spielfigur> figures = new ArrayList<>();

		// Detect every stone
		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur currentFigure = felder[i][j].getFigure();
				if (currentFigure != null && currentFigure.getColor().equals(this.currentGamer.getColor())) {
					if (canDestroyOtherFigures(new Point(i,j)).size() > 0) {
						if (!figures.contains(currentFigure))
							figures.add(currentFigure);
					}
				}
			}
		}

		Point removePosition = null;

		if (figures.size() > 0) {
			Random rand = new Random();
			int number = rand.nextInt(figures.size());
			removePosition = figures.get(number).getPosiiton();
		}

		if (removePosition != null) {
			int currentX = (int) removePosition.getX();
			int currentY = (int) removePosition.getY();

			this.gameboard.getField(currentX, currentY).removeFigure();

			Logging.globalPointer.addMessage("[Pusten] Folgende Spielfigur wird entfernt: " + this.posToString(removePosition));
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
	 * Method to convert the current gaming state into a String to save in CSV
	 * 
	 * @return returns a String of the current gaming state
	 */
	public String csvString() {
		// Get gameboard fields
		Spielfeld felder[][] = this.gameboard.getFields();
		String gameString = "";
//		for(int i = 0; i < felder.length; i++) {
//			for(int j = 0; j < felder[i].length; j++) {
//				gameString += felder[i][j].getID();
//			}
//			                                           
//		}

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
					if (currentFigure.getColor() == FarbEnum.weiﬂ) {
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

	@XmlElement( name = "id")
	public int getID(){
		return this.gameID;
	}
	
	@XmlElement( name = "player")
	public Spieler[] getGamer(){
		return this.gamer;
	}
	
	public Point getFieldClicked(){
		return this.fieldClicked;
	}

	@XmlElement (name = "gameboard")
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

	@XmlElement (name = "currentGamer")
	public Spieler getCurrentGamer() {
		if (this.currentGamer == null) return null;

		return this.currentGamer;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	public void setIsTemporary(boolean state){
		this.isTemporary = state;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public SpielBean clone(){
		SpielBean newObj = new SpielBean();
		if(this.gameboard != null) newObj.setGameboard(this.getGameboard().clone());
		if(this.getPlayer(1) != null) newObj.setPlayer(1, this.getPlayer(1).clone());
		//if(this.getPlayer(2) != null)newObj.setPlayer(2, this.getPlayer(2).clone());
		//if(this.currentGamer != null)newObj.setCurrentGamer(this.currentGamer.getColor());

		return newObj;
	}

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
		return this.gameboard.felder.length;
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

		// Create empty line
		System.out.println("");

		// For every row - DESC
		for (int i = felder.length - 1; i >= 0; i--) {

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
					if (currentFigure.getColor() == FarbEnum.weiﬂ) {
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
	public FarbEnum gameFinished() {
		int whiteFigures = 0, blackFigures = 0;

		Spielfeld felder[][] = this.gameboard.getFields();
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[i].length; j++) {
				Spielfigur figure = felder[i][j].getFigure();
				if (figure != null) {
					if (figure.getColor() == FarbEnum.schwarz)
						blackFigures++;
					else if (figure.getColor() == FarbEnum.weiﬂ)
						whiteFigures++;
				}
			}
		}

		FarbEnum winColor = null;
		if (whiteFigures == 0 || blackFigures == 0) {
			if (whiteFigures == 0)
				winColor = FarbEnum.schwarz;
			else
				winColor = FarbEnum.weiﬂ;
		} else {
			boolean canMoveValue;
			if (this.currentGamer.getColor() == FarbEnum.schwarz)
				canMoveValue = canMove(FarbEnum.schwarz);
			else
				canMoveValue = canMove(FarbEnum.weiﬂ);

			if (!canMoveValue) {
				if (this.currentGamer.getColor() == FarbEnum.schwarz)
					winColor = FarbEnum.weiﬂ;
				else
					winColor = FarbEnum.schwarz;
			} else
				return null;
		}

		return winColor;
	}
}