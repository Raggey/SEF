package Test;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Class.CreditCard;
import Class.Customer;

public class CustomerTest extends TestCase {
	Customer customer1;

	@Before
	protected void setUp() throws Exception {
		
	}
	
	@Test
	public void TestgetPoint() {
		customer1 = new Customer("C12345", "Gloria", 3000);
		customer1.calculatePoints(100);
		assertEquals(customer1.getPoint(), 10);
	}
	
	@Test
	public void TestcheckDiscount()	{
		customer1 = new Customer("C12345", "Gloria", 3000);
		customer1.calculatePoints(200);
		assertEquals(customer1.checkDiscount(), 5);
	}
	
	@Test
	public void TestMoreDiscount()	{
		customer1 = new Customer("C12345", "Gloria", 3000);
		customer1.calculatePoints(2000);
		assertEquals(customer1.checkDiscount(), 50);
	}
	
	@Test
	public void TestaddCard()	{
		customer1 = new Customer("C12345", "Gloria", 3000);
		customer1.addCard(new CreditCard(1234543221));
//		assertEquals(customer1);
	}
	@After
	protected void tearDown() throws Exception {
	}
	
}
