package GUI;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import GameLogic.Spiel;

	public class Control extends MainPanelComponent{
		
		private Logging logg;
		private TextField felderEingabe;
		private JLabel label;
		private ImageButton start;
		
		public Control(MainPanel mp){
			super(mp);
			logg = new Logging(mp);
			label = new JLabel("Eingabe: ");
			felderEingabe = new TextField();
			start= new ImageButton(this,"start move");
			this.setLayout(new GridLayout(3,1));
			start.setDefaultImage("Images/Play.png");
			start.setHoverImage("Images/Play_Hover.png");
			start.setPressImage("Images/Play_Click.png");
			this.add(label);
			this.add(felderEingabe);
			this.add(start);
			start.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				startMove();
				}
			});
		}
		
		public void startMove(){
			
			String eingabe = felderEingabe.getText();
			String startFeld= eingabe.substring(0, 2);
			String endFeld = eingabe.substring(4,6);
			Pattern p = Pattern.compile( "'[A-z][0-9][0-9]-[A-z][0-9][0-9]'" );
			Matcher m = p.matcher( eingabe );
			boolean b = m.matches();
			if(b== true){
				try{
					Point start= stringToPoint(startFeld);
					Point end = stringToPoint(endFeld);
					this.getMainPanel().getMainFrame().getGame().move(start,end);
				}catch(Spiel.eInvalidPointException e){
				}catch (Spiel.eSomeOtherMoveErrorsException e) {
					logg.getJTextField().setText("Unbekannter Fehler. Sorry.");
					//System.out.println("Unbekannter Fehler. sorry");
				}catch (Spiel.eDestinationPointIsBlockedException e) {
					logg.getJTextField().setText("Ziel-Feld ist blockiert");
					//System.out.println("Ziel-Feld ist blockiert");
				}catch (Spiel.eNoDiagonalMoveException e) {
					logg.getJTextField().setText("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
					//System.out.println("Ungültige Bewegungsrichtung (nur diagonal ist erlaubt)");
				}catch (Spiel.eNoFigureFoundOnFieldException e) {
					logg.getJTextField().setText("Feld hat keine gültige Spielfigur");
					//System.out.println("Feld hat keine gültige Spielfigur");
				}catch (Spiel.eOutOfGameboardException e) {
					logg.getJTextField().setText("Position ist außerhalb des Spielfeldes");
					//System.out.println("Position ist außerhalb des Spielfeldes");
				}catch (Spiel.eSamePositionException e) {
					logg.getJTextField().setText("Spielfigur-Feld und Ziel-Feld sind identisch");
					//System.out.println("Spielfigur-Feld und Ziel-Feld sind identisch");
				}catch (Spiel.eDistanceToFarException e){
					logg.getJTextField().setText("Sorry.Steine dürfen nur 1 Feld weit springen");
					//System.out.println("Bauern dürfen nur 1 Feld weit springen");
				}catch(Spiel.eEnemyFigureSelectedException e){
					logg.getJTextField().setText("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
					//System.out.println("Es ist nicht erlaubt die Spielfigur des Gegners zu verschieben");
				}catch(Spiel.eOwnFigureIsBlockingException e){
					logg.getJTextField().setText("Kann keine eigenen Steine überspringen");
					//System.out.println("Kann keine eigenen Steine überspringen");
				}catch(Spiel.eNoBackJumpExcpetion e){
					logg.getJTextField().setText("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
					//System.out.println("Falsche Richtung. Nur erlaubt beim Schlagen einer Figur oder als Dame");
				}catch(Spiel.eWayIsBlockedException e){
					logg.getJTextField().setText("Es dürfen keine 2 Stein gleichzeitig übersürungen werden");
					//System.out.println("Es dürfen keine 2 Stein gleichzeitig übersürungen werden");
				}catch (Exception e){
					logg.getJTextField().setText("Sry, some other problems");
					//System.out.println("Sry, some other problems ");
				}			
			}

		}
		public Point stringToPoint(String sPoint) throws Spiel.eInvalidPointException{
			if (sPoint.charAt(0) >= 65 && sPoint.charAt(0) <= 90) {
				Point point = new Point();
				int y = sPoint.charAt(0) - 65;

				if (sPoint.charAt(1) >= 48 && sPoint.charAt(1) <= 57) {
					int x = -1;
					x = (sPoint.charAt(1) - 48) * 10;

					if (sPoint.charAt(2) >= 48 && sPoint.charAt(2) <= 57) {
						x += (sPoint.charAt(2) - 48) - 1;

						if (x < 0 || y < 0) throw new Spiel.eInvalidPointException();

						point.setLocation(x, y);

						return point;
					} 

				} 

			}

			throw new Spiel.eInvalidPointException();
		}
	}
