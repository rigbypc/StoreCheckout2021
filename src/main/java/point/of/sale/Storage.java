package point.of.sale;

public interface Storage {

	void put(String barcode, String item);

	String barcode(String barcode);

}