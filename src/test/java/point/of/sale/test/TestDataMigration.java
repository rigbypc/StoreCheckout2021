package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import point.of.sale.*;

class TestDataMigration {

	@Test
	void test() {
		IDisplay display = mock(IDisplay.class);
		
		ArrayStorageMigration storage = new ArrayStorageMigration();
		storage.put("1", "Milk, 3.99");
		storage.put("2", "Beer, 3.99");
		
		//Forklift (mass migration)

		storage.forklift();
		
		//Consistency Checking (includes incremental replication)

		assertEquals(0, storage.checkConsistency());
		
		storage.put("3", "aklsfjselkhfouhwsef, 4.99");
		assertEquals(0, storage.checkConsistency());
		//fixes the inconsistency, so next check should have 0 inconsistencies		
		assertEquals(0, storage.checkConsistency());
		
		//Shadow writes (writes to old and new datastore)

		//Shadow reads (checks that old and new datastores return the same value

		//Read and write from new datastore (get rid of old datastore)

		
		Sale sale = new Sale(display, storage);
		
		sale.scan("1");
		
		verify(display).showLine("Milk, 3.99");
	}

}
