//###########################################################
//## Imports

import java.awt.*;

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

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * @param color
	 * @param position
	 */
	public Spielfigur(FarbEnum color)
	{
		// Set values
		this.setColor(color);
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

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	/**
	 * @param color
	 */
	public void setColor(FarbEnum color){
		this.color = color;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

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
