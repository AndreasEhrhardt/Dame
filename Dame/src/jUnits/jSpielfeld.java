package jUnits;
import org.junit.Test;

import GameLogic.Spielfeld;
import GameLogic.Spielfigur;

public class jSpielfeld {

	@Test
	public void testSpielfeld() {
		Spielfeld feld = new Spielfeld();
		Spielfigur figure = feld.getFigure();

		equals(feld.getID());

	}

}
