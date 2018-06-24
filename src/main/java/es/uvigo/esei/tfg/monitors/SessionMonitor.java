package es.uvigo.esei.tfg.monitors;

import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.gui.FrameMessage;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;

public class SessionMonitor extends AbstractMonitor {
	private Operations operations;

	public SessionMonitor() throws IOException {
		super();
		limit = 300000;
		operations = OperationsSingleton.getInstance();
	}

	@Override
	public void run() {
		try {
			running = true;
			startTime = System.currentTimeMillis();

			while (running) {
				long endTime = System.currentTimeMillis();

				if ((endTime - startTime) >= limit) {
					Map<String, String> session = operations.keepAlive();

					if (!session.get("status").equals("SUCCESS")) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, language.getProperty("PROBLEM_LOGIN"),
								"Error");
					}
				}
			}
		} catch (IOException e) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, language.getProperty("PROBLEM_IO"), "Error");
		}
	}
}