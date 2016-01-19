//###########################################################
//## Package

package KI;

//###########################################################
//## Imports

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;

import Enumerations.FarbEnum;
import GUI.*;
import GameLogic.SpielBean;
import GameLogic.Spieler;
import GameLogic.Spielfeld;
import GameLogic.Spielfigur;

//###########################################################
//## Class

/**
 * @author ehrha
 *
 */
@SuppressWarnings("serial")
public class KI_Dame extends KI implements Serializable {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private boolean moveStarted = false;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public KI_Dame(){
		
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods	


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public KI_Dame clone(){
		KI_Dame newObj = new KI_Dame();

		return newObj;
	}

	/* (non-Javadoc)
	 * @see KI.KI#move(GameLogic.Spiel, GameLogic.Spieler)
	 */
	public void move(SpielBean game){
		//+++++++++++++++++++++++++++++++++++++++++++++++
		//++ Check for blowing rule

		ArrayList <Point> blowable = new ArrayList<>();
		Spielfeld felder[][] = game.getGameboard().getFields();
		for(int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++){
				Spielfigur currentFigure = felder[i][j].getFigure();
				if(currentFigure != null && currentFigure.getColor() == game.getCurrentGamer().getColor()){
					Point currentPosition = new Point(i,j);
					if(game.canDestroyOtherFigures(currentPosition).size() > 0) blowable.add(currentPosition);
				}
			}
		}

		if(blowable.size() != 0){
			try{
				int random = (int)(Math.random() * (blowable.size() - 1));
				Point fromPoint = blowable.get(random);
				if(game.getFieldClicked() != null) fromPoint = game.getFieldClicked();
				ArrayList <Point> movePoints = game.canDestroyOtherFigures(fromPoint);

				random = (int)(Math.random() * (movePoints.size() - 1));
				game.move(fromPoint, movePoints.get(random));
			}
			catch(Exception e){
				throw new RuntimeException();
			}
		}
		else{
			//+++++++++++++++++++++++++++++++++++++++++++++++
			//++ Get every valid move (save and unsafe moves)

			ArrayList <Point> validFigures = new ArrayList<>();
			for(int i = 0; i < felder.length; i++){
				for(int j = 0; j < felder[i].length; j++){
					Spielfigur currentFigure = felder[i][j].getFigure();
					if(currentFigure != null && currentFigure.getColor() == game.getCurrentGamer().getColor()){
						Point currentPosition = new Point(i,j);
						validFigures.add(currentPosition);
					}
				}
			}

			ArrayList <moveScenario> validToPosition = new ArrayList<>();
			ArrayList <moveScenario> validSaveToPosition = new ArrayList<>();

			ArrayList <moveScenario> validToPositionDame = new ArrayList<>();
			ArrayList <moveScenario> validSaveToPositionDame = new ArrayList<>();

			for(Point figurePoint : validFigures){
				// Check for valid fields
				for(int i = 0; i < felder.length; i++){
					for(int j = 0; j < felder[i].length; j++){
						Point currentPoint = new Point(i,j);
						try{							
							if(game.moveIsValid(figurePoint.getLocation(), currentPoint)){
								// Move figure to position
								Spielfeld fieldFrom = game.getGameboard().getField((int) figurePoint.getX(), (int) figurePoint.getY());
								Spielfigur figure = fieldFrom.getFigure();
								Spielfeld fieldTo = game.getGameboard().getField((int) currentPoint.getX(), (int) currentPoint.getY());
								fieldFrom.setFigure(null);
								fieldTo.setFigure(figure);
								game.switchCurrentPlayer();

								if(!figure.isDame()){
									if(game.willBeDestroyed(currentPoint)) validToPosition.add(new moveScenario(figurePoint.getLocation(), currentPoint));
									else validSaveToPosition.add(new moveScenario(figurePoint.getLocation(), currentPoint));
								}else{
									if(game.willBeDestroyed(currentPoint)) validToPositionDame.add(new moveScenario(figurePoint.getLocation(), currentPoint));
									else validSaveToPositionDame.add(new moveScenario(figurePoint.getLocation(), currentPoint));
								}

								// Move person back
								fieldTo.setFigure(null);
								fieldFrom.setFigure(figure);
								game.switchCurrentPlayer();
							}
						}catch(Exception e){}
					}
				}
			}

			try{
				moveScenario choosenScenario = null;


				// Choose one of those move-scenarios
				if(validSaveToPosition.size() > 0){
					ArrayList < moveScenario > best = new ArrayList<>();
					for(moveScenario move : validSaveToPosition){

						if(game.getCurrentGamer().getColor() == FarbEnum.schwarz){
							if(choosenScenario == null || move.getFrom().getY() < choosenScenario.getFrom().getY()){
								choosenScenario = move;
								best.clear();
								best.add(move);
							}else if(move.getFrom().getY() == choosenScenario.getFrom().getY()) best.add(move);
						}else{
							if(choosenScenario == null || move.getFrom().getY() > choosenScenario.getFrom().getY()){
								choosenScenario = move;
								best.clear();
								best.add(move);
							}else if(move.getFrom().getY() == choosenScenario.getFrom().getY()) best.add(move);
						}
					}
					
					if(best.size() > 1){
						int random = (int)(Math.random() * (best.size() - 1));
						choosenScenario = best.get(random);
					}
				}else if(validSaveToPositionDame.size() > 0){
					int random = (int)(Math.random() * (validSaveToPositionDame.size() - 1));
					choosenScenario = validSaveToPositionDame.get(random);
				}else if(validToPosition.size() > 0){
					int random = (int)(Math.random() * (validToPosition.size() - 1));
					choosenScenario = validToPosition.get(random);
				}else if(validToPositionDame.size() > 0){
					int random = (int)(Math.random() * (validToPositionDame.size() - 1));
					choosenScenario = validToPositionDame.get(random);
				}
				else throw new RuntimeException();

				if(choosenScenario != null){
					game.move(choosenScenario.from, choosenScenario.to);
				}
			}catch (Exception e){
				throw new RuntimeException();
			}
		}
	}

	//###########################################################
	//## Inner class

	public class moveScenario{
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Properties

		private Point from;
		private Point to;

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Constructor

		public moveScenario(Point from, Point to){
			this.setFrom(from);
			this.setTo(to);
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Getter)

		public Point getFrom(){
			return this.from;
		}

		public Point getTo(){
			return this.to;
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Setter)

		public void setFrom(Point from){
			this.from = from;
		}

		public void setTo(Point to){
			this.to = to;
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}
}