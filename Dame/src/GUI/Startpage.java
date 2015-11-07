//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import javax.swing.*;
import java.awt.*;

//###########################################################
//## Class

public class Startpage extends JPanel {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Startpage(){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		ImageButton button = new ImageButton("Button 1");
		button.setDefaultImage("Images/Play.png");
		button.setPressImage("Images/Play_Click.png");
		button.setHoverImage("Images/Play_Hover.png");
        c.gridx = 0;
        c.gridy = 0;
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, c);

        button = new ImageButton();
        button.setDefaultImage("Images/Load.png");
        button.setPressImage("Images/Load_Pressed.png");
        button.setHoverImage("Images/Load_Hover.png");
        c.insets = new Insets(0,150,0,0);
        c.gridx = 1;
        c.gridy = 0;
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, c);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}