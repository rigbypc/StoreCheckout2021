package point.of.sale;

public class ArrayStorageMigration extends HashStorage {

	int size = 999;
	String[] array;
	
	public ArrayStorageMigration() {
		array = new String[size];
	}

	@Override
	public void put(String barcode, String item) {
		// TODO Auto-generated method stub
		super.put(barcode, item);
	}

	@Override
	public String barcode(String barcode) {
		// TODO Auto-generated method stub
		return super.barcode(barcode);
	}
	
	public void forklift() {
		//copy over all the data that is in the hash
		for (String barcode : hashMap.keySet()) {
			array[Integer.parseInt(barcode)] = hashMap.get(barcode);
		}
	}
	
	public int checkConsistency() {
		int inconsistency = 0;
		
		for (String barcode : hashMap.keySet()) {
			String expected = hashMap.get(barcode);
			String actual = array[Integer.parseInt(barcode)];
			
			if (! expected.equals(actual)) {
				//record the inconsistency
				inconsistency ++;
				//log it
				violation(barcode, expected, actual);
				
				//correct it in the new datastore
				array[Integer.parseInt(barcode)] = 
						hashMap.get(barcode);
			}
		}
		
		return inconsistency;
	}

	private void violation(String barcode, String expected, String actual) {
		System.out.println("Consistency Violation!\n" +
						"barcode = " + barcode
						+ "\n\t expected = " + expected
						+ "\n\t actual = " + actual);
		
	}

}
