package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class TopLanguagePanel {
	private TopComboBoxPanel comboboxPanel;
	private JPanel panel;

	public TopLanguagePanel() throws IOException, LoginException {
		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		comboboxPanel = new TopComboBoxPanel();

		panel = new JPanel();
		panel.setBorder(
				BorderFactory.createTitledBorder(grayLine, LanguageSingleton.getInstance().getProperty("LANGUAGE"), 0,
						0, null, HexToRGBConverter.hexToRGB("#2789CE")));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setPreferredSize(new Dimension(100, 55));
		panel.add(comboboxPanel.getPanel());
	}

	public JPanel getPanel() {
		return panel;
	}

	public void updateBorder() throws IOException {
		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		panel.setBorder(
				BorderFactory.createTitledBorder(grayLine, LanguageSingleton.getInstance().getProperty("LANGUAGE"), 0,
						0, null, HexToRGBConverter.hexToRGB("#2789CE")));
	}
}