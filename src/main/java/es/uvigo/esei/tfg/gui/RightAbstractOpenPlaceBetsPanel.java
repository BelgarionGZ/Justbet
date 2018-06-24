package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class RightAbstractOpenPlaceBetsPanel {
	protected JPanel panel;
	protected JScrollPane scrollBar;

	public RightAbstractOpenPlaceBetsPanel() throws IOException {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(null);
	}

	public JScrollPane getScrollBar() {
		return scrollBar;
	}
}