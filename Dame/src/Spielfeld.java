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
	 * 
	 * @param ID
	 *            Sets the ID
	 */
	public Spielfeld(String ID) {
		this.setID(ID);
	}

	/**
	 * Constructor 3
	 * 
	 * @param ID
	 * @param figur
	 *            Sets the token and the ID on the game board
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
	 * @return Returns the token on the board
	 */
	public Spielfigur getFigur() {
		return this.figur;
	}

	/**
	 * @return Returns the ID
	 */
	public String getID() {
		return this.ID;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods ( Setter)

	/**
	 * @param figur
	 *            This Method sets the token on the game board
	 */
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
	}

	/**
	 * @param ID
	 *            This Method sets the ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ Methods (Override)

}