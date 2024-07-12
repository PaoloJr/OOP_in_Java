import edu.duke.*;
import java.util.*;

public class WordFrequencies{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        System.out.println("# of unique words: " + myWords.size()); 
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        System.out.println("# of unique words: " + myWords.size()); 
        int maxIndex = findIndexMax();
        String maxWord = myWords.get(maxIndex);
        int maxWordFreq = myFreqs.get(maxIndex);
        System.out.println("The word that occurs most often and its count is: " + maxWord + " " + maxWordFreq);
    }    
    
    public int findIndexMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        
        for (int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) > max) {
            max = myFreqs.get(i);
            maxIndex = i;
            }
        }
        return maxIndex;
    }
}
