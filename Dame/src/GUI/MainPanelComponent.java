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
		// Creaste extended draw device
		Graphics2D g2d = (Graphics2D) g;

		/*
		// Set colors
		Color color1 = new Color(20,20,20);
		Color color2 = new Color(150,150,150);
		System.out.println(color1 + " " + color2);

		// Get height und width
		int w = getWidth(), h = getHeight();

		// Create gradient
		GradientPaint left = new GradientPaint(0, 0, color1, w / 2, 0, color2);
		GradientPaint right = new GradientPaint(w / 2, 0, color2, w, 0, color1);

		// Set left color
		g2d.setPaint(left);
		g2d.fillRect(0, 0, w / 2, h);

		// Set right color
		g2d.setPaint(right);
		g2d.fillRect(w / 2, 0, w, h);
		 */

		// Set right color
		g2d.setPaint(new Color(100,100,100));
		g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(),50,50);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}