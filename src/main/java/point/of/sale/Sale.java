package point.of.sale;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Sale {
	
	private IStorage storage;
	private IDisplay display;
	private Interac interac;
	
	private static Logger analytics = LogManager.getLogger("Analytics");
	
	ArrayList<String> items = new ArrayList<>();
	
	public Sale () {
		
		//the display terminal is called ArtR56
		display = new ArtR56DisplayAdapter();
		//Storage, add the items in the store
		storage = new HashStorage();
		storage.put("1A", "Milk, 3.99");
		storage.put("2A", "Bread, 4.99");
		init(display, storage);
		
	}
	
	public Sale(IDisplay display) {
		//Storage, add the items in the store
		storage = new HashStorage();
		storage.put("1A", "Milk, 3.99");
		storage.put("2A", "Bread, 4.99");
		
		init(display, storage);
	}
	
	public Sale(IDisplay display, IStorage storage) {
		init(display, storage);
	}
	
	private void init(IDisplay display, IStorage storage) {
		
		this.display = display;
		this.storage = storage;
		this.interac = new Interac(12);
		
		display.showLine(StoreInfo.getInstance().getName());
		
		
	}
	
	public void scan(String barcode) {
		//display the barcode
		display.showLine(barcode);
		
		//lookup barcode in postgres and get item
		String item = storage.barcode(barcode);
		
		//display the item
		display.showLine(item);
		
		items.add(item);
		
	}
	
	public void completePurchase() {
		
		if (StorageToggles.isDiscountAllItems == true) {
			analytics.info("Discount applied, " + items.size());
			//todo: actually apply the discount
			
			display.showLine("Discount applied");
		}
		else {
			analytics.info("No Discount, " + items.size());
		}
		
		interac.pay(items);
	}
	
	public void TestingOnlySupersedeInterac(Interac interac) {
		this.interac = interac;
	}
	
	


}
