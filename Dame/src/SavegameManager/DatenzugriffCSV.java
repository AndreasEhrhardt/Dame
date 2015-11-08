package SavegameManager;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
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
import GameLogic.Spielfigur;
import Interfaces.iDatenzugriff;
import KI.KI;
import KI.KI_Dame;

public class DatenzugriffCSV implements iDatenzugriff {	
	@Override
	public boolean saveGame(String path, String filename, Spiel game){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(path + filename));
			
			// Write string to CSV
			pw.write((String)game.csvString());
		} catch (IOException e) {
			return false;
		} finally {
			if (pw != null){
				pw.flush();
				pw.close();
			}
		}
		
		return true;
	}

	@Override
	public boolean loadGame(String path, String filename, Spiel game) {
		if(!this.haveSaveGame(path, filename)) return false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + filename));
			String line = reader.readLine();
			String[] fields = line.split(";");
			Spieler player1 = new Spieler();
			Spieler player2 = new Spieler();
			Spielbrett board = null;
			String[] gameConfigurationString = null;
			String [][] fieldsOfBoard;
			int count = 0;
			while(line != null) {
				if (count == 0) {
					//read information of first line and assign them to player 1
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
					//read information of second line and assign them to player 2
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
					//read information of third line; first entry is the current Player, second entry the board size
					if(fields[0] == "schwarz") {
					game.setCurrentGamer(FarbEnum.schwarz);
					} 
					else {
						game.setCurrentGamer(FarbEnum.weiß);
					}
					board = new Spielbrett(Integer.parseInt(fields[1]));
				//information of the current boardstate
				int size = Integer.parseInt(fields[1]);
				gameConfigurationString = new String[size*size];
				for(int i = 0; i < line.length(); i++) {
					gameConfigurationString[i] = fields[i];
				}
				count++; 
				}
			} 
			//String representation of the boardstate in 2D Array
			int counter = 0; 
			fieldsOfBoard = new String[(int) Math.sqrt(gameConfigurationString.length)][(int) Math.sqrt(gameConfigurationString.length)];
			
			for(int i = 0; i < (int) Math.sqrt(gameConfigurationString.length); i++) {
				for(int j = 0; j < (int) Math.sqrt(gameConfigurationString.length); j++) {
					fieldsOfBoard[i][j] = gameConfigurationString[counter];
					counter++;
				}
			}
			//create new Spielfigur 
			for(int k = 0; k < fieldsOfBoard.length; k++) {
				for(int l = 0; k < fieldsOfBoard[k].length; l++) {
					if(fieldsOfBoard[k][l] == "W ") {
						Spielfigur figure = new Spielfigur(FarbEnum.weiß, new Point(k,l));
						figure.setDame(false);
						board.getField(k, l).setFigure(figure);
					} else if(fieldsOfBoard[k][l] == "W+") {
						Spielfigur figure = new Spielfigur(FarbEnum.weiß, new Point(k,l));
						figure.setDame(true);
						board.getField(k, l).setFigure(figure);
					} else if(fieldsOfBoard[k][l] == "S ") {
						Spielfigur figure = new Spielfigur(FarbEnum.schwarz, new Point(k,l));
						figure.setDame(false);
						board.getField(k, l).setFigure(figure);
					} else if(fieldsOfBoard[k][l] == "S+") {
						Spielfigur figure = new Spielfigur(FarbEnum.weiß, new Point(k,l));
						figure.setDame(true);
						board.getField(k, l).setFigure(figure);
					} else board.getField(k, l).setFigure(null);
				}
			}
			game.setGameboard(board);
			
			reader.close();
			
			return true;
		} catch(IOException e){
			return false;
		}
		finally{
		}
	}
			
	@Override
	public boolean haveSaveGame(String path, String filename) {
		File f = new File(path + filename);
		if(!f.exists() || f.isDirectory()){
			return false;
		}
		return true;
	}

}
