package point.of.sale;

public interface IStorage {

	void put(String barcode, String item);

	String barcode(String barcode);

}