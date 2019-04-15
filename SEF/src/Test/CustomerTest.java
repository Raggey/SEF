package Test;
import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	protected void setUp() throws Exception {
		Customer hi = new Customer(123, Gloria, 3000);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
