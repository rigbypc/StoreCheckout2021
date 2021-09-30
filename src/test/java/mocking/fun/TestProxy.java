package mocking.fun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.*;

import point.of.sale.Display;
import point.of.sale.Sale;
import point.of.sale.Storage;

public class TestProxy {

	@Test
	public void testProxy() {
		
		//do it using mockito
		Storage storage = mock(Storage.class);
		
		//we want to replace the display with our proxy
		Display display = mock(Display.class);
		Sale sale = new Sale(display, storage);
		when(storage.barcode("1A")).thenReturn("Milk, 3.99");
		sale.scan("1A");
		verify(display).showLine("1A");
		verify(display).showLine("Milk, 3.99");
	}

}
