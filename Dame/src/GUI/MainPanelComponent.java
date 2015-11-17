//###########################################################
//## Package

package GUI;

import java.awt.*;

import javax.swing.*;

//###########################################################
//## Imports


//###########################################################
//## Class

public class MainPanelComponent extends JPanel {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public MainPanelComponent(){
		this.setOpaque(false);

		// Hide widget
		this.setVisible(false);
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
		// Create extended draw device
		Graphics2D g2D = (Graphics2D) g;

		// Render hints
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Set colors
		Color color1 = new Color(100,100,100);
		Color color2 = new Color(200,200,200);

		// Get height and width
		int w = getWidth(), h = getHeight();

		// Create gradient
		GradientPaint gradient = new GradientPaint(0, 0, color1, w / 2, 0, color2, true);

		// Set left color
		g2D.setPaint(gradient);
		g2D.fillRoundRect(0, 0, w, h, 100, 100);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}