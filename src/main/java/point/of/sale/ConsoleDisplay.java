package point.of.sale;

public class ConsoleDisplay implements IDisplay {

	public ConsoleDisplay() {
		
	}

	@Override
	public void showLine(String line) {
		System.out.println(line);

	}

}
