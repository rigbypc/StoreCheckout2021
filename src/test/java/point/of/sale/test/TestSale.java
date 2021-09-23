package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import point.of.sale.*;

public class TestSale {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		FakeDisplay fakeDisplay = new FakeDisplay();
		Sale sale = new Sale();
		sale.scan("1A");
		assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
	}

}
