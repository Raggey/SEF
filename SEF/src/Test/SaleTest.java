package Test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Class.Customer;
import Class.Sale;

public class SaleTest {

	Sale testSale;
	
	@Before
	public void setUp() throws Exception {
		double discount = 0.8;
		int id1 = 10;
		int id2 = 20;
		int id3 = 30;
		int[] productIDs = {id1, id2, id3};
		Customer person = new Customer("c002", "Saad", 3064);
		testSale = new Sale(person);
	}
	



	@Test
	public void testApplyDiscounts() {
		double productPrice = 10;
		double discountedPrice;
		discountedPrice = testSale.ApplyDiscounts(productPrice);
		//System.out.println(discountedPrice);
		assertEquals(discountedPrice, 8.0,0.1);
	}
	
	@Test
	public void test

	@After
	public void tearDown() throws Exception {
	}
	
	//check if it gets person object,if it gets cart correctly,checkif it records the 
}