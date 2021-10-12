package point.of.sale;

import java.util.ArrayList;

public class Sale {
	
	private IStorage storage;
	private IDisplay display;
	private Interac interac;
	
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
		interac.pay(items);
	}
	
	public void TestingOnlySupersedeInterac(Interac interac) {
		this.interac = interac;
	}
	
	


}
