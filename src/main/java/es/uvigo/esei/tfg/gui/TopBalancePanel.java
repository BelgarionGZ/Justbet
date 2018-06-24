package es.uvigo.esei.tfg.gui;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class TopBalancePanel {
	private JLabel accountFunds;
	private JPanel panel;

	public TopBalancePanel() throws IOException {
		Operations operations = OperationsSingleton.getInstance();

		Language language = LanguageSingleton.getInstance();

		accountFunds = new JLabel(
				language.getProperty("BALANCE") + ": " + operations.getAccountFunds().get("availableToBetBalance"));

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(accountFunds);
	}

	public JLabel getAccountFunds() {
		return accountFunds;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void updateFunds() throws IOException {
		accountFunds.setText(LanguageSingleton.getInstance().getProperty("BALANCE") + ": "
				+ OperationsSingleton.getInstance().getAccountFunds().get("availableToBetBalance"));
	}
}