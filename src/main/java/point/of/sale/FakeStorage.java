package point.of.sale;

public class FakeStorage implements Storage {

	public FakeStorage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void put(String barcode, String item) {
		// TODO Auto-generated method stub

	}

	@Override
	public String barcode(String barcode) {
		return "Milk, 3.99";
	}

}
