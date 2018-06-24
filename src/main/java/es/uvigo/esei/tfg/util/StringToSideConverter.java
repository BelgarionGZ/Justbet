package es.uvigo.esei.tfg.util;

import es.uvigo.esei.tfg.enums.Side;

public class StringToSideConverter {
	public static Side StringToSide(String s) {
		Side side = Side.BACK;

		if (s.toLowerCase().equals("lay")) {
			side = Side.LAY;
		}

		return side;
	}
}