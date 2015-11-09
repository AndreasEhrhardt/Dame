//###########################################################
//## Package

package Interfaces;

//###########################################################
//## Imports

import java.awt.Point;

import GameLogic.Spiel;
import GameLogic.Spieler;
import GameLogic.Spiel.eOwnFigureIsBlockingException;
import GameLogic.Spiel.eWayIsBlockedException;

//###########################################################
//## Class

public interface iBediener {
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * Check all fields and generates a gameboard with CSV-notation
	 * @return
	 */
	void outputGameboardCSV();

	/**
	 * Method to move a figure to another position
	 * @param fromPoint Current position of the figure
	 * @param toPoint New position of the figure
	 * @throws Spiel.eSamePositionException fromtPoint and toPoint have the same position
	 * @throws Spiel.eNoDiagonalMoveException x-direction and y-direction are not equal
	 * @throws Spiel.eOutOfGameboardException One position is out of bound
	 * @throws Spiel.eNoFigureFoundOnFieldException The fromPoint-field have no figure
	 * @throws Spiel.eDestinationPointIsBlockedException On destination point is already a figure
	 * @throws Spiel.eSomeOtherMoveErrorsException Unkown error, should not happens
	 * @throws Spiel.eDistanceToFarException Figure is moving further than allowed
	 * @throws Spiel.eEnemyFigureSelectedException Player choosed a figure from enemy team
	 * @throws Spiel.eNoBackJumpExcpetion A normal figure is trying to jump backward
	 * @throws Spiel.eOwnFigureIsBlockingException On the way from fromPoint to toPoint is a own figure
	 * @throws Spiel.eWayIsBlockedException Double figures found on move-way
	 */
	void move(Point fromPoint, Point toPoint)
			throws Spiel.eSamePositionException, Spiel.eNoDiagonalMoveException, Spiel.eOutOfGameboardException,
			Spiel.eNoFigureFoundOnFieldException, Spiel.eDestinationPointIsBlockedException, Spiel.eSomeOtherMoveErrorsException,
			Spiel.eDistanceToFarException, Spiel.eEnemyFigureSelectedException, Spiel.eNoBackJumpExcpetion,
			Spiel.eOwnFigureIsBlockingException, Spiel.eWayIsBlockedException;

	/** 
	 * Returns the current size of the gameboard
	 * @return Gameboard size
	 */
	int getGameboardSize();
	
	/**
	 * Check if one player have won the game
	 * @return Returns the current size of the gameboard
	 */
	boolean gameFinished();
	
	/**
	 * Ask if a new game should be generated or a exist one should be load
	 * @return
	 */
	int askNewGame();
	
	/**
	 * 
	 * @return
	 */
	boolean save(String path, String name);
	
	/**
	 * 
	 * @return
	 */
	boolean load(String path, String name);
	
	/**
	 * Creates a new player or KI
	 * @param playerNumber Current player number (valid = 1-2)
	 * @return The new player will be returned
	 */
	Spieler createNewPlayer(int playerNumber);

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
}
