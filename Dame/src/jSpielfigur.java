import static org.junit.Assert.*;
import java.awt.*;

import org.junit.Test;

public class jSpielfigur {
	
	@Test
	public void constructors(){
		FarbEnum farbe = FarbEnum.wei�;
		Spielfigur figur = new Spielfigur(farbe, new Point());
	}
}