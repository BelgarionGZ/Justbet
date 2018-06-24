package es.uvigo.esei.tfg.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.reflect.TypeToken;

import es.uvigo.esei.tfg.api.KeepAliveRequest;
import es.uvigo.esei.tfg.api.LoginRequest;
import es.uvigo.esei.tfg.api.LogoutRequest;
import es.uvigo.esei.tfg.api.OperationRequest;
import es.uvigo.esei.tfg.entities.CancelExecutionReport;
import es.uvigo.esei.tfg.entities.CancelInstruction;
import es.uvigo.esei.tfg.entities.ClearedOrderSummaryReport;
import es.uvigo.esei.tfg.entities.CompetitionResult;
import es.uvigo.esei.tfg.entities.CurrentOrderSummaryReport;
import es.uvigo.esei.tfg.entities.EventResult;
import es.uvigo.esei.tfg.entities.EventTypeResult;
import es.uvigo.esei.tfg.entities.LimitOrder;
import es.uvigo.esei.tfg.entities.MarketBook;
import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.entities.MarketFilter;
import es.uvigo.esei.tfg.entities.PlaceExecutionReport;
import es.uvigo.esei.tfg.entities.PlaceInstruction;
import es.uvigo.esei.tfg.entities.PriceProjection;
import es.uvigo.esei.tfg.enums.BetStatus;
import es.uvigo.esei.tfg.enums.MarketProjection;
import es.uvigo.esei.tfg.enums.MarketSort;
import es.uvigo.esei.tfg.enums.OrderType;
import es.uvigo.esei.tfg.enums.PersistenceType;
import es.uvigo.esei.tfg.enums.PriceData;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.util.JsonConverter;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;

public class Operations {
	private Language language;
	private String ssoToken;

	public Operations() throws IOException {
		language = LanguageSingleton.getInstance();
	}

	public CancelExecutionReport cancelOrders(String bet, String market) throws IOException {
		CancelInstruction instruction = new CancelInstruction();
		instruction.setBetId(bet);

		List<CancelInstruction> instructions = new ArrayList<CancelInstruction>();
		instructions.add(instruction);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("marketId", market);
		params.put("instructions", instructions);

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("cancelOrders", requestString, ssoToken, "BETTING_URL");

		CancelExecutionReport toRet = JsonConverter.convertFromJson(response, new TypeToken<CancelExecutionReport>() {
		}.getType());

		return toRet;
	}

	public Map<String, String> doLogin() throws IOException, NullPointerException {
		String response = LoginRequest.sendRequest();

		Map<String, String> toRet = JsonConverter.convertFromJson(response, new TypeToken<Map<String, String>>() {
		}.getType());

		ssoToken = toRet.get("token");

		return toRet;
	}

	public Map<String, String> doLogout() throws IOException {
		String response = LogoutRequest.sendRequest(ssoToken);

		Map<String, String> toRet = JsonConverter.convertFromJson(response, new TypeToken<Map<String, String>>() {
		}.getType());

		return toRet;
	}

	public Map<String, String> getAccountFunds() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("getAccountFunds", requestString, ssoToken, "ACCOUNT_URL");

		Map<String, String> toRet = JsonConverter.convertFromJson(response, new TypeToken<Map<String, String>>() {
		}.getType());

		return toRet;
	}

	public Map<String, String> keepAlive() throws IOException {
		String response = KeepAliveRequest.sendRequest(ssoToken);

		Map<String, String> toRet = JsonConverter.convertFromJson(response, new TypeToken<Map<String, String>>() {
		}.getType());

		return toRet;
	}

	public ClearedOrderSummaryReport listClearedOrders(BetStatus betStatus) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("betStatus", betStatus);
		params.put("includeItemDescription", true);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listClearedOrders", requestString, ssoToken, "BETTING_URL");

		ClearedOrderSummaryReport toRet = JsonConverter.convertFromJson(response,
				new TypeToken<ClearedOrderSummaryReport>() {
				}.getType());

		return toRet;
	}

	public List<CompetitionResult> listCompetitions(String eventType) throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listCompetitions", requestString, ssoToken, "BETTING_URL");

		List<CompetitionResult> toRet = JsonConverter.convertFromJson(response,
				new TypeToken<List<CompetitionResult>>() {
				}.getType());

		Collections.sort(toRet, new CompetitionResult());

		return toRet;
	}

	public List<CompetitionResult> listCompetitions(String eventType, String competition) throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		Set<String> competitionIds = new HashSet<String>();
		competitionIds.add(competition);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);
		marketFilter.setCompetitionIds(competitionIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listCompetitions", requestString, ssoToken, "BETTING_URL");

		List<CompetitionResult> toRet = JsonConverter.convertFromJson(response,
				new TypeToken<List<CompetitionResult>>() {
				}.getType());

		Collections.sort(toRet, new CompetitionResult());

		return toRet;
	}

	public CurrentOrderSummaryReport listCurrentOrders() throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listCurrentOrders", requestString, ssoToken, "BETTING_URL");

		CurrentOrderSummaryReport toRet = JsonConverter.convertFromJson(response,
				new TypeToken<CurrentOrderSummaryReport>() {
				}.getType());

		return toRet;
	}

	public List<EventResult> listEvents(String eventType, String competition) throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		Set<String> competitionIds = new HashSet<String>();
		competitionIds.add(competition);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);
		marketFilter.setCompetitionIds(competitionIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listEvents", requestString, ssoToken, "BETTING_URL");

		List<EventResult> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<EventResult>>() {
		}.getType());

		Collections.sort(toRet, new EventResult());

		return toRet;
	}

	public List<EventResult> listEvents(String eventType, String competition, String event) throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		Set<String> competitionIds = new HashSet<String>();
		competitionIds.add(competition);

		Set<String> eventIds = new HashSet<String>();
		eventIds.add(event);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);
		marketFilter.setCompetitionIds(competitionIds);
		marketFilter.setEventIds(eventIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listEvents", requestString, ssoToken, "BETTING_URL");

		List<EventResult> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<EventResult>>() {
		}.getType());

		Collections.sort(toRet, new EventResult());

		return toRet;
	}

	public List<EventTypeResult> listEventTypes() throws IOException {
		MarketFilter marketFilter = new MarketFilter();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listEventTypes", requestString, ssoToken, "BETTING_URL");

		List<EventTypeResult> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<EventTypeResult>>() {
		}.getType());

		Collections.sort(toRet, new EventTypeResult());

		return toRet;
	}

	public List<EventTypeResult> listEventTypes(String eventType) throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listEventTypes", requestString, ssoToken, "BETTING_URL");

		List<EventTypeResult> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<EventTypeResult>>() {
		}.getType());

		Collections.sort(toRet, new EventTypeResult());

		return toRet;
	}

	public List<MarketBook> listMarketBook(String market) throws IOException {
		List<String> marketIds = new ArrayList<String>();
		marketIds.add(market);

		Set<PriceData> priceData = new HashSet<PriceData>();
		priceData.add(PriceData.EX_ALL_OFFERS);

		PriceProjection priceProjection = new PriceProjection();
		priceProjection.setPriceData(priceData);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("marketIds", marketIds);
		params.put("priceProjection", priceProjection);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listMarketBook", requestString, ssoToken, "BETTING_URL");

		List<MarketBook> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<MarketBook>>() {
		}.getType());

		return toRet;
	}

	public List<MarketCatalogue> listMarketCatalogue(String eventType, String competition, String event)
			throws IOException {
		Set<String> eventTypeIds = new HashSet<String>();
		eventTypeIds.add(eventType);

		Set<String> competitionIds = new HashSet<String>();
		competitionIds.add(competition);

		Set<String> eventIds = new HashSet<String>();
		eventIds.add(event);

		Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
		marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setEventTypeIds(eventTypeIds);
		marketFilter.setCompetitionIds(competitionIds);
		marketFilter.setEventIds(eventIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("maxResults", 1000);
		params.put("marketProjection", marketProjection);
		params.put("sort", MarketSort.FIRST_TO_START);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listMarketCatalogue", requestString, ssoToken, "BETTING_URL");

		List<MarketCatalogue> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<MarketCatalogue>>() {
		}.getType());

		Collections.sort(toRet, new MarketCatalogue());

		return toRet;
	}

	public List<MarketCatalogue> listMarketCatalogue(String market) throws IOException {
		Set<String> marketIds = new HashSet<String>();
		marketIds.add(market);

		Set<MarketProjection> marketProjection = new HashSet<MarketProjection>();
		marketProjection.add(MarketProjection.RUNNER_DESCRIPTION);

		MarketFilter marketFilter = new MarketFilter();
		marketFilter.setMarketIds(marketIds);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("filter", marketFilter);
		params.put("maxResults", 1000);
		params.put("marketProjection", marketProjection);
		params.put("sort", MarketSort.FIRST_TO_START);
		params.put("locale", language.getLanguageSpecial());

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("listMarketCatalogue", requestString, ssoToken, "BETTING_URL");

		List<MarketCatalogue> toRet = JsonConverter.convertFromJson(response, new TypeToken<List<MarketCatalogue>>() {
		}.getType());

		Collections.sort(toRet, new MarketCatalogue());

		return toRet;
	}

	public PlaceExecutionReport placeOrders(String market, String selection, Side side, String size, String price)
			throws IOException {
		LimitOrder limitOrder = new LimitOrder();
		limitOrder.setSize(Double.parseDouble(size));
		limitOrder.setPrice(Double.parseDouble(price));
		limitOrder.setPersistenceType(PersistenceType.PERSIST);

		PlaceInstruction instruction = new PlaceInstruction();
		instruction.setOrderType(OrderType.LIMIT);
		instruction.setSelectionId(Long.parseLong(selection));
		instruction.setSide(side);
		instruction.setLimitOrder(limitOrder);

		List<PlaceInstruction> instructions = new ArrayList<PlaceInstruction>();
		instructions.add(instruction);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("marketId", market);
		params.put("instructions", instructions);

		String requestString = JsonConverter.convertToJson(params);

		String response = OperationRequest.sendRequest("placeOrders", requestString, ssoToken, "BETTING_URL");

		PlaceExecutionReport toRet = JsonConverter.convertFromJson(response, new TypeToken<PlaceExecutionReport>() {
		}.getType());

		return toRet;
	}
}