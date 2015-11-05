package SavegameManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Enumerations.FarbEnum;
import GameLogic.Spiel;
import GameLogic.Spielbrett;
import GameLogic.Spieler;
import Interfaces.iDatenzugriff;
import KI.KI;
import KI.KI_Dame;

public class DatenzugriffCSV implements iDatenzugriff {

	@Override
	public void saveGame(Object game) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("dame.csv"));
			pw.write((String) game);

		} catch (FileNotFoundException e) {
			System.err.println("'game.ser' couldn't be generated");
		} catch (IOException e) {
			System.err.println("Error during imput-/output: " + e);
		} finally {
			if (pw != null)
				pw.close();
		}
		pw.flush();
	}

	@Override
	public void loadGame(Spiel game) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("dame.csv"));
			String line = reader.readLine();
			String[] fields = line.split(";");
			Spieler player1 = new Spieler();
			Spieler player2 = new Spieler();
			Spielbrett board;
			ArrayList<String> gameConfigurationString = new ArrayList<String>();
			int count = 0;
			while(line != null) {
				if (count == 0) {
					
					player1.setName(fields[0]);
					if(fields[1] == "schwarz") {
						player1.setColor(FarbEnum.schwarz);
					}
					else {
						player1.setColor(FarbEnum.weiß);
					}
					if(fields[2] == null) {
						player1.setKI(null);
					}
					else {
						player1.setKI(new KI_Dame());
					}
					game.setPlayer(1, player1);
				} else if(count == 1) {
					
					player2.setName(fields[0]);
					if(fields[1] == "schwarz") {
						player2.setColor(FarbEnum.schwarz);
					}
					else {
						player2.setColor(FarbEnum.weiß);
					}
					if(fields[2] == null) {
						player2.setKI(null);
					}
					else {
						player2.setKI(new KI_Dame());
					}
					game.setPlayer(2, player1);
				} else if(count == 2) {
					
					if(fields[0] == "schwarz") {
					game.setCurrentGamer(FarbEnum.schwarz);
					} 
					else {
						game.setCurrentGamer(FarbEnum.weiß);
					}
					board = new Spielbrett(Integer.parseInt(fields[1]));
				} 
				for(int i = 0; i < line.length(); i++) {
					gameConfigurationString.add(fields[i]);
				} gameConfigurationString.add("nextRow");
				count++; 
			} 
			//Rows of board
			for(int j = 0; j < gameConfigurationString.indexOf("nextRow"); j++) {
				//columns of board
				for(int k = 0; k < gameConfigurationString.size(); k++) {
					
				}
			}
		}
	}
			
			
				
			
	

	

	@Override
	public boolean haveSaveGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
