package GameLogic;
//###########################################################
//## Import

import java.awt.*;
import java.io.Serializable;

import Enumerations.FarbEnum;

//###########################################################
//## Class

/**
 * @author Andreas
 *
 */
public class Spielfigur implements Serializable  {

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
		
		// Set position
		this.setPoint(pos);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods
	
	/**
	 * Get the state of the figure. Is the figure a "dame" or "normal"
	 * 
	 * @return Return property "dame"
	 */
	public boolean isDame(){
		return this.dame;
	}
	
	/**
	 * Changed figure to "dame" or to "normal"
	 * 
	 * @param isDame Set to "dame" or to "normal"
	 */
	public void setDame(boolean isDame){
		this.dame = isDame;
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Getter)
	
	/**
	 * Getter method of "color" Returns a reference of color
	 * 
	 * @return color Get the current color of the figure
	 */
	public FarbEnum getColor() {
		return color;
	}

	/**
	 * Returns the position
	 * 
	 * @return Returns the current position of the figure
	 */
	public Point getPosiiton() {
		return this.pos;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 * Method for setting the color of the token
	 * 
	 * @param color Set the color of the figure to "color"
	 */
	public void setColor(FarbEnum color) {
		this.color = color;
	}

	/**
	 * Method for setting the position of the token
	 * 
	 * @param pos The new position of the figure
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
		if (obj == this) return true;

		// Check for same class
		if (obj == null || obj.getClass() != this.getClass()) return false;

		// Cast object to same class
		Spielfigur SpielfigurObj = (Spielfigur) obj;

		// Check for same color
		if (this.color == SpielfigurObj.color) {
			// Check for same position
			if (this.pos.equals(SpielfigurObj.getPosiiton())) return true;
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
