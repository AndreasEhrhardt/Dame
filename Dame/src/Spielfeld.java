//###########################################################
//## Imports

//###########################################################
//## Class

public class Spielfeld {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Properties

	private String ID;
	private Spielfigur figur = null;

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
	 * Constructor 3
	 * Sets the token and the ID on the game board
	 * 
	 * @param ID
	 * @param figur
	 */
	public Spielfeld(String ID, Spielfigur figur) {
		this.setFigur(figur);
		this.setID(ID);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods

	/**
	 * Removes the token from the board
	 */
	public void removeFigur() {
		this.figur = null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Getter)

	/**
	 * Returns the token on the board
	 * 
	 * @return 
	 */
	public Spielfigur getFigur() {
		return this.figur;
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
	 * @param figur
	 */
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
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

}