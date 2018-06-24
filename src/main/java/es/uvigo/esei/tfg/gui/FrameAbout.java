package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class FrameAbout {
	private JFrame frame;
	private JPanel panel;
	private Language language;

	public FrameAbout() throws IOException {
		language = LanguageSingleton.getInstance();

		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setResizable(false);
		frame.setTitle(language.getProperty("ABOUT"));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		frame.add(panel);
	}

	public void buildUp() {
		createAuthor();
		createHomePage();
		createLicense();
	}

	private void createAuthor() {
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

		JLabel authorLabel = new JLabel(language.getProperty("AUTHOR") + " : ");
		JLabel author = new JLabel("Santiago Andrés del Mazo");

		panelAux.add(authorLabel);
		panelAux.add(author);

		panel.add(panelAux);
	}

	private void createHomePage() {
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

		JLabel homePageLabel = new JLabel("GitHub : ");
		JLabel homePage = new JLabel("https://github.com/belgariongz");

		goWebsite(homePage, "https://github.com/belgariongz");

		panelAux.add(homePageLabel);
		panelAux.add(homePage);

		panel.add(panelAux);
	}

	private void createLicense() {
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel license = new JLabel("<html>"
				+ "<p>This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.</p>"
				+ "<br>"
				+ "<p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.</p>"
				+ "<br>"
				+ "<p>You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.</p>"
				+ "</html>");
		license.setPreferredSize(new Dimension(600, 175));

		panelAux.add(license);

		panel.add(panelAux);
	}

	public JFrame getFrame() {
		return frame;
	}

	private void goWebsite(JLabel website, final String url) {
		website.setText("<html><a href=\"\">" + url + "</a></html>");
		website.setCursor(new Cursor(Cursor.HAND_CURSOR));
		website.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (URISyntaxException | IOException ex) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, language.getProperty("PROBLEM_WEBSITE"),
							"Error");
				}
			}
		});
	}
}