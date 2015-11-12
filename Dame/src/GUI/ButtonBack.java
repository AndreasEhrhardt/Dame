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

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public ButtonBack(MainPanelComponent mpc){
		// Set main panel
		super(mpc);
		
		// Set button design
		this.setDefaultImage("Images/Back.png");
		this.setHoverImage("Images/Back_Hover.png");
		this.setPressImage("Images/Back_Pressed.png");
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void back(){
		mpc.getMainPanel().back();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}