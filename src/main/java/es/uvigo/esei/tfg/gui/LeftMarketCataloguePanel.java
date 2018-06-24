package es.uvigo.esei.tfg.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.monitors.MarketMonitor;
import es.uvigo.esei.tfg.util.GradientButton;
import es.uvigo.esei.tfg.util.HexToRGBConverter;
import es.uvigo.esei.tfg.util.IdsStructureSingleton;

public class LeftMarketCataloguePanel extends LeftAbstractPanel {
	private MarketMonitor marketMonitor = null;
	private String competitionId;
	private String eventId;
	private String eventTypeId;

	public LeftMarketCataloguePanel() throws IOException, LoginException {
		super();
	}

	@Override
	public void createBackButton(final String panelName) {
		JButton btnNewButton = new JButton(l.getProperty("BACK"));
		btnNewButton.setBackground(HexToRGBConverter.hexToRGB("#303030"));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnNewButton.getMinimumSize().height));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.getLeftPanel().getCardLayout().show(frame.getLeftPanel().getPanel(), panelName);
					frame.getTopPanel().getBreadCrumbsPanel().removeBreadCrumbsX2();
					frame.getTopPanel().getBreadCrumbsPanel().setFlag();

					setActualPanelBackwards();

					if (marketMonitor != null && marketMonitor.isAlive()) {
						marketMonitor.setStop();
						marketMonitor.join();
					}
				} catch (InterruptedException e1) {
					FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
							l.getProperty("PROBLEM_INTERRUPTED") + " " + e1.getMessage(), "Error");
				}
			}
		});

		panel.add(btnNewButton);
	}

	public void createButtons(final String eventTypeIdParameter, final String competitionParameter,
			final String eventParameter) throws IOException {
		List<MarketCatalogue> markets = operations.listMarketCatalogue(eventTypeIdParameter, competitionParameter,
				eventParameter);

		competitionId = competitionParameter;
		eventId = eventParameter;
		eventTypeId = eventTypeIdParameter;

		for (final MarketCatalogue market : markets) {
			GradientButton btnNewButton = createButton(market.getMarketName());

			panel.add(btnNewButton);

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (marketMonitor != null && marketMonitor.isAlive()) {
							marketMonitor.setStop();
							marketMonitor.join();
						}

						CenterMainPanel centerPanel = frame.getCenterPanel();

						IdsStructureSingleton.getInstance().setMarketId(market.getMarketId());

						centerPanel.createMarketBook(market);

						frame.getTopPanel().getBreadCrumbsPanel().setLastBreadCrumbs(market.getMarketName());

						marketMonitor = new MarketMonitor();
						marketMonitor.start();
					} catch (InterruptedException e1) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
								l.getProperty("PROBLEM_INTERRUPTED") + " " + e1.getMessage(), "Error");
					} catch (IOException e2) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE, l.getProperty("PROBLEM_IO"), "Error");
					} catch (LoginException e3) {
						FrameMessage.createErrorFrame(JOptionPane.ERROR_MESSAGE,
								l.getProperty("PROBLEM_LOGIN") + " " + e3.getMessage(), "Error");
					}
				}
			});
		}
	}

	@Override
	protected void setActualPanelBackwards() {
		setActualPanel("eventsPanel");
	}

	public void updateButtons() throws IOException {
		clean();
		createBackButton("eventsPanel");
		createButtons(eventTypeId, competitionId, eventId);
		frame.getLeftPanel().getEventsPanel().updateButtons();
	}
}