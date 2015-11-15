//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;

import Events.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class GameboardSettings extends MainPanelComponent{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private GameboardDesign design;

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
		label.setPreferredSize(new Dimension(600,30));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Gill Sans", Font.BOLD, 40));
		this.add(label, c);

		c.gridx = 0; c.gridy = 1;
		c.insets = new Insets(50,0,0,0);
		design = new GameboardDesign();
		this.add(design, c);

		c.gridx = 0; c.gridy = 2;
		JSlider slider = new JSlider(JSlider.HORIZONTAL,8,20,8);
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

		this.repaint();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void setGameboardSize(int size){
		if(size >= 8 && size <= 20){
			this.design.setGameboardSize(size);
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


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
			this.setPreferredSize(new Dimension(500,500));
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