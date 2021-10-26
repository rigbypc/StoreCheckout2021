package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import point.of.sale.*;

class TestDataMigration {

	@Test
	void test() {
		IDisplay display = mock(IDisplay.class);
		
		IStorage storage = new HashStorage();
		storage.put("1", "Milk, 3.99");
		storage.put("2", "Beer, 3.99");
		
		Sale sale = new Sale(display, storage);
		
		sale.scan("1");
		
		verify(display).showLine("Milk, 3.99");
	}

}
