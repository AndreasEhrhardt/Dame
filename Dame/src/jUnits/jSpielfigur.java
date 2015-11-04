package jUnits;
import static org.junit.Assert.*;
import java.awt.*;

import org.junit.Test;

import Enumerations.FarbEnum;
import GameLogic.Spielfigur;

public class jSpielfigur {
	
	@Test
	public void methodenTest(){
		boolean x= false;
		Point a= new Point(5,4);
		Spielfigur stein= new Spielfigur(FarbEnum.schwarz,a);
		if(stein.getColor()== FarbEnum.schwarz && stein.getPosiiton()==a){
			x=true;
		}assertTrue(x);
	}
}
