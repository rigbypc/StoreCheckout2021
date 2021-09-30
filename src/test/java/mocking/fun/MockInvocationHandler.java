package mocking.fun;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;


public class MockInvocationHandler implements InvocationHandler {

	HashMap<String, String> verifyMap = new HashMap<String, String>();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return null;
	}
	
}