package es.uvigo.esei.tfg.entities;

import java.util.List;

import es.uvigo.esei.tfg.enums.ExecutionReportErrorCode;
import es.uvigo.esei.tfg.enums.ExecutionReportStatus;

public class PlaceExecutionReport {
	private String customerRef;
	private ExecutionReportStatus status;
	private ExecutionReportErrorCode errorCode;
	private String marketId;
	private List<PlaceInstructionReport> instructionReports;

	public String getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	public ExecutionReportStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionReportStatus status) {
		this.status = status;
	}

	public ExecutionReportErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ExecutionReportErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public List<PlaceInstructionReport> getInstructionReports() {
		return instructionReports;
	}

	public void setInstructionReports(List<PlaceInstructionReport> instructionReports) {
		this.instructionReports = instructionReports;
	}

	public String toString() {
		return "PlaceExecutionReport [customerRef=" + customerRef + ", status=" + status + ", errorCode=" + errorCode
				+ ", marketId=" + marketId + ", instructionReports=" + instructionReports + "]";
	}
}