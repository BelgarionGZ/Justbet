package es.uvigo.esei.tfg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.entities.EventResult;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.IdsStructureSingleton;

public class LeftEventsPanel extends LeftAbstractPanel {
	private String competitionId;
	private String eventTypeId;

	public LeftEventsPanel() throws IOException, LoginException {
		super();
	}

	public void createButtons(final String eventTypeIdParameter, final String competitionParameter) throws IOException {
		List<EventResult> events = operations.listEvents(eventTypeIdParameter, competitionParameter);

		competitionId = competitionParameter;
		eventTypeId = eventTypeIdParameter;

		for (final EventResult event : events) {
			GradientButton btnNewButton = createButton(event.getEvent().getName());

			panel.add(btnNewButton);

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						LeftMarketCataloguePanel marketCataloguePanel = frame.getLeftPanel().getMarketCataloguePanel();

						String eventId = event.getEvent().getId();

						IdsStructureSingleton.getInstance().setEventId(eventId);

						setActualPanel("marketCataloguePanel");

						marketCataloguePanel.clean();
						marketCataloguePanel.createBackButton("eventsPanel");
						marketCataloguePanel.createButtons(eventTypeId, competitionId, eventId);

						frame.getLeftPanel().getCardLayout().show(frame.getLeftPanel().getPanel(),
								"marketCataloguePanel");
						frame.getTopPanel().getBreadCrumbsPanel().setBreadCrumbs(event.getEvent().getName());

						repaintAndRevalidate();
					} catch (IOException e1) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
					}
				}
			});
		}
	}

	@Override
	protected void setActualPanelBackwards() {
		setActualPanel("competitionsPanel");
	}

	public void updateButtons() throws IOException {
		clean();
		createBackButton("competitionsPanel");
		createButtons(eventTypeId, competitionId);
		frame.getLeftPanel().getCompetitionsPanel().updateButtons();
	}
}