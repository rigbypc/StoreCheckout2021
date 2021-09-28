package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class MockingFun {
	
	
	@Test
	public void testMock() {
	
		Stack<String> stack = mock(Stack.class);
		stack.add("one");
		stack.add("two");
		verify(stack).add("one");
		verify(stack).add("two");
		when(stack.pop()).thenReturn("two");
		assertEquals("two", stack.pop());
		assertEquals("one", stack.pop());
	}
	

}
