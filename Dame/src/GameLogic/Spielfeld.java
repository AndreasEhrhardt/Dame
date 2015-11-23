package GameLogic;
//###########################################################
//## Imports

import java.io.Serializable;

//###########################################################
//## Class

/**
 * @author ehrha
 *
 */
public class Spielfeld implements Serializable {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private String ID;
	private Spielfigur figure = null;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Constructor

	/**
	 * Default constructor
	 */
	public Spielfeld() {

	}

	/**
	 * Constructor 2
	 * Sets the ID
	 * 
	 * @param ID
	 */
	public Spielfeld(String ID) {
		this.setID(ID);
	}

	/**
	 * @param ID
	 * @param figure
	 */
	public Spielfeld(String ID, Spielfigur figure) {
		this.setFigure(figure);
		this.setID(ID);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	/**
	 * Removes the token from the board
	 */
	public void removeFigure() {
		this.figure = null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	/**
	 * Returns the token on the board
	 * 
	 * @return 
	 */
	public Spielfigur getFigure() {
		return this.figure;
	}

	/**
	 * Returns the ID
	 * 
	 * @return 
	 */
	public String getID() {
		return this.ID;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 *  This Method sets the token on the game board
	 * 
	 * @param figure
	 */
	public void setFigure(Spielfigur figure) {
		this.figure = figure;
	}

	/**
	 * This Method sets the ID
	 * 
	 * @param ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Spielfeld clone(){
		Spielfeld newObj = new Spielfeld();
		
		if(this.figure != null) newObj.setFigure(this.figure.clone());
		else newObj.setFigure(null);
		
		newObj.setID(this.ID);
		
		return newObj;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "Spielfeld = { ID=" + this.getID() + "; figure=" + this.getFigure() + " }";
	}
	
}