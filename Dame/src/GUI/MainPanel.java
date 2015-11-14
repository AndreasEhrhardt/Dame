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

	private Startpage startpage;
	private LoadingMenu loadingMenu;
	private ImageButton backButton;
	private ImageButton forwardButton;

	private Component currentComponent = null;
	private BufferedImage background;

	private MainFrame mf;

	public static MainPanel globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * @param mf
	 */
	public MainPanel(MainFrame mf){
		// Set global pointer
		if(MainPanel.globalPointer != null) throw new RuntimeException();
		else MainPanel.globalPointer = this;

		// Set parent
		this.setMainFrame(mf);

		// Disable layout manager
		this.setLayout(null);

		// Load background image
		try {
			background = ImageIO.read( ClassLoader.getSystemResource("Images/Background.png") );
		} catch (IOException e) {}
		
		// Create components
		startpage = new Startpage();
		loadingMenu = new LoadingMenu();

		// Create back and forward buttons
		backButton = new ImageButton("Back");
		backButton.setDefaultImage("Images/Back.png");
		backButton.setHoverImage("Images/Back_Hover.png");
		backButton.setPressImage("Images/Back_Pressed.png");
		backButton.setDisabledImage("Images/Back_Disabled.png");
		backButton.addActionListener(new EventHandler().new eButtonBack());
		backButton.setSize(new Dimension(100,100));
		backButton.setVisible(true);
		forwardButton = new ImageButton("Back");
		forwardButton.setDefaultImage("Images/Back.png");
		forwardButton.setHoverImage("Images/Back_Hover.png");
		forwardButton.setPressImage("Images/Back_Pressed.png");
		forwardButton.setDisabledImage("Images/Back_Disabled.png");
		forwardButton.addActionListener(new EventHandler().new eButtonBack());
		forwardButton.setSize(new Dimension(100,100));
		forwardButton.setVisible(true);

		// Add components to MainPanel
		this.add(startpage);
		this.add(loadingMenu);
		this.add(backButton);
		
		// Set Z-Order
		this.setComponentZOrder(backButton, 0);
		this.setComponentZOrder(forwardButton, 0);

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
		this.backButton.setLocation(0,(this.getHeight() / 2) - (backButton.getHeight() / 2));

		if(this.currentComponent != null){
			this.currentComponent.setLocation(100, 30);
			this.currentComponent.setSize(this.getSize().width - 200, this.getSize().height - 60);
		}
	}

	/**
	 * 
	 */
	public void back(){
		if(currentComponent instanceof LoadingMenu) this.showStartpage();
	}

	/**
	 * 
	 */
	public void forward(){

	}

	/**
	 * 
	 */
	public void hideCurrentComponent(){
		if(this.currentComponent != null){
			this.currentComponent.setVisible(false);
		}
	}

	/**
	 * 
	 */
	public void showCurrentComponent(){
		if(this.currentComponent != null){
			this.currentComponent.setVisible(true);
			this.fitComponent();
			this.repaint();
		}
	}

	/**
	 * @param current
	 */
	public void setNewComponent(Component current){
		hideCurrentComponent();
		this.currentComponent = current;
		showCurrentComponent();
	}

	public void showStartpage(){
		setNewComponent(startpage);
	}

	public void showLoadingMenu(){
		setNewComponent(loadingMenu);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * @return
	 */
	public Component getCurrentComponent(){
		return this.currentComponent;
	}

	/**
	 * @return
	 */
	public MainFrame getMainFrame(){
		return this.getMainFrame();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	/**
	 * @param mf
	 */
	public void setMainFrame(MainFrame mf){
		if(mf == null) throw new RuntimeException();

		this.mf = mf;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;

		TexturePaint paint = new TexturePaint(background, new Rectangle(0, 0, background.getWidth(), background.getHeight()));
		g2D.setPaint(paint);
		g2D.fill(new Rectangle(0, 0, getWidth(), getHeight()));
	}
}