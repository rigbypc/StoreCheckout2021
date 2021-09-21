package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import point.of.sale.*;

public class TestSale {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Sale sale = new Sale();
		sale.scan("1A");
		//assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
		fail("Not yet implemented");
	}

}
