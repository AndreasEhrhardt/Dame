/**
 * 
 *
 */

public class SpielTest implements Bedienung {

	public static void main(String[] args) {

		Spiel spiel = new Spiel();
		Spielfeld[][] felder = null;
	
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder.length; j++) {
				//
				if(felder[i][j] ){
				System.out.println("Feld" + i + "," + j + "geklickt ");
				//
				//
			}
		  }
		}
	}

	@Override
	public String iBediener() {

		return null;
	}

}
