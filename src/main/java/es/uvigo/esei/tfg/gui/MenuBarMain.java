package es.uvigo.esei.tfg.gui;

import java.io.IOException;

import javax.swing.JMenuBar;

import es.uvigo.esei.tfg.util.HexToRGBConverter;

public class MenuBarMain {
	private JMenuBar menubar;
	private MenuBarAbout menuBarAbout;
	private MenuBarFile menuBarFile;

	public MenuBarMain() throws IOException {
		menuBarAbout = new MenuBarAbout();
		menuBarFile = new MenuBarFile();

		menubar = new JMenuBar();
		menubar.add(menuBarFile.getMenu());
		menubar.add(menuBarAbout.getMenu());
		menubar.setBackground(HexToRGBConverter.hexToRGB("#FFB80C"));
	}

	public JMenuBar getMenubar() {
		return menubar;
	}

	public void updateMenuBar() {
		menuBarFile.updateMenu();
		menuBarAbout.updateMenu();
	}
}