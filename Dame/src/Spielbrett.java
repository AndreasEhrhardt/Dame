
/**
 * @author Andreas
 *
 */
public class Spielbrett {
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	Spielfeld felder[][];

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	/**
	 * 
	 */
	public Spielbrett(){
		// Create a 8x8 game-board
		felder = new Spielfeld[8][8];
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public Spielfeld[][] getFelder() {
		return felder;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)

	public void setFelder(Spielfeld[][] felder) {
		this.felder = felder;
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( default )

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