package es.uvigo.esei.tfg.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class Language extends Properties {
	private static final long serialVersionUID = 1L;
	private List<String> files;
	private String language;

	public Language() throws IOException {
		String defaultLanguage = (String) SettingsSingleton.getInstance().get("DEFAULT_LANGUAGE");
		files = ResourceFiles.getResources(Pattern.compile(".*\\.txt"));
		getProperties(defaultLanguage);
		language = new String(defaultLanguage);
	}

	public void changeLanguage(String l) throws IOException {
		String aux = l.toUpperCase();
		getProperties(aux);
		language = new String(aux);
	}

	public List<String> getFiles() {
		return files;
	}

	public String getLanguage() {
		return language;
	}

	private String getFileFullPathFromLanguageName(String languageName) {
		String toRet = null;

		for (String file : files) {
			if (languageName.equals(substringLanguageNameFromFileName(file))) {
				toRet = file;
			}
		}

		return toRet;
	}

	public String getLanguageSpecial() {
		String toRet = new String(language);

		if (language.equals("GB")) {
			toRet = new String("EN");
		}

		return toRet;
	}

	public List<String> getLanguagesNamesAvailable() {
		List<String> toRet = new ArrayList<String>();

		for (String file : files) {
			toRet.add(substringLanguageNameFromFileName(file));
		}

		return toRet;
	}

	private void getProperties(String languageName) throws IOException {
		String fileFullPath = getFileFullPathFromLanguageName(languageName);
		FileInputStream in = new FileInputStream(fileFullPath);
		load(in);
	}

	public String languagesAvailable() {
		String toRet = new String("(");

		for (String file : files) {
			toRet = toRet.concat(substringLanguageNameFromFileName(file + "/"));
		}

		toRet = toRet.substring(0, toRet.length() - 1);

		toRet = toRet.concat(")");

		return toRet;
	}

	private String substringLanguageNameFromFileName(String file) {
		return file.substring(file.length() - 6, file.length() - 4);
	}
}