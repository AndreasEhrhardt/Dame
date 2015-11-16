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
	private GameboardSettings gameboardSettings;
	private PlayerSettings playerSettings;
	ImageButton backButton;
	private ImageButton forwardButton;

	private Component currentComponent = null;
	private BufferedImage background;

	public static MainPanel globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * @param mf
	 */
	public MainPanel(){
		// Set global pointer
		if(MainPanel.globalPointer != null) throw new RuntimeException();
		else MainPanel.globalPointer = this;

		// Disable layout manager
		this.setLayout(null);

		// Load background image
		try {
			background = ImageIO.read( ClassLoader.getSystemResource("Images/Background.png") );
		} catch (IOException e) {}

		// Create components
		startpage = new Startpage();
		loadingMenu = new LoadingMenu();
		gameboardSettings = new GameboardSettings();
		playerSettings = new PlayerSettings();

		// Create back and forward buttons
		backButton = new ImageButton("Back");
		backButton.setDefaultImage("Images/Back.png");
		backButton.setHoverImage("Images/Back_Hover.png");
		backButton.setPressImage("Images/Back_Pressed.png");
		backButton.setDisabledImage("Images/Back_Disabled.png");
		backButton.addActionListener(new EventHandler().new eButtonBack());
		backButton.setSize(new Dimension(100,100));
		backButton.setVisible(true);
		
		forwardButton = new ImageButton("Forward");
		forwardButton.setDefaultImage("Images/Forward.png");
		forwardButton.setHoverImage("Images/Forward_Hover.png");
		forwardButton.setPressImage("Images/Forward_Pressed.png");
		forwardButton.setDisabledImage("Images/Forward_Disabled.png");
		forwardButton.addActionListener(new EventHandler().new eButtonForward());
		forwardButton.setSize(new Dimension(100,100));
		forwardButton.setVisible(true);
		
		this.add(this.loadingMenu);
		this.add(this.gameboardSettings);
		this.add(this.startpage);
		this.add(this.playerSettings);

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
		this.forwardButton.setLocation(this.getWidth() - forwardButton.getWidth(),(this.getHeight() / 2) - (forwardButton.getHeight() / 2));

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
		else if(currentComponent instanceof GameboardSettings) this.showStartpage();
		else if(currentComponent instanceof PlayerSettings) this.showGameboardSettings();
	}

	/**
	 * 
	 */
	public void forward(){
		if(currentComponent instanceof GameboardSettings){
			GameboardSettings.globalPointer.save();
			this.showPlayerSettings();
		}
	}

	/**
	 * 
	 */
	public void showHideBack(){
		boolean state = false;

		if(currentComponent instanceof LoadingMenu) state = true;
		else if(currentComponent instanceof GameboardSettings) state = true;
		else if(currentComponent instanceof PlayerSettings) state = true;
		
		this.backButton.setVisible(state);
	}

	/**
	 * 
	 */
	public void showHideForward(){
		boolean state = false;
		
		if(currentComponent instanceof GameboardSettings) state = true;
		else if(currentComponent instanceof PlayerSettings) state = true;
		
		this.forwardButton.setVisible(state);
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
			this.showHideBack();
			this.showHideForward();
			
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

	/**
	 * 
	 */
	public void showStartpage(){
		setNewComponent(startpage);
	}

	/**
	 * 
	 */
	public void showLoadingMenu(){
		setNewComponent(loadingMenu);
	}
	
	/**
	 * 
	 */
	public void showGameboardSettings(){
		setNewComponent(gameboardSettings);
	}
	
	public void showPlayerSettings(){
		setNewComponent(playerSettings);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * @return
	 */
	public Component getCurrentComponent(){
		return this.currentComponent;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


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