package es.uvigo.esei.tfg.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameMessage {
	public static void createErrorFrame(int messageType, Object message, String title) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, message, title, messageType);
	}
}