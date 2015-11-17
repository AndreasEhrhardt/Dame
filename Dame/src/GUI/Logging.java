//###########################################################
//## Package

package GUI;

//###########################################################
//## Imports

import java.awt.*;
import java.sql.*;
import java.text.*;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.text.*;

//###########################################################
//## Class

@SuppressWarnings("serial")
public class Logging extends JScrollPane   {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private JTextArea textArea;

	public static Logging globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Logging(){
		// Set global pointer
		Logging.globalPointer = this;

		// Create text area
		textArea =  new JTextArea();

		// Set font color
		textArea.setDisabledTextColor(new Color(0,0,0));

		// Disable logging
		textArea.setEnabled(false);

		// Set margin
		textArea.setMargin( new Insets(10,10,10,10) );
		
		// Set font
		textArea.setFont(new Font("Gill Sans",Font.PLAIN,14));

		// Add text area to scroll pane
		this.setViewportView(textArea);

		// Always show scrollbar
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * @param message
	 */
	public void addMessage(String message){		
		if(!textArea.getText().isEmpty()) textArea.append("\n");

		int day = LocalDateTime.now().getDayOfMonth();
		int month = LocalDateTime.now().getMonth().getValue();
		int year = LocalDateTime.now().getYear();
		int minute = LocalDateTime.now().getMinute();
		int hour = LocalDateTime.now().getHour();

		textArea.append(day + "." + month + "." + year + " (" + hour + ":" + minute + "): " + message);
	}

	/**
	 * @param message
	 */
	public void addErrorMessage(String message){
		this.addMessage("[ERROR] " + message);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public JTextArea getTextArea(){
		return this.textArea;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}