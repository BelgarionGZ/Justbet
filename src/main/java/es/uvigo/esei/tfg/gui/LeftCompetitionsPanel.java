package es.uvigo.esei.tfg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.entities.CompetitionResult;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.IdsStructureSingleton;

public class LeftCompetitionsPanel extends LeftAbstractPanel {
	private String eventTypeId;

	public LeftCompetitionsPanel() throws IOException, LoginException {
		super();
	}

	public void createButtons(final String eventTypeIdParameter) throws IOException {
		List<CompetitionResult> competitions = operations.listCompetitions(eventTypeIdParameter);

		eventTypeId = eventTypeIdParameter;

		for (final CompetitionResult competition : competitions) {
			GradientButton btnNewButton = createButton(competition.getCompetition().getName());

			panel.add(btnNewButton);

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						LeftEventsPanel eventsPanel = frame.getLeftPanel().getEventsPanel();

						String competitionId = competition.getCompetition().getId();

						IdsStructureSingleton.getInstance().setCompetitionId(competitionId);

						setActualPanel("eventsPanel");

						eventsPanel.clean();
						eventsPanel.createBackButton("competitionsPanel");
						eventsPanel.createButtons(eventTypeId, competitionId);

						frame.getLeftPanel().getCardLayout().show(frame.getLeftPanel().getPanel(), "eventsPanel");
						frame.getTopPanel().getBreadCrumbsPanel()
								.setBreadCrumbs(competition.getCompetition().getName());

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
		setActualPanel("eventTypesPanel");
	}

	public void updateButtons() throws IOException {
		clean();
		createBackButton("eventTypesPanel");
		createButtons(eventTypeId);
		frame.getLeftPanel().getEventTypesPanel().updateButtons();
	}
}