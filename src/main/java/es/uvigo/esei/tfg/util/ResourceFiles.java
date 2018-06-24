package es.uvigo.esei.tfg.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class ResourceFiles {
	public static List<String> getResources(Pattern pattern) throws IOException {
		List<String> retval = new ArrayList<String>();
		Settings settings = SettingsSingleton.getInstance();

		String classPath = settings.getProperty("JAVA_CLASS_PATH");
		String[] classPathElements = classPath.split(settings.getProperty("PATH_SEPARATOR"));

		for (String element : classPathElements) {
			retval.addAll(getResources(element, pattern));
		}

		return retval;
	}

	private static List<String> getResources(String element, Pattern pattern) {
		File file = new File(element);
		List<String> retval = new ArrayList<String>();

		if (file.isDirectory()) {
			retval.addAll(getResourcesFromDirectory(file, pattern));
		}

		return retval;
	}

	private static Collection<String> getResourcesFromDirectory(File directory, Pattern pattern) {
		ArrayList<String> retval = new ArrayList<String>();
		File[] fileList = directory.listFiles();

		for (File file : fileList) {
			if (file.isDirectory()) {
				retval.addAll(getResourcesFromDirectory(file, pattern));
			} else {
				try {
					String fileName = file.getCanonicalPath();
					Boolean accept = pattern.matcher(fileName).matches();

					if (accept) {
						retval.add(fileName);
					}
				} catch (IOException e) {
					throw new Error(e);
				}
			}
		}

		return retval;
	}
}