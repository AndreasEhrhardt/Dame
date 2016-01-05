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
import GameLogic.SpielBean;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private Startpage startpage;
	private LoadingMenu loadingMenu;
	private GameboardSettings gameboardSettings;
	private PlayerSettings playerSettings;
	private GameGUI gameGUI;
	private WinningScreen winningScreen;
	private SaveGUI saveGUI;
	private LoadGUI loadGUI;
	private MailSending mailSending;
	
	private ImageButton backButton;
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
		gameGUI = new GameGUI();
		winningScreen = new WinningScreen();
		saveGUI = new SaveGUI();
		loadGUI = new LoadGUI();
		mailSending = new MailSending();

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
		this.add(this.gameGUI);
		this.add(this.winningScreen);
		this.add(this.saveGUI);
		this.add(this.loadGUI);
		this.add(this.mailSending);

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
		else if(currentComponent instanceof GameGUI){ 
			this.showPlayerSettings();
			Logging.globalPointer.getTextArea().setText("");
			Control.globalPointer.getTextField().setText("");
		}
		else if(currentComponent instanceof SaveGUI) this.showGameGUI();
		else if(currentComponent instanceof LoadGUI) this.showLoadingMenu();
		else if(currentComponent instanceof MailSending) this.showGameGUI();
	}

	/**
	 * 
	 */
	public void forward(){
		if(currentComponent instanceof GameboardSettings){
			this.showPlayerSettings();
		}
		else if(currentComponent instanceof PlayerSettings){
			PlayerSettings.globalPointer.save();
			GameboardSettings.globalPointer.save();
			this.showGameGUI();
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
		else if(currentComponent instanceof GameGUI) state = true;
		else if(currentComponent instanceof SaveGUI) state = true;
		else if(currentComponent instanceof LoadGUI) state = true;
		else if(currentComponent instanceof MailSending) state = true;
		
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
			
			this.updateUI();
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
	
	/**
	 * 
	 */
	public void showPlayerSettings(){
		setNewComponent(playerSettings);
	}
	
	/**
	 * 
	 */
	public void showGameGUI(){
		setNewComponent(this.gameGUI);
	}
	
	/**
	 * 
	 */
	public void showWinningScreen(){
		setNewComponent(this.winningScreen);
	}
	
	/**
	 * 
	 */
	public void showSaveGUI(){
		setNewComponent(this.saveGUI);
	}
	
	/**
	 * 
	 */
	public void showLoadGUI(){
		setNewComponent(this.loadGUI);
	}
	
	/**
	 * 
	 */
	public void showMailSending(){
		setNewComponent(this.mailSending);
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