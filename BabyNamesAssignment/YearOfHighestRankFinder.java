import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class YearOfHighestRankFinder {

    public int yearOfHighestRank(String name, String gender) {
        FileResource fr = new FileResource("AllYearWithYear.csv");

        Map<Integer, List<CSVRecord>> yearToRecords = new HashMap<>();

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String recGender = rec.get(1);
            int year = Integer.parseInt(rec.get(3));

            if (recGender.equals(gender)) {
                yearToRecords.putIfAbsent(year, new ArrayList<>());
                yearToRecords.get(year).add(rec);
            }
        }

        int bestYear = -1;
        int bestRank = Integer.MAX_VALUE;

        for (int year : yearToRecords.keySet()) {
            List<CSVRecord> records = yearToRecords.get(year);
            // Sort records descending by births (for rank)
            records.sort((r1, r2) -> Integer.parseInt(r2.get(2)) - Integer.parseInt(r1.get(2)));

            int rank = 1;
            boolean found = false;

            for (CSVRecord rec : records) {
                String recName = rec.get(0);
                if (recName.equals(name)) {
                    found = true;
                    break;
                }
                rank++;
            }

            if (found && rank < bestRank) {
                bestRank = rank;
                bestYear = year;
            } else if (found && rank == bestRank && year < bestYear) {
                // If tied rank, choose the earlier year
                bestYear = year;
            }
        }

        if (bestYear == -1) {
            return -1;  // Name not found in any year
        }

        return bestYear;
    }

    public void test() {
        int genevieveYear = yearOfHighestRank("Genevieve", "F");
        if (genevieveYear == -1) {
            System.out.println("Genevieve (F) not found in any year.");
        } else {
            System.out.println("Year of highest rank for Genevieve (F): " + genevieveYear);
        }

        int michYear = yearOfHighestRank("Mich", "M");
        if (michYear == -1) {
            System.out.println("Mich (M) not found in any year.");
        } else {
            System.out.println("Year of highest rank for Mich (M): " + michYear);
        }
    }

    public static void main(String[] args) {
        YearOfHighestRankFinder obj = new YearOfHighestRankFinder();
        obj.test();
    }
}
