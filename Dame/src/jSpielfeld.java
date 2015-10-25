import org.junit.Test;

public class jSpielfeld {

	@Test
	public void testSpielfeld() {
		Spielfeld feld = new Spielfeld();
		Spielfigur figur = feld.getFigur();

		equals(feld.getID());

	}

}
