import edu.duke.*;

public class WordLengths{
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int startIdx = 0;
            int endIdx = word.length();
            // find first letter (skip over non-letters) from start of word
            while (startIdx < endIdx && !Character.isLetter(word.charAt(startIdx))) {
                startIdx += 1;
            }
            // find last letter from end of the word (inclusive; subtract 1)
            while (endIdx > startIdx && !Character.isLetter(word.charAt(endIdx - 1))) {
                endIdx -= 1;
            }
            int currentWordLength = endIdx - startIdx;
            // System.out.println(startIdx + " " + endIdx);
            if (currentWordLength >= counts.length) {
                currentWordLength = counts.length -1;
            }
            if (currentWordLength > 0) {
                counts[currentWordLength]++;
            }
            // System.out.println(counts[currentWordLength] + " word(s)" + " of length " + currentWordLength + ": " + word);
        }
        for(int i = 0; i < counts.length; i++){
            System.out.println("words(s) of length " + i + "  --> " + counts[i]);
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        int idxOfMax = indexOfMax(counts);
        System.out.println("idxOfMax: " + idxOfMax);
    }
    
    public int indexOfMax(int[] values) {   
      int indexOfMax = 0;
      int maxValue = values[0];
      for (int k = 1; k < values.length; k++) {
        if (values[k] > maxValue) {
          maxValue = values[k];
          indexOfMax = k;
        }
      }
      return indexOfMax;
    }
}
