package point.of.sale.test;

import java.util.concurrent.ThreadLocalRandom;

public class Flaky {
	private static Integer probability = 25;
	
	public static boolean asyncWait() {
		Integer waitTime = 
				ThreadLocalRandom.current().nextInt(1,100+1);
		System.out.println(waitTime);
		if (waitTime <= probability) {
			return true;
		}
		
		return false;
	}
	
	public static boolean asyncWait25() {
		Integer waitTime = 
				ThreadLocalRandom.current().nextInt(1,100+1);
		System.out.println(waitTime);
		if (waitTime <= 25) {
			return true;
		}
		
		return false;
	}
	
	public static boolean asyncWait75() {
		Integer waitTime = 
				ThreadLocalRandom.current().nextInt(1,100+1);
		System.out.println(waitTime);
		if (waitTime <= 75) {
			return true;
		}
		
		return false;
	}
	
	public static boolean timeEven() {
		long totalMilliseconds = System.currentTimeMillis();
		//even
		System.out.println(totalMilliseconds);
		return totalMilliseconds % 2 == 0;
	}
}
