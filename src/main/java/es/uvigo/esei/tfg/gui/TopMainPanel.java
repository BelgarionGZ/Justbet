package es.uvigo.esei.tfg.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;

public class TopMainPanel {
	private TopAccountFundsPanel accountFundsPanel;
	private TopBreadCrumbsPanel breadCrumbsPanel;
	private TopLanguagePanel languagePanel;
	private JPanel panel;

	public TopMainPanel() throws IOException, LoginException {
		accountFundsPanel = new TopAccountFundsPanel();
		breadCrumbsPanel = new TopBreadCrumbsPanel();
		languagePanel = new TopLanguagePanel();

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints a = new GridBagConstraints();
		a.fill = GridBagConstraints.HORIZONTAL;
		a.weightx = 1;

		panel.add(breadCrumbsPanel.getPanel(), a);
		panel.add(accountFundsPanel.getPanel());
		panel.add(languagePanel.getPanel());
	}

	public TopAccountFundsPanel getAccountFundsPanel() {
		return accountFundsPanel;
	}

	public TopBreadCrumbsPanel getBreadCrumbsPanel() {
		return breadCrumbsPanel;
	}

	public TopLanguagePanel getLanguagePanel() {
		return languagePanel;
	}

	public JPanel getPanel() {
		return panel;
	}
}