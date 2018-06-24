package es.uvigo.esei.tfg.util;

import java.io.IOException;

public class LanguageSingleton {
	private static Language language;

	public static synchronized Language getInstance() throws IOException {
		if (language == null) {
			language = new Language();
		}

		return language;
	}
}