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

	private JTextField felderEingabe;
	private JLabel label;
	private JLabel labelCurrentPlayer;
	private ImageButton start;
	private currentPlayer player1;
	private currentPlayer player2;

	public static Control globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Control(){
		// Set global pointer
		Control.globalPointer = this;

		// Set transparent background
		this.setOpaque(false);

		// Current player label
		labelCurrentPlayer = new JLabel("Current player");
		labelCurrentPlayer.setHorizontalAlignment(JLabel.CENTER);
		labelCurrentPlayer.setFont(new Font("Gill Sans",Font.BOLD,30));
		labelCurrentPlayer.setForeground(Color.white);
		
		// Create player overview
		player1 = new currentPlayer(1);
		player2 = new currentPlayer(2);
		
		// Label
		label = new JLabel("Eingabe");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans",Font.BOLD,30));
		label.setForeground(Color.white);

		felderEingabe = new JTextField();
		felderEingabe.setPreferredSize(new Dimension(100,30));

		// Start button
		start = new ImageButton("Move");
		start.setDefaultImage("Images/Play.png");
		start.setHoverImage("Images/Play_Hover.png");
		start.setPressImage("Images/Play_Pressed.png");
		start.setPreferredSize(new Dimension(60,60));

		// Layout
		GridBagConstraints c = new GridBagConstraints();
		
		innerPanels mp = new innerPanels();
		mp.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(10,10,10,10);
		mp.add(labelCurrentPlayer,c);
		c.gridx = 0; c.gridy = 1;
		mp.add(player1,c);
		c.gridx = 0; c.gridy = 2;
		mp.add(player2,c);
		
		innerPanels mp2 = new innerPanels();
		mp2.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(10,10,10,10);
		mp2.add(label,c);
		c.gridx = 0; c.gridy = 1;
		mp2.add(felderEingabe,c);
		c.gridx = 0; c.gridy = 2;
		mp2.add(start,c);
		
		this.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; c.insets = new Insets(10,10,100,10);
		this.add(mp,c);
		c.gridx = 0; c.gridy = 1; c.insets = new Insets(100,10,10,10);
		this.add(mp2,c);

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
		Pattern p = Pattern.compile( "[A-z][0-9][0-9]-[A-z][0-9][0-9]" );
		Matcher m = p.matcher( eingabe );
		boolean b = m.matches();
		if(b== true){
			String startFeld= eingabe.substring(0, 3);
			String endFeld = eingabe.substring(4,7);

			Spiel game = MainFrame.globalPointer.getGame();
			try{
				start= game.stringToPoint(startFeld);
				end = game.stringToPoint(endFeld);
				startMove(start,end);
			}catch(Spiel.eInvalidPointException e){ 
				Logging.globalPointer.addErrorMessage("Invalid input");
			}
		}else {
			Logging.globalPointer.addErrorMessage("Invalid input");
		}
	}


	public void startMove(Point start,Point end){
		if(MainFrame.globalPointer.getGame().getCurrentGamer().getKi() != null){
			Logging.globalPointer.addErrorMessage("KI ist am zug");
			return;
		}

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
	public void setFelderEingabe(String s){
		this.felderEingabe.setText(s);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public JTextField getTextField(){
		return this.felderEingabe;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class
	
	public class innerPanels extends JPanel{
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Properties


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Constructor

		public innerPanels(){
			this.setPreferredSize(new Dimension(250,250));
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Getter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Setter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		public void paintComponent(Graphics g){
			// Create better paint device
			Graphics2D g2D = (Graphics2D) g;

			// Render hints
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			// Draw background
			g2D.setColor(Color.black);
			//g2D.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 100, 100);
			g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

			// Create gradient
			Color color = new Color(20,20,20);
			Color color2 = new Color(150,150,150);
			GradientPaint gradient = new GradientPaint(0, 0, color, this.getWidth() / 2, 0, color.darker(), true);

			// Set left color
			g2D.setPaint(gradient);

			//g2D.fillRoundRect(5, 5, this.getWidth() - 10, this.getHeight() - 10, 100, 100);
			g2D.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);
		}
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}

	public class currentPlayer extends JPanel{
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Properties

		int playerID = 0;

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Constructor

		public currentPlayer(int playerID){
			this.playerID = playerID;
			
			this.setPreferredSize(new Dimension(150,50));
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods

		public void updateUI(){
			this.repaint();
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Getter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Setter)
		

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		public void paintComponent(Graphics g){
			// Create better paint device
			Graphics2D g2D = (Graphics2D) g;

			// Render hints
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			// Draw background
			g2D.setColor(Color.black);
			//g2D.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 100, 100);
			g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

			// Draw inner rect
			Color color;
			Spiel game = MainFrame.globalPointer.getGame();
			if(game.getCurrentGamer() == game.getPlayer(this.playerID)) color = new Color(0,175,220);
			else color = new Color(150,150,150);

			// Create gradient
			GradientPaint gradient = new GradientPaint(0, 0, color, this.getWidth() / 2, 0, color.darker(), true);

			// Set left color
			g2D.setPaint(gradient);

			//g2D.fillRoundRect(5, 5, this.getWidth() - 10, this.getHeight() - 10, 100, 100);
			g2D.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);

			// Draw text
			g2D.setColor(Color.white);
			g2D.setFont(new Font("Gill Sans",Font.BOLD,25));
			String text = "Player ";
			if(this.playerID == 1) text += "1";
			else if(this.playerID == 2) text += "2";
			MainFrame.drawCenteredString(g2D, text, new Rectangle(0,0,this.getWidth(), this.getHeight()));
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}
}
