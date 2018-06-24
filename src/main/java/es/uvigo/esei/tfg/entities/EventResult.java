package es.uvigo.esei.tfg.entities;

import java.util.Comparator;

public class EventResult implements Comparator<EventResult> {
	private Event event;
	private int marketCount;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public String toString() {
		return "{" + "event=" + event + ", marketCount=" + marketCount + "}";
	}

	public int compare(EventResult o1, EventResult o2) {
		return o1.getEvent().getName().compareTo(o2.getEvent().getName());
	}
}
