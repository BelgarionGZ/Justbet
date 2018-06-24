package es.uvigo.esei.tfg.entities;

import java.util.Date;

import es.uvigo.esei.tfg.enums.OrderStatus;
import es.uvigo.esei.tfg.enums.OrderType;
import es.uvigo.esei.tfg.enums.PersistenceType;
import es.uvigo.esei.tfg.enums.Side;

public class CurrentOrderSummary {
	private String betId;
	private String marketId;
	private Long selectionId;
	private Double handicap;
	private PriceSize priceSize;
	private Double bspLiability;
	private Side side;
	private OrderStatus status;
	private PersistenceType persistenceType;
	private OrderType orderType;
	private Date placedDate;
	private Date matchedDate;
	private Double averagePriceMatched;
	private Double sizeMatched;
	private Double sizeRemaining;
	private Double sizeLapsed;
	private Double sizeCancelled;
	private Double sizeVoided;
	private String regulatorAuthCode;
	private String regulatorCode;
	private String customerOrderRef;
	private String customerStrategyRef;

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	public Double getHandicap() {
		return handicap;
	}

	public void setHandicap(Double handicap) {
		this.handicap = handicap;
	}

	public PriceSize getPriceSize() {
		return priceSize;
	}

	public void setPriceSize(PriceSize priceSize) {
		this.priceSize = priceSize;
	}

	public Double getBspLiability() {
		return bspLiability;
	}

	public void setBspLiability(Double bspLiability) {
		this.bspLiability = bspLiability;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public PersistenceType getPersistenceType() {
		return persistenceType;
	}

	public void setPersistenceType(PersistenceType persistenceType) {
		this.persistenceType = persistenceType;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public Date getMatchedDate() {
		return matchedDate;
	}

	public void setMatchedDate(Date matchedDate) {
		this.matchedDate = matchedDate;
	}

	public Double getAveragePriceMatched() {
		return averagePriceMatched;
	}

	public void setAveragePriceMatched(Double averagePriceMatched) {
		this.averagePriceMatched = averagePriceMatched;
	}

	public Double getSizeMatched() {
		return sizeMatched;
	}

	public void setSizeMatched(Double sizeMatched) {
		this.sizeMatched = sizeMatched;
	}

	public Double getSizeRemaining() {
		return sizeRemaining;
	}

	public void setSizeRemaining(Double sizeRemaining) {
		this.sizeRemaining = sizeRemaining;
	}

	public Double getSizeLapsed() {
		return sizeLapsed;
	}

	public void setSizeLapsed(Double sizeLapsed) {
		this.sizeLapsed = sizeLapsed;
	}

	public Double getSizeCancelled() {
		return sizeCancelled;
	}

	public void setSizeCancelled(Double sizeCancelled) {
		this.sizeCancelled = sizeCancelled;
	}

	public Double getSizeVoided() {
		return sizeVoided;
	}

	public void setSizeVoided(Double sizeVoided) {
		this.sizeVoided = sizeVoided;
	}

	public String getRegulatorAuthCode() {
		return regulatorAuthCode;
	}

	public void setRegulatorAuthCode(String regulatorAuthCode) {
		this.regulatorAuthCode = regulatorAuthCode;
	}

	public String getRegulatorCode() {
		return regulatorCode;
	}

	public void setRegulatorCode(String regulatorCode) {
		this.regulatorCode = regulatorCode;
	}

	public String getCustomerOrderRef() {
		return customerOrderRef;
	}

	public void setCustomerOrderRef(String customerOrderRef) {
		this.customerOrderRef = customerOrderRef;
	}

	public String getCustomerStrategyRef() {
		return customerStrategyRef;
	}

	public void setCustomerStrategyRef(String customerStrategyRef) {
		this.customerStrategyRef = customerStrategyRef;
	}

	public String toString() {
		return "CurrentOrderSummary [betId=" + betId + ", marketId=" + marketId + ", selectionId=" + selectionId
				+ ", handicap=" + handicap + ", priceSize=" + priceSize + ", bspLiability=" + bspLiability + ", side="
				+ side + ", status=" + status + ", persistenceType=" + persistenceType + ", orderType=" + orderType
				+ ", placedDate=" + placedDate + ", matchedDate=" + matchedDate + ", averagePriceMatched="
				+ averagePriceMatched + ", sizeMatched=" + sizeMatched + ", sizeRemaining=" + sizeRemaining
				+ ", sizeLapsed=" + sizeLapsed + ", sizeCancelled=" + sizeCancelled + ", sizeVoided=" + sizeVoided
				+ ", regulatorAuthCode=" + regulatorAuthCode + ", regulatorCode=" + regulatorCode
				+ ", customerOrderRef=" + customerOrderRef + ", customerStrategyRef=" + customerStrategyRef + "]";
	}
}