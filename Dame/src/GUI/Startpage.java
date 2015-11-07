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
		
		JButton button = new JButton("Button 1");
        c.gridx = 0;
        c.gridy = 0;
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, c);

        button = new JButton("Button 2");
        c.insets = new Insets(0,50,0,0);
        c.gridx = 1;
        c.gridy = 0;
        button.setPreferredSize(new Dimension(200,200));
        this.add(button, c);
        
        this.setVisible(true);
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