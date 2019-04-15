package Test;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Class.Customer;

public class CustomerTest extends TestCase {
	Customer customer1;

	@Before
	protected void setUp() throws Exception {
		customer1 = new Customer("C12345", "Gloria", 3000);
	}

	@Test
	public void pointCalculateTest() {
		customer1.calculatePoints(100);
		assertEquals(customer1.getPoint(), 10);
	}
	
	@After
	protected void tearDown() throws Exception {
	}
	
}
