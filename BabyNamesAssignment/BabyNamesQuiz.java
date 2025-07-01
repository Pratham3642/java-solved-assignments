import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNamesQuiz {

    // ✅ Method 1 - Count girls' names in a file
    public int countGirlsNames(String filename) {
        FileResource fr = new FileResource(filename);
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("F")) {
                count++;
            }
        }
        return count;
    }

    // ✅ Method 2 - Count boys' names in a file
    public int countBoysNames(String filename) {
        FileResource fr = new FileResource(filename);
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                count++;
            }
        }
        return count;
    }

    // ✅ Method 3 & 4 - Get rank of name
    public int getRank(String filename, String name, String gender) {
        FileResource fr = new FileResource(filename);
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    // ✅ Method 5 & 6 - Get name at rank
    public String getName(String filename, int rank, String gender) {
        FileResource fr = new FileResource(filename);
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    // ✅ Method 7 & 8 - What is name in new year
    public String whatIsNameInYear(String name, String gender, String yearOriginalFile, String yearNewFile) {
        int rank = getRank(yearOriginalFile, name, gender);
        if (rank == -1) return "NO NAME";
        return getName(yearNewFile, rank, gender);
    }

    // ✅ Method 9 & 10 - Year of highest rank
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int bestYear = -1;
        int bestRank = Integer.MAX_VALUE;
        for (File f : dr.selectedFiles()) {
            String filename = f.getName();
            int year = Integer.parseInt(filename.substring(3,7));

            int rank = getRank(f.getAbsolutePath(), name, gender);
            if (rank != -1 && rank < bestRank) {
                bestRank = rank;
                bestYear = year;
            }
        }
        return bestYear;
    }

    // ✅ Method 11 & 12 - Average rank
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            int rank = getRank(f.getAbsolutePath(), name, gender);
            if (rank != -1) {
                totalRank += rank;
                count++;
            }
        }
        if (count == 0) return -1.0;
        return (double) totalRank / count;
    }

    // ✅ Method 13 & 14 - Total births ranked higher
    public int getTotalBirthsRankedHigher(String filename, String name, String gender) {
        FileResource fr = new FileResource(filename);
        int total = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    break;
                } else {
                    total += Integer.parseInt(rec.get(2));
                }
            }
        }
        return total;
    }

    // ✅ Test all
    public void test() {
        // Q1
        System.out.println("Q1 - Girls in 1900: " + countGirlsNames("yob1900.csv"));
        
        // Q2
        System.out.println("Q2 - Boys in 1905: " + countBoysNames("yob1905.csv"));
        
        // Q3
        System.out.println("Q3 - Rank of Emily (1960, F): " + getRank("yob1960.csv", "Emily", "F"));
        
        // Q4
        System.out.println("Q4 - Rank of Frank (1971, M): " + getRank("yob1971.csv", "Frank", "M"));
        
        // Q5
        System.out.println("Q5 - Name of rank 350 (1980, F): " + getName("yob1980.csv", 350, "F"));
        
        // Q6
        System.out.println("Q6 - Name of rank 450 (1982, M): " + getName("yob1982.csv", 450, "M"));
        
        // Q7
        String newNameSusan = whatIsNameInYear("Susan", "F", "yob1972.csv", "yob2014.csv");
        System.out.println("Q7 - Susan in 2014: " + newNameSusan);
        
        // Q8
        String newNameOwen = whatIsNameInYear("Owen", "M", "yob1974.csv", "yob2014.csv");
        System.out.println("Q8 - Owen in 2014: " + newNameOwen);
        
        // Q9
        int bestYearGenevieve = yearOfHighestRank("Genevieve", "F");
        System.out.println("Q9 - Year of highest rank for Genevieve (F): " + bestYearGenevieve);
        
        // Q10
        int bestYearMich = yearOfHighestRank("Mich", "M");
        System.out.println("Q10 - Year of highest rank for Mich (M): " + bestYearMich);
        
        // Q11
        double avgSusan = getAverageRank("Susan", "F");
        System.out.printf("Q11 - Average rank of Susan (F): %.2f\n", avgSusan);
        
        // Q12
        double avgRobert = getAverageRank("Robert", "M");
        System.out.printf("Q12 - Average rank of Robert (M): %.2f\n", avgRobert);
        
        // Q13
        int totalHigherEmily = getTotalBirthsRankedHigher("yob1990.csv", "Emily", "F");
        System.out.println("Q13 - Total girls born in 1990 with names ranked higher than Emily: " + totalHigherEmily);
        
        // Q14
        int totalHigherDrew = getTotalBirthsRankedHigher("yob1990.csv", "Drew", "M");
        System.out.println("Q14 - Total boys born in 1990 with names ranked higher than Drew: " + totalHigherDrew);
    }

    public static void main(String[] args) {
        BabyNamesQuiz bq = new BabyNamesQuiz();
        bq.test();
    }
}
