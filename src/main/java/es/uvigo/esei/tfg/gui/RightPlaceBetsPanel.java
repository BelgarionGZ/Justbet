package es.uvigo.esei.tfg.gui;

import java.io.IOException;

import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;

public class RightPlaceBetsPanel extends RightAbstractOpenPlaceBetsPanel {
	private RightBackLayPlaceBetsPanel backPanel;
	private RightBackLayPlaceBetsPanel layPanel;

	public RightPlaceBetsPanel() throws IOException, LoginException {
		super();

		backPanel = new RightBackLayPlaceBetsPanel();
		layPanel = new RightBackLayPlaceBetsPanel();

		panel.add(backPanel.getPanel());
		panel.add(layPanel.getPanel());
	}

	public void addBet(Double price, Long selectionId, Side side, String marketId, String runnerName) {
		if (side.equals(Side.BACK)) {
			if (backPanel.getPanel().getComponentCount() == 0) {
				backPanel.createSuperiorPanel(side, "#71C1FF");
			}

			backPanel.createInferiorPanel(price, selectionId, Side.BACK, "#A6D8FF", marketId, runnerName);
		} else {
			if (layPanel.getPanel().getComponentCount() == 0) {
				layPanel.createSuperiorPanel(side, "#F6A9BA");
			}

			layPanel.createInferiorPanel(price, selectionId, Side.LAY, "#FAC9D4", marketId, runnerName);
		}
	}

	public RightBackLayPlaceBetsPanel getBackPanel() {
		return backPanel;
	}

	public RightBackLayPlaceBetsPanel getLayPanel() {
		return layPanel;
	}

	public void removeAllFromPanels() {
		backPanel.getPanel().removeAll();
		layPanel.getPanel().removeAll();
	}
}