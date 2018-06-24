package es.uvigo.esei.tfg.gui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class GUI {
	public static void begin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI();
				} catch (Exception e) {
					e.printStackTrace();
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, "Error", "Error");
				}
			}
		});
	}

	public GUI() throws IOException, LoginException {
		initialize();
	}

	private void initialize() {
		Language l = null;
		try {
			l = LanguageSingleton.getInstance();
		} catch (IOException e1) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, "There is a problem with properties file",
					"Error");
		}

		try {
			FrameMain frame = FrameMainSingleton.getInstance();
			frame.getFrame().setVisible(true);
		} catch (IOException e) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
		} catch (LoginException e) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
					l.getProperty("PROBLEM_LOGIN") + " " + e.getMessage(), "Error");
		} catch (NullPointerException e) {
			FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_API"), "Error");
		}
	}
}