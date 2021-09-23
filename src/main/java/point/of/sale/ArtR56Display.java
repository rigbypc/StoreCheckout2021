package point.of.sale;

public class ArtR56Display implements Display{
	ArtR56 artR56;
	
	public ArtR56Display() {
		artR56 = new ArtR56();
	}
	
	public void showLine(String barcode) {
		artR56.showLine(barcode);
	}
}
