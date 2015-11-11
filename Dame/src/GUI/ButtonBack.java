//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports


//###########################################################
//## Class

public class ButtonBack extends ImageButton {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	MainPanel mp = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public ButtonBack(MainPanel mp){
		// Set main panel
		this.setMainPanel(mp);
		
		// Set button design
		this.setDefaultImage("Images/Back.png");
		this.setHoverImage("Images/Back_Hover.png");
		this.setPressImage("Images/Back_Pressed.png");
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void back(){
		mp.back();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setMainPanel(MainPanel mp){
		if(mp != null){
			this.mp = mp;
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}