//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class Logging extends JTextArea  {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	public static Logging globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Logging(){
		// Set global pointer
		Logging.globalPointer = this;

		// Disable logging
		this.setEnabled(false);
		
		// Set font color
		this.setDisabledTextColor(new Color(0,0,0));
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void addMessage(String message){		
		if(!this.getText().isEmpty()) this.append("\n");
		this.append(message);
	}
	
	public void addErrorMessage(String message){
		if(!this.getText().isEmpty()) this.append("\n");
		this.append("[ERROR] " + message);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}