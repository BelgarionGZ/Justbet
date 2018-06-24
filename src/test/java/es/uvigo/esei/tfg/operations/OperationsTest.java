package es.uvigo.esei.tfg.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uvigo.esei.tfg.entities.EventTypeResult;

public class OperationsTest {
	private Operations operations;

	@Before
	public void setUp() throws Exception {
		operations = OperationsSingleton.getInstance();
		Map<String, String> login = operations.doLogin();
		assertEquals("SUCCESS", login.get("status"));
	}

	@After
	public void tearDown() throws Exception {
		Map<String, String> logout = operations.doLogout();
		assertEquals("SUCCESS", logout.get("status"));
	}

	@Test
	public void testGetAccountFunds() throws Exception {
		Map<String, String> operation = operations.getAccountFunds();
		assertNotNull(operation);
	}

	@Test
	public void testKeepAlive() throws Exception {
		Map<String, String> operation = operations.keepAlive();
		assertEquals("SUCCESS", operation.get("status"));
	}

	@Test
	public void testListEventTypes() throws Exception {
		List<EventTypeResult> operation = operations.listEventTypes();
		assertNotNull(operation);
	}
}