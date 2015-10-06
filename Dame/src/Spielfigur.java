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
	//++ Methods ( Getter)

	/**
	 * Getter method of "color"
	 * Return a reference of color
	 * @return color
	 */
	public FarbEnum getColor(){
		return color;
	}

	/**
	 * Getter method of "position"
	 * Return a reference of position
	 * @return position
	 */
	public Point getPosition(){
		return position;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

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

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (defaults)

	@Override
	public boolean equals(Object obj){
		// Check for self-comparing
		if(obj == this) return true;

		// Check for same class
		if ( obj == null || obj.getClass() != this.getClass() ) return false;

		// Cast object to same class
		Spielfigur SpielfigurObj = (Spielfigur)obj;

		// Check for same color
		if(this.color == SpielfigurObj.color){
			// Check for same position
			if(this.position.equals(SpielfigurObj.position)){
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Spielfigur [color=" + color + ", position=" + position + "]";
	}	

	@Override
	public Spielfigur clone(){
		Spielfigur newObj = new Spielfigur(this.getColor(),this.getPosition());
		return newObj;
	}
}
