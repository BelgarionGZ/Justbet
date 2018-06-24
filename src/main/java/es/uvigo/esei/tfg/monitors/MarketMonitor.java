package es.uvigo.esei.tfg.monitors;

import java.io.IOException;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.gui.FrameMain;
import es.uvigo.esei.tfg.gui.FrameMainSingleton;
import es.uvigo.esei.tfg.gui.FrameMessage;

public class MarketMonitor extends AbstractMonitor {
	private FrameMain frame;

	public MarketMonitor() throws IOException, LoginException {
		super();
		frame = FrameMainSingleton.getInstance();
		limit = 5000;
	}

	@Override
	public void run() {
		try {
			running = true;
			startTime = System.currentTimeMillis();

			while (running) {
				long endTime = System.currentTimeMillis();

				if ((endTime - startTime) >= limit) {
					frame.getCenterPanel().updatePanel();
					startTime = System.currentTimeMillis();
				}
			}
		} catch (IOException e) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, language.getProperty("PROBLEM_IO"), "Error");
		}
	}
}