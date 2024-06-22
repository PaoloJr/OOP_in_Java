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
    
    public String getName(int year, int rank, String gender, FileResource fr) {
        // FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        String nameFound = "NO NAME";
        int count = 0;
        for (CSVRecord row : parser) {
            if(row.get(1).equals(gender)) {
                count++;
                if(count == rank) {
                    nameFound = row.get(0);
                    break;
                } 
            }
        }
        return nameFound;
    }
    
    public void testGetName () {
        FileResource fr = new FileResource();
        int testYear = 2012;
        int testRank = 2;
        String testGender = "F";
        String name = getName(testYear, testRank, testGender, fr);
        System.out.println("Gender: " + testGender + ", with rank = " + testRank + ", has name " + name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = -1;
        String newName = "";
        String output = "";
        
       for (File f : dr.selectedFiles()) {            
            String fileName = f.getAbsolutePath();
            int findYear = f.getAbsolutePath().indexOf("yob");
            int yearInFileName = Integer.parseInt(fileName.substring(findYear + 3, findYear + 7));
            
            // getRank in year
            if (yearInFileName == year) {
            FileResource fr = new FileResource(f);
            rank = getRank(yearInFileName, name, gender, fileName);
            break;
            }
       }

       if (rank != -1) {
        for (File f : dr.selectedFiles()) {
           String fileName = f.getAbsolutePath();
           int findYear = fileName.indexOf("yob");
           int yearInFileName = Integer.parseInt(fileName.substring(findYear + 3, findYear + 7));
           
           // getName in newYear
           if (yearInFileName == newYear) {
            FileResource fr = new FileResource(f);
            newName = getName(yearInFileName, rank, gender, fr);
            break;
            }           
        }
       }
        
        
       if (!newName.equals("") && rank != -1) {
            output = name + " born in " + year + " would be " + newName + " if he/she was born in " + newYear +".";
       } else {
            output = "Name and/or rank not found.";
        }
       System.out.println(output);
    }
    
    public void testWhatIsNameInYear () {
        String testName = "Isabella";
        int testYear = 2012;
        int testNewYear = 2014;
        String testGender = "F";
        whatIsNameInYear(testName, testYear, testNewYear, testGender);        
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int yearOfHighestRank = -1;
        
        for (File f : dr.selectedFiles()) {
           String fileName = f.getAbsolutePath();
           int findYear = fileName.indexOf("yob");
           int yearInFileName = Integer.parseInt(fileName.substring(findYear + 3, findYear + 7));
           int rank = getRank(yearInFileName, name, gender, fileName);
           
           // System.out.println(rank);
           if (rank != -1 && (highestRank == 0 || rank < highestRank)) {
               highestRank = rank;
               yearOfHighestRank = yearInFileName;
           }
        }
        
        return yearOfHighestRank;
    }
    
    public void testYearOfHighestRank() {
        String testName = "Mason";
        String testGender = "M";
        int year = yearOfHighestRank(testName, testGender);
        System.out.println("Year of highest rank = " + year);
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0;
        int count = 0;
        
        for (File f : dr.selectedFiles()) {
           String fileName = f.getAbsolutePath();
           int findYear = fileName.indexOf("yob");
           int yearInFileName = Integer.parseInt(fileName.substring(findYear + 3, findYear + 7));
           int rank = getRank(yearInFileName, name, gender, fileName);
           // System.out.println("Rank is --> " + rank);
           if (rank != -1) {
                totalRank += rank;
                count++;
           }    
        }
        // System.out.println("totalRank: " + totalRank + " count: " + count);
        if (count > 0) {
            return totalRank / count;
        } else {
            return (double)(-1);
        }
    }
    
    public void testGetAverageRank() {
        String testName = "Jacob";
        String testGender = "M";
        double avgRank = getAverageRank(testName, testGender);
        System.out.println("Average rank = " + avgRank);
    }
}
