package jUnits;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Enumerations.FarbEnum;
import GameLogic.Spiel;
import GameLogic.Spielbrett;
import GameLogic.Spieler;
import GameLogic.Spielfeld;
import GameLogic.Spielfigur;
import KI.KI;
import KI.KI_Dame;

public class jKI_Dame {
	
	@Test
	public void KI_DameTest(){
	
	// Create fields, gameboards and figures
			KI player = new KI_Dame();
			Spieler ali= new Spieler(player , FarbEnum.schwarz);
			Spieler bob= new Spieler(player,FarbEnum.wei�);
			Spielbrett gameboard = new Spielbrett();
			Spielfeld felder[][] = new Spielfeld[8][8];
			for (int i = 0; i < felder.length; i++){
				for(int j = 0; j < felder[i].length; j++) felder[i][j] = new Spielfeld();
			}
				
			Spielfigur normalW = new Spielfigur(FarbEnum.wei�, new Point(0,1));
			Spielfigur normala = new Spielfigur(FarbEnum.wei�, new Point(3,1));
			Spielfigur normalb = new Spielfigur(FarbEnum.schwarz, new Point(2,1));
			Spielfigur normalS = new Spielfigur(FarbEnum.schwarz, new Point(1,0));
			felder[0][1].setFigure(normalS);
			felder[1][0].setFigure(normalW);
			felder[3][1].setFigure(normala);
			felder[1][0].setFigure(normalb);
			gameboard.setFelder(felder);

			// Create game
			Spiel game = new Spiel(gameboard,new Spieler[]{ali,bob});
			game.setCurrentGamer(FarbEnum.schwarz);
			
			// Check if game finished
			boolean result = game.gameFinished();
			Assert.assertTrue(result); 

	}	
}
