package point.of.sale;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PosFactory {

	private static PosFactory instance;
	private Properties prop;
	
	private PosFactory() throws IOException {
		prop = new Properties();
		String fileName = "src/main/resources/pos.conf";
		FileInputStream fis = new FileInputStream(fileName);
		prop.load(fis);
		
		System.out.println(prop.getProperty("pos.storage"));
	}
	
	public static PosFactory getInstance() throws IOException {
		if(instance == null) {
			instance = new PosFactory();
		}
		
		return instance;
	}
	
	//configuration logic
	public IDisplay getDisplayAdapter() {
		return new ConsoleDisplay();
	}
	
	public IStorage getStorageAdapter() {
		IStorage storage;
		
		if (prop.getProperty("pos.storage").equals("hash")) {
			
			storage = new HashStorage();
			
		}
		else {
			storage = new ArrayStorage();
		}
		
		storage.put("1", "Milk, 3.99");
		storage.put("2", "Eggs, 4.99");
		
		return storage;
	}
}
