package SavegameManager;

import java.awt.Point;

import Enumerations.*;
import GameLogic.*;
import Interfaces.*;
import KI.*;

public class SpielTestSpeichern {

	public static void main(String[] args) {
		try{
			Spiel newGame = new Spiel();
			iDatenzugriff serialised = new DatenzugriffSerialisiert();
			newGame.setPlayer(1, new Spieler(new KI_Dame(),FarbEnum.weiß));
			newGame.setPlayer(2, new Spieler(new KI_Dame(),FarbEnum.schwarz));
			newGame.setCurrentGamer(FarbEnum.weiß);
			newGame.setGameboard(new Spielbrett(10));
			newGame.getCurrentGamer().move(newGame, null);
			newGame.getCurrentGamer().move(newGame, null);
			newGame.getCurrentGamer().move(newGame, null);
			newGame.getCurrentGamer().move(newGame, null);
			newGame.outputGameboardCSV();
			newGame.save("./", "dame");
			serialised.saveGame("./", "dameSerialisiert", newGame);
			
		}
		catch(Exception e){
			System.out.println("Shit happens: " + e.getMessage());
		}
	}

}
