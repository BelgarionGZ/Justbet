package es.uvigo.esei.tfg.entities;

public class CancelInstruction {
	private String betId;
	private Double sizeReduction;

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public Double getSizeReduction() {
		return sizeReduction;
	}

	public void setSizeReduction(Double sizeReduction) {
		this.sizeReduction = sizeReduction;
	}

	public String toString() {
		return "CancelInstruction [betId=" + betId + ", sizeReduction=" + sizeReduction + "]";
	}
}