package SavegameManager;

import java.awt.Point;

import GameLogic.Spiel;
import GameLogic.Spiel.eDestinationPointIsBlockedException;
import GameLogic.Spiel.eDistanceToFarException;
import GameLogic.Spiel.eEnemyFigureSelectedException;
import GameLogic.Spiel.eNoBackJumpExcpetion;
import GameLogic.Spiel.eNoDiagonalMoveException;
import GameLogic.Spiel.eNoFigureFoundOnFieldException;
import GameLogic.Spiel.eOutOfGameboardException;
import GameLogic.Spiel.eOwnFigureIsBlockingException;
import GameLogic.Spiel.eSamePositionException;
import GameLogic.Spiel.eSomeOtherMoveErrorsException;
import GameLogic.Spiel.eWayIsBlockedException;
import Interfaces.iBediener;

public class SpielTestSpeichern {

	public static void main(String[] args) throws eSamePositionException, eNoDiagonalMoveException, eOutOfGameboardException, eNoFigureFoundOnFieldException, eDestinationPointIsBlockedException, eSomeOtherMoveErrorsException, eDistanceToFarException, eEnemyFigureSelectedException, eNoBackJumpExcpetion, eOwnFigureIsBlockingException, eWayIsBlockedException {
		iBediener newGame = new Spiel();
		newGame.move(new Point(0,3), new Point(1, 4));
		//newGame.save();
	}

}
