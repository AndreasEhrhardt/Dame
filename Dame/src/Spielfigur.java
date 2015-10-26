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

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	/**
	 * @param color Color input
	 *            Sets the color of the token
	 */
	private Spielfigur(FarbEnum color) {
		// Set values
		this.setColor(color);
	}

	/**
	 * @param color Color input
	 * @param pos Position input
	 * 
	 *            Sets the color and the position of the token
	 */
	public Spielfigur(FarbEnum color, Point pos) {
		// Set values
		this.setColor(color);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	/**
	 * Getter method of "color" Return a reference of color
	 * 
	 * @return color
	 */
	public FarbEnum getColor() {
		return color;
	}

	/**
	 * @return Returns the position
	 */
	public Point getPosiiton() {
		return this.pos;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 * @param color Color 
	 *            Method for setting the color of the token
	 */
	public void setColor(FarbEnum color) {
		this.color = color;
	}

	/**
	 * @param pos Position data
	 *            Method for setting the position of the token
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
