package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import point.of.sale.ArrayStorageMigration;

class TestConsistencyChecker {

	@Test
	public void testArrayStorageConsistency() {
		ArrayStorageMigration storage = new ArrayStorageMigration();
		storage.put("1", "Milk, 3.99");
		storage.put("2", "Beer, 4.99");
		
		storage.updateConsistencyCheck();
		assertTrue(storage.checkHashConsistency());
		
		//corruption that would cost the store money
		storage.put("2", "Beer, 0.01");
		assertFalse(storage.checkHashConsistency());
	}
	
	@Test
	void test() {
		String password = "1234";
		String expectedHash = "7110eda4d09e062aa5e4a390b0a572ac0d2c0220";
		
		String actualHash = DigestUtils.sha1Hex(password);
		
		assertEquals(expectedHash, actualHash);
	}

}
