//################################################################################
//## Enums

public enum FarbEnum {
	
	//################################################################################
	//## Properties
	
	schwarz, weiﬂ;

	//################################################################################
	//## Methods
	
	public static String getColorName(FarbEnum color){
		if(color == weiﬂ) return "Weiﬂ";
		else if(color == schwarz) return "Schwarz";
		else return "UNKOWN";
	}
}
