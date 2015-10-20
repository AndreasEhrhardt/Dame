//###########################################################
//## Imports


//###########################################################
//## Class

public class Spieler {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	String name;
	FarbEnum color;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor
	
	public Spieler(){
		this.setName("");
	}
	
	/**
	 * @param name
	 */
	public Spieler(String name){
		this.setName(name);
	}
	
	/**
	 * @param name
	 * @param color
	 */
	public Spieler(String name, FarbEnum color){
		this.setName(name);
		this.setColor(color);
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods
	
	protected void move(){
		
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	/**
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return
	 */
	public FarbEnum getColor(){
		return this.color;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	/**
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * @param color
	 */
	public void setColor(FarbEnum color){
		this.color = color;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

}