package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import point.of.sale.*;

public class TestSale {

	@Test
	public void testScanFake() {
		FakeDisplay fakeDisplay = new FakeDisplay();
		FakeStorage fakeStorage = new FakeStorage();
		Sale sale = new Sale(fakeDisplay, fakeStorage);
		sale.scan("XYZ");
		assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
		
		//fail because a fake is a kludge and only stores last thing displayed
		//assertEquals("1A", fakeDisplay.getLastLine());
	}
	
	@Test
	public void testScanMock() {
		Display display = mock(Display.class);
		Sale sale = new Sale(display);
		sale.scan("1A");
		verify(display).showLine("Milk, 3.99");
		verify(display).showLine("1A");
	}
	
	@Test
	public void testScanMockOrder() {
		Display display = mock(Display.class);
		Sale sale = new Sale(display);
		sale.scan("1A");
		InOrder inOrder = inOrder(display);
		inOrder.verify(display).showLine("1A");
		inOrder.verify(display).showLine("Milk, 3.99");
	}
	
	//@Test
	public void testMockStorage() {
		Storage storage = mock(Storage.class);
		Display display = mock(Display.class);
		Sale sale = new Sale(display, storage);
		sale.scan("1A");
		verify(display).showLine("Milk, 3.99");
		verify(display).showLine("1A");
	}

}
