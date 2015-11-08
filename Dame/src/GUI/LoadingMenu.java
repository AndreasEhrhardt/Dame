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

public class LoadingMenu extends JPanel{
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	ImageButton loadingSerialize = null;
	ImageButton loadingCSV = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public LoadingMenu(){
		this.addComponentListener(new EventHandler().new eLoadingMenu());
		
		loadingSerialize = new ImageButton();
		loadingSerialize.setDefaultImage("");
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