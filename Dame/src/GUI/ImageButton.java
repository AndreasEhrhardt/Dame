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

	private boolean disabled = false;

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

	public boolean getDisabled(){
		return this.disabled;
	}

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

		// Start repaint
		this.repaint();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	@Override
	protected void paintComponent(Graphics g){	
		// Use better paint class
		Graphics2D g2D = (Graphics2D) g;

		// Render hints
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

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
		g2D.drawImage(currentImage, 0, 0, (int)size.getWidth(), (int)size.getHeight(), this);

		// Set text
		if(getModel().isRollover() && !this.getText().isEmpty()){			
			Rectangle currentSize = new Rectangle(0,0,(int)this.getSize().getWidth(),(int)this.getSize().getHeight());
			currentSize.setLocation(0, (int)currentSize.getHeight() - 25);
			currentSize.setSize(currentSize.width, 10);

			g2D.setFont(new Font("Gill Sans",Font.BOLD,20));
			g2D.setColor(Color.white);
			MainFrame.drawCenteredString(g2D, this.getText(), currentSize);
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}