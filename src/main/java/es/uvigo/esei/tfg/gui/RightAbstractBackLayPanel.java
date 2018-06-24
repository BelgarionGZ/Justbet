package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.JTextFieldLimit;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public abstract class RightAbstractBackLayPanel {
	protected FrameMain frame;
	protected JPanel panel;
	protected Language l;
	protected Operations operations;

	public RightAbstractBackLayPanel() throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();
		l = LanguageSingleton.getInstance();
		operations = OperationsSingleton.getInstance();

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	protected JButton createButton(FontAwesome type) {
		Icon icon = IconFontSwing.buildIcon(type, 14, Color.BLACK);
		JButton crossIcon = new JButton(icon);
		crossIcon.setBorder(null);
		crossIcon.setBorderPainted(false);
		crossIcon.setContentAreaFilled(false);
		crossIcon.setOpaque(false);
		return crossIcon;
	}

	protected JPanel createJPanelWithTextField(JTextField textField, String color) {
		JPanel auxPanel = new JPanel(new GridBagLayout());
		auxPanel.setBackground(HexToRGBConverter.hexToRGB(color));
		auxPanel.add(textField);

		FrameResize.resize(frame, 40, 80, 40, 40, auxPanel, "setPreferredSize");
		FrameResize.componentListener(frame, 40, 80, 40, 40, auxPanel, "setPreferredSize");

		return auxPanel;
	}

	protected JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Sans Serif", Font.PLAIN, 10));
		label.setHorizontalAlignment(JLabel.CENTER);

		FrameResize.resize(frame, 40, 80, 20, 20, label, "setPreferredSize");
		FrameResize.componentListener(frame, 40, 80, 20, 20, label, "setPreferredSize");

		return label;
	}

	public void createSuperiorPanel(Side side, String color) {
		JPanel superiorPanel = new JPanel();
		superiorPanel.setBackground(HexToRGBConverter.hexToRGB(color));
		superiorPanel.setLayout(new GridLayout());

		superiorPanel.add(createLabel(l.getProperty("CANCEL")));
		superiorPanel.add(createLabel(l.getProperty("SELECTION")));
		superiorPanel.add(createLabel(l.getProperty("ODD")));
		superiorPanel.add(createLabel(l.getProperty("AMOUNT")));
		superiorPanel.add(createLabel(side.equals(Side.BACK) ? l.getProperty("PROFIT") : l.getProperty("RISK")));
		superiorPanel.add(createLabel(l.getProperty("BET")));

		panel.add(superiorPanel);

		FrameResize.resize(frame, 250, 500, 20, 20, superiorPanel, "setMaximumSize");
		FrameResize.componentListener(frame, 250, 500, 20, 20, superiorPanel, "setMaximumSize");
	}

	protected JTextField createTextField(Double amount) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("####.##", otherSymbols);

		JTextField textField = new JTextField();
		textField.setBorder(BorderFactory.createEmptyBorder());
		textField.setDocument(new JTextFieldLimit(6));
		textField.setFont(new Font("Sans Serif", Font.PLAIN, 10));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setText(df.format(amount));

		FrameResize.resize(frame, 30, 60, 30, 30, textField, "setPreferredSize");
		FrameResize.componentListener(frame, 30, 60, 30, 30, textField, "setPreferredSize");

		return textField;
	}

	protected void createTextField(Boolean flag, final JLabel profit, final JTextField odd, final JTextField stake,
			final Side side) {
		JTextField aux = (flag) ? odd : stake;

		aux.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateProfit(profit, odd, stake, side);
			}

			public void insertUpdate(DocumentEvent e) {
				updateProfit(profit, odd, stake, side);
			}

			public void removeUpdate(DocumentEvent e) {
				updateProfit(profit, odd, stake, side);
			}
		});

		updateProfit(profit, odd, stake, side);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void repaintAndRevalidate() {
		panel.revalidate();
		panel.repaint();
	}

	private void updateProfit(JLabel profit, JTextField odd, JTextField stake, Side side) {
		Double result = Double.valueOf(0);

		try {
			Double oddDouble = Double.parseDouble(odd.getText());
			Double stakeDouble = Double.parseDouble(stake.getText());
			result = (oddDouble * stakeDouble) - stakeDouble;
			DecimalFormat f = new DecimalFormat("#.##");
			profit.setText(f.format(result));
		} catch (NumberFormatException e) {
			profit.setText("ERROR");
		}
	}
}