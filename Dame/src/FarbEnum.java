//################################################################################
//## Enums

public enum FarbEnum {

	// ################################################################################
	// ## Properties

	schwarz, weiﬂ;

	// ################################################################################
	// ## Methods

	/**
	 * Checks the color of the token and returns it.
	 * @param color
	 */

	public static String getColorName(FarbEnum color) {
		if (color == weiﬂ)
			return "Weiﬂ";
		else if (color == schwarz)
			return "Schwarz";
		else
			return "UNKOWN";
	}
}
