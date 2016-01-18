package SavegameManager;

import java.io.*;

import GameLogic.SpielBean;
import Interfaces.*;


public class DatenzugriffSerialisiert implements iDatenzugriff{	
	private static String defaultName = "savegameSerial.data";
	private static String defaultPath = "./";
	
	/**
	 * @param game
	 */
	public boolean saveGame(SpielBean game){
		return this.saveGame(defaultPath, defaultName, game);
	}
	
	/**
	 * @param game
	 */
	public boolean loadGame(SpielBean game){
		return this.loadGame(defaultPath, defaultName, game);
	}
	
	/**
	 * 
	 */
	public boolean haveSaveGame(){
		return this.haveSaveGame(defaultPath, defaultName);
	}
	
	public static void deleteSaveGame(){
		deleteSaveGame(defaultPath, defaultName);
	}
	
	public static void deleteSaveGame(String path, String filename){
		File file = new File(path + filename);
		System.out.println(path + filename);
		System.out.println(file.delete());
	}
	
	/* (non-Javadoc)
	 * @see Interfaces.iDatenzugriff#saveGame(java.lang.String, java.lang.String, GameLogic.Spiel)
	 */
	@Override
	public boolean saveGame(String path, String filename, SpielBean game) {
		if(!path.endsWith("/"))path+="/";
		try{
			// Save game state
			FileOutputStream gameOS = new FileOutputStream(path + filename);
			ObjectOutputStream gameObjStream = new ObjectOutputStream (gameOS);
			gameObjStream.writeObject(game);

			// Close file handle
			gameObjStream.close();
			gameOS.close();
			
			// Return success state
			return true;
		}
		catch(IOException e){
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see Interfaces.iDatenzugriff#loadGame(java.lang.String, java.lang.String, GameLogic.Spiel)
	 */
	@Override
	public boolean loadGame(String path, String filename, SpielBean game) {
		if(!path.endsWith("/")) path += "/";

		try{
			
			// Open file stream
			FileInputStream f_in = new FileInputStream(path + filename);
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			
			// Read object hier wird exeption geschmissen?
			Object obj = obj_in.readObject();
			
			// Check if object is from same class
			if(obj.getClass() == SpielBean.class){
				// Parse object
				SpielBean lastGame = (SpielBean)obj;

				// Get game-data
				game.setPlayer(1, lastGame.getPlayer(1));
				System.out.println(lastGame.getPlayer(1));
				game.setPlayer(2, lastGame.getPlayer(2));
				game.setGameboard(lastGame.getGameboard());
				game.setCurrentGamer(lastGame.getCurrentGamer().getColor());
			}
			// Close file stream
			f_in.close();

			// Return success state
			return true;
		}
		catch(IOException | ClassNotFoundException e){
			System.out.println(e);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see Interfaces.iDatenzugriff#haveSaveGame(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean haveSaveGame(String path, String filename){
		File f = new File(path + filename);
		if(!f.exists() || f.isDirectory()) return false;
		else return true;
	}
}
