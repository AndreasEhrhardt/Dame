//###########################################################
//## Package

package Interfaces;

//###########################################################
//## Imports

import java.awt.Point;

import Enumerations.FarbEnum;
import GameLogic.SpielBean;
import GameLogic.Spieler;
import GameLogic.SpielBean.eOwnFigureIsBlockingException;
import GameLogic.SpielBean.eWayIsBlockedException;

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
	 * @throws SpielBean.eSamePositionException fromtPoint and toPoint have the same position
	 * @throws SpielBean.eNoDiagonalMoveException x-direction and y-direction are not equal
	 * @throws SpielBean.eOutOfGameboardException One position is out of bound
	 * @throws SpielBean.eNoFigureFoundOnFieldException The fromPoint-field have no figure
	 * @throws SpielBean.eDestinationPointIsBlockedException On destination point is already a figure
	 * @throws SpielBean.eSomeOtherMoveErrorsException Unkown error, should not happens
	 * @throws SpielBean.eDistanceToFarException Figure is moving further than allowed
	 * @throws SpielBean.eEnemyFigureSelectedException Player choosed a figure from enemy team
	 * @throws SpielBean.eNoBackJumpExcpetion A normal figure is trying to jump backward
	 * @throws SpielBean.eOwnFigureIsBlockingException On the way from fromPoint to toPoint is a own figure
	 * @throws SpielBean.eWayIsBlockedException Double figures found on move-way
	 */
	void move(Point fromPoint, Point toPoint)
			throws SpielBean.eSamePositionException, SpielBean.eNoDiagonalMoveException, SpielBean.eOutOfGameboardException,
			SpielBean.eNoFigureFoundOnFieldException, SpielBean.eDestinationPointIsBlockedException, SpielBean.eSomeOtherMoveErrorsException,
			SpielBean.eDistanceToFarException, SpielBean.eEnemyFigureSelectedException, SpielBean.eNoBackJumpExcpetion,
			SpielBean.eOwnFigureIsBlockingException, SpielBean.eWayIsBlockedException;

	/** 
	 * Returns the current size of the gameboard
	 * @return Gameboard size
	 */
	int getGameboardSize();
	
	/**
	 * Check if one player have won the game
	 * @return Returns the current size of the gameboard
	 */
	FarbEnum gameFinished();
	
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
