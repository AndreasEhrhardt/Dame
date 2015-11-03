package SavegameManager;

import java.io.*;

import GameLogic.Spiel;
import Interfaces.*;


public class DatenzugriffSerialisiert implements iDatenzugriff{
	private String saveGameName = "./savegameSer.data";
	
	@Override
	public void saveGame(Spiel game) {
		try{
			// Save game state
			FileOutputStream gameOS = new FileOutputStream(saveGameName);
			ObjectOutputStream gameObjStream = new ObjectOutputStream (gameOS);
			gameObjStream.writeObject(game);

			// Close file handle
			gameObjStream.close();
		}
		catch(IOException e){
			// File save error
			System.out.println("Cant save game - state");
		}
	}

	@Override
	public void loadGame(Spiel game) {
		try{
			// Open file stream
			FileInputStream f_in = new FileInputStream(saveGameName);
			ObjectInputStream obj_in = new ObjectInputStream (f_in);

			// Read object
			Object obj = obj_in.readObject();

			// Check if object is from same class
			if(obj.getClass() == Spiel.class){
				// Parse object
				Spiel lastGame = (Spiel)obj;

				// Get game-data
				game.setPlayer(1, lastGame.getPlayer(1));
				game.setPlayer(2, lastGame.getPlayer(2));
				game.setGameboard(lastGame.getGameboard());
				game.setCurrentGamer(lastGame.getCurrentGamer().getColor());
			}
		}
		catch(IOException | ClassNotFoundException e){
			// Output error message
			System.out.println("Savegame is corrupt");

			// Exit game
			System.exit(-1);
		}
	}
	
	public boolean haveSaveGame(){
		File f = new File(saveGameName);
		if(!f.exists() || f.isDirectory()){
			return false;
		}
		return true;
	}
}
