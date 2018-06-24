package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class TopAccountFundsPanel {
	private TopBalancePanel balancePanel;
	private JPanel panel;
	private TopRefreshPanel refreshPanel;

	public TopAccountFundsPanel() throws IOException {
		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		balancePanel = new TopBalancePanel();

		refreshPanel = new TopRefreshPanel(balancePanel);

		Language language = LanguageSingleton.getInstance();

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(grayLine, language.getProperty("FUNDS"), 0, 0, null,
				HexToRGBConverter.hexToRGB("#2789CE")));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setPreferredSize(new Dimension(150, 55));
		panel.add(balancePanel.getPanel());
		panel.add(refreshPanel.getPanel());
	}

	public TopBalancePanel getBalancePanel() {
		return balancePanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void updateBorder() throws IOException {
		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createTitledBorder(grayLine, LanguageSingleton.getInstance().getProperty("FUNDS"),
				0, 0, null, HexToRGBConverter.hexToRGB("#2789CE")));
	}
}