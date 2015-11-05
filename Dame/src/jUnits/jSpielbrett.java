//################################################################################
//## Package

package jUnits;

//################################################################################
//## Import packages

import static org.junit.Assert.*;
import org.junit.Test;

import Enumerations.FarbEnum;
import GameLogic.*;

//################################################################################
//## Class 

public class jSpielbrett {

	@Test
	public void spielfeld(){
		boolean wow=false;
		Spielbrett brett = new Spielbrett();
		Spielfeld fields = brett.getField(0,0);
		if(fields.getFigure().getColor() == FarbEnum.weiﬂ){
			wow=true;
		}assertTrue(wow);
		
	}
	
	
}
