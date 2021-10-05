package point.of.sale;

import java.util.ArrayList;

public class Sale {
	
	private Storage storage;
	private Display display;
	private Interac interac;
	
	ArrayList<String> items = new ArrayList<>();
	
	public Sale () {
		
		//the display terminal is called ArtR56
		display = new ArtR56Display();
		//Storage, add the items in the store
		storage = new HashStorage();
		storage.put("1A", "Milk, 3.99");
		storage.put("2A", "Bread, 4.99");
		init(display, storage);
		
	}
	
	public Sale(Display display) {
		//Storage, add the items in the store
		storage = new HashStorage();
		storage.put("1A", "Milk, 3.99");
		storage.put("2A", "Bread, 4.99");
		
		init(display, storage);
	}
	
	public Sale(Display display, Storage storage) {
		init(display, storage);
	}
	
	private void init(Display display, Storage storage) {
		
		this.display = display;
		this.storage = storage;
		this.interac = new Interac(12);
		
		
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


}
