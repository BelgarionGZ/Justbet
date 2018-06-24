package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.monitors.RefreshMonitor;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class TopRefreshPanel {
	private JButton refresh;
	private JPanel panel;
	private Language l;
	private RefreshMonitor refreshMonitor = null;

	public TopRefreshPanel(final TopBalancePanel balancePanel) throws IOException {
		l = LanguageSingleton.getInstance();

		refresh = new JButton(IconFontSwing.buildIcon(FontAwesome.REFRESH, 14, new Color(0, 150, 0)));
		refresh.setBorder(null);
		refresh.setBorderPainted(false);
		refresh.setContentAreaFilled(false);
		refresh.setOpaque(false);

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (refreshMonitor != null && refreshMonitor.isAlive()) {
						refreshMonitor.setStop();
						refreshMonitor.join();
					}

					balancePanel.updateFunds();

					refreshMonitor = new RefreshMonitor(refresh);
					refreshMonitor.start();
				} catch (InterruptedException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
							l.getProperty("PROBLEM_INTERRUPTED") + " " + e1.getMessage(), "Error");
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
				}
			}
		});

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(refresh);
	}

	public JPanel getPanel() {
		return panel;
	}
}