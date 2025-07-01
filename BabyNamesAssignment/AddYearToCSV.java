import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AddYearToCSV {

    public void addYear() throws IOException {
        FileResource fr = new FileResource("AllYear.csv");
        PrintWriter pw = new PrintWriter("AllYearWithYear.csv");
        
        int year = 1880;
        String prevGender = "";
        
        // Write header
        pw.println("Name,Gender,Rank,Year");

        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name = rec.get(0);
            String gender = rec.get(1);
            String rank = rec.get(2);

            // If previous gender is M and current is F, year increases
            if (prevGender.equals("M") && gender.equals("F")) {
                year++;
            }

            prevGender = gender;

            // Write with year added
            pw.println(name + "," + gender + "," + rank + "," + year);
        }
        pw.close();
        System.out.println("File created: AllYearWithYear.csv");
    }

    public static void main(String[] args) throws IOException {
        AddYearToCSV obj = new AddYearToCSV();
        obj.addYear();
    }
}
