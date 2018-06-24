package es.uvigo.esei.tfg.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.uvigo.esei.tfg.entities.CancelExecutionReport;
import es.uvigo.esei.tfg.entities.CurrentOrderSummary;
import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.entities.PlaceExecutionReport;
import es.uvigo.esei.tfg.entities.RunnerCatalog;
import es.uvigo.esei.tfg.enums.ExecutionReportStatus;
import es.uvigo.esei.tfg.enums.OrderStatus;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import jiconfont.icons.FontAwesome;

public class RightBackLayOpenBetsPanel extends RightAbstractBackLayPanel {
	public RightBackLayOpenBetsPanel() throws IOException, LoginException {
		super();
	}

	private JButton createButtonBet(FontAwesome type, final JTextField odd, final JTextField stake,
			final Long selectionId, final Side side, final String betId, final String marketId) {
		JButton button = createButton(type);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CancelExecutionReport cancelExecutionReport = operations.cancelOrders(betId, marketId);

					if (cancelExecutionReport.getStatus().equals(ExecutionReportStatus.FAILURE)) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
								cancelExecutionReport.getErrorCode().getMessage(),
								cancelExecutionReport.getInstructionReports().get(0).getErrorCode().toString());
					} else {
						PlaceExecutionReport placeExecutionReport = operations.placeOrders(marketId,
								String.valueOf(selectionId), side, stake.getText(), odd.getText());

						if (placeExecutionReport.getStatus().equals(ExecutionReportStatus.FAILURE)) {
							FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
									placeExecutionReport.getErrorCode().getMessage(),
									placeExecutionReport.getInstructionReports().get(0).getErrorCode().toString());
						} else {
							FrameMessage.createErrorFrame(JOptionPane.INFORMATION_MESSAGE, l.getProperty("SUCCESS_BET"),
									l.getProperty("SUCCESS"));
							frame.getTopPanel().getAccountFundsPanel().getBalancePanel().updateFunds();
							frame.getRightPanel().getBetsPanel().getOpenBetsPanel().getBets();
						}

						repaintAndRevalidate();
					}
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
				}
			}
		});

		return button;
	}

	private JButton createButtonCancel(FontAwesome type, final JPanel inferiorPanel, final JPanel informationPanel,
			final String betId, final String marketId) {
		JButton button = createButton(type);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CancelExecutionReport cancelExecutionReport = operations.cancelOrders(betId, marketId);

					if (cancelExecutionReport.getStatus().equals(ExecutionReportStatus.FAILURE)) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
								cancelExecutionReport.getErrorCode().getMessage(),
								cancelExecutionReport.getInstructionReports().get(0).getErrorCode().toString());
					} else {
						if (panel.getComponentCount() < 4) {
							panel.removeAll();
						} else {
							panel.remove(inferiorPanel);
							panel.remove(informationPanel);
						}

						FrameMessage.createErrorFrame(JOptionPane.INFORMATION_MESSAGE, l.getProperty("CANCEL_BET"),
								l.getProperty("SUCCESS"));
						frame.getTopPanel().getAccountFundsPanel().getBalancePanel().updateFunds();

						repaintAndRevalidate();
					}
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
				}
			}
		});

		return button;
	}

	public void createInferiorPanel(CurrentOrderSummary currentOrder, String color) throws IOException {
		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setBackground(HexToRGBConverter.hexToRGB(color));
		inferiorPanel.setLayout(new GridLayout());

		JLabel profit = createLabel("");

		JTextField odd = createTextField(currentOrder.getPriceSize().getPrice());
		JTextField stake = createTextField(currentOrder.getPriceSize().getSize());

		createTextField(true, profit, odd, stake, currentOrder.getSide());
		createTextField(false, profit, odd, stake, currentOrder.getSide());

		JPanel oddPanel = createJPanelWithTextField(odd, color);
		JPanel stakePanel = createJPanelWithTextField(stake, color);

		JPanel informationPanel = new JPanel();

		if (currentOrder.getSide().equals(Side.BACK)) {
			informationPanel = createInformationPanel(currentOrder, informationPanel, "#DBEFFF");
		} else {
			informationPanel = createInformationPanel(currentOrder, informationPanel, "#FEE9EE");
		}

		if (!currentOrder.getStatus().equals(OrderStatus.EXECUTION_COMPLETE)) {
			inferiorPanel.add(createButtonCancel(FontAwesome.TIMES, inferiorPanel, informationPanel,
					currentOrder.getBetId(), currentOrder.getMarketId()));
		} else {
			inferiorPanel.add(createLabel(""));
		}

		String runnerName = getRunnerName(currentOrder);

		inferiorPanel.add(createLabel(runnerName));
		inferiorPanel.add(oddPanel);
		inferiorPanel.add(stakePanel);
		inferiorPanel.add(profit);

		if (!currentOrder.getStatus().equals(OrderStatus.EXECUTION_COMPLETE)) {
			inferiorPanel.add(createButtonBet(FontAwesome.CHECK, odd, stake, currentOrder.getSelectionId(),
					currentOrder.getSide(), currentOrder.getBetId(), currentOrder.getMarketId()));
		} else {
			inferiorPanel.add(createLabel(""));
		}

		panel.add(inferiorPanel);
		panel.add(informationPanel);

		FrameResize.resize(frame, 250, 500, 40, 40, inferiorPanel, "setMaximumSize");
		FrameResize.componentListener(frame, 250, 500, 40, 40, inferiorPanel, "setMaximumSize");
	}

	private JPanel createInformationPanel(CurrentOrderSummary currentOrder, JPanel informationPanel, String color) {
		informationPanel.setBackground(HexToRGBConverter.hexToRGB(color));
		informationPanel.setLayout(new GridLayout());

		informationPanel.add(createLabel(l.getProperty("SIZE_MATCHED") + ": " + currentOrder.getSizeMatched()));
		informationPanel.add(createLabel(l.getProperty("SIZE_REMAINING") + ": " + currentOrder.getSizeRemaining()));

		FrameResize.resize(frame, 250, 500, 20, 20, informationPanel, "setMaximumSize");
		FrameResize.componentListener(frame, 250, 500, 20, 20, informationPanel, "setMaximumSize");

		return informationPanel;
	}

	private String getRunnerName(CurrentOrderSummary currentOrder) throws IOException {
		String runnerName = new String();

		List<MarketCatalogue> markets = operations.listMarketCatalogue(currentOrder.getMarketId());

		for (MarketCatalogue market : markets) {
			for (RunnerCatalog runner : market.getRunners()) {
				if (runner.getSelectionId().equals(currentOrder.getSelectionId())) {
					runnerName = runner.getRunnerName();
				}
			}
		}

		return runnerName;
	}
}