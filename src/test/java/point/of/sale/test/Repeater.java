package point.of.sale.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import io.github.artsok.RepeatedIfExceptionsTest;

class Repeater {

	private static int runs = 0;
	
	@RepeatedIfExceptionsTest(repeats = 2)
    void reRunTest() {
		if (runs <= 1) { 
			runs ++;
			assertTrue(false);
		}
		
		assertTrue(true);
    }
}
