import edu.duke.*;
import java.util.*;
import java.io.File;

public class wordsInFiles{
    private HashMap<String, ArrayList> wordsMap;
    
    public wordsInFiles() {
        wordsMap = new HashMap<String, ArrayList>();
    }
        
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        // System.out.println(fileName);
        
        for(String word : fr.words()) {
            if (!wordsMap.containsKey(word)) {
                ArrayList<String> Files = new ArrayList<String>();
                Files.add(fileName);
                wordsMap.put(word, Files);
            } else {
                ArrayList Files = wordsMap.get(word);
                if (!Files.contains(fileName)) {
                    Files.add(fileName);
                }
            }
        }    
    }
    
    public void buildWordFileMap() {
        wordsMap.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int max = 0;
        
        for (String word : wordsMap.keySet()) {
            int numFiles = wordsMap.get(word).size();
            if (numFiles > max) {
                max = numFiles;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsInFiles = new ArrayList<String>();
        
        for (String word : wordsMap.keySet()) {
            int countOfFiles = wordsMap.get(word).size();
            if (countOfFiles == number) {
                wordsInFiles.add(word);
            }
        }
        
        return wordsInFiles;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> files = wordsMap.get(word);
        for (String name : files) {
            System.out.println(name);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        // for (String s : wordsMap.keySet()) {
            // System.out.println(s);
            // System.out.println(wordsMap.get(s));
        // }
        
        for (String s : wordsMap.keySet()) {
            int arrayListSize = wordsMap.get(s).size();
            System.out.println(s + " appears in " + arrayListSize + " files");
        }
        
        int maxFilesWithWord = maxNumber();
        System.out.println("max number of files with same word = " + maxFilesWithWord);
        
        int num = 3;
        ArrayList<String> wordsInFiles = wordsInNumFiles(num);
        System.out.println("words that appear in " + num + " files: " + wordsInFiles);
        
        String testWord = "cats";
        printFilesIn(testWord);
    }    
}
