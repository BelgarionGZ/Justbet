package es.uvigo.esei.tfg.entities;

import java.util.Comparator;

public class CompetitionResult implements Comparator<CompetitionResult> {
	private Competition competition;
	private int marketCount;
	private String competitionRegion;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public String getCompetitionRegion() {
		return competitionRegion;
	}

	public void setCompetitionRegion(String competitionRegion) {
		this.competitionRegion = competitionRegion;
	}

	public String toString() {
		return "{Competition=" + competition + ", marketCount=" + marketCount + ", competitionRegion="
				+ competitionRegion + "}";
	}

	public int compare(CompetitionResult o1, CompetitionResult o2) {
		return o1.getCompetition().getName().compareTo(o2.getCompetition().getName());
	}
}