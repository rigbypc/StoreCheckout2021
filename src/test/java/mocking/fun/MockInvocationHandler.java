package mocking.fun;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;


public class MockInvocationHandler implements InvocationHandler {

	HashMap<String, String> verifyMap = new HashMap<String, String>();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println(method.getName());
		
		for (Object arg : args) {
			System.out.println(arg.toString());
			verifyMap.put(method.getName(), arg.toString());
		}
		
		return null;
	}
	
	public void verify(String method_expected, String arg_expected) {
		//list of arguments for each method, and check that list (exercise)
		//storing the last interaction with the method
		assertEquals(arg_expected, verifyMap.get(method_expected));
	}
	
}