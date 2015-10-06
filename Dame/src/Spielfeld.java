//###########################################################
//## Imports


//###########################################################
//## Class

public class Spielfeld {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private String ID;
	private Spielfigur figur;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public Spielfeld(){
		
	}
	
	public Spielfeld(String ID){
		this.setID(ID);
	}
	
	public Spielfeld(String ID, Spielfigur figur){
		this.setFigur(figur);
		this.setID(ID);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * @return
	 */
	public Spielfigur getFigur(){
		return this.figur;
	}
	
	/**
	 * @return
	 */
	public String getID(){
		return this.ID;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)
	
	/**
	 * @param figur
	 */
	public void setFigur(Spielfigur figur){
		this.figur = figur;
	}

	/**
	 * @param ID
	 */
	public void setID(String ID){
		this.ID = ID;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}