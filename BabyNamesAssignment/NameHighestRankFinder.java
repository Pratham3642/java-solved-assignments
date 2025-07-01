import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NameHighestRankFinder {

    public int yearOfHighestRank(String name, String gender) {
        FileResource fr = new FileResource("AllYearWithYear.csv");

        int highestRank = Integer.MAX_VALUE;
        int yearOfHighest = -1;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String recName = rec.get(0);
            String recGender = rec.get(1);
            int recRank = Integer.parseInt(rec.get(2));
            int recYear = Integer.parseInt(rec.get(3));

            if (recName.equals(name) && recGender.equals(gender)) {
                if (recRank < highestRank) {
                    highestRank = recRank;
                    yearOfHighest = recYear;
                } else if (recRank == highestRank && recYear < yearOfHighest) {
                    // If same rank, choose the earliest year
                    yearOfHighest = recYear;
                }
            }
        }

        return yearOfHighest;
    }

    public void testGenevieve() {
        int year = yearOfHighestRank("Genevieve", "F");
        if (year == -1) {
            System.out.println("Name 'Genevieve' not found in dataset.");
        } else {
            System.out.println("Year when 'Genevieve' (F) had the highest rank: " + year);
        }
    }

    public static void main(String[] args) {
        NameHighestRankFinder obj = new NameHighestRankFinder();
        obj.testGenevieve();
    }
}
