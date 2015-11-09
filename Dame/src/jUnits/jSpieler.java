//################################################################################
//## Import packages

package jUnits;

//################################################################################
//## Import packages

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;
import GameLogic.Spieler;
import Enumerations.FarbEnum;
import GameLogic.*;
import KI.KI_Dame;

//################################################################################
//## Class

public class jSpieler {
	@Test
	public void posToStringTest(){
		boolean wow=false;
		String erg="A1";
		Spieler ali= new Spieler();
		Point A1 = new Point();
		A1.setLocation(0,2);
		System.out.println(ali.posToString(A1));
		if(ali.posToString(A1)==erg){
			wow = true;
		}assertTrue(wow);
	}
}
