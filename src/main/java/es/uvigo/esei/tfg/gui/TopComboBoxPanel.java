package es.uvigo.esei.tfg.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.CountryComboBox;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class TopComboBoxPanel {
	private FrameMain frame;
	private JPanel panel;

	public TopComboBoxPanel() throws IOException, LoginException {
		String currentLanguage = null;

		frame = FrameMainSingleton.getInstance();

		final Language l = LanguageSingleton.getInstance();

		List<String> countryList = l.getLanguagesNamesAvailable();
		countryList.replaceAll(String::toLowerCase);

		for (String language : countryList) {
			if (language.equals(l.getLanguage().toLowerCase())) {
				currentLanguage = language;
			}
		}

		CountryComboBox customCombobox = new CountryComboBox();
		customCombobox.setEditable(true);
		customCombobox.addItems(countryList);
		customCombobox.setSelectedItem(currentLanguage);

		customCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					l.changeLanguage((String) customCombobox.getSelectedItem());
					updateCenterPanel();
					updateLeftPanel();
					updateMenuBar();
					updateRightPanel();
					updateTopPanel();
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_COMBOBOX"),
							"Error");
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(customCombobox);
	}

	public JPanel getPanel() {
		return panel;
	}

	private void updateCenterPanel() throws IOException {
		frame.getCenterPanel().updatePanel();
	}

	private void updateLeftPanel() throws IOException {
		switch (LeftAbstractPanel.getActualPanel()) {
		case "eventTypesPanel":
			frame.getLeftPanel().getEventTypesPanel().updateButtons();
			break;
		case "competitionsPanel":
			frame.getLeftPanel().getCompetitionsPanel().updateButtons();
			break;
		case "eventsPanel":
			frame.getLeftPanel().getEventsPanel().updateButtons();
			break;
		case "marketCataloguePanel":
			frame.getLeftPanel().getMarketCataloguePanel().updateButtons();
			break;
		}
	}

	private void updateMenuBar() {
		frame.getMenuBar().updateMenuBar();
	}

	private void updateRightPanel() throws IOException {
		frame.getRightPanel().getButtonsPanel().updateButtons();
		frame.getRightPanel().getBetsPanel().getPlaceBetsPanel().removeAllFromPanels();
		frame.getRightPanel().getBetsPanel().getOpenBetsPanel().getBets();
	}

	private void updateTopPanel() throws IOException {
		frame.getTopPanel().getBreadCrumbsPanel().updateBorder();
		frame.getTopPanel().getBreadCrumbsPanel().updateBreadCrumbs();
		frame.getTopPanel().getAccountFundsPanel().updateBorder();
		frame.getTopPanel().getAccountFundsPanel().getBalancePanel().updateFunds();
		frame.getTopPanel().getLanguagePanel().updateBorder();
	}
}