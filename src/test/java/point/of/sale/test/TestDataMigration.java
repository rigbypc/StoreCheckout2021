package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import point.of.sale.*;

class TestDataMigration {

	@Test
	void test() {
		IDisplay display = mock(IDisplay.class);
		
		IStorage storage = new ArrayStorageMigration();
		storage.put("1", "Milk, 3.99");
		storage.put("2", "Beer, 3.99");
		
		//Forklift (mass migration)

		//Consistency Checking (includes incremental replication)

		//Shadow writes (writes to old and new datastore)

		//Shadow reads (checks that old and new datastores return the same value

		//Read and write from new datastore (get rid of old datastore)

		
		Sale sale = new Sale(display, storage);
		
		sale.scan("1");
		
		verify(display).showLine("Milk, 3.99");
	}

}
