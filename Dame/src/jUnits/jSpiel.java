package jUnits;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import Enumerations.*;
import GameLogic.*;
import KI.*;

public class jSpiel {

	@Test
	public void testGameFinished() throws Exception{
		// Output information
		System.out.println("[TEST] Test game finished");

		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for (int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
		}

		Spielfigur dame = new Spielfigur(FarbEnum.weiß, new Point(2,3));
		dame.setDame(true);
		Spielfigur normal = new Spielfigur(FarbEnum.schwarz, new Point(4,1));
		felder[2][3].setFigure(dame);
		felder[4][1].setFigure(normal);
		gameboard.setFelder(felder);

		// Create game
		Spiel game = new Spiel(gameboard,new Spieler[]{new Spieler("Test",FarbEnum.weiß),new Spieler(new KI_Dame(),FarbEnum.schwarz)});
		game.setCurrentGamer(FarbEnum.weiß);
		game.move(new Point(2,3),new Point(5,0));

		// Check if game finished
		FarbEnum result = game.gameFinished();
		Assert.assertTrue((result != null));
	}

	@Test
	public void testGameFinishedByBlocking() throws Exception{
		// Output information
		System.out.println("[TEST] Test game finished (blocked)");

		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for (int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
		}

		Spielfigur normalW = new Spielfigur(FarbEnum.weiß, new Point(0,1));
		Spielfigur normalS = new Spielfigur(FarbEnum.schwarz, new Point(1,0));
		felder[0][1].setFigure(normalS);
		felder[1][0].setFigure(normalW);
		gameboard.setFelder(felder);

		// Create game
		Spiel game = new Spiel(gameboard,new Spieler[]{new Spieler("Test",FarbEnum.weiß),new Spieler(new KI_Dame(),FarbEnum.schwarz)});
		game.setCurrentGamer(FarbEnum.schwarz);

		// Check if game finished
		FarbEnum result = game.gameFinished();
		Assert.assertTrue((result != null));
	}

	@Test
	public void testDameMove(){
		// Output information
		System.out.println("[TEST] Test dame movement");

		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for (int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
		}

		Spielfigur dame = new Spielfigur(FarbEnum.weiß, new Point(5,4));
		dame.setDame(true);
		Spielfigur normal = new Spielfigur(FarbEnum.schwarz, new Point(7,2));
		felder[2][3].setFigure(dame);
		felder[4][1].setFigure(normal);
		gameboard.setFelder(felder);

		// Create game
		Spiel game = new Spiel(gameboard,new Spieler[]{new Spieler("Test",FarbEnum.weiß),new Spieler(new KI_Dame(),FarbEnum.schwarz)});
		try{
			game.setCurrentGamer(FarbEnum.weiß);
			game.move(new Point(2,3),new Point(5,0));
		}
		catch(Exception e){
			fail("Exception thrown: " + e);
		}
	}

	@Test(expected = Spiel.eWayIsBlockedException.class)
	public void testWayBlocked() throws Exception{
		// Output information
		System.out.println("[TEST] Test way is blocked");

		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for (int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
		}

		Spielfigur dame = new Spielfigur(FarbEnum.weiß, new Point(2,3));
		dame.setDame(true);
		Spielfigur normal = new Spielfigur(FarbEnum.schwarz, new Point(4,1));
		Spielfigur normal2 = new Spielfigur(FarbEnum.schwarz, new Point(3,2));
		felder[2][3].setFigure(dame);
		felder[4][1].setFigure(normal);
		felder[3][2].setFigure(normal2);
		gameboard.setFelder(felder);

		// Create game
		Spiel game = new Spiel(gameboard,new Spieler[]{new Spieler("Test",FarbEnum.weiß),new Spieler(new KI_Dame(),FarbEnum.schwarz)});
		game.setCurrentGamer(FarbEnum.weiß);
		game.move(new Point(2,3),new Point(5,0));
	}

	@Test(expected = Spiel.eDestinationPointIsBlockedException.class)
	public void testBlockedDestinationField() throws Exception{
		// Output information
		System.out.println("[TEST] Test way is blocked");

		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for (int i = 0; i < felder.length; i++){
			for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
		}

		Spielfigur normal1 = new Spielfigur(FarbEnum.weiß, new Point(2,3));
		Spielfigur normal2 = new Spielfigur(FarbEnum.schwarz, new Point(3,2));
		felder[2][3].setFigure(normal1);
		felder[3][2].setFigure(normal2);
		gameboard.setFelder(felder);

		// Create game
		Spiel game = new Spiel(gameboard,new Spieler[]{new Spieler("Test",FarbEnum.weiß),new Spieler(new KI_Dame(),FarbEnum.schwarz)});
		game.setCurrentGamer(FarbEnum.weiß);
		game.move(new Point(2,3),new Point(3,2));
	}
}
