package es.uvigo.esei.tfg.monitors;

import java.io.IOException;

import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class AbstractMonitor extends Thread {
	protected Language language;
	protected long endTime;
	protected long limit;
	protected long startTime;
	protected volatile boolean running;

	public AbstractMonitor() throws IOException {
		language = LanguageSingleton.getInstance();
		running = false;
	}

	public void setStop() {
		running = false;
	}
}
