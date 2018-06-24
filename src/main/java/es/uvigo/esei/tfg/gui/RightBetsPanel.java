package es.uvigo.esei.tfg.gui;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;

public class RightBetsPanel {
	private CardLayout cardLayout;
	private FrameMain frame;
	private RightOpenBetsPanel openBetsPanel;
	private JPanel panel;
	private RightPlaceBetsPanel placeBetsPanel;

	public RightBetsPanel() throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();

		cardLayout = new CardLayout();
		panel = new JPanel(cardLayout);

		openBetsPanel = new RightOpenBetsPanel();
		placeBetsPanel = new RightPlaceBetsPanel();

		panel.add(openBetsPanel.getScrollBar(), "openBetsPanel");
		panel.add(placeBetsPanel.getScrollBar(), "placeBetsPanel");

		cardLayout.show(panel, "placeBetsPanel");

		FrameResize.resize(frame, 250, 500, panel.getHeight(), panel.getHeight(), panel, "setPreferredSize");
		FrameResize.componentListener(frame, 250, 500, panel.getHeight(), panel.getHeight(), panel, "setPreferredSize");
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public RightOpenBetsPanel getOpenBetsPanel() {
		return openBetsPanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public RightPlaceBetsPanel getPlaceBetsPanel() {
		return placeBetsPanel;
	}
}