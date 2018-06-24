package es.uvigo.esei.tfg.entities;

import java.util.Date;

import es.uvigo.esei.tfg.enums.InstructionReportErrorCode;
import es.uvigo.esei.tfg.enums.InstructionReportStatus;
import es.uvigo.esei.tfg.enums.OrderStatus;

public class PlaceInstructionReport {
	private InstructionReportStatus status;
	private InstructionReportErrorCode errorCode;
	private PlaceInstruction instruction;
	private String betId;
	private Date placedDate;
	private double averagePriceMatched;
	private double sizeMatched;
	private OrderStatus orderStatus;

	public InstructionReportStatus getStatus() {
		return status;
	}

	public void setStatus(InstructionReportStatus status) {
		this.status = status;
	}

	public InstructionReportErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(InstructionReportErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public PlaceInstruction getInstruction() {
		return instruction;
	}

	public void setInstruction(PlaceInstruction instruction) {
		this.instruction = instruction;
	}

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public double getAveragePriceMatched() {
		return averagePriceMatched;
	}

	public void setAveragePriceMatched(double averagePriceMatched) {
		this.averagePriceMatched = averagePriceMatched;
	}

	public double getSizeMatched() {
		return sizeMatched;
	}

	public void setSizeMatched(double sizeMatched) {
		this.sizeMatched = sizeMatched;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String toString() {
		return "PlaceInstructionReport [status=" + status + ", errorCode=" + errorCode + ", instruction=" + instruction
				+ ", betId=" + betId + ", placedDate=" + placedDate + ", averagePriceMatched=" + averagePriceMatched
				+ ", sizeMatched=" + sizeMatched + ", orderStatus=" + orderStatus + "]";
	}
}