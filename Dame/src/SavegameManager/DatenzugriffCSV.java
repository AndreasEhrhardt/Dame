package SavegameManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Enumerations.FarbEnum;
import GameLogic.Spiel;
import Interfaces.iDatenzugriff;
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
			
			game.createNewPlayer(1).setName(fields[1]);
			while(line != null) {
				
			if(fields[2] == "schwarz") {
				game.getPlayer(1).setColor(FarbEnum.schwarz);
			}
			else {
				game.getPlayer(1).setColor(FarbEnum.weiß);
			}
			if(fields[3] == null) {
				game.getPlayer(1).setKI(null);
			}
			else {
			}
			}	
		} catch
		
	}

	@Override
	public boolean haveSaveGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
