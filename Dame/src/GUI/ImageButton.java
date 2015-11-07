//###########################################################
//## Package

package GUI;

import javax.imageio.ImageIO;

//###########################################################
//## Imports

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

//###########################################################
//## Class

public class ImageButton extends JButton  {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	BufferedImage defaultImage = null;
	BufferedImage hoverImage = null;
	BufferedImage pressImage = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public ImageButton(){
		super();
		
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
	
	public ImageButton(String text){
		this();
		this.setText(text);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public BufferedImage getImage(String path){
		try {
			return ImageIO.read( ClassLoader.getSystemResource(path));
		} catch (Exception e) {
			return null;
		}
	}
	
	public Dimension fitImageToSize(Dimension imageDimension)
	{
		Dimension validDimension = new Dimension();
		double percent = (double)this.getWidth() / (double)imageDimension.getWidth();
		validDimension.setSize(imageDimension.getWidth() * percent, imageDimension.getHeight() * percent);
		
		if(validDimension.getHeight() > this.getHeight()){
			percent = (double)this.getHeight() / (double)imageDimension.getHeight();
			validDimension.setSize(imageDimension.getWidth() * percent, imageDimension.getHeight() * percent);
		}
		
		return validDimension;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setDefaultImage(String path){
		// Set image
		this.defaultImage = this.getImage(path);
		
		// Start repaint
		this.repaint();
	}
	
	public void setHoverImage(String path){
		// Set image
		this.hoverImage = this.getImage(path);
		
		// Start repaint
		this.repaint();
	}
	
	public void setPressImage(String path){
		// Set image
		this.pressImage = this.getImage(path);
		
		// Start repaint
		this.repaint();
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	@Override
	protected void paintComponent(Graphics g){		
		// Detect current image
		BufferedImage currentImage = this.defaultImage;
		if(getModel().isPressed()) currentImage = this.pressImage;
		else if(getModel().isRollover()) currentImage = this.hoverImage;
		
		// Check if image is valid
		if(currentImage == null){
			super.paintComponent(g);
			return;
		}
		
		// Detect the maximum possible size
		Dimension size = this.fitImageToSize(new Dimension(currentImage.getWidth(),currentImage.getHeight()));
		
		// Draw image
		g.drawImage(currentImage, 0, 0, (int)size.getWidth(), (int)size.getHeight(), this);
		
		// Set text
		g.drawString(str, x, y);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class
	
}