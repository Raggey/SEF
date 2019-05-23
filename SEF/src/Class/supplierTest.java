package Class;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Class.supplier;

public class supplierTest {

	supplier supplier1;
	
	@Before
	public void setUp(){
		supplier1 = new supplier(1,"Supplier 1");
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetID() {
		supplier1.GetSupplierID();
		assertEquals(1,supplier1.GetSupplierID() );
	}
	
	@Test
	public void testGetName() {
		supplier1.GetSupplierName();
		assertEquals("Supplier 1",supplier1.GetSupplierName() );
	}

		
}



