package es.uvigo.esei.tfg;

import es.uvigo.esei.tfg.console.Console;
import es.uvigo.esei.tfg.gui.GUI;

public class App {
	public static void main(String[] args) {
		if (args.length > 0) {
			Console.begin();
		} else {
			GUI.begin();
		}
	}
}