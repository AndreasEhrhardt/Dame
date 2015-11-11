//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import Events.*;

//###########################################################
//## Class

public class MainPanel extends JPanel {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	Startpage startpage;
	Component currentComponent = null;
	BufferedImage background;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public MainPanel(){
		// Load background image
		try {
			background = ImageIO.read( ClassLoader.getSystemResource("Images/Background.png") );
		} catch (IOException e) {}

		// Create components
		startpage = new Startpage();
		
		// Disable layout manager
		this.setLayout(null);
		
		// Add component listener
		this.addComponentListener(new EventHandler().new eMainPanel());

		// Show the startpage
		this.showStartpage();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * 
	 */
	public void fitComponent(){
		if(this.currentComponent != null){
			this.currentComponent.setLocation(30, 30);
			this.currentComponent.setSize(this.getSize().width - 60, this.getSize().height - 60);
		}
	}
	
	/**
	 * 
	 */
	public void back(){
		if(currentComponent instanceof LoadingMenu) this.showStartpage();
	}
	
	public void forward(){
		
	}
	
	public void hideCurrentComponent(){
		if(this.currentComponent != null){
			this.currentComponent.setVisible(false);
			this.remove(this.currentComponent);
		}
	}
	
	public void showCurrentComponent(){
		if(this.currentComponent != null){
			this.currentComponent.setVisible(true);
			this.add(currentComponent);
		}
	}
	
	public void showStartpage(){
		hideCurrentComponent();
		this.currentComponent = this.startpage;
		showCurrentComponent();
	}
	
	public void showLoadingMenu(){
		hideCurrentComponent();
		this.currentComponent = this.startpage;
		showCurrentComponent();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public Component getCurrentComponent(){
		return this.currentComponent;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setCurrentComponent(Component currentComponent){
		if(this.currentComponent != null) this.currentComponent.setVisible(false);
		this.currentComponent = currentComponent;
		this.fitComponent();
		this.currentComponent.setVisible(true);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		TexturePaint paint = new TexturePaint(background, new Rectangle(0, 0, background.getWidth(), background.getHeight()));
		g2D.setPaint(paint);
		g2D.fill(new Rectangle(0, 0, getWidth(), getHeight()));
	}
}