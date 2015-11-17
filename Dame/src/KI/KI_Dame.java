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

import Enumerations.FarbEnum;
import GUI.GameGUI;
import GUI.Logging;
import GUI.MainFrame;
import GameLogic.Spiel;
import GameLogic.Spieler;
import GameLogic.Spielfeld;
import GameLogic.Spielfigur;

//###########################################################
//## Class

public class KI_Dame extends KI implements Serializable {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	Timer timer;

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

	public void checkForMove(){
		System.out.println("TEST");
		if(GameGUI.globalPointer.isVisible() == true){
			Spiel game = MainFrame.globalPointer.getGame();
			if(game.getCurrentGamer() == this.player){
				Timer moveTimer = new Timer();
				moveTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						move(game, player);
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
		// Check for blowing rule
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
			// Collect all available figures
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

			if(validFigures.size() == 0) return;

			// Take random one figure and try to move the figure
			Point fromPoint = null, toPoint = null;
			do{
				// Take first figure
				int random = (int)(Math.random() * (validFigures.size() - 1));
				fromPoint = validFigures.get(random);

				// Check for valid fields
				ArrayList <Point> validToPosition = new ArrayList<>();
				for(int i = 0; i < felder.length; i++){
					for(int j = 0; j < felder[i].length; j++){
						Point currentPoint = new Point(i,j);
						try{
							if(game.moveIsValid(fromPoint, currentPoint)) validToPosition.add(currentPoint);
						}catch(Exception e){}
					}
				}

				// Check if at least one toPoint was valid
				if(validToPosition.size() > 0){
					random = (int)(Math.random() * (validToPosition.size() - 1));
					toPoint = validToPosition.get(random);
				}
				else{
					// Remove figure from list
					validFigures.remove(fromPoint);
				}
			}while((fromPoint == null || toPoint == null));

			try{
				game.move(fromPoint, toPoint);
			}catch (Exception e){
				throw new RuntimeException();
			}
		}
	}
}