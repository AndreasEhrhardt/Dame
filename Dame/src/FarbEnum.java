//################################################################################
//## Enums

public enum FarbEnum {

	// ################################################################################
	// ## Properties

	schwarz, weiﬂ;

	// ################################################################################
	// ## Methods

	/**
	 * @param color
	 *            Checks the color of the token and returns it.
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
