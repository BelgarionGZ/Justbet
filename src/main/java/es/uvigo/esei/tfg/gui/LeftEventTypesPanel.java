package es.uvigo.esei.tfg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.entities.EventTypeResult;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.IdsStructureSingleton;

public class LeftEventTypesPanel extends LeftAbstractPanel {
	public LeftEventTypesPanel() throws IOException, LoginException {
		super();
	}

	public void createButtons() throws IOException {
		List<EventTypeResult> eventTypes = operations.listEventTypes();

		for (final EventTypeResult eventType : eventTypes) {
			GradientButton btnNewButton = createButton(eventType.getEventType().getName());

			panel.add(btnNewButton);

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						LeftCompetitionsPanel competitionsPanel = frame.getLeftPanel().getCompetitionsPanel();

						String eventTypeId = eventType.getEventType().getId();

						IdsStructureSingleton.getInstance().setEventTypeId(eventTypeId);

						setActualPanel("competitionsPanel");

						competitionsPanel.clean();
						competitionsPanel.createBackButton("eventTypesPanel");
						competitionsPanel.createButtons(eventTypeId);

						frame.getLeftPanel().getCardLayout().show(frame.getLeftPanel().getPanel(), "competitionsPanel");
						frame.getTopPanel().getBreadCrumbsPanel().setBreadCrumbs(eventType.getEventType().getName());

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
	}

	public void updateButtons() throws IOException {
		clean();
		createButtons();
	}
}