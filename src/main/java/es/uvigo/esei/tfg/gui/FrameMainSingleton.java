package es.uvigo.esei.tfg.gui;

import java.io.IOException;

import es.uvigo.esei.tfg.exceptions.LoginException;

public class FrameMainSingleton {
	private static FrameMain frame;

	public static synchronized FrameMain getInstance() throws IOException, LoginException {
		if (frame == null) {
			frame = new FrameMain();
			frame.buildUp();
		}

		return frame;
	}
}