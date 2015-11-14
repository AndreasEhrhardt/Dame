//###########################################################
//## Package

package GUI;

//###########################################################
//## Import

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

	private BufferedImage defaultImage = null;
	private BufferedImage hoverImage = null;
	private BufferedImage pressImage = null;
	private BufferedImage disabledImage = null;

	boolean disabled = false;

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

	public void setDisabledImage(String path){
		// Set image
		this.disabledImage = this.getImage(path);

		// Start repaint
		this.repaint();
	}

	public void setDisabled(boolean state){
		this.disabled = state;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	@Override
	protected void paintComponent(Graphics g){			
		// Detect current image
		BufferedImage currentImage = null;
		if(this.disabled){
			currentImage = this.disabledImage;
		}
		else{
			currentImage = this.defaultImage;
			if(getModel().isPressed()) currentImage = this.pressImage;
			else if(getModel().isRollover()) currentImage = this.hoverImage;
		}

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
		if(getModel().isRollover() && !this.getText().isEmpty()){			
			Rectangle currentSize = new Rectangle(0,0,(int)this.getSize().getWidth(),(int)this.getSize().getHeight());
			currentSize.setLocation(0, (int)currentSize.getHeight() - 10);
			currentSize.setSize(currentSize.width, 10);

			g.setFont(new Font("Gill Sans",Font.BOLD,21));
			g.setColor(Color.white);
			this.drawCenteredString(g, this.getText(), currentSize);
		}
	}

	public Rectangle drawCenteredString(Graphics g, String text, Rectangle rect){
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(g.getFont());

		// Determine the X coordinate for the text
		int x = (rect.width - metrics.stringWidth(text)) / 2 + (int)rect.getX();

		// Determine the Y coordinate for the text
		int y = ((rect.height - metrics.getHeight()) / 2) + (int)rect.getY();

		// Draw the String
		g.drawString(text, x, y);

		// Dispose the Graphics
		g.dispose();

		// Return the drawn size
		return new Rectangle(x,y,metrics.stringWidth(text),metrics.getHeight());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}