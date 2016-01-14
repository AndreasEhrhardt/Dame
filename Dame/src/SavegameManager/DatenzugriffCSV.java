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
import GameLogic.SpielBean;
import GameLogic.Spielbrett;
import GameLogic.Spieler;
import GameLogic.Spielfigur;
import Interfaces.iDatenzugriff;
import KI.KI;
import KI.KI_Dame;

public class DatenzugriffCSV implements iDatenzugriff {	
	@Override
	public boolean saveGame(String path, String fileName, SpielBean game){
		if(!path.endsWith("/")) path += "/";
		if(!fileName.endsWith(".csv")) fileName += ".csv";
		
		// Create folder if not exist
		File dir = new File(path);
		if(!dir.exists()) dir.mkdir();
		
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(path + fileName));

			// Write string to CSV
			pw.write((String)game.csvString());
			System.out.println((String)game.csvString());
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
	public boolean loadGame(String path, String filename, SpielBean game) {
		if(!path.endsWith("/")) path += "/";
		if(!filename.endsWith(".csv")) filename += ".csv";
		
		//if(!this.haveSaveGame(path, filename)) return false;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path + filename));
			String line = reader.readLine();
			String[] fields = line.split(";");
			Spieler player1 = new Spieler();
			Spieler player2 = new Spieler();
			Spielbrett board  = null;
			ArrayList <ArrayList<String>> fieldLines = new ArrayList<>();
			String [][] fieldsOfBoard;
			int count = 0;
			while(line != null) {
				// Parse every CSV value
				fields = line.split(";");
				if (count == 0) {
					//read information of first line and assign them to player 1
					player1.setName(fields[0]);
					
					if(fields[1].equals("schwarz")) {
						player1.setColor(FarbEnum.schwarz);
					}
					else {
						player1.setColor(FarbEnum.weiß);
					}
					
					if(fields[2].equals("null")) {
						player1.setKI(null);
					}
					else {
						player1.setKI(new KI_Dame());
					}
					game.setPlayer(1, player1);
				} else if(count == 1) {
					//read information of second line and assign them to player 2
					player2.setName(fields[0]);
					if(fields[1].equals("schwarz")) {
						player2.setColor(FarbEnum.schwarz);
					}
					else {
						player2.setColor(FarbEnum.weiß);
					}
					if(fields[2].equals("null")) {
						player2.setKI(null);
					}
					else {
						player2.setKI(new KI_Dame());
					}
					game.setPlayer(2, player2);
				} else if(count == 2) {
					//read information of third line; first entry is the current Player, second entry the board size
					if(fields[0].equals("schwarz")) {
						game.setCurrentGamer(FarbEnum.schwarz);
					} 
					else {
						game.setCurrentGamer(FarbEnum.weiß);
					}
					board = new Spielbrett(Integer.parseInt(fields[1]));

				} else {
					ArrayList <String> currentXLine = new ArrayList<>();
					for(int i = 0; i < fields.length; i++) {
						currentXLine.add(fields[i].trim());
					}
					
					fieldLines.add(currentXLine);

				}
				count++;
				line = reader.readLine();
			}
			
			// Swap field list
			for(int i = 0; i < (int)(fieldLines.size() / 2); i++){
				ArrayList<String> save = fieldLines.get(i);
				fieldLines.set(i, fieldLines.get(fieldLines.size() - 1 -i));
				fieldLines.set(fieldLines.size() - 1 - i, save);
				
			}
			
			//create new Spielfigur 
			for(int l = 0; l < fieldLines.size(); l++) {
				for(int k = 0; k < fieldLines.get(l).size(); k++) {
					if(fieldLines.get(k).get(l).equals("W")) {
						Spielfigur figure = new Spielfigur(FarbEnum.weiß, new Point(l,k));
						board.getField(l, k).setFigure(figure);
					} else if(fieldLines.get(k).get(l).equals("W+")) {
						Spielfigur figure = new Spielfigur(FarbEnum.weiß, new Point(l,k));
						figure.setDame(true);
						board.getField(l, k).setFigure(figure);
					} else if(fieldLines.get(k).get(l).equals("S")) {
						Spielfigur figure = new Spielfigur(FarbEnum.schwarz, new Point(l,k));
						board.getField(l, k).setFigure(figure);
					} else if(fieldLines.get(k).get(l).equals("S+")) {
						Spielfigur figure = new Spielfigur(FarbEnum.schwarz, new Point(l,k));
						figure.setDame(true);
						board.getField(l, k).setFigure(figure);
					}
					else{
						board.getField(l, k).setFigure(null);
					}
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
		if(!path.endsWith("/")) path += "/";
		if(!filename.endsWith(".csv")) filename += ".csv";
		
		File f = new File(path + filename);
		if(!f.exists() || f.isDirectory()){
			return false;
		}
		return true;
	}

}
