package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class MenuBarAbout {
	private JMenu menu;
	private Language language;

	public MenuBarAbout() throws IOException {
		language = LanguageSingleton.getInstance();

		menu = new JMenu(language.getProperty("HELP"));
		menu.add(createAboutItem());
	}

	private JMenuItem createAboutItem() {
		Icon icon = IconFontSwing.buildIcon(FontAwesome.INFO, 14, Color.BLACK);

		JMenuItem eMenuItem = new JMenuItem(language.getProperty("ABOUT"), icon);
		eMenuItem.setToolTipText(language.getProperty("ABOUT_APP"));
		eMenuItem.addActionListener((ActionEvent event) -> {
			try {
				FrameAbout frameAbout = new FrameAbout();
				frameAbout.buildUp();
				frameAbout.getFrame().setVisible(true);
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
		menu.setText(language.getProperty("HELP"));
		menu.add(createAboutItem());
	}
}