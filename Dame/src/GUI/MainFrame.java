//###########################################################
//## Package

package GUI;	

//###########################################################
//## Imports

import javax.swing.*;

import Events.*;
import Events.EventHandler.*;

import java.awt.*;
import GameLogic.*;

//###########################################################
//## Class

public class MainFrame extends JFrame {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private Spiel game;
	private MainPanel mp;

	public static MainFrame globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public MainFrame(){
		// Set global pointer
		MainFrame.globalPointer = this;

		// Undecorate the windows
		this.setUndecorated(false);

		// Set close handling
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create game
		this.game = new Spiel();

		// Create main panel
		mp = new MainPanel();
		this.setContentPane(mp);

		// Set size of game
		Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.setSize(rec.width,rec.height);
		this.setLocation(0,0);

		// Show the window
		this.setVisible(true);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public static Rectangle drawCenteredString(Graphics g, String text, Rectangle rect){
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(g.getFont());

		// Determine the X coordinate for the text
		int x = (rect.width - metrics.stringWidth(text)) / 2 + (int)rect.getX();

		// Determine the Y coordinate for the text
		//int y = ((rect.height - metrics.getHeight()) / 2) + (int)rect.getY();
		int y = (int)((metrics.getAscent() + (rect.getHeight() - (metrics.getAscent() + metrics.getDescent())) / 2) + rect.getY());

		// Draw the String
		g.drawString(text, x, y);

		// Dispose the Graphics
		g.dispose();

		// Return the drawn size
		return new Rectangle(x,y,metrics.stringWidth(text),metrics.getHeight());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public MainPanel getMainPanel(){
		return this.mp;
	}

	public Spiel getGame(){
		return this.game;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setGame(Spiel game){
		if(game == null) throw new RuntimeException();

		this.game = game;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)
}