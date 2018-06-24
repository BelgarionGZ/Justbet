package es.uvigo.esei.tfg.console;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import es.uvigo.esei.tfg.entities.CancelExecutionReport;
import es.uvigo.esei.tfg.entities.ClearedOrderSummaryReport;
import es.uvigo.esei.tfg.entities.CompetitionResult;
import es.uvigo.esei.tfg.entities.CurrentOrderSummaryReport;
import es.uvigo.esei.tfg.entities.EventResult;
import es.uvigo.esei.tfg.entities.EventTypeResult;
import es.uvigo.esei.tfg.entities.MarketBook;
import es.uvigo.esei.tfg.entities.MarketCatalogue;
import es.uvigo.esei.tfg.entities.PlaceExecutionReport;
import es.uvigo.esei.tfg.enums.BetStatus;
import es.uvigo.esei.tfg.enums.Side;
import es.uvigo.esei.tfg.exceptions.LoginException;
import es.uvigo.esei.tfg.monitors.SessionMonitor;
import es.uvigo.esei.tfg.operations.Operations;
import es.uvigo.esei.tfg.operations.OperationsSingleton;
import es.uvigo.esei.tfg.util.Language;
import es.uvigo.esei.tfg.util.LanguageSingleton;
import es.uvigo.esei.tfg.util.StringToSideConverter;

public class Console {
	public static void begin() {
		Language l = null;
		try {
			l = LanguageSingleton.getInstance();
		} catch (IOException e1) {
			System.out.println("There is a problem with properties file");
		}

		try {
			Operations operations = OperationsSingleton.getInstance();
			int option = 0;

			Map<String, String> login = operations.doLogin();
			SessionMonitor sessionMonitor = new SessionMonitor();
			sessionMonitor.start();

			if (login.get("status").equals("SUCCESS")) {
				Scanner keyboard = new Scanner(System.in);

				do {
					System.out.println(l.getProperty("OPERATIONS_AVAILABLE") + ":");
					System.out.println("0 - " + l.getProperty("EXIT"));
					System.out.println("1 - " + l.getProperty("GET_ACCOUNT_FUNDS"));
					System.out.println("2 - " + l.getProperty("LIST_EVENT_TYPES"));
					System.out.println("3 - " + l.getProperty("LIST_COMPETITIONS"));
					System.out.println("4 - " + l.getProperty("LIST_EVENTS"));
					System.out.println("5 - " + l.getProperty("LIST_MARKET_CATALOGUE"));
					System.out.println("6 - " + l.getProperty("LIST_MARKET_BOOK"));
					System.out.println("7 - " + l.getProperty("PLACE_ORDER"));
					System.out.println("8 - " + l.getProperty("CANCEL_ORDER"));
					System.out.println("9 - " + l.getProperty("LIST_CURRENT_ORDERS"));
					System.out.println("10 - " + l.getProperty("LIST_CLEARED_ORDERS"));
					System.out.println("11 - " + l.getProperty("CHANGE_LANGUAGE"));
					option = keyboard.nextInt();

					switch (option) {
					case 1:
						Map<String, String> operation1 = operations.getAccountFunds();
						System.out.println(operation1);
						break;
					case 2:
						List<EventTypeResult> operation2 = operations.listEventTypes();
						System.out.println(operation2);
						break;
					case 3:
						System.out.println(l.getProperty("ASK_FOR_EVENT_TYPE"));
						String eventType = keyboard.next();
						List<CompetitionResult> operation3 = operations.listCompetitions(eventType);
						System.out.println(operation3);
						break;
					case 4:
						System.out.println(l.getProperty("ASK_FOR_EVENT_TYPE"));
						String eventType1 = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_COMPETITION"));
						String competition = keyboard.next();
						List<EventResult> operation4 = operations.listEvents(eventType1, competition);
						System.out.println(operation4);
						break;
					case 5:
						System.out.println(l.getProperty("ASK_FOR_EVENT_TYPE"));
						String eventType2 = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_COMPETITION"));
						String competition1 = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_EVENT"));
						String event = keyboard.next();
						List<MarketCatalogue> operation5 = operations.listMarketCatalogue(eventType2, competition1,
								event);
						System.out.println(operation5);
						break;
					case 6:
						System.out.println(l.getProperty("ASK_FOR_MARKET"));
						String market = keyboard.next();
						List<MarketBook> operation6 = operations.listMarketBook(market);
						System.out.println(operation6);
						break;
					case 7:
						System.out.println(l.getProperty("ASK_FOR_MARKET"));
						String market1 = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_SELECTION"));
						String selection = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_SIDE"));
						String sideString = keyboard.next();
						Side side = StringToSideConverter.StringToSide(sideString);
						System.out.println(l.getProperty("ASK_FOR_SIZE"));
						String size = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_PRICE"));
						String price = keyboard.next();
						PlaceExecutionReport operation7 = operations.placeOrders(market1, selection, side, size, price);
						System.out.println(operation7);
						break;
					case 8:
						System.out.println(l.getProperty("ASK_FOR_MARKET"));
						String market2 = keyboard.next();
						System.out.println(l.getProperty("ASK_FOR_SELECTION"));
						String selection1 = keyboard.next();
						CancelExecutionReport operation8 = operations.cancelOrders(market2, selection1);
						System.out.println(operation8);
						break;
					case 9:
						CurrentOrderSummaryReport operation9 = operations.listCurrentOrders();
						System.out.println(operation9);
						break;
					case 10:
						ClearedOrderSummaryReport operation10 = operations.listClearedOrders(BetStatus.SETTLED);
						System.out.println(operation10);
						break;
					case 11:
						System.out.println(l.getProperty("ASK_FOR_LANGUAGE") + l.languagesAvailable());
						String language = keyboard.next();
						l.changeLanguage(language);
						break;
					}
				} while (option != 0);

				operations.doLogout();

				keyboard.close();
			} else {
				throw new LoginException(login.get("error"));
			}
		} catch (IOException e) {
			System.out.println(l.getProperty("PROBLEM_IO"));
		} catch (LoginException e) {
			System.out.println(l.getProperty("PROBLEM_LOGIN") + " " + e.getMessage());
		}
	}
}