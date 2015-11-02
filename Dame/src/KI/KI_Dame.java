package KI;
import java.awt.*;
import java.util.ArrayList;

import GameLogic.Spiel;
import GameLogic.Spieler;
import GameLogic.Spielfeld;
import GameLogic.Spielfigur;

public class KI_Dame extends KI{
	//Properties
	private Spieler kiPlayer;
	//Constructor
	public KI_Dame(){
		setKiPlayer();
	}
	//getter/Setter
	private void setKiPlayer(){
		this.kiPlayer = new Spieler();
	}

	//move to empty space
	public void move(Spiel game, Spieler player){
		//exception abfangen
		//if position != free -> nextPosition
		//if position == free -> setze stein 
		//spiel.move

		try{
			System.out.print("("+ player.getName() + ")" + " Thinking");
			int sleepTime = 200;
			Thread.sleep(sleepTime * 1);
			System.out.print(".");
			Thread.sleep(sleepTime * 1);
			System.out.print(".");
			Thread.sleep(sleepTime * 1);
			System.out.print(".");
			Thread.sleep(sleepTime * 1);
			System.out.println("");
		}
		catch(Exception e){}

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
				//System.out.println("MOVE FROM=" + fromPoint + " TO=" + toPoint);
				game.move(fromPoint, toPoint);
			}catch (Exception e){
				System.out.println("Sry, some other problems ");
			}
		}
	}
}
