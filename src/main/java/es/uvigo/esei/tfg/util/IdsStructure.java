package es.uvigo.esei.tfg.util;

public class IdsStructure {
	private String competitionId;
	private String eventId;
	private String eventTypeId;
	private String marketId;

	public IdsStructure() {
		competitionId = new String();
		eventId = new String();
		eventTypeId = new String();
		marketId = new String();
	}

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
}