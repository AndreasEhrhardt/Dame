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
		try {
			background = ImageIO.read( ClassLoader.getSystemResource("Images/Background.png") );
		} catch (IOException e) {}

		startpage = new Startpage();

		this.add(startpage);

		this.setLayout(null);

		this.addComponentListener(new EventHandler().new eMainPanel());

		this.showStartpage();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void showStartpage(){
		this.setCurrentComponent(startpage);
	}

	public void fitComponent(){
		System.out.println(currentComponent);
		if(this.currentComponent != null){
			this.currentComponent.setLocation(30, 30);
			this.currentComponent.setSize(this.getSize().width - 60, this.getSize().height - 60);
		}
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
		Graphics2D g2D = (Graphics2D) g;
		
		TexturePaint paint = new TexturePaint(background, new Rectangle(0, 0, background.getWidth(), background.getHeight()));
		g2D.setPaint(paint);
		g2D.fill(new Rectangle(0, 0, getWidth(), getHeight()));

	}
}