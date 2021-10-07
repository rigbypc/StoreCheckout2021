package point.of.sale;

public class PosFactory {

	private static PosFactory instance;
	
	private PosFactory() {
		
	}
	
	public static PosFactory getInstance() {
		if(instance == null) {
			instance = new PosFactory();
		}
		
		return instance;
	}
	
	//configuration logic
	public IDisplay getDisplayAdapter() {
		return new ConsoleDisplay();
	}
}
