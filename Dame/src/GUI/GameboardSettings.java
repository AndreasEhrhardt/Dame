//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;

import Events.*;
import GameLogic.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class GameboardSettings extends MainPanelComponent{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private GameboardDesign design;
	private JSlider slider;

	public static GameboardSettings globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public GameboardSettings(){
		// Set global pointer
		GameboardSettings.globalPointer = this;

		// Prepare grid-layout
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0; c.gridy = 0;
		JLabel label = new JLabel("Gameboard size");
		label.setPreferredSize(new Dimension(400,30));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 40));
		this.add(label, c);

		c.gridx = 0; c.gridy = 1;
		c.insets = new Insets(30,0,0,0);
		design = new GameboardDesign();
		this.add(design, c);

		c.gridx = 0; c.gridy = 2;
		slider = new JSlider(JSlider.HORIZONTAL,8,20,8);
		slider.setPreferredSize(new Dimension(400,100));
		slider.addChangeListener(new EventHandler().new eGameboardStateChange());
		slider.setOpaque(false);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		this.add(slider, c);
		
		// Add listener
		this.addComponentListener(new EventHandler().new eGameboardSettings());

		// Repaint widget
		this.repaint();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void setGameboardSize(int size){
		if(size >= 8 && size <= 20){
			this.design.setGameboardSize(size);
		}
	}
	
	public void save(){
		Spiel game = MainFrame.globalPointer.getGame();
		game.setGameboard(new Spielbrett(this.slider.getValue()));
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public JSlider getSlider(){
		return this.slider;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

	private class GameboardDesign extends JPanel{

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Properties

		Color color1 = Color.gray;
		Color color2 = Color.white;

		int gameboardSize = 8;

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Constructor

		public GameboardDesign(){
			this.setPreferredSize(new Dimension(400,400));
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods

		public void setGameboardSize(int size){
			this.gameboardSize = size;

			this.repaint();
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Getter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods ( Setter)


		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		@Override
		protected void paintComponent(Graphics g){
			// Create extended draw device
			Graphics2D g2D = (Graphics2D) g;
			
			// Render hints
			g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			// Draw background
			g2D.setColor(Color.black);
			g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

			double currentX = 5, currentY = 5;
			double size;

			if(this.getWidth() < this.getHeight()) size = (double)(this.getWidth() - 10) /  this.gameboardSize;
			else size = (double)(this.getHeight() - 10) /  this.gameboardSize;

			for(int i = 0; i < gameboardSize; i++){
				currentX = 5;

				for(int j = 0; j < gameboardSize; j++){
					if(gameboardSize % 2 != 0){
						if((i + j) % 2 == 0) g.setColor(this.color1);
						else g.setColor(this.color2);
					}
					else{
						if((i + j) % 2 == 0) g.setColor(this.color2);
						else g.setColor(this.color1);
					}

					g2D.fillRect((int)currentX, (int)currentY, (int)size, (int)size);

					currentX += size;
				}
				currentY += size;
			}
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Inner class
	}

}