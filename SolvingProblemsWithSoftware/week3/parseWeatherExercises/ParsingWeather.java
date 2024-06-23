import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeather
{
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord lowestTempSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestTempSoFar = getLowestOfTwo(currentRow, lowestTempSoFar);
        }
        return lowestTempSoFar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));        
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
         if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            if (currentTemp != -9999 && currentTemp < lowestTemp) {
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        String fileName = "";
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
            if (lowestSoFar == currentRow) {
                fileName = f.toString();
                // or
                // fileName = f.getName();
            }
        }        
        return fileName;
    }
    
    public void testFileWithColdestTemperature() {
        String fileWithLowestTemp = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileWithLowestTemp);
        FileResource fr = new FileResource(fileWithLowestTemp);        
        CSVRecord lowestTempInFile = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + lowestTempInFile.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord line : parser) {
            String date = line.get("DateUTC");
            double temp = Double.parseDouble(line.get("TemperatureF"));
            System.out.println(date + ": " + temp);
        }
        
    }
    
    public CSVRecord getLowestHumOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
         if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
            if (currentHum < lowestHum) {
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
                if(currentRow.get("Humidity").equals("N/A")) {
                    continue;
                } else {
                    lowestSoFar = getLowestHumOfTwo(currentRow, lowestSoFar);                   
                }                
            }
            return lowestSoFar;
        }        
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String humidity = csv.get("Humidity");
        String date = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + date);
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumInFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumInFile = getLowestHumOfTwo(currentRow, lowestHumInFile);
        }
        return lowestHumInFile;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHum = lowestHumidityInManyFiles();
        String humidity = lowestHum.get("Humidity");
        String date = lowestHum.get("DateUTC");
        System.out.println("Lowest Humidity was " + humidity + " at " + date);
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        int days = 0;
        for (CSVRecord line : parser) {
            double temp = Double.parseDouble(line.get("TemperatureF"));
            totalTemp += temp;
            days += 1;
        }
        double avgTemp = totalTemp/days;
        return avgTemp;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTempHighHum = 0;
        int days = 0;
        for(CSVRecord row : parser) {
            double humidity = Double.parseDouble(row.get("Humidity"));
            double temp = Double.parseDouble(row.get("TemperatureF"));
            if (humidity >= value) {
                totalTempHighHum += temp;
                days += 1;
            }
        }
        double avgTempHighHum = totalTempHighHum/days;
        return avgTempHighHum;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avgTempHighHum = averageTemperatureWithHighHumidityInFile(parser, value);
        if (avgTempHighHum > 0) {
            System.out.println("Average Temp when high humidity is " + avgTempHighHum);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
