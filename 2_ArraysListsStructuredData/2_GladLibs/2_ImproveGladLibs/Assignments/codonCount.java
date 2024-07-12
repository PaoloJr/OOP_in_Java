import edu.duke.*;
import java.util.*;

public class codonCount{
    private HashMap<String, Integer> codonMap;
    
    public codonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
        String codon = dna.substring(i, i + 3);
        // System.out.println(codon);
            if(!codonMap.containsKey(codon)) {
                codonMap.put(codon, 1);
            } else {
                int value = codonMap.get(codon);
                codonMap.put(codon, value + 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxCountCodon = 0;
        String codonMaxFound = "";
        for(String codon : codonMap.keySet()) {
            int codonCount = codonMap.get(codon);
            if (codonCount > maxCountCodon) {
                maxCountCodon = codonCount;
                codonMaxFound = codon;
            }
        }
        return codonMaxFound;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int codonCount = codonMap.get(codon);
            if (codonCount >= start && codonCount <= end) {
                System.out.println(codon + "\t" + codonCount);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String frString = fr.asString().toUpperCase().trim();
        buildCodonMap(2, frString);
        String mostCommonCodon = getMostCommonCodon();
        System.out.println("most commond codon = " + mostCommonCodon + " with a count of: " + codonMap.get(mostCommonCodon));
        int start = 1;
        int end = 5;
        printCodonCounts(start, end);
    }
}
