package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import point.of.sale.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSale {

	
	
	@Test
	@Order(2)
	public void testSupersedeInterac() {
		Storage storage = mock(Storage.class);
		Display display = mock(Display.class);
		Interac interac = mock(Interac.class);
		//StoreInfo.TestResetStoreInfo();
				
		Sale sale = new Sale(display, storage);
		sale.TestingOnlySupersedeInterac(interac);
		
		when(storage.barcode("2A")).thenReturn("Milk, 3.99");
		sale.scan("2A");
		
		verify(display).showLine("No Name");
		
		sale.completePurchase();
		verify(interac).pay(any(ArrayList.class));
	}
	
	@Test
	@Order(1)
	public void testSetName() {
		StoreInfo storeInfo = StoreInfo.getInstance();
		storeInfo.setName("Concordia Bookstore");
		assertEquals("Concordia Bookstore", storeInfo.getName());
	}
	
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
	
	@Test
	public void testMockStorage() {
		Storage storage = mock(Storage.class);
		Display display = mock(Display.class);
		ArgumentCaptor<String> barcodeCaptor = 
				ArgumentCaptor.forClass(String.class);
		
		Sale sale = new Sale(display, storage);
		when(storage.barcode("2A")).thenReturn("Milk, 3.99");
		sale.scan("2A");
		verify(storage).barcode(barcodeCaptor.capture());
		verify(display).showLine(barcodeCaptor.getValue());
		verify(display).showLine("Milk, 3.99");
	}

}
