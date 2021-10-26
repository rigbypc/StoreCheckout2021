package flaky.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import io.github.artsok.RepeatedIfExceptionsTest;

class Repeater {

	private static int runs = 0;
	
	//@RepeatedIfExceptionsTest(repeats = 2)
    void reRunTest() {
		if (runs <= 1) { 
			runs ++;
			assertTrue(false);
		}
		
		assertTrue(true);
    }
	
	//Google 2 repeats strategy, this test needs to fail three times in a row to be considered a failure
	//@RepeatedIfExceptionsTest(repeats = 2)
    //@Test
	void asynchWait25Test() {
		assertTrue(Flaky.asyncWait25());
	}
	
	//@RepeatedTest(100)
	//figured out the distribution for reruns.
	@RepeatedIfExceptionsTest(repeats = 4, minSuccess = 3)
	void synchWait75Test() {
		assertTrue(Flaky.asyncWait75());
	}
}
