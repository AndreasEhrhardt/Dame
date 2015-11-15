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
		Graphics2D g2d = (Graphics2D) g;
		
		// Set colors
		Color color1 = new Color(100,100,100);
		Color color2 = new Color(200,200,200);

		// Get height and width
		int w = getWidth(), h = getHeight();

		// Create gradient
		GradientPaint gradient = new GradientPaint(0, 0, color1, w / 2, 0, color2, true);

		// Set left color
		g2d.setPaint(gradient);
		g2d.fillRoundRect(0, 0, w, h, 100, 100);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}