//###########################################################
//## Imports

import java.awt.*;

//###########################################################
//## Enums

enum FarbEnum{
	schwarz, weiﬂ
}

//###########################################################
//## Class


/**
 * @author Andreas
 *
 */
public class Spielfigur {
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties
	
	private FarbEnum color;
	private Point position;
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor
		
	/**
	 * @param color
	 * @param position
	 */
	public Spielfigur(FarbEnum color, Point position)
	{
		// Set values
		this.setColor(color);
		this.setPosition(position);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods
	
	/**
	 * @param color
	 */
	public void setColor(FarbEnum color){
		this.color = color;
	}
	
	/**
	 * @param position
	 */
	public void setPosition(Point position){
		this.position = position;
	}
}
