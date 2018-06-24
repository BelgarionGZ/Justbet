package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.uvigo.esei.tfg.entities.ClearedOrderSummary;
import es.uvigo.esei.tfg.entities.ClearedOrderSummaryReport;
import es.uvigo.esei.tfg.enums.BetStatus;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class FrameHistory {
	private JFrame frame;
	private JPanel panel;
	private JScrollPane scrollBar;
	private Language language;
	private Operations operations;

	public FrameHistory() throws IOException {
		language = LanguageSingleton.getInstance();

		operations = OperationsSingleton.getInstance();

		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setTitle(language.getProperty("HISTORY"));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBorder(null);

		frame.add(scrollBar);
	}

	public void buildUp() throws IOException {
		ClearedOrderSummaryReport bets = operations.listClearedOrders(BetStatus.SETTLED);

		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		JPanel firstRow = new JPanel();
		firstRow.setBackground(HexToRGBConverter.hexToRGB("#303030"));
		firstRow.setLayout(new GridBagLayout());
		firstRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

		GridBagConstraints a = new GridBagConstraints();
		a.fill = GridBagConstraints.HORIZONTAL;
		a.weightx = 1;

		firstRow.add(createLabel(Color.WHITE, new Dimension(125, 50), language.getProperty("PLACED_DATE")));
		firstRow.add(createLabel(Color.WHITE, new Dimension(125, 50), language.getProperty("SETTLED_DATE")));
		firstRow.add(createLabel(Color.WHITE, new Dimension(150, 50), language.getProperty("DESCRIPTION")), a);
		firstRow.add(createLabel(Color.WHITE, new Dimension(125, 50), language.getProperty("SELECTION")));
		firstRow.add(createLabel(Color.WHITE, new Dimension(85, 50), language.getProperty("PRICE_MATCHED")));
		firstRow.add(createLabel(Color.WHITE, new Dimension(85, 50), language.getProperty("SIZE_SETTLED")));
		firstRow.add(createLabel(Color.WHITE, new Dimension(85, 50), language.getProperty("PROFIT")));

		panel.add(firstRow);

		for (ClearedOrderSummary bet : bets.getClearedOrders()) {
			JPanel panelAux = new JPanel();
			panelAux.setLayout(new GridBagLayout());
			panelAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

			if (bet.getSide().equals(Side.BACK)) {
				panelAux.setBackground(HexToRGBConverter.hexToRGB("#A6D8FF"));
			} else {
				panelAux.setBackground(HexToRGBConverter.hexToRGB("#FAC9D4"));
			}

			panelAux.add(createLabel(Color.BLACK, new Dimension(125, 50), dt.format(bet.getPlacedDate())));
			panelAux.add(createLabel(Color.BLACK, new Dimension(125, 50), dt.format(bet.getSettledDate())));
			panelAux.add(createLabel(Color.BLACK, new Dimension(150, 50),
					String.valueOf(bet.getItemDescription().getEventTypeDesc() + " / "
							+ bet.getItemDescription().getEventDesc() + " / "
							+ bet.getItemDescription().getMarketDesc())),
					a);
			panelAux.add(createLabel(Color.BLACK, new Dimension(125, 50),
					String.valueOf(bet.getItemDescription().getRunnerDesc())));
			panelAux.add(createLabel(Color.BLACK, new Dimension(85, 50), String.valueOf(bet.getPriceMatched())));
			panelAux.add(createLabel(Color.BLACK, new Dimension(85, 50), String.valueOf(bet.getSizeSettled())));
			panelAux.add(createLabel(Color.BLACK, new Dimension(85, 50), String.valueOf(bet.getProfit())));

			panel.add(panelAux);
		}
	}

	private JLabel createLabel(Color color, Dimension dimension, String name) {
		JLabel label = new JLabel(name);
		label.setForeground(color);
		label.setFont(new Font("Sans Serif", Font.BOLD, 12));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setPreferredSize(dimension);

		return label;
	}

	public JFrame getFrame() {
		return frame;
	}
}