package es.uvigo.esei.tfg.entities;

import java.util.Date;

import es.uvigo.esei.tfg.enums.OrderType;
import es.uvigo.esei.tfg.enums.PersistenceType;
import es.uvigo.esei.tfg.enums.Side;

public class ClearedOrderSummary {
	private String eventTypeId;
	private String eventId;
	private String marketId;
	private Long selectionId;
	private Double handicap;
	private String betId;
	private Date placedDate;
	private PersistenceType persistenceType;
	private OrderType orderType;
	private Side side;
	private ItemDescription itemDescription;
	private String betOutcome;
	private Double priceRequested;
	private Date settledDate;
	private Date lastMatchedDate;
	private Integer betCount;
	private Double commission;
	private Double priceMatched;
	private boolean priceReduced;
	private Double sizeSettled;
	private Double profit;
	private Double sizeCancelled;
	private String customerOrderRef;
	private String customerStrategyRef;

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public ItemDescription getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(ItemDescription itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getBetOutcome() {
		return betOutcome;
	}

	public void setBetOutcome(String betOutcome) {
		this.betOutcome = betOutcome;
	}

	public Double getPriceRequested() {
		return priceRequested;
	}

	public void setPriceRequested(Double priceRequested) {
		this.priceRequested = priceRequested;
	}

	public Date getSettledDate() {
		return settledDate;
	}

	public void setSettledDate(Date settledDate) {
		this.settledDate = settledDate;
	}

	public Date getLastMatchedDate() {
		return lastMatchedDate;
	}

	public void setLastMatchedDate(Date lastMatchedDate) {
		this.lastMatchedDate = lastMatchedDate;
	}

	public Integer getBetCount() {
		return betCount;
	}

	public void setBetCount(Integer betCount) {
		this.betCount = betCount;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getPriceMatched() {
		return priceMatched;
	}

	public void setPriceMatched(Double priceMatched) {
		this.priceMatched = priceMatched;
	}

	public boolean isPriceReduced() {
		return priceReduced;
	}

	public void setPriceReduced(boolean priceReduced) {
		this.priceReduced = priceReduced;
	}

	public Double getSizeSettled() {
		return sizeSettled;
	}

	public void setSizeSettled(Double sizeSettled) {
		this.sizeSettled = sizeSettled;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getSizeCancelled() {
		return sizeCancelled;
	}

	public void setSizeCancelled(Double sizeCancelled) {
		this.sizeCancelled = sizeCancelled;
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
		return "ClearedOrderSummary [eventTypeId=" + eventTypeId + ", eventId=" + eventId + ", marketId=" + marketId
				+ ", selectionId=" + selectionId + ", handicap=" + handicap + ", betId=" + betId + ", placedDate="
				+ placedDate + ", persistenceType=" + persistenceType + ", orderType=" + orderType + ", side=" + side
				+ ", itemDescription=" + itemDescription + ", betOutcome=" + betOutcome + ", priceRequested="
				+ priceRequested + ", settledDate=" + settledDate + ", lastMatchedDate=" + lastMatchedDate
				+ ", betCount=" + betCount + ", commission=" + commission + ", priceMatched=" + priceMatched
				+ ", priceReduced=" + priceReduced + ", sizeSettled=" + sizeSettled + ", profit=" + profit
				+ ", sizeCancelled=" + sizeCancelled + ", customerOrderRef=" + customerOrderRef
				+ ", customerStrategyRef=" + customerStrategyRef + "]";
	}
}