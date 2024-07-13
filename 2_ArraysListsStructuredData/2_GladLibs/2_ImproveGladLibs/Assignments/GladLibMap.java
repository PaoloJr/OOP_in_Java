import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedLabels;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "../GladLib/data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "verb", "fruit"};
        for(String s : labels) {
            ArrayList<String> list = readIt(source + "/"+ s + ".txt");
            myMap.put(s, list);
        }
        // usedWord initialize
        usedWords = new ArrayList<String>();
        usedLabels = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        // (infinite) while loop with break statement
        // while (true) {
            // if (!usedWords.contains(sub)) {
                // usedWords.add(sub);
                // break;
            // }
            // sub = getSubstitute(w.substring(first+1, last));
        // }
        // recursive call
        if(usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first + 1, last));
            return processWord(w);
        } else {
            usedWords.add(sub);
            usedLabels.add(w.substring(first + 1, last));
            return prefix+sub+suffix;
        }         
        // return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        
        // for (ArrayList<String> words : myMap.values()) {
            // total += words.size();
        // }
        
        for (String label : myMap.keySet()) {
            int sizeOfLabel = myMap.get(label).size();
            total += sizeOfLabel;
        }
        
        return total;
    }
    
    private int totalWordsConsidered() {
      int result = 0;
      for (String category : myMap.keySet()) {
        if (usedLabels.contains(category)) {
          ArrayList<String> words = myMap.get(category);
          result += words.size();
        }
      }
      return result;
    }
    
    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("../GladLib/data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\n" + "Number of replaced words = " + usedWords.size());
        int totalWordsInMap = totalWordsInMap();
        System.out.println("total words in each ArrayList: " + totalWordsInMap);
        int totalWordsInUsedFiles = totalWordsConsidered();
        System.out.println("total words in used categories: " + totalWordsInUsedFiles);
        // System.out.println(myMap);
        // System.out.println(myMap.keySet());
        System.out.println(usedWords);
        // System.out.println(usedLabels);
        // System.out.println(myMap.get("noun").size());
    }
}
