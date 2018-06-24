package es.uvigo.esei.tfg.entities;

import java.util.Date;

import es.uvigo.esei.tfg.enums.InstructionReportErrorCode;
import es.uvigo.esei.tfg.enums.InstructionReportStatus;

public class CancelInstructionReport {
	private InstructionReportStatus status;
	private InstructionReportErrorCode errorCode;
	private CancelInstruction instruction;
	private Double sizeCancelled;
	private Date cancelledDate;

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

	public CancelInstruction getInstruction() {
		return instruction;
	}

	public void setInstruction(CancelInstruction instruction) {
		this.instruction = instruction;
	}

	public Double getSizeCancelled() {
		return sizeCancelled;
	}

	public void setSizeCancelled(Double sizeCancelled) {
		this.sizeCancelled = sizeCancelled;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public String toString() {
		return "CancelInstructionReport [status=" + status + ", errorCode=" + errorCode + ", instruction=" + instruction
				+ ", sizeCancelled=" + sizeCancelled + ", cancelledDate=" + cancelledDate + "]";
	}
}