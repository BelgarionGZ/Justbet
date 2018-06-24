package es.uvigo.esei.tfg.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.monitors.SessionMonitor;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import es.uvigo.esei.tfg.util.UIFont;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrameMain {
	private CenterMainPanel centerPanel;
	private JFrame frame;
	private JPanel panel;
	private Language language;
	private LeftMainPanel leftPanel;
	private MenuBarMain menuBar;
	private Operations operations;
	private RightMainPanel rightPanel;
	private TopMainPanel topPanel;

	public FrameMain() throws IOException {
		language = LanguageSingleton.getInstance();

		operations = OperationsSingleton.getInstance();

		IconFontSwing.register(FontAwesome.getIconFont());

		menuBar = new MenuBarMain();

		frame = new JFrame();
		frame.setBounds(100, 100, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		frame.setJMenuBar(menuBar.getMenubar());
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setTitle("JustBet");

		setCloseOperation();

		setUIFont();
	}

	public void buildUp() throws IOException, LoginException, NullPointerException {
		Map<String, String> login = operations.doLogin();

		if (login.get("status").equals("SUCCESS")) {
			SessionMonitor sessionMonitor = new SessionMonitor();
			sessionMonitor.start();

			centerPanel = new CenterMainPanel();
			leftPanel = new LeftMainPanel();
			rightPanel = new RightMainPanel();
			topPanel = new TopMainPanel();

			panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			panel.setLayout(new BorderLayout());
			panel.add(centerPanel.getScrollBar(), BorderLayout.CENTER);
			panel.add(leftPanel.getPanel(), BorderLayout.WEST);
			panel.add(rightPanel.getPanel(), BorderLayout.EAST);
			panel.add(topPanel.getPanel(), BorderLayout.NORTH);

			frame.add(panel);
		} else {
			throw new LoginException(login.get("error"));
		}
	}

	public CenterMainPanel getCenterPanel() {
		return centerPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public LeftMainPanel getLeftPanel() {
		return leftPanel;
	}

	public MenuBarMain getMenuBar() {
		return menuBar;
	}

	public RightMainPanel getRightPanel() {
		return rightPanel;
	}

	public TopMainPanel getTopPanel() {
		return topPanel;
	}

	private void setCloseOperation() {
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					operations.doLogout();
				} catch (IOException e) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
							language.getProperty("PROBLEM_LOGOUT") + " " + e.getMessage(), "Error");
				}
			}
		});
	}

	private void setUIFont() {
		Dimension frameSize = frame.getSize();
		if (frameSize.getWidth() <= 1500) {
			UIFont.setUIFont(new javax.swing.plaf.FontUIResource("Sans Serif", Font.BOLD, 10));
		} else {
			UIFont.setUIFont(new javax.swing.plaf.FontUIResource("Sans Serif", Font.BOLD, 12));
		}
	}
}