//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import javax.swing.*;

import Events.*;
import Events.EventHandler.*;

import java.awt.*;

//###########################################################
//## Class

public class Startpage extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Startpage(){	
		// Prepare grid-layout
		this.setLayout(new GridBagLayout());
		
		ImageButton playButton = new ImageButton("Play");
		playButton.setDefaultImage("Images/Play.png");
		playButton.setPressImage("Images/Play_Click.png");
		playButton.setHoverImage("Images/Play_Hover.png");

        ImageButton loadButton = new ImageButton("Load");
        loadButton.setDefaultImage("Images/Load.png");
        loadButton.setPressImage("Images/Load_Pressed.png");
        loadButton.setHoverImage("Images/Load_Hover.png");
        
        playButton.setPreferredSize(new Dimension(200,200));
        loadButton.setPreferredSize(new Dimension(200,200));
        
        playButton.addActionListener(new EventHandler().new eShowGameboardSettingsButton());
        loadButton.addActionListener(new EventHandler().new eShowLoadingMenuButton());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        this.add(playButton, c);
        
        c.insets = new Insets(0,150,0,0);
        c.gridx = 1;
        c.gridy = 0;
        this.add(loadButton, c);
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