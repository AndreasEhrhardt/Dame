//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import javax.swing.JPanel;

//###########################################################
//## Class

public abstract class MainPanelComponent extends JPanel{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	MainPanel mp;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public MainPanelComponent(MainPanel mp){
		this.setMainPanel(mp);
		
		mp.add(this);
		this.setVisible(false);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public MainPanel getMainPanel(){
		return this.mp;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setMainPanel(MainPanel mp){
		if(mp == null) throw new RuntimeException();
		
		this.mp = mp;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}