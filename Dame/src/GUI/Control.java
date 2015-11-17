//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;
import javax.swing.*;
import GameLogic.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class Control extends JPanel {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private TextField felderEingabe;
	private JLabel label;
	private ImageButton start;
	
	public static Control globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Control(){
		// Set global pointer
		Control.globalPointer = this;
		
		// Set transparent background
		this.setOpaque(false);

		label = new JLabel("Eingabe: ");
		felderEingabe = new TextField();

		// Start button
		start = new ImageButton("Start move");
		start.setDefaultImage("Images/Play.png");
		start.setHoverImage("Images/Play_Hover.png");
		start.setPressImage("Images/Play_Pressed.png");

		// Layout
		this.setLayout(new GridLayout(3,1));
		this.add(label);
		this.add(felderEingabe);
		this.add(start);
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				eingabeTest();
			}
		});
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods
	
	public void eingabeTest(){
		Point start= new Point(0,0);
		Point end = new Point (0,0);
		String eingabe = felderEingabe.getText();
		String startFeld= eingabe.substring(0, 2);
		String endFeld = eingabe.substring(4,6);
		Pattern p = Pattern.compile( "'[A-z][0-9][0-9]-[A-z][0-9][0-9]'" );
		Matcher m = p.matcher( eingabe );
		boolean b = m.matches();
		if(b== true){
			Spiel game = MainFrame.globalPointer.getGame();
			try{
				start= game.stringToPoint(startFeld);
				end = game.stringToPoint(endFeld);
			}catch(Spiel.eInvalidPointException e){
			}
			startMove(start,end);
		}
	}
	
	public void startMove(Point start,Point end){
		Spiel game = MainFrame.globalPointer.getGame();
		try{
			game.move(start,end);
			
			Board.globalPointer.updateUI();
		}catch (Spiel.eSomeOtherMoveErrorsException e) {
			Logging.globalPointer.addErrorMessage("Unbekannter Fehler. Sorry.");
		}catch (Spiel.eDestinationPointIsBlockedException e) {
			Logging.globalPointer.addErrorMessage("Ziel-Feld ist blockiert");
		}catch (Spiel.eNoDiagonalMoveException e) {
			Logging.globalPointer.addErrorMessage("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
		}catch (Spiel.eNoFigureFoundOnFieldException e) {
			Logging.globalPointer.addErrorMessage("Feld hat keine gültige Spielfigur");
		}catch (Spiel.eOutOfGameboardException e) {
			Logging.globalPointer.addErrorMessage("Position ist außerhalb des Spielfeldes");
		}catch (Spiel.eSamePositionException e) {
			Logging.globalPointer.addErrorMessage("Spielfigur-Feld und Ziel-Feld sind identisch");
		}catch (Spiel.eDistanceToFarException e){
			Logging.globalPointer.addErrorMessage("Sorry.Steine dürfen nur 1 Feld weit springen");
		}catch(Spiel.eEnemyFigureSelectedException e){
			Logging.globalPointer.addErrorMessage("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
		}catch(Spiel.eOwnFigureIsBlockingException e){
			Logging.globalPointer.addErrorMessage("Kann keine eigenen Steine überspringen");
		}catch(Spiel.eNoBackJumpExcpetion e){
			Logging.globalPointer.addErrorMessage("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
		}catch(Spiel.eWayIsBlockedException e){
			Logging.globalPointer.addErrorMessage("Es dürfen keine 2 Stein gleichzeitig übersürungen werden");
		}catch (Exception e){
			Logging.globalPointer.addErrorMessage("Sry, some other problems");
		}

	}

}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ Methods ( Getter)


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ Methods ( Setter)


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ Methods (Override)


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ Inner class


