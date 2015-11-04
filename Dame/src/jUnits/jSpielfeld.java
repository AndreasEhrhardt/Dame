package jUnits;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import GameLogic.Spielfeld;
import GameLogic.Spielfigur;

public class jSpielfeld {

	@Test
	public void testSpielfeld() {
		boolean wow=false;
		Spielfeld feld = new Spielfeld();
		Spielfigur figur = feld.getFigure();
		String ID= "A1";
		Spielfeld A1 = new Spielfeld(ID,figur);
		if(A1.getFigure()==feld.getFigure()&& A1.getID()=="A1"){
			wow=true;
		}assertTrue(wow);
		
	}

}
