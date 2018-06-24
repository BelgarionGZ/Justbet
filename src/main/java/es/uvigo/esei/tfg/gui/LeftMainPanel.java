package es.uvigo.esei.tfg.gui;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.FrameResize;

public class LeftMainPanel {
	private CardLayout cardLayout;
	private FrameMain frame;
	private LeftCompetitionsPanel competitionsPanel;
	private LeftEventsPanel eventsPanel;
	private LeftEventTypesPanel eventTypesPanel;
	private JPanel panel;
	private LeftMarketCataloguePanel marketCataloguePanel;

	public LeftMainPanel() throws IOException, LoginException {
		frame = FrameMainSingleton.getInstance();

		cardLayout = new CardLayout();
		panel = new JPanel(cardLayout);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		competitionsPanel = new LeftCompetitionsPanel();
		eventsPanel = new LeftEventsPanel();
		eventTypesPanel = new LeftEventTypesPanel();
		marketCataloguePanel = new LeftMarketCataloguePanel();

		panel.add(competitionsPanel.getScrollBar(), "competitionsPanel");
		panel.add(eventsPanel.getScrollBar(), "eventsPanel");
		panel.add(eventTypesPanel.getScrollBar(), "eventTypesPanel");
		panel.add(marketCataloguePanel.getScrollBar(), "marketCataloguePanel");

		LeftAbstractPanel.setActualPanel("eventTypesPanel");

		eventTypesPanel.createButtons();

		cardLayout.show(panel, "eventTypesPanel");

		FrameResize.resize(frame, 150, 300, Integer.MAX_VALUE, Integer.MAX_VALUE, panel, "setPreferredSize");
		FrameResize.componentListener(frame, 150, 300, Integer.MAX_VALUE, Integer.MAX_VALUE, panel, "setPreferredSize");
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public LeftCompetitionsPanel getCompetitionsPanel() {
		return competitionsPanel;
	}

	public LeftEventsPanel getEventsPanel() {
		return eventsPanel;
	}

	public LeftEventTypesPanel getEventTypesPanel() {
		return eventTypesPanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public LeftMarketCataloguePanel getMarketCataloguePanel() {
		return marketCataloguePanel;
	}
}