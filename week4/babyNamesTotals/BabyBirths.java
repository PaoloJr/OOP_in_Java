/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalBoyNames = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoyNames += 1;
            }
            else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
        }
        int totalUniqueNames = totalBoyNames + totalGirlNames;
        System.out.println("total births = " + totalBirths);
        System.out.println("total female births = " + totalGirls);
        System.out.println("total male births = " + totalBoys);
        System.out.println("total unique names = " + totalUniqueNames);
        System.out.println("# of female names = " + totalGirlNames);
        System.out.println("# of male names = " + totalBoyNames);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        // FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int rank = 1;
        for (CSVRecord row : parser) {
            long lineNum = parser.getCurrentLineNumber();
            System.out.println("line # " + lineNum);
        }
                
        
        return rank;
    }
    
    public void testGetRank () {
        DirectoryResource dr = new DirectoryResource();
        int fileYear = 2012;
        String testName = "Mason";
        String testGender = "M";
        getRank(fileYear, testName, testGender);
    }
}
