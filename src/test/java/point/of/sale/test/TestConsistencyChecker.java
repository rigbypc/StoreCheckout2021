package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

class TestConsistencyChecker {

	@Test
	void test() {
		String password = "1234";
		String expectedHash = "7110eda4d09e062aa5e4a390b0a572ac0d2c0220";
		
		String actualHash = DigestUtils.sha1Hex(password);
		
		assertEquals(expectedHash, actualHash);
	}

}
