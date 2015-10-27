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

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private FarbEnum color;
	private Point pos;
	private boolean dame;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	private Spielfigur(){
		this.setDame(false);
	}
	
	/**
	 * Sets the color of the token
	 * 
	 * @param color Color input
	 */
	private Spielfigur(FarbEnum color) {
		// Call default constructor
		this();
		
		// Set values
		this.setColor(color);
	}

	/**
	 * Sets the color and the position of the token
	 * 
	 * @param color Color input
	 * @param pos Position input
	 */
	public Spielfigur(FarbEnum color, Point pos) {
		// Call default constructor
		this();
		
		// Set values
		this.setColor(color);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods
	
	/**
	 * @return
	 */
	public boolean isDame(){
		return this.dame;
	}
	
	/**
	 * @param isDame
	 */
	public void setDame(boolean isDame){
		this.dame = isDame;
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Getter)
	
	/**11
	 * Getter method of "color" Returns a reference of color
	 * 
	 * @return color
	 */
	public FarbEnum getColor() {
		return color;
	}

	/**
	 * Returns the position
	 * 
	 * @return 
	 */
	public Point getPosiiton() {
		return this.pos;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 * Method for setting the color of the token
	 * 
	 * @param color
	 */
	public void setColor(FarbEnum color) {
		this.color = color;
	}

	/**
	 * Method for setting the position of the token
	 * 
	 * @param pos
	 */
	public void setPoint(Point pos) {
		this.pos = pos;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// Check for self-comparing
		if (obj == this)
			return true;

		// Check for same class
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		// Cast object to same class
		Spielfigur SpielfigurObj = (Spielfigur) obj;

		// Check for same color
		if (this.color == SpielfigurObj.color) {
			// Check for same position
			if (this.equals(SpielfigurObj)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Spielfigur [color=" + color + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Spielfigur clone() {
		Spielfigur newObj = new Spielfigur(this.getColor());
		return newObj;
	}
}
