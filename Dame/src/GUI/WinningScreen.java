//###########################################################
//## Package

package GUI;

import java.awt.*;

import javax.swing.JPanel;

import Enumerations.FarbEnum;
import Events.EventHandler;

//###########################################################
//## Imports


//###########################################################
//## Class

public class WinningScreen extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	public static WinningScreen globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public WinningScreen(){
		// Set global pointer
		WinningScreen.globalPointer = this;
		
		// Add action listener
		this.addMouseListener(new EventHandler().new eWinningScreen());
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
		Graphics2D g2D = (Graphics2D) g;

		// Render hints
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		FarbEnum winner = MainFrame.globalPointer.getGame().gameFinished();

		if(winner == null) MainPanel.globalPointer.showStartpage();

		Color backgroundColor;
		Color textColor;

		if(winner == FarbEnum.schwarz){
			backgroundColor = new Color(0,0,0);
			textColor = new Color(255,255,255);
		}else {
			backgroundColor = new Color(255,255,255);
			textColor = new Color(0,0,0);
		}

		Rectangle rect = new Rectangle((this.getWidth() / 2) - 250,(this.getHeight() / 2 - 250),500,500);

		// Draw background
		g2D.setColor(textColor);
		g2D.fillOval((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
		g2D.setColor(backgroundColor);
		g2D.fillOval((int)rect.getX() + 5, (int)rect.getY() + 5, (int)rect.getWidth() - 10, (int)rect.getHeight() - 10);

		// Draw text
		g2D.setColor(textColor);
		g2D.setFont(new Font("Gill Sans",Font.BOLD,30));
		MainFrame.drawCenteredString(g2D, "Good job " + FarbEnum.getColorName(winner).toLowerCase() +"! YOU WON", rect);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}