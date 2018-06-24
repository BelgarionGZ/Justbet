package es.uvigo.esei.tfg.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.uvigo.esei.tfg.entities.PlaceExecutionReport;
import es.uvigo.esei.tfg.enums.ExecutionReportStatus;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import jiconfont.icons.FontAwesome;

public class RightBackLayPlaceBetsPanel extends RightAbstractBackLayPanel {
	public RightBackLayPlaceBetsPanel() throws IOException, LoginException {
		super();
	}

	private JButton createButtonBet(FontAwesome type, final JPanel inferiorPanel, final JTextField odd,
			final JTextField stake, final Long selectionId, final Side side, final String marketId) {
		JButton button = createButton(type);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
					}

					if (panel.getComponentCount() < 3) {
						panel.removeAll();
					} else {
						panel.remove(inferiorPanel);
					}

					repaintAndRevalidate();
				} catch (IOException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
				}
			}
		});

		return button;
	}

	private JButton createButtonCancel(FontAwesome type, final JPanel inferiorPanel) {
		JButton button = createButton(type);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panel.getComponentCount() < 3) {
					panel.removeAll();
				} else {
					panel.remove(inferiorPanel);
				}

				repaintAndRevalidate();
			}
		});

		return button;
	}

	public void createInferiorPanel(Double price, Long selectionId, Side side, String color, String marketId,
			String runnerName) {
		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setBackground(HexToRGBConverter.hexToRGB(color));
		inferiorPanel.setLayout(new GridLayout());

		JLabel profit = createLabel("");

		JTextField odd = createTextField(price);
		JTextField stake = createTextField(Double.valueOf(0));

		createTextField(true, profit, odd, stake, side);
		createTextField(false, profit, odd, stake, side);

		JPanel oddPanel = createJPanelWithTextField(odd, color);
		JPanel stakePanel = createJPanelWithTextField(stake, color);

		inferiorPanel.add(createButtonCancel(FontAwesome.TIMES, inferiorPanel));
		inferiorPanel.add(createLabel(runnerName));
		inferiorPanel.add(oddPanel);
		inferiorPanel.add(stakePanel);
		inferiorPanel.add(profit);
		inferiorPanel.add(createButtonBet(FontAwesome.CHECK, inferiorPanel, odd, stake, selectionId, side, marketId));

		panel.add(inferiorPanel);

		FrameResize.resize(frame, 250, 500, 40, 40, inferiorPanel, "setMaximumSize");
		FrameResize.componentListener(frame, 250, 500, 40, 40, inferiorPanel, "setMaximumSize");
	}
}