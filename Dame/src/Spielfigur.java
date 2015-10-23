//###########################################################
//## Import

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
	private Point pos;
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * @param color
	 * @param position
	 */
	private Spielfigur(FarbEnum color)
	{
		// Set values
		this.setColor(color);
	}
	
	/**
	 * @param color
	 * @param pos
	 */
	public Spielfigur(FarbEnum color, Point pos)
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
	
	/**
	 * @return
	 */
	public Point getPosiiton(){
		return this.pos;
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
	 * @param pos
	 */
	public void setPoint(Point pos){
		this.pos = pos;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
			if(this.equals(SpielfigurObj)){
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Spielfigur [color=" + color +  "]";
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Spielfigur clone(){
		Spielfigur newObj = new Spielfigur(this.getColor());
		return newObj;
	}
}
