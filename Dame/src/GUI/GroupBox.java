//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

//###########################################################
//## Class

public class GroupBox extends JPanel{
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public GroupBox(){
		
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
