package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public abstract class LeftAbstractPanel {
	protected static String actualPanel;
	protected FrameMain frame;
	protected JPanel panel;
	protected JScrollPane scrollBar;
	protected Language l;
	protected Operations operations;

	public LeftAbstractPanel() throws IOException, LoginException {
		l = LanguageSingleton.getInstance();

		frame = FrameMainSingleton.getInstance();
		operations = OperationsSingleton.getInstance();

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(null);
	}

	protected void clean() {
		panel.removeAll();
	}

	protected void createBackButton(final String panelName) {
		JButton btnNewButton = new JButton(l.getProperty("BACK"));
		btnNewButton.setBackground(HexToRGBConverter.hexToRGB("#303030"));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getLeftPanel().getCardLayout().show(frame.getLeftPanel().getPanel(), panelName);
				frame.getTopPanel().getBreadCrumbsPanel().removeBreadCrumbs();
				setActualPanelBackwards();
			}
		});

		FrameResize.resize(frame, 150, 300, btnNewButton.getMinimumSize().height, btnNewButton.getMinimumSize().height,
				btnNewButton, "setMaximumSize");
		FrameResize.componentListener(frame, 150, 300, btnNewButton.getMinimumSize().height,
				btnNewButton.getMinimumSize().height, btnNewButton, "setMaximumSize");

		panel.add(btnNewButton);
	}

	protected GradientButton createButton(String name) {
		GradientButton btnNewButton = new GradientButton(name);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(HexToRGBConverter.hexToRGB("#2789CE"));

		FrameResize.resize(frame, 150, 300, btnNewButton.getMinimumSize().height, btnNewButton.getMinimumSize().height,
				btnNewButton, "setMaximumSize");
		FrameResize.componentListener(frame, 150, 300, btnNewButton.getMinimumSize().height,
				btnNewButton.getMinimumSize().height, btnNewButton, "setMaximumSize");

		return btnNewButton;
	}

	protected static String getActualPanel() {
		return actualPanel;
	}

	protected JScrollPane getScrollBar() {
		return scrollBar;
	}

	protected void repaintAndRevalidate() {
		frame.getLeftPanel().getPanel().revalidate();
		frame.getLeftPanel().getPanel().repaint();
	}

	protected static void setActualPanel(String actualPanel) {
		LeftAbstractPanel.actualPanel = actualPanel;
	}

	protected abstract void setActualPanelBackwards();
}