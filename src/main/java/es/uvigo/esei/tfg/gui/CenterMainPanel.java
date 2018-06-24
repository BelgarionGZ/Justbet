package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import es.uvigo.esei.tfg.entities.MarketBook;
import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.entities.PriceSize;
import es.uvigo.esei.tfg.entities.Runner;
import es.uvigo.esei.tfg.entities.RunnerCatalog;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class CenterMainPanel {
	private Boolean flag;
	private FrameMain frame;
	private JPanel panel;
	private JScrollPane scrollBar;
	private Language l;
	private MarketCatalogue market;
	private Operations operations;

	public CenterMainPanel() throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();
		operations = OperationsSingleton.getInstance();

		l = LanguageSingleton.getInstance();

		flag = true;

		panel = new JPanel(new GridBagLayout());
		panel.add(new JLabel(l.getProperty("CHOOSE_EVENT")));

		scrollBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
	}

	private void addButtons(Integer x, Integer y, List<Double> list, final Long selectionId, final Side side,
			String color, final String marketId, final String runnerName) {
		GridBagConstraints c = new GridBagConstraints();

		Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);

		for (final Double item : list) {
			c.gridx = (x >= 5) ? x++ : x--;
			c.gridy = y;

			JButton button = new JButton(item.toString());
			button.setBackground(HexToRGBConverter.hexToRGB(color));
			button.setBorder(whiteBorder);

			panel.add(button, c);

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getRightPanel().getBetsPanel().getPlaceBetsPanel().addBet(item, selectionId, side, marketId,
							runnerName);
					frame.getRightPanel().getBetsPanel().getPlaceBetsPanel().getBackPanel().repaintAndRevalidate();
					frame.getRightPanel().getBetsPanel().getPlaceBetsPanel().getLayPanel().repaintAndRevalidate();
					frame.getRightPanel().getButtonsPanel().showPlaceBetsPanel();
				}
			});

			FrameResize.resize(frame, 35, 60, 30, 30, button, "setPreferredSize");
			FrameResize.componentListener(frame, 35, 60, 30, 30, button, "setPreferredSize");
		}

		while (x > 1 && x < 8) {
			c.gridx = (x >= 5) ? x++ : x--;
			c.gridy = y;

			JLabel label = new JLabel();
			label.setBackground(HexToRGBConverter.hexToRGB(color));
			label.setBorder(whiteBorder);
			label.setOpaque(true);

			panel.add(label, c);

			FrameResize.resize(frame, 35, 60, 30, 30, label, "setPreferredSize");
			FrameResize.componentListener(frame, 35, 60, 30, 30, label, "setPreferredSize");
		}
	}

	private void addEmptyLabel(Integer y) {
		Border whiteBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE);

		JLabel emptyLabel = new JLabel();
		emptyLabel.setBorder(whiteBorder);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = y;

		panel.add(emptyLabel, c);

		FrameResize.resize(frame, 35, 60, 30, 30, emptyLabel, "setPreferredSize");
		FrameResize.componentListener(frame, 35, 60, 30, 30, emptyLabel, "setPreferredSize");
	}

	private void addRunnerName(Integer y, String runnerName) {
		GridBagConstraints c = new GridBagConstraints();

		Border whiteBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE);

		c.gridx = 0;
		c.gridy = y;

		JLabel label = new JLabel(runnerName);
		label.setBorder(whiteBorder);

		panel.add(label, c);

		FrameResize.resize(frame, 95, 200, 30, 30, label, "setPreferredSize");
		FrameResize.componentListener(frame, 95, 200, 30, 30, label, "setPreferredSize");
	}

	private void clean() {
		panel.removeAll();
	}

	private List<Double> createButtons(List<PriceSize> priceSizes) {
		List<Double> list = new ArrayList<Double>();

		for (PriceSize priceSize : priceSizes) {
			list.add(priceSize.getPrice());
		}

		Collections.sort(list);

		return list;
	}

	public void createMarketBook(MarketCatalogue marketParameter) throws IOException {
		clean();

		List<MarketBook> marketBooks = operations.listMarketBook(marketParameter.getMarketId());

		market = marketParameter;

		flag = false;

		Integer y = 0;

		if (marketBooks.size() > 0) {
			MarketBook marketBook = marketBooks.get(0);
			if (marketBook.getStatus().equals("OPEN")) {
				for (Runner runner : marketBook.getRunners()) {
					String runnerName = getRunnerName(marketParameter, runner);
					addRunnerName(y, runnerName);
					addEmptyLabel(y);
					List<Double> backList = createButtons(runner.getEx().getAvailableToBack());
					Collections.sort(backList, Collections.reverseOrder());
					List<Double> awayList = createButtons(runner.getEx().getAvailableToLay());
					addButtons(4, y, backList, runner.getSelectionId(), Side.BACK, "#A6D8FF",
							marketParameter.getMarketId(), runnerName);
					addButtons(5, y, awayList, runner.getSelectionId(), Side.LAY, "#FAC9D4",
							marketParameter.getMarketId(), runnerName);
					y++;
				}
			} else {
				createMarketStatus(marketBook.getStatus());
			}
		} else {
			panel.add(new JLabel(l.getProperty("MARKET_CLOSED")));
		}

		repaintAndRevalidate();
	}

	private void createMarketStatus(String status) {
		JLabel label = new JLabel(status);
		label.setFont(new Font("Sans Serif", Font.PLAIN, 25));
		label.setForeground(HexToRGBConverter.hexToRGB("#B30000"));
		label.setHorizontalAlignment(JLabel.CENTER);

		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, HexToRGBConverter.hexToRGB("#B30000"));
		Border padding = new EmptyBorder(20, 20, 20, 20);

		label.setBorder(new CompoundBorder(border, padding));

		panel.add(label);
	}

	public JPanel getPanel() {
		return panel;
	}

	private String getRunnerName(MarketCatalogue market, Runner runner) {
		String toRet = new String();

		for (RunnerCatalog option : market.getRunners()) {
			if (runner.getSelectionId().equals(option.getSelectionId())) {
				toRet = option.getRunnerName();
			}
		}

		return toRet;
	}

	public JScrollPane getScrollBar() {
		return scrollBar;
	}

	private void repaintAndRevalidate() {
		panel.revalidate();
		panel.repaint();
	}

	public void updatePanel() throws IOException {
		if (flag) {
			clean();
			panel.add(new JLabel(l.getProperty("CHOOSE_EVENT")));
		} else {
			List<MarketCatalogue> markets = operations.listMarketCatalogue(market.getMarketId());
			if (markets.size() > 0) {
				createMarketBook(markets.get(0));
			}
		}
	}
}