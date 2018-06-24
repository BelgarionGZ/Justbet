package es.uvigo.esei.tfg.operations;

import java.io.IOException;

public class OperationsSingleton {
	private static Operations operations;

	public static synchronized Operations getInstance() throws IOException {
		if (operations == null) {
			operations = new Operations();
		}

		return operations;
	}
}