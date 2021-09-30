package mocking.fun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class MockingFun {
	
	
	@Test
	public void testMock() {
	
		Stack<String> stack = mock(Stack.class);
		stack.add("one");
		stack.add("two");
		InOrder inOrder = inOrder(stack);
		inOrder.verify(stack).add("one");
		inOrder.verify(stack).add("two");
		when(stack.pop()).thenReturn("two","one",null);
		when(stack.isEmpty()).thenReturn(true);
		assertEquals("two", stack.pop());
		assertEquals("one", stack.pop());
		assertEquals(true, stack.isEmpty());
		assertEquals(null, stack.pop());
	}
	
	@Test
	public void testSpy() {
	
		Stack<String> actualStack = new Stack<String>(); 
		Stack<String> stack = spy(actualStack);
		stack.add("one");
		stack.add("two");
		//fails because the mock/stub has priority over the actual object
		//when(stack.pop()).thenReturn("one");
		//when(stack.size()).thenReturn(100);
		assertEquals("two", stack.pop());
		assertEquals("one", stack.pop());
		//avoid spying as much as possible (last resort)
		//assertEquals(true, stack.isEmpty());
		
		
	
	}
	

}
