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

import Enumerations.FarbEnum;
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
	private JLabel labelSave;
	private ImageButton start;
	private ImageButton saveButton;
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
		label = new JLabel("Insert");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans",Font.BOLD,30));
		label.setForeground(Color.white);

		labelSave = new JLabel("Save");
		labelSave.setHorizontalAlignment(JLabel.CENTER);
		labelSave.setFont(new Font("Gill Sans",Font.BOLD,30));
		labelSave.setForeground(Color.white);
		
		// Text fields
		felderEingabe = new JTextField();
		felderEingabe.setPreferredSize(new Dimension(100,30));

		// Start button
		start = new ImageButton("Move");
		start.setDefaultImage("Images/Play.png");
		start.setHoverImage("Images/Play_Hover.png");
		start.setPressImage("Images/Play_Pressed.png");
		start.setPreferredSize(new Dimension(60,60));
		
		// Save button
		saveButton = new ImageButton("Save");
		saveButton.setDefaultImage("Images/Save.png");
		saveButton.setHoverImage("Images/Save_Hover.png");
		saveButton.setPressImage("Images/Save_Pressed.png");
		saveButton.setPreferredSize(new Dimension(60,60));

		// Layout
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);

		innerPanels mp = new innerPanels();
		mp.setPreferredSize(new Dimension(250,200));
		mp.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; 
		mp.add(labelCurrentPlayer,c);
		c.gridx = 0; c.gridy = 1;
		mp.add(player1,c);
		c.gridx = 0; c.gridy = 2;
		mp.add(player2,c);

		innerPanels mp2 = new innerPanels();
		mp2.setPreferredSize(new Dimension(250,140));
		mp2.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
		mp2.add(label,c);
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
		mp2.add(felderEingabe,c);
		c.gridx = 2; c.gridy = 1;
		mp2.add(start,c);
		
		innerPanels mp3 = new innerPanels();
		mp3.setPreferredSize(new Dimension(250,140));
		mp3.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; 
		mp3.add(labelSave,c);
		c.gridx = 0; c.gridy = 1;
		mp3.add(saveButton,c);

		this.setLayout(new GridBagLayout());
		c.gridx = 0; c.gridy = 0; 
		this.add(mp,c);
		c.gridx = 0; c.gridy = 1;
		this.add(mp2,c);
		c.gridx = 0; c.gridy = 2;
		this.add(mp3,c);

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

			this.setPreferredSize(new Dimension(200,50));
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
			Spieler player = MainFrame.globalPointer.getGame().getPlayer(this.playerID);
			if(player != null){
				// Set font
				g2D.setColor(Color.white);
				g2D.setFont(new Font("Gill Sans",Font.BOLD,25));
				
				// Draw player name
				MainFrame.drawCenteredString(g2D, player.getName(), new Rectangle(5,5,this.getWidth() - 10, this.getHeight() - 10));
			
				// Get color for borders
				Color border = null;
				if(player.getColor() == FarbEnum.schwarz) border = new Color(0,0,0);
				else border = new Color(255,255,255);
				
				// Set the new color
				g2D.setColor(border);
				
				// Draw borders
				g2D.fillRect(5, 5, this.getWidth(), this.getHeight() - 10);
				g2D.fillRect(this.getWidth() - 5, 5, 5, this.getHeight() - 10);
			}
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}
}
