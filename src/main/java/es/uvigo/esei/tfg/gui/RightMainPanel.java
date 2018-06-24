package es.uvigo.esei.tfg.gui;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;

public class RightMainPanel {
	private FrameMain frame;
	private RightButtonsPanel buttonsPanel;
	private RightBetsPanel betsPanel;
	private JPanel panel;

	public RightMainPanel() throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();

		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		betsPanel = new RightBetsPanel();
		buttonsPanel = new RightButtonsPanel(betsPanel);

		panel.add(buttonsPanel.getPanel());
		panel.add(betsPanel.getPanel());

		FrameResize.resize(frame, 250, 500, Integer.MAX_VALUE, Integer.MAX_VALUE, panel, "setPreferredSize");
		FrameResize.componentListener(frame, 250, 500, Integer.MAX_VALUE, Integer.MAX_VALUE, panel, "setPreferredSize");
	}

	public RightButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public RightBetsPanel getBetsPanel() {
		return betsPanel;
	}

	public JPanel getPanel() {
		return panel;
	}
}