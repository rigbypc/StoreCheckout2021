package point.of.sale.test;

import static org.mockito.Mockito.*;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import point.of.sale.*;

public class FuzzTester {
	@Test
	public void testSale() {
		
		int numIterations = 10;
		for (int i = 0; i < numIterations; i++) {
			
	
			IDisplay display = mock(IDisplay.class); 
			
			ArrayStorage storage =  new ArrayStorage();
			storage.put("1", "Milk, 3.99");
			storage.put("2", "Beer, 9.99");
			storage.put("3", "Bread, 10.99");
			
			Integer rndBarcode = 
					ThreadLocalRandom.current().nextInt(1,3+1);
			
			Sale sale = new Sale(display, storage);
			
			sale.scan(rndBarcode.toString());
			
			//verify(display, times(3)).showLine(any(String.class));
			
			verify(display).showLine("No Name");
			verify(display).showLine(rndBarcode.toString());
			verify(display).showLine(
					storage.barcode(
							rndBarcode.toString()));
			
		}
	}

}
