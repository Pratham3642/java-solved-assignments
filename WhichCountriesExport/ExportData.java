
/**
 * Write a description of ExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class ExportData {
    public String countryInfo(CSVParser parser,String country){
        for(CSVRecord rec : parser){
            String name = rec.get("Country");
            if(name.equalsIgnoreCase(country)){
                String exports = rec.get("Exports");
                String value = rec.get("Value (dollars)");
                return name + ": " + exports + " :" + value;
            }
        }
        return "Not Found";
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord rec : parser){
            String exports = rec.get("Exports");
            if(exports.contains(exportItem1)&&exports.contains(exportItem2)){
                System.out.println(rec.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count=0;
        for(CSVRecord rec : parser){
            String export = rec.get("Exports");
            if(export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount) {
    for (CSVRecord record : parser) {
        String value = record.get("Value (dollars)");
        if (value.length() > amount.length()) {
            System.out.println(record.get("Country") + " " + value);
        }
    }
}


    public void tester(){
        FileResource fr = new FileResource(); // Choose exports_small.csv or exportdata.csv
        CSVParser parser = fr.getCSVParser();

        // Test countryInfo
        System.out.println("Country Info:");
        System.out.println(countryInfo(fr.getCSVParser(), "Nauru"));

        // Test listExportersTwoProducts
        System.out.println("\nCountries exporting both gold and diamonds:");
        listExportersTwoProducts(fr.getCSVParser(), "cotton", "flowers");

        // Test numberOfExporters
        System.out.println("\nNumber of exporters of gold:");
        System.out.println(numberOfExporters(fr.getCSVParser(), "cocoa"));

        // Test bigExporters
        System.out.println("\nBig exporters (value > $999,999,999):");
        bigExporters(fr.getCSVParser(), "$999,999,999");
    }
    public static void main(String[] args) {
        ExportData ed = new ExportData();
        ed.tester();
    }
}
