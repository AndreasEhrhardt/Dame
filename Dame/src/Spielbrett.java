//################################################################################
//## Imports

import java.awt.*;

//################################################################################
//## Class

/**
 * @author Andreas, Schie, Mintaha, Marvin
 *
 */
public class Spielbrett {
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	Spielfeld felder[][];

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * Constructor for a regular sized checkers game
	 */
	public Spielbrett(){
		// Create a 8x8 game-board
		this.createField(8);
	}

	/**
	 * @param fieldCount Saves the number of Felder for the game in an two dimensional array
	 * Constructor for a custom sized checkers game
	 */
	public Spielbrett(int fieldCount){
		this.createField(fieldCount);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	private void createField(int size){
		// Create field
		felder = new Spielfeld[size][size];

		// Fill "felder"
		for(int i = 0; i < this.felder.length; i++){
			for(int j = 0; j < this.felder[i].length; j++){
				this.felder[i][j] = new Spielfeld();
			}
		}

		// Add gmae figurs
		this.addFigure();

		// Set IDs
		this.setID();
	}

	private void addFigure(){
		int xPos = 0;

		// Add white figures
		for(int i = 0; i < 3; i++){
			for(int j = xPos; j < this.felder.length; j = j + 2){
				this.felder[i][j].setFigur(new Spielfigur(FarbEnum.weiß,new Point(i,j)));
			}

			xPos = ++xPos % 2;
		}

		xPos = 1;
		
		// Add black figures
		for(int i = felder.length - 1; i >= felder.length - 3; i--){
			for(int j = xPos; j < this.felder.length; j = j + 2){
				this.felder[i][j].setFigur(new Spielfigur(FarbEnum.schwarz, new Point(i,j)));
			}

			xPos = ++xPos % 2;
		}
	}

	private void setID(){
		// Define start variable
		char currentRow = (char)65;
		int currentColumn = 1;

		// For every row - DESC
		for(int i = this.felder.length - 1; i >= 0; i--){
			currentColumn = 1;

			// For every column
			for(int j = this.felder[i].length - 1; j >= 0; j--){
				// Create new ID-Name
				StringBuilder fieldName = new StringBuilder();
				fieldName.append(currentRow);
				fieldName.append(currentColumn);

				// Set field names
				this.felder[i][j].setID(fieldName.toString());

				// Increase column value
				currentColumn++;
			}

			// Increase row value
			currentRow = currentRow++;
		}
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * Gets the number of Felder
	 */
	public Spielfeld[][] getFelder() {
		return felder;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	/**
	 * @param felder Number of Felder for the final board size
	 * Sets the number of Felder
	 */
	public void setFelder(Spielfeld[][] felder) {
		this.felder = felder;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Override )

	/**
	 * @param obj 
	 * Compares if object is the same as this.
	 * If not it is casted to an object of this class.
	 * 
	 * Position data will also be checked and if there's already an object on a Feld,
	 * it will return false.
	 */
	public boolean equals(Object obj){
		// Check for self-comparing
		if(obj == this) return true;

		// Check for same class
		if ( obj == null || obj.getClass() != this.getClass() ) return false;

		// Cast object to same class
		Spielbrett SpielbrettObj = (Spielbrett)obj;

		// Check for same position data		
		if(this.felder.length == SpielbrettObj.felder.length){
			for(int i = 0; i < this.felder.length; i++){
				if(this.felder[i].length == SpielbrettObj.felder[i].length){
					for(int p = 0; p < this.felder[p].length; p++){
						if(!this.felder[i][p].equals(SpielbrettObj.felder[i][p])) return false;
					}
				}
			}
		}
		return true;
	}
}