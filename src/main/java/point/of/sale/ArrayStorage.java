package point.of.sale;

public class ArrayStorage implements IStorage {

	int size = 999;
	String[] array;
	
	public ArrayStorage() {
		array = new String[size];
	}

	@Override
	public void put(String barcode, String item) {
		array[Integer.parseInt(barcode)] = item;

	}

	@Override
	public String barcode(String barcode) {
		return array[Integer.parseInt(barcode)];
	}

}
