package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class MenuBarFile {
	private JMenu menu;
	private Language language;
	private Operations operations;

	public MenuBarFile() throws IOException {
		language = LanguageSingleton.getInstance();

		operations = OperationsSingleton.getInstance();

		menu = new JMenu(language.getProperty("FILE"));
		menu.add(createHistoryItem());
		menu.addSeparator();
		menu.add(createExitItem());
	}

	private JMenuItem createExitItem() {
		Icon icon = IconFontSwing.buildIcon(FontAwesome.WINDOW_CLOSE, 14, Color.BLACK);

		JMenuItem eMenuItem = new JMenuItem(language.getProperty("EXIT"), icon);
		eMenuItem.setToolTipText(language.getProperty("EXIT_APP"));
		eMenuItem.addActionListener((ActionEvent event) -> {
			try {
				operations.doLogout();
				System.exit(0);
			} catch (IOException e) {
				FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
						language.getProperty("PROBLEM_LOGOUT") + " " + e.getMessage(), "Error");
			}
		});

		return eMenuItem;
	}

	private JMenuItem createHistoryItem() {
		Icon icon = IconFontSwing.buildIcon(FontAwesome.BOOK, 14, Color.BLACK);

		JMenuItem eMenuItem = new JMenuItem(language.getProperty("HISTORY"), icon);
		eMenuItem.setToolTipText(language.getProperty("EXIT_APP"));
		eMenuItem.addActionListener((ActionEvent event) -> {
			try {
				FrameHistory frameHistory = new FrameHistory();
				frameHistory.buildUp();
				frameHistory.getFrame().setVisible(true);
			} catch (IOException e) {
				FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, language.getProperty("PROBLEM_IO"), "Error");
			}
		});

		return eMenuItem;
	}

	public JMenu getMenu() {
		return menu;
	}

	public void updateMenu() {
		menu.removeAll();
		menu.setText(language.getProperty("FILE"));
		menu.add(createHistoryItem());
		menu.addSeparator();
		menu.add(createExitItem());
	}
}