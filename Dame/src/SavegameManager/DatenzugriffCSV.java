package SavegameManager;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import GameLogic.Spiel;
import Interfaces.iDatenzugriff;

public class DatenzugriffCSV implements iDatenzugriff{

	@Override
	public void saveGame(Object game) {
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(new FileWriter("dame.csv"));
			pw.write((String) game);

		}
		catch(FileNotFoundException e){
			System.err.println("'game.ser' couldn't be generated");
		}
		catch(IOException e){
			System.err.println("Error during imput-/output: "+e);
		}
		finally{
			if(pw!= null) pw.close();
		}
		pw.flush();
	}
		

	@Override
	public void loadGame(Spiel game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveSaveGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
