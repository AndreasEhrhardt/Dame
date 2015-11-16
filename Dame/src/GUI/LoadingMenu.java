//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;
import Events.EventHandler.*;
import Events.*;

//###########################################################
//## Class

public class LoadingMenu extends MainPanelComponent {
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	ImageButton loadingSerializeButton = null;
	ImageButton loadingCSVButton = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public LoadingMenu(){		
		this.addComponentListener(new EventHandler().new eLoadingMenu());
		
		loadingSerializeButton = new ImageButton("Restore");
		loadingSerializeButton.setDefaultImage("Images/Restore.png");
		loadingSerializeButton.setHoverImage("Images/Restore_Hover.png");
		loadingSerializeButton.setPressImage("Images/Restore_Pressed.png");
		loadingSerializeButton.setDisabledImage("Images/Restore_Disabled.png");
		
		loadingCSVButton = new ImageButton("Load file");
		loadingCSVButton.setDefaultImage("Images/Load_File.png");
		loadingCSVButton.setHoverImage("Images/Load_File_Hover.png");
		loadingCSVButton.setPressImage("Images/Load_File_Pressed.png");
		
		loadingSerializeButton.setPreferredSize(new Dimension(200,200));
		loadingCSVButton.setPreferredSize(new Dimension(200,200));
		
		// Prepare grid-layout
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
        c.gridx = 0; c.gridy = 0; 
        this.add(loadingSerializeButton, c);
        c.insets = new Insets(0,150,0,0);
        c.gridx = 1; c.gridy = 0;
        this.add(loadingCSVButton, c);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public ImageButton getLoadingSerializeButton(){
		return this.loadingSerializeButton;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}