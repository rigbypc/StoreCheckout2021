package point.of.sale;

import org.apache.commons.codec.digest.DigestUtils;

public class ArrayStorageMigration extends HashStorage {

	int readInconsistencies = 0;
	int size = 999;
	String[] array;
	private Object itemCheck;
	
	public ArrayStorageMigration() {
		array = new String[size];
	}

	public void testingOnlyHashPut(String barcode, String item) {
		//write to the hash only
		super.put(barcode, item);
	}
	
	@Override
	public void put(String barcode, String item) {
		//write to the hash
		super.put(barcode, item);
		
		//async write to new array datastore 
		array[Integer.parseInt(barcode)] = item;
	}

	@Override
	public String barcode(String barcode) {
		String expected = super.barcode(barcode);
		
		//asyc reading and checking from the array (low priority)
		String actual = array[Integer.parseInt(barcode)];
		
		if (! expected.equals(actual)) {
			
			array[Integer.parseInt(barcode)] = expected;
			
			violation(barcode, expected, actual);
			
			readInconsistencies ++;
		}
		
		//return expected; //the hash
		return actual; //the array or new datastore
	}
	
	public void resetReadInconsistencies() {
		readInconsistencies = 0;
	}
	
	public int getReadInconsistencies() {
		return readInconsistencies;
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

	public void updateConsistencyCheck() {
		itemCheck = calculateConsistency();
		
	}

	private String calculateConsistency() {
		String items = "";
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				items = hashValue(items + Integer.toBinaryString(i) + array[i]);
			}
		}
		
		return items;
	}
	
	public boolean checkHashConsistency() {
		String actual = calculateConsistency();
		
		System.out.println("expected = " + itemCheck + " vs " + "actual = " + actual);
		
		return itemCheck.equals(actual);
	}
	
	private String hashValue(String value) {
		return DigestUtils.sha1Hex(value).toUpperCase();
	}
	

	

}
