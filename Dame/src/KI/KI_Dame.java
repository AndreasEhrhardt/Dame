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

import GUI.*;
import GameLogic.Spiel;
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

	Timer timer;
	boolean moveStarted = false;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public KI_Dame(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				checkForMove();
			}
		}, 0, 1 * 1 * 50);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods	

	/**
	 * 
	 */
	public void checkForMove(){		
		if(GameGUI.globalPointer.isVisible() == true){
			Spiel game = MainFrame.globalPointer.getGame();
			if(game.getCurrentGamer() == this.player && !moveStarted){
				moveStarted = true;
				Timer moveTimer = new Timer();
				moveTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						move(game, player);
						moveStarted = false;
					}
				}, 500); 
			}
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	/* (non-Javadoc)
	 * @see KI.KI#move(GameLogic.Spiel, GameLogic.Spieler)
	 */
	public void move(Spiel game, Spieler player){
		if(MainFrame.globalPointer.getGame().getCurrentGamer() != this.player) return;

		//+++++++++++++++++++++++++++++++++++++++++++++++
		//++ Check for blowing rule
		
		ArrayList <Point> blowable = new ArrayList<>();
		Spielfeld felder[][] = game.getGameboard().getFields();
		for(int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++){
				Spielfigur currentFigure = felder[i][j].getFigure();
				if(currentFigure != null && currentFigure.getColor() == player.getColor()){
					Point currentPosition = new Point(i,j);
					if(game.canDestroyOtherFigures(currentPosition).size() > 0) blowable.add(currentPosition);
				}
			}
		}

		if(blowable.size() != 0){
			try{
				int random = (int)(Math.random() * (blowable.size() - 1));
				Point fromPoint = blowable.get(random);
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
					if(currentFigure != null && currentFigure.getColor() == player.getColor()){
						Point currentPosition = new Point(i,j);
						validFigures.add(currentPosition);
					}
				}
			}

			// Take random one figure and try to move the figure
			ArrayList <moveScenario> validToPosition = new ArrayList<>();
			ArrayList <moveScenario> validSaveToPosition = new ArrayList<>();
			
			for(Point figure : validFigures){
				// Check for valid fields
				for(int i = 0; i < felder.length; i++){
					for(int j = 0; j < felder[i].length; j++){
						Point currentPoint = new Point(i,j);
						try{							
							if(game.moveIsValid(figure.getLocation(), currentPoint)){
								Spiel tempGame = game.clone();
								tempGame.move(figure.getLocation(), currentPoint);
								
								if(tempGame.willBeDestroyed(currentPoint)) validToPosition.add(new moveScenario(figure.getLocation(), currentPoint));
								else validSaveToPosition.add(new moveScenario(figure.getLocation(), currentPoint));
							}
						}catch(Exception e){}
					}
				}
			}

			try{
				moveScenario choosenScenario = null;
				
				// Choose one of those move-scenarios
				if(validSaveToPosition.size() > 0){
					int random = (int)(Math.random() * (validSaveToPosition.size() - 1));
					choosenScenario = validSaveToPosition.get(random);
				}
				else if(validToPosition.size() > 0){
					int random = (int)(Math.random() * (validToPosition.size() - 1));
					choosenScenario = validToPosition.get(random);
				}
				else throw new RuntimeException();
				
				if(choosenScenario != null) game.move(choosenScenario.from, choosenScenario.to);
			}catch (Exception e){
				System.out.println("Unexpected exception: " + e);
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