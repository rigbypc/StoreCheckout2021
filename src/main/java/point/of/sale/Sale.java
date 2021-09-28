package point.of.sale;


public class Sale {
	
	Storage storage;
	Display display;
	
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
		
		
	}
	
	public void scan(String barcode) {
		//display the barcode
		display.showLine(barcode);
		
		//lookup barcode in postgres and get item
		String item = storage.barcode(barcode);
		
		//display the item
		display.showLine(item);
		
	}

}
