//################################################################################
//## Package

package Enumerations;

//################################################################################
//## Enums

public enum FarbEnum {

	// ################################################################################
	// ## Properties

	schwarz, wei�;

	// ################################################################################
	// ## Methods

	/**
	 * Checks the color of the token and returns it.
	 * @param color
	 */

	public static String getColorName(FarbEnum color) {
		if (color == wei�)
			return "White";
		else if (color == schwarz)
			return "Black";
		else
			return "UNKOWN";
	}
}
