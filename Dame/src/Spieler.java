//###########################################################
//## Imports


//###########################################################
//## Class

public class Spieler {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	String name;
	FarbEnum color;
	KI ki_player = null;
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor
	
	public Spieler(){
		this.setName("");
	}
	
	/**
	 * @param name
	 */
	public Spieler(KI ki_player){
		setKI(ki_player);
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
	
	public KI getKi(){
		return this.ki_player;
	}

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

	public void setKI(KI ki_player){
		this.ki_player = ki_player;
	}
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