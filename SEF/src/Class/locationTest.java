package Class;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Class.location;

public class locationTest {

	location locationMap;
	
	@Before
	public void setUp(){
		locationMap = new location();
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLocationTest() {
		locationMap.addNewLocation("NoTown",1234);
		locationMap.addNewLocation("NewTown",4321);
		locationMap.printAllLocations();
		assertEquals(true,true);
	}
	
	@Test
	public void getLocationPostcodeTest() {
		locationMap.addNewLocation("NoTown",1234);
		assertEquals(1234,locationMap.getLocationByName("NoTown") );
	}

		
}



