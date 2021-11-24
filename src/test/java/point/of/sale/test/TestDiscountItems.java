package point.of.sale.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import point.of.sale.*;

class TestDiscountItems {

	IDisplay display;
	IStorage storage;
	
	@BeforeEach
	void before() {
	
			display = mock(IDisplay.class);
			storage = mock(IStorage.class);
			
			when(storage.barcode("1")).thenReturn("Milk, 3.99");
			when(storage.barcode("2")).thenReturn("Beer, 9.99");
			
	}
	
	//@Test
	void testRollbackDiscount() {
		
		//feature is dark, nobody can get to it
		StorageToggles.isDiscountAllItems = false;
		
		Sale sale = new Sale(display, storage);
		sale.scan("2");
		sale.completePurchase();
		
		verify(display).showLine("Beer, 9.99");
		verify(display, never()).showLine("Discount applied");
		
		//turn on the feature to apply a discount
		StorageToggles.isDiscountAllItems = true;
		
		sale.completePurchase();
		verify(display).showLine("Discount applied");
		
		//turn off the feature, if there is a problem discovered!
		StorageToggles.isDiscountAllItems = false;
		
		sale.completePurchase();
		verify(display, times(1)).showLine("Discount applied");
		
		
	}
	
	@Test
	void testRandomDiscount() {
		AssignRandomDiscount rnd = new AssignRandomDiscount();
		
		int interations = 10;
		
		for (int i = 0; i < interations; i++) {
			//randomly assign a proportion of users to get a discount
			StorageToggles.isDiscountAllItems = rnd.getDiscount(30);
			
			Sale sale = new Sale(display, storage);
			sale.scan("2");
			sale.completePurchase();
		}
	}

}
