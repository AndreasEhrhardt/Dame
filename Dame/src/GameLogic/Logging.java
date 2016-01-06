//###########################################################
//## Package

package GameLogic;

//###########################################################
//## Imports

import java.time.LocalDateTime;
import java.util.ArrayList;

//###########################################################
//## Class

public class Logging  {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	public static Logging globalPointer = null;
	ArrayList <String> logs = new ArrayList<>();

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Logging(){
		// Set global pointer
		Logging.globalPointer = this;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * @param message
	 */
	public void addMessage(String message){		
		int day = LocalDateTime.now().getDayOfMonth();
		int month = LocalDateTime.now().getMonth().getValue();
		int year = LocalDateTime.now().getYear();
		int minute = LocalDateTime.now().getMinute();
		int hour = LocalDateTime.now().getHour();

		logs.add(day + "." + month + "." + year + " (" + hour + ":" + minute + "): " + message);
	}

	/**
	 * @param message
	 */
	public void addErrorMessage(String message){
		this.addMessage("[ERROR] " + message);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public ArrayList<String> getLogs(){
		return this.logs;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}