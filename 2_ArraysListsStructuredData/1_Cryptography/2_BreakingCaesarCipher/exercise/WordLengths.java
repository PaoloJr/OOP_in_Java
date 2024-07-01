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
            System.out.println(counts[currentWordLength] + " word(s)" + " of length " + currentWordLength + ": " + word);
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[10000];
        countWordLengths(fr, counts);
        int maxIndex = indexOfMax(counts);
        System.out.println("maxIndex: " + maxIndex);
    }
    
    public int indexOfMax(int[] values) {
        int indexOfMax = 0;
        for (int k = 0; k < values.length; k++) {
            int current = values[k];
            if (current > values[indexOfMax]) {
                indexOfMax = current;
            }
        }
        // for (int i = 0; i < values.length; i++) {
            // System.out.println(values[i]);
        // }
        return indexOfMax;
    }
}
