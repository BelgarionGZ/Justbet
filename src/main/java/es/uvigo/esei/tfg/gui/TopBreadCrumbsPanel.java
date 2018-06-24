package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import es.uvigo.esei.tfg.entities.CompetitionResult;
import es.uvigo.esei.tfg.entities.EventResult;
import es.uvigo.esei.tfg.entities.EventTypeResult;
import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.IdsStructure;
import es.uvigo.esei.tfg.util.IdsStructureSingleton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class TopBreadCrumbsPanel {
	private Boolean flag;
	private JLabel breadCrumbs;
	private JPanel panel;
	private Language l;

	public TopBreadCrumbsPanel() throws IOException {
		l = LanguageSingleton.getInstance();

		flag = false;

		breadCrumbs = new JLabel(l.getProperty("START"));

		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(grayLine, l.getProperty("NAVIGATION"), 0, 0, null,
				HexToRGBConverter.hexToRGB("#2789CE")));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 55));
		panel.add(breadCrumbs);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void remove() {
		String label = breadCrumbs.getText();
		String[] parts = label.split(" > ");

		if (parts.length > 1) {
			String newLabel = new String();

			for (int i = 0; i < parts.length - 1; i++) {
				newLabel += parts[i] + " > ";
			}

			newLabel = newLabel.substring(0, newLabel.length() - 3);

			breadCrumbs.setText(newLabel);
		}
	}

	public void removeBreadCrumbs() {
		remove();
	}

	public void removeBreadCrumbsX2() {
		if (flag) {
			remove();
		}
		remove();
	}

	public void setBreadCrumbs(String text) {
		breadCrumbs.setText(breadCrumbs.getText() + " > " + text);
	}

	public void setFlag() {
		flag = false;
	}

	public void setLastBreadCrumbs(String text) {
		if (flag) {
			removeBreadCrumbs();
		} else {
			flag = true;
		}
		breadCrumbs.setText(breadCrumbs.getText() + " > " + text);
	}

	public void updateBreadCrumbs() throws IOException {
		Boolean flagAux = false;

		String toRet = new String(l.getProperty("START"));

		IdsStructure ids = IdsStructureSingleton.getInstance();
		Operations operations = OperationsSingleton.getInstance();

		if (!flagAux && !LeftAbstractPanel.actualPanel.equals("eventTypesPanel")) {
			List<EventTypeResult> eventTypes = operations.listEventTypes(ids.getEventTypeId());
			toRet = toRet.concat(" > " + eventTypes.get(0).getEventType().getName());
		} else {
			flagAux = true;
		}

		if (!flagAux && !LeftAbstractPanel.actualPanel.equals("competitionsPanel")) {
			List<CompetitionResult> competitions = operations.listCompetitions(ids.getEventTypeId(),
					ids.getCompetitionId());
			toRet = toRet.concat(" > " + competitions.get(0).getCompetition().getName());
		} else {
			flagAux = true;
		}

		if (!flagAux && !LeftAbstractPanel.actualPanel.equals("eventsPanel")) {
			List<EventResult> events = operations.listEvents(ids.getEventTypeId(), ids.getCompetitionId(),
					ids.getEventId());
			toRet = toRet.concat(" > " + events.get(0).getEvent().getName());
		} else {
			flagAux = true;
		}

		if (flag && !flagAux && LeftAbstractPanel.actualPanel.equals("marketCataloguePanel")) {
			List<MarketCatalogue> markets = operations.listMarketCatalogue(ids.getMarketId());
			toRet = toRet.concat(" > " + markets.get(0).getMarketName());
		}

		breadCrumbs.setText(toRet);
	}

	public void updateBorder() throws IOException {
		Border grayLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		panel.setBorder(
				BorderFactory.createTitledBorder(grayLine, LanguageSingleton.getInstance().getProperty("NAVIGATION"), 0,
						0, null, HexToRGBConverter.hexToRGB("#2789CE")));
	}
}