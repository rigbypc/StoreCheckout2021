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
	public void showMethods() {
		Method[] methods = Display.class.getDeclaredMethods();
		
		for (Method method : methods) {
			System.out.println(method);	
		}
		
		MockInvocationHandler mockHandler = new MockInvocationHandler();
		Display display = (Display) Proxy.newProxyInstance(
				//class
				Display.class.getClassLoader(), 
				//methods
				new Class[] {Display.class}, 
				mockHandler);
		
		display.showLine("1A");
		mockHandler.verify("showLine", "1A");
				
	}
	
	@Test
	public void testProxy() {
		
		//do it using mockito
		Storage storage = mock(Storage.class);
		
		//we want to replace the display with our proxy
		//Display display = mock(Display.class);
		MockInvocationHandler mockHandler = new MockInvocationHandler();
		Display display = (Display) Proxy.newProxyInstance(
				//class
				Display.class.getClassLoader(), 
				//methods
				new Class[] {Display.class}, 
				mockHandler);
		
		Sale sale = new Sale(display, storage);
		when(storage.barcode("1A")).thenReturn("Milk, 3.99");
		sale.scan("1A");
		
		//verify(display).showLine("Milk, 3.99");
		mockHandler.verify("showLine", "Milk, 3.99");
	}

}
