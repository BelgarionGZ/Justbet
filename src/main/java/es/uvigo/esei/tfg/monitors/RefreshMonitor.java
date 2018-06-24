package es.uvigo.esei.tfg.monitors;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class RefreshMonitor extends AbstractMonitor {
	private JButton button;

	public RefreshMonitor(JButton b) throws IOException {
		super();
		button = b;
		limit = 1000;
	}

	@Override
	public void run() {
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("loading.gif")));
		running = true;
		startTime = System.currentTimeMillis();

		while (running) {
			long endTime = System.currentTimeMillis();

			if ((endTime - startTime) >= limit) {
				running = false;
			}
		}

		button.setIcon(IconFontSwing.buildIcon(FontAwesome.REFRESH, 14, new Color(0, 150, 0)));
	}
}