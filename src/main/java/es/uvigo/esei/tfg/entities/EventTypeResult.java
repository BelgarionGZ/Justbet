package es.uvigo.esei.tfg.entities;

import java.util.Comparator;

public class EventTypeResult implements Comparator<EventTypeResult> {
	private EventType eventType;
	private int marketCount;

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public String toString() {
		return "{eventType=" + eventType + ", marketCount=" + marketCount + "}";
	}

	public int compare(EventTypeResult o1, EventTypeResult o2) {
		return o1.getEventType().getName().compareTo(o2.getEventType().getName());
	}
}