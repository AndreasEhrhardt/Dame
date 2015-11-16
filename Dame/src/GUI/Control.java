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

	private Logging logg;
	private TextField felderEingabe;
	private JLabel label;
	private ImageButton start;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Control(){
		logg = new Logging();

		label = new JLabel("Eingabe: ");
		felderEingabe = new TextField();

		// Start button
		start = new ImageButton("Start move");
		start.setDefaultImage("Images/Play.png");
		start.setHoverImage("Images/Play_Hover.png");
		start.setPressImage("Images/Play_Click.png");

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
				Point startpoint= start;
				Point endpoint = end;
				game.move(startpoint,endpoint);
			}catch (Spiel.eSomeOtherMoveErrorsException e) {
				logg.getJTextArea().append("Unbekannter Fehler. Sorry.");
			}catch (Spiel.eDestinationPointIsBlockedException e) {
				logg.getJTextArea().append("Ziel-Feld ist blockiert");
			}catch (Spiel.eNoDiagonalMoveException e) {
				logg.getJTextArea().append("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
			}catch (Spiel.eNoFigureFoundOnFieldException e) {
				logg.getJTextArea().append("Feld hat keine gültige Spielfigur");
			}catch (Spiel.eOutOfGameboardException e) {
				logg.getJTextArea().append("Position ist außerhalb des Spielfeldes");
			}catch (Spiel.eSamePositionException e) {
				logg.getJTextArea().append("Spielfigur-Feld und Ziel-Feld sind identisch");
			}catch (Spiel.eDistanceToFarException e){
				logg.getJTextArea().append("Sorry.Steine dürfen nur 1 Feld weit springen");
			}catch(Spiel.eEnemyFigureSelectedException e){
				logg.getJTextArea().append("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
			}catch(Spiel.eOwnFigureIsBlockingException e){
				logg.getJTextArea().append("Kann keine eigenen Steine überspringen");
			}catch(Spiel.eNoBackJumpExcpetion e){
				logg.getJTextArea().append("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
			}catch(Spiel.eWayIsBlockedException e){
				logg.getJTextArea().append("Es dürfen keine 2 Stein gleichzeitig übersürungen werden");
			}catch (Exception e){
				logg.getJTextArea().append("Sry, some other problems");
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


