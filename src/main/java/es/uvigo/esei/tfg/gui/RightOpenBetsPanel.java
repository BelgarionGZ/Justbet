package es.uvigo.esei.tfg.gui;

import java.io.IOException;

import es.uvigo.esei.tfg.entities.CurrentOrderSummary;
import es.uvigo.esei.tfg.entities.CurrentOrderSummaryReport;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;

public class RightOpenBetsPanel extends RightAbstractOpenPlaceBetsPanel {
	private RightBackLayOpenBetsPanel backPanel;
	private RightBackLayOpenBetsPanel layPanel;

	public RightOpenBetsPanel() throws IOException, LoginException {
		super();

		backPanel = new RightBackLayOpenBetsPanel();
		layPanel = new RightBackLayOpenBetsPanel();

		panel.add(backPanel.getPanel());
		panel.add(layPanel.getPanel());
	}

	public void addBet(CurrentOrderSummary currentOrder) throws IOException {
		Side side = currentOrder.getSide();

		if (side.equals(Side.BACK)) {
			if (backPanel.getPanel().getComponentCount() == 0) {
				backPanel.createSuperiorPanel(side, "#71C1FF");
			}

			backPanel.createInferiorPanel(currentOrder, "#A6D8FF");
		} else {
			if (layPanel.getPanel().getComponentCount() == 0) {
				layPanel.createSuperiorPanel(side, "#F6A9BA");
			}

			layPanel.createInferiorPanel(currentOrder, "#FAC9D4");
		}
	}

	public void getBets() throws IOException {
		removeAllFromPanels();

		Operations operations = OperationsSingleton.getInstance();
		CurrentOrderSummaryReport bets = operations.listCurrentOrders();

		for (CurrentOrderSummary currentOrder : bets.getCurrentOrders()) {
			addBet(currentOrder);
		}
	}

	public void removeAllFromPanels() {
		backPanel.getPanel().removeAll();
		layPanel.getPanel().removeAll();
	}
}