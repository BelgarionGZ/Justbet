package es.uvigo.esei.tfg.util;

import java.io.IOException;

public class IdsStructureSingleton {
	private static IdsStructure idsStructure;

	public static synchronized IdsStructure getInstance() throws IOException {
		if (idsStructure == null) {
			idsStructure = new IdsStructure();
		}

		return idsStructure;
	}
}