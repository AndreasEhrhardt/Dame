package Main;
import java.util.Scanner;

import GUI.MainFrame;
import GameLogic.Spiel;

public class Main {

	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		Spiel game = new Spiel();

		System.out.print("Grafik (1) oder Konsole (2):");
		int menu;
		try{
			Scanner sc = new Scanner(System.in);
			menu = sc.nextInt();
		}
		catch(Exception e){
			throw new RuntimeException();
		}

		if(menu == 1){
			MainFrame mf = new MainFrame(game);
		}
		else if(menu == 2){
			game.initialize();
			game.gameLoop();
		}
	}
}
