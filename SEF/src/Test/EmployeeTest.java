package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Class.Employee;

public class EmployeeTest {

	//	@BeforeClass
	//	public static void setUpBeforeClass() throws Exception {
	//	}
	//
	//	@AfterClass
	//	public static void tearDownAfterClass() throws Exception {
	//	}

	Employee e1, e2;
	@Before
	public void setUp() throws Exception {
		e1 = new Employee("123", "Daniel", "password1", 2);
		e2 = new Employee("124", "Jake", "password2", 3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getNameTest() {
		assertEquals(e1.GetName(), "Daniel"); 	//Case Sensitive
		assertEquals(e2.GetName(), "Jake"); 	//Case Sensitive

	}

	@Test
	public void setNameTest() {
		e1.SetName("Craig");
		assertEquals(e1.GetName(), "Craig"); 
	}

	@Test
	public void getNumberTest() {
		assertEquals(e1.GetID(), "123"); 
		assertEquals(e2.GetID(), "124"); 
	}
	

	@Test
	public void loginTest() {
		assertEquals(e1.Login("lkalka"), false);
		assertEquals(e1.Login("password1"), true);
	}

	@Test
	public void passwordTest() {
		assertEquals(e1.GetPassword(), "password1");
		assertEquals(e2.GetPassword(), "password2");
		assertEquals(e1.Login("passwordpassword"), false);

		//e1 Forgets password, gets higher up to set password
		e1.SetPassword("defaultpassword");

		assertEquals(e1.GetPassword(), "defaultpassword");
		assertEquals(e1.Login("defaultpassword"), true);
	}
}
