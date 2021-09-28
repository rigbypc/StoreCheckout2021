package point.of.sale;


public class Sale {
	
	Storage storage;
	Display display;
	
	public Sale () {
		
		//the display terminal is called ArtR56
		display = new ArtR56Display();
		init(display);
		
	}
	
	public Sale(Display display) {
		init(display);
	}
	
	private void init(Display display) {
		
		this.display = display;
		
		//Storage, add the items in the store
		storage = new HashStorage();
		storage.put("1A", "Milk, 3.99");
		storage.put("2A", "Bread, 4.99");
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
