import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class AverageRankCalculator {

    public double getAverageRank(String name, String gender) {
        FileResource fr = new FileResource("AllYearWithYear.csv");

        // Map year -> list of records (same gender)
        Map<Integer, List<CSVRecord>> yearToRecords = new HashMap<>();

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String recName = rec.get(0);
            String recGender = rec.get(1);
            int births = Integer.parseInt(rec.get(2));
            int year = Integer.parseInt(rec.get(3));

            if (recGender.equals(gender)) {
                yearToRecords.putIfAbsent(year, new ArrayList<>());
                yearToRecords.get(year).add(rec);
            }
        }

        double totalRank = 0.0;
        int yearCount = 0;

        for (int year : yearToRecords.keySet()) {
            List<CSVRecord> records = yearToRecords.get(year);

            // Sort descending by number of births
            records.sort((r1, r2) -> Integer.parseInt(r2.get(2)) - Integer.parseInt(r1.get(2)));

            int rank = 1;
            boolean found = false;

            for (CSVRecord rec : records) {
                String recName = rec.get(0);
                if (recName.equalsIgnoreCase(name)) {
                    totalRank += rank;
                    found = true;
                    break;
                }
                rank++;
            }

            if (found) {
                yearCount++;
            }
        }

        if (yearCount == 0) {
            return -1.0; // Name not found in any year
        }

        return totalRank / yearCount;
    }

    public void test() {
        double avgSusan = getAverageRank("Susan", "F");
        System.out.printf("Average rank of Susan (F): %.2f\n", avgSusan);

        double avgRobert = getAverageRank("Robert", "M");
        System.out.printf("Average rank of Robert (M): %.2f\n", avgRobert);
    }

    public static void main(String[] args) {
        AverageRankCalculator obj = new AverageRankCalculator();
        obj.test();
    }
}
