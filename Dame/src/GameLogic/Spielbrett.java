package GameLogic;
//################################################################################
//## Imports

import java.awt.*;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import Enumerations.FarbEnum;

//################################################################################
//## Class

/**
 * @author ehrha
 *
 */
@SuppressWarnings("serial")
public class Spielbrett implements Serializable  {
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
		//this.createField(8);
	}

	/**
	 * Saves the number of Felder for the game in an two dimensional array
	 * Constructor for a custom sized checkers game
	 * 
	 * @param fieldCount 
	 */
	public Spielbrett(int fieldCount){
		this.createField(fieldCount);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * @param size
	 */
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

	/**
	 * 
	 */
	private void addFigure(){
		int xPos = 0;
		// Add white figures
		for(int i = 0; i < (this.felder.length / 2) - 1; i++){
			for(int j = xPos; j < this.felder.length; j = j + 2){
				this.felder[j][i].setFigure(new Spielfigur(FarbEnum.weiß,new Point(j,i)));
			}

			xPos = ++xPos % 2;
		}

		xPos = 0;
		if(this.felder.length % 2 == 0) xPos = 1;
		// Add black figures
		for(int i = felder.length - 1; i >= felder.length - (this.felder.length / 2) + 1; i--){
			for(int j = xPos; j < this.felder.length; j = j + 2){
				this.felder[j][i].setFigure(new Spielfigur(FarbEnum.schwarz, new Point(j,i)));
			}

			xPos = ++xPos % 2;
		}
	}

	/**
	 * 
	 */
	private void setID(){
		// Define start variable
		int currentRow = 1;
		char currentColumn = (char)65;

		for (int i = 0; i < felder.length; i++){
			currentColumn = (char) 65;
			for (int j = 0; j < felder[i].length; j++) {
				String id = String.valueOf(currentColumn);
				id += String.valueOf(currentRow);
				currentColumn++;
				felder[j][i].setID(id);
			}
			currentRow++;
		} 
		/*
		// For every row - DESC
		for(int i = this.felder.length - 1; i >= 0; i--){
			currentColumn = (char)65;

			// For every column
			for(int j = 0; j < this.felder[i].length; j++){
				// Create new ID-Name
				StringBuilder fieldName = new StringBuilder();
				fieldName.append(currentRow);
				fieldName.append(currentColumn);

				// Set field names
				this.felder[j][i].setID(fieldName.toString());

				// Increase column value
				currentRow++;
			}

			// Increase row value
			 currentColumn++;
		}*/
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * Gets the number of Felder
	 */
	@XmlElement (name = "Column")
	public Spielfeld[][] getFields() {
		return felder;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	
	public Spielfeld getField(int x, int y){
		return this.felder[x][y];
	}
	
	

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	/**
	 * Number of Felder for the final board size
	 * Sets the number of Felder
	 * 
	 * @param felder 
	 */
	public void setFelder(Spielfeld[][] felder) {
		this.felder = felder;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Override )

	public Spielbrett clone(){
		Spielbrett newObj = new Spielbrett();
		
		Spielfeld newFelder[][] = new Spielfeld[this.felder.length][];
		
		for(int i = 0; i < this.felder.length; i++){
			
			Spielfeld newFelderRow[] = new Spielfeld[this.felder[i].length];
			
			for(int j = 0; j < this.felder[i].length; j++){
				newFelderRow[j] = this.felder[i][j].clone();
			}
			
			newFelder[i] = newFelderRow;
		}
		newObj.setFelder(newFelder);
		
		return newObj;
	}
	
	/**
	 * Compares if object is the same as this.
	 * If not it is casted to an object of this class.
	 * 
	 * Position data will also be checked and if there's already an object on a Feld,
	 * it will return false.
	 * 
	 * @param obj 
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