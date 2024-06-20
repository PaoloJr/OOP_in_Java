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
    
    public int getRank(int year, String name, String gender, String fileName) {
        // String file = "C:\\Users\\pjang\\Documents\\GitHub\\OOP-in-Java\\week4\\us_babynames_small\\yob" + year + "short.csv";
        // FileResource fr = new FileResource(file);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        boolean nameFoundWithinGender = false;
        int rank = 0;
    
        for (CSVRecord row : parser) {
            if(row.get(1).equals(gender)) {
                rank++; // if gender is found, increment by 1
                if(row.get(0).equals(name)) {
                    nameFoundWithinGender = true; // if name, within the gender is found, switch boolean value
                    break; // stop when executed
                }
            }    
        }
        if (nameFoundWithinGender == true) {
            return rank;
        } else {
            return -1;
        }
    }
    
    public void testGetRank () {
        String file = "C:\\Users\\pjang\\Documents\\GitHub\\OOP-in-Java\\week4\\us_babynames_small\\yob2012short.csv";
        int fileYear = 2012;
        String testName = "Sophia";
        String testGender = "F";
        int rank = getRank(fileYear, testName, testGender, file);
        System.out.println("Rank for " + testName + ", Gender: " + testGender + " is " + rank);
    }
    
    public String getName(int year, int rank, String gender, String fileName) {
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        String nameFound = "";
        int count = 0;
        for (CSVRecord row : parser) {
            if(row.get(1).equals(gender)) {
                count++;
                if(count == rank) {
                    nameFound = row.get(0);
                } else {
                    nameFound = "NO NAME";
                }
            }
        }
        return nameFound;
    }
    
    public void testGetName () {
        int testYear = 2012;
        int testRank = 5;
        String testGender = "F";
        // String name = getName(testYear, testRank, testGender);
        // System.out.println("Gender: " + testGender + ", with rank = " + testRank + ", has name " + name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        DirectoryResource dr = new DirectoryResource();
        
       for (File f : dr.selectedFiles()) {
            // FileResource fr = new FileResource(f);
            // CSVParser parser = fr.getCSVParser(false);
            String fileName = f.getAbsolutePath();
            int findYear = f.getAbsolutePath().indexOf("yob");
            int yearInFileName = Integer.parseInt(fileName.substring(findYear + 3, findYear + 7));
            int rank = getRank(yearInFileName, name, gender, fileName);
            String nameFound = getName(yearInFileName, rank, gender, fileName);
            // System.out.println(fileName);
            // System.out.println(findYear);
            // System.out.println(yearInFileName);
            System.out.println(nameFound + " born in " + yearInFileName + " rank: "+ rank);
        }
        // System.out.println("getF irstYearRank is " + getFirstYearRank);
        // System.out.println("getSecondYearRank is " + getSecondYearRank);
    }
    
    public void testWhatIsNameInYear () {
        String testName = "Isabella";
        int testYear = 2012;
        int testNewYear = 2014;
        String testGender = "F";
        whatIsNameInYear(testName, testYear, testNewYear, testGender);        
    }
}
