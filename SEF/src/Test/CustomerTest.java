package Test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Class.CreditCard;
import Class.Customer;

public class CustomerTest {
	Customer customer1;

	@Before
	public void setUp() throws Exception {
		customer1 = new Customer("C12345", "Gloria", 3000);
	}
	
	@Test
	public void TestgetPoint() {
		customer1.calculatePoints(100);
		assertEquals(customer1.getPoint(), 10);
	}
	
	@Test
	public void TestcheckDiscount()	{
		customer1.calculatePoints(200);
		assertEquals(customer1.checkDiscount(100), 5);
	}
	
	@Test
	public void TestMoreDiscount()	{
		customer1.calculatePoints(2000);
		assertEquals(customer1.checkDiscount(100), 50);
	}
	
	@Test
	public void TestMaxDiscount()	{
		customer1.calculatePoints(2000);
		//Buy more than discount:
		assertEquals(customer1.checkDiscount(40), 40);
		assertEquals(customer1.getPoint(), 40);
	}
	
	@Test
	public void TestaddCard()	{
		customer1.addCard(new CreditCard(1234543221));
	}
	@After
	public void tearDown() throws Exception {
	}
	
}