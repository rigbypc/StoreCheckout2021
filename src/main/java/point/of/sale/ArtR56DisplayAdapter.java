package point.of.sale;

public class ArtR56DisplayAdapter implements IDisplay{
	ArtR56 artR56;
	
	public ArtR56DisplayAdapter() {
		artR56 = new ArtR56();
	}
	
	public void showLine(String line) {
		artR56.showLine(line);
	}
}
