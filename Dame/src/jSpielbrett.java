import static org.junit.Assert.*;

import org.junit.Test;


public class jSpielbrett {

	@Test
	public void spielfeld(){
		Spielbrett brett=new Spielbrett();
		Spielfeld fields = brett.getField(7,7);
		equals(brett);
		equals(fields);
		
	}
	
	
}
