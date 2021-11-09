package point.of.sale;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStorageMigration extends HashStorage {

	int readInconsistencies = 0;
	int size = 999;
	String[] array;
	private Object itemCheck;
	
	private static Logger logger = LogManager.getLogger("migration");
	
	public ArrayStorageMigration() {
		if (StorageToggles.isArrayEnabled) {
			array = new String[size];
			logger.trace("Creating storage array of size " + size);
		}
	}

	public void testingOnlyHashPut(String barcode, String item) {
		
		if (StorageToggles.isHashEnabled && StorageToggles.isUnderTest) { 
			//write to the hash only
			super.put(barcode, item);
			logger.info("Testing only method");
		}
	}
	
	@Override
	public void put(String barcode, String item) {
		
		if (StorageToggles.isHashEnabled) {
			//write to the hash
			super.put(barcode, item);
		}
		
		if (StorageToggles.isArrayEnabled) {
			//async write to new array datastore 
			array[Integer.parseInt(barcode)] = item;
		}
	}

	@Override
	public String barcode(String barcode) {
		
		if (StorageToggles.isArrayEnabled && StorageToggles.isHashEnabled) {
			String expected = super.barcode(barcode);
			
			//asyc reading and checking from the array (low priority)
			String actual = array[Integer.parseInt(barcode)];
			
			if (! expected.equals(actual)) {
				
				array[Integer.parseInt(barcode)] = expected;
				
				violation(barcode, expected, actual);
				
				logger.warn("Read inconsistency");
				readInconsistencies ++;
			}
			
		}
		
		if (StorageToggles.isHashEnabled) {
			return super.barcode(barcode);
		}
		
		if (StorageToggles.isArrayEnabled) {
			return array[Integer.parseInt(barcode)];
		}
		
		return null;
	}
	
	public void resetReadInconsistencies() {
		if (StorageToggles.isArrayEnabled && StorageToggles.isHashEnabled) {
			readInconsistencies = 0;
		}
	}
	
	public int getReadInconsistencies() {
		
		if (StorageToggles.isArrayEnabled && StorageToggles.isHashEnabled) {
			return readInconsistencies;
		}
		
		return -1;
	}
	
	public void forklift() {
		
		if (StorageToggles.isArrayEnabled && StorageToggles.isHashEnabled) {
			//copy over all the data that is in the hash
			for (String barcode : hashMap.keySet()) {
				array[Integer.parseInt(barcode)] = hashMap.get(barcode);
			}
			logger.trace("Forklift complete");
		}
	}
	
	public int checkConsistency() {
		
		if (StorageToggles.isArrayEnabled && StorageToggles.isHashEnabled) {
		
			int inconsistency = 0;
			
			for (String barcode : hashMap.keySet()) {
				String expected = hashMap.get(barcode);
				String actual = array[Integer.parseInt(barcode)];
				
				if (! expected.equals(actual)) {
					//record the inconsistency
					inconsistency ++;
					
					logger.warn("Data inconsistency");
					
					//log it
					violation(barcode, expected, actual);
					
					//correct it in the new datastore
					array[Integer.parseInt(barcode)] = 
							hashMap.get(barcode);
				}
			}
			
			return inconsistency;
		}
		
		return -1;
	}

	private void violation(String barcode, String expected, String actual) {
		logger.info("Consistency Violation!\n" +
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
