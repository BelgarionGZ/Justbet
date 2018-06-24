package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class RightButtonsPanel {
	private FrameMain frame;
	private RightBetsPanel betPanel;
	private GradientButton openBets;
	private GradientButton placeBets;
	private JPanel panel;

	public RightButtonsPanel(RightBetsPanel bp) throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();

		betPanel = bp;

		panel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.weighty = 1;

		final Language l = LanguageSingleton.getInstance();

		placeBets = createButton(l.getProperty("PLACE_BETS"), Color.WHITE, Color.BLACK);

		c.gridx = 0;
		c.gridy = 0;

		panel.add(placeBets, c);

		openBets = createButton(l.getProperty("OPEN_BETS"), Color.LIGHT_GRAY, Color.BLACK);

		c.gridx = 1;
		c.gridy = 0;

		panel.add(openBets, c);

		placeBets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlaceBetsPanel();
			}
		});

		openBets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					betPanel.getOpenBetsPanel().getBets();
					showOpenBetsPanel();
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
				}
			}
		});

		FrameResize.resize(frame, 251, 503, 30, 30, panel, "setMaximumSize");
		FrameResize.componentListener(frame, 251, 503, 30, 30, panel, "setMaximumSize");
	}

	private GradientButton createButton(String name, Color background, Color foreground) {
		GradientButton button = new GradientButton(name);
		button.setBackground(background);
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setForeground(foreground);
		button.setOpaque(false);

		FrameResize.resize(frame, 125, 250, 30, 30, button, "setPreferredSize");
		FrameResize.componentListener(frame, 125, 250, 30, 30, button, "setPreferredSize");

		return button;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void showOpenBetsPanel() {
		openBets.setBackground(Color.WHITE);
		placeBets.setBackground(Color.LIGHT_GRAY);
		betPanel.getCardLayout().show(betPanel.getPanel(), "openBetsPanel");
	}

	public void showPlaceBetsPanel() {
		openBets.setBackground(Color.LIGHT_GRAY);
		placeBets.setBackground(Color.WHITE);
		betPanel.getCardLayout().show(betPanel.getPanel(), "placeBetsPanel");
	}

	public void updateButtons() throws IOException {
		Language l = LanguageSingleton.getInstance();
		openBets.setText(l.getProperty("OPEN_BETS"));
		placeBets.setText(l.getProperty("PLACE_BETS"));
	}
}