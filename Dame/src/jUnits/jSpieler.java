//################################################################################
//## Import packages

package jUnits;

//################################################################################
//## Import packages

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Enumerations.FarbEnum;
import GameLogic.*;
import KI.KI_Dame;

//################################################################################
//## Class

public class jSpieler {
	
	@Test
	public void testDameMove(){
		// Create game
		Spiel game = new Spiel();
		
		// Create fields, gameboards and figures
		Spielbrett gameboard = new Spielbrett();
		Spielfeld felder[][] = new Spielfeld[8][8];
		for(int i = 0; i < felder.length; i++){
			for(int p = 0; p < felder[i].length; p++){
				felder[i][p] = new Spielfeld();
			}
		}
		
		Spielfigur dame = new Spielfigur(FarbEnum.weiß, new Point(5,4));
		dame.setDame(true);
		Spielfigur normal = new Spielfigur(FarbEnum.schwarz, new Point(7,2));
		felder[5][4].setFigure(dame);
		felder[7][2].setFigure(normal);
		
		gameboard.setFelder(felder);
		game.setGameboard(gameboard);
		game.gameLoop();
	}

}
