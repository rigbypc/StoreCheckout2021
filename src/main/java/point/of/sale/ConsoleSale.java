package point.of.sale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleSale {

	public static void main(String[] args) throws IOException {
		
		
		Sale sale = new Sale(new ConsoleDisplay());
		 // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
 
		
		while(true) {
	        // Printing the read line
			
	        System.out.println("Type in item barcode:");
			
			// Reading data using readLine
	        String line = reader.readLine();
	 	        
	        if (line.equals("Exit")) {
	        	break;
	        }
	        
	        sale.scan(line);
	       
		}

	}

}
