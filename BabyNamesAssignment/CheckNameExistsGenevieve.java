import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class CheckNameExistsGenevieve {

    public void checkName(String name, String gender) {
        FileResource fr = new FileResource("AllYearWithYear.csv");

        // Map of year -> gender -> List of births for that gender-year
        Map<Integer, List<Integer>> yearGenderBirths = new HashMap<>();
        boolean found = false;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String recName = rec.get(0);
            String recGender = rec.get(1);
            int births = Integer.parseInt(rec.get(2));
            int year = Integer.parseInt(rec.get(3));

            // Check if name matches
            if (recName.equals(name) && recGender.equals(gender)) {
                System.out.println("Found " + name + " (" + gender + ") in year " + year + " with births: " + births);
                found = true;
            }

            // Process only matching gender for highest births
            if (recGender.equals(gender)) {
                int key = year;
                if (!yearGenderBirths.containsKey(key)) {
                    yearGenderBirths.put(key, new ArrayList<>());
                }
                yearGenderBirths.get(key).add(births);
            }
        }

        // Print highest births per year for the gender
        for (int year : yearGenderBirths.keySet()) {
            List<Integer> birthsList = yearGenderBirths.get(year);
            int maxBirths = Collections.max(birthsList);
            System.out.println("Highest births in year " + year + " for gender " + gender + " is: " + maxBirths);
        }

        if (!found) {
            System.out.println(name + " (" + gender + ") not found in any year.");
        }
    }

    public static void main(String[] args) {
        CheckNameExistsGenevieve obj = new CheckNameExistsGenevieve();
        obj.checkName("Genevieve", "F");
    }
}
