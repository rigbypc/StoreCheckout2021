package mocking.fun;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.*;

import point.of.sale.IDisplay;
import point.of.sale.Sale;
import point.of.sale.IStorage;

public class TestProxy {

	@Test
	public void showMethods() {
		Method[] methods = IDisplay.class.getDeclaredMethods();
		
		for (Method method : methods) {
			System.out.println(method);	
		}
		
		MockInvocationHandler mockHandler = new MockInvocationHandler();
		IDisplay display = (IDisplay) Proxy.newProxyInstance(
				//class
				IDisplay.class.getClassLoader(), 
				//methods
				new Class[] {IDisplay.class}, 
				mockHandler);
		
		display.showLine("1A");
		mockHandler.verify("showLine", "1A");
				
	}
	
	@Test
	public void testProxy() {
		
		//do it using mockito
		IStorage storage = mock(IStorage.class);
		
		//we want to replace the display with our proxy
		//Display display = mock(Display.class);
		MockInvocationHandler mockHandler = new MockInvocationHandler();
		IDisplay display = (IDisplay) Proxy.newProxyInstance(
				//class
				IDisplay.class.getClassLoader(), 
				//methods
				new Class[] {IDisplay.class}, 
				mockHandler);
		
		Sale sale = new Sale(display, storage);
		when(storage.barcode("1A")).thenReturn("Milk, 3.99");
		sale.scan("1A");
		
		//verify(display).showLine("Milk, 3.99");
		mockHandler.verify("showLine", "Milk, 3.99");
	}

}
