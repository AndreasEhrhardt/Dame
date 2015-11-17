//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;

import Enumerations.FarbEnum;
import Events.EventHandler;
import GameLogic.Spieler;
import KI.KI_Dame;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class PlayerSettings extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private PlayerSelectionButton player1;
	private PlayerSelectionButton player1_KI;
	private PlayerSelectionButton player2;
	private PlayerSelectionButton player2_KI;

	private JTextField name1;
	private JTextField name2;

	public static PlayerSettings globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public PlayerSettings(){
		// Set global pointer
		PlayerSettings.globalPointer = this;

		// Prepare grid-layout
		this.setLayout(new GridBagLayout());

		// Create gui elements
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,0,50,50);

		JLabel label = new JLabel("Player settings");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 40));
		c.gridwidth = 3;
		c.gridx = 0; c.gridy = 0;
		this.add(label, c);
		c.gridwidth = 1;




		c.insets = new Insets(5,0,5,100);




		c.gridx = 0; c.gridy = 1;
		label = new JLabel("Player 1 (Schwarz)");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 20));
		this.add(label, c);

		c.gridx = 0; c.gridy = 2;
		player1 = new PlayerSelectionButton("Player",false,1);
		this.add(player1, c);

		c.gridx = 0; c.gridy = 3;
		player1_KI = new PlayerSelectionButton("Computer",true,1);
		this.add(player1_KI, c);

		c.gridx = 0; c.gridy = 4;
		label = new JLabel("Name");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 20));
		this.add(label, c);

		c.gridx = 0; c.gridy = 5;
		name1 = new JTextField();
		name1.setPreferredSize(new Dimension(300,50));
		name1.setFont(new Font("Gill Sans", Font.BOLD, 30));
		this.add(name1, c);



		c.insets = new Insets(5,0,5,0);



		c.gridx = 1; c.gridy = 1;
		c.gridheight = 5;
		JPanel mid = new JPanel();
		mid.setBackground(new Color(20,20,20,150));
		mid.setPreferredSize(new Dimension(5,400));
		this.add(mid, c);
		c.gridheight = 1;



		c.insets = new Insets(5,100,5,0);



		c.gridx = 2; c.gridy = 1;
		JLabel player2_label = new JLabel("Player 2 (Weiß)");
		player2_label.setHorizontalAlignment(JLabel.CENTER);
		player2_label.setFont(new Font("Gill Sans", Font.BOLD, 20));
		this.add(player2_label, c);

		c.gridx = 2; c.gridy = 2;
		player2 = new PlayerSelectionButton("Player",false,2);
		this.add(player2, c);

		c.gridx = 2; c.gridy = 3;
		player2_KI = new PlayerSelectionButton("Computer",true,2);
		this.add(player2_KI, c);

		c.gridx = 2; c.gridy = 4;
		label = new JLabel("Name");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 20));
		this.add(label, c);

		c.gridx = 2; c.gridy = 5;
		name2 = new JTextField();
		name2.setPreferredSize(new Dimension(300,50));
		name2.setFont(new Font("Gill Sans", Font.BOLD, 30));
		this.add(name2, c);

		// Add listener
		super.addComponentListener(new EventHandler().new ePlayerSettings());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void save(){
		Spieler player1 = new Spieler();
		player1.setColor(FarbEnum.schwarz);
		if(this.name1.getText().isEmpty()) this.name1.setText("Spieler 1");
		player1.setName(this.name1.getText());
		if(this.player1_KI.isEnabled) player1.setKI(new KI_Dame());
		
		Spieler player2 = new Spieler();
		player2.setColor(FarbEnum.schwarz);
		if(this.name2.getText().isEmpty()) this.name2.setText("Spieler 2");
		player1.setName(this.name2.getText());
		if(this.player2_KI.isEnabled) player2.setKI(new KI_Dame());
		
		MainFrame.globalPointer.getGame().setPlayer(1, player1);
		MainFrame.globalPointer.getGame().setPlayer(2, player2);
		
		MainFrame.globalPointer.getGame().setCurrentGamer(FarbEnum.schwarz);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public PlayerSelectionButton getPlayer1Button(){
		return this.player1;
	}

	public PlayerSelectionButton getKI1Button(){
		return this.player1_KI;
	}

	public PlayerSelectionButton getPlayer2Button(){
		return this.player2;
	}

	public PlayerSelectionButton getKI2Button(){
		return this.player2_KI;
	}

	public JTextField getPlayer1Name(){
		return this.name1;
	}

	public JTextField getPlayer2Name(){
		return this.name2;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

	public class PlayerSelectionButton extends JButton {

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Properties

		private boolean isKI = false;
		private int playerID = 0;
		private boolean isEnabled;

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Constructor

		/**
		 * 
		 */
		public PlayerSelectionButton(String text, boolean isKI, int playerID){
			super(text);

			this.isKI = isKI;
			this.playerID = playerID;
			isEnabled = false;

			this.setOpaque(false);

			this.setPreferredSize(new Dimension(300,100));

			this.addActionListener(new EventHandler().new ePlayerButtonClicked());
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods

		public void setEnabled(){
			this.isEnabled = true;

			JTextField name;
			if(this.playerID == 1) name = PlayerSettings.globalPointer.getPlayer1Name();
			else name = PlayerSettings.globalPointer.getPlayer2Name();

			if(this.isKI){
				name.setEnabled(false);
				name.setText("Player " + this.playerID + " (Computer)");
			}
			else{
				name.setEnabled(true);
				name.setText("");
			}

			this.repaint();
		}

		public void setDisabled(){
			this.isEnabled = false;
			this.repaint();
		}

		public boolean isEnabled(){
			return this.isEnabled;
		}

		public boolean isKI(){
			return this.isKI;
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Getter)

		public int getPlayerID(){
			return this.playerID;
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Setter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		@Override
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

			// Draw inner rect
			Color color;
			if(getModel().isPressed()) color = new Color(0,115,160);
			else if(getModel().isRollover()) color = new  Color(0,145,190);
			else if(this.isEnabled) color = new Color(0,175,220);
			else color = new Color(150,150,150);

			// Create gradient
			GradientPaint gradient = new GradientPaint(0, 0, color, this.getWidth() / 2, 0, color.darker(), true);

			// Set left color
			g2D.setPaint(gradient);

			//g2D.fillRoundRect(5, 5, this.getWidth() - 10, this.getHeight() - 10, 100, 100);
			g2D.fillRect(5, 5, this.getWidth() - 10, this.getHeight() - 10);

			// Draw text
			g2D.setColor(Color.white);
			g2D.setFont(new Font("Gill Sans",Font.BOLD,25));
			MainFrame.drawCenteredString(g2D, this.getText(), new Rectangle(0,0,this.getWidth(), this.getHeight()));
		}


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}
}