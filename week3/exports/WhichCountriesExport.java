/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String test1 = countryInfo(parser, "Nauru");
        System.out.println(test1);
        
        parser = fr.getCSVParser();
        String test2 = countryInfo(parser, "Germany");
        System.out.println(test2);
        
        parser = fr.getCSVParser();
        String test3 = countryInfo(parser, "Macedonia");
        System.out.println(test3);
        
        parser = fr.getCSVParser();
        System.out.println("Exporters of two products: ");
        listExportersTwoProducts(parser, "fish", "nuts");
        
        parser = fr.getCSVParser();
        int test4 = numberOfExporters(parser, "sugar");
        System.out.println("Count --> " + test4);
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        String notFound = "NOT FOUND";
        for (CSVRecord record : parser) {
            String countryFound = record.get("Country");
            String exportsFound = record.get("Exports");
            String valueFound = record.get("Value (dollars)");
            
            if (countryFound.equals(country)) {
                String itemFound = country + ": " + exportsFound + ": " + valueFound; 
                return itemFound;
            }
        }
        return notFound;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            String country = record.get("Country");
            
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            
            if (value.length() > amount.length()) {
                System.out.println(country + " " + value);
            }
        }
    }
}
