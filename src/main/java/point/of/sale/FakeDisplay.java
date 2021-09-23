package point.of.sale;

public class FakeDisplay implements Display {

	String lastLine;
	
	public FakeDisplay() {
		lastLine = "";
	}

	@Override
	public void showLine(String line) {
		this.lastLine = line;

	}

	public String getLastLine() {
		return lastLine;
	}

}
