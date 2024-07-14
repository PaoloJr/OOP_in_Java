import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker ck = new CaesarCracker(mostCommon);
        for(int i = 0; i < key.length; i++) {
            String sliced = sliceString(encrypted, i, klength);
            int getKey = ck.getKey(sliced);
            key[i] = getKey;
            // System.out.println(sliced);
            // System.out.println("\n key --> " + getKey);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hs = new HashSet<String>();
        for(String word : fr.words()) {
            String lower = word.toLowerCase();
            hs.add(lower);
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] splitMessage = message.split("\\W");
        for(String word : splitMessage) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decrypted = "";
        char mostCommon = mostCommonCharIn(dictionary);
        int key = 0;
        int max = 0;
        int[] array = null;
        for (int i = 1; i <= 100; i++) {
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, i, mostCommon));
            String decryptMsg = vc.decrypt(encrypted);
            int countWords = countWords(decryptMsg, dictionary);
            // System.out.println(countWords);
            if(countWords > max) {
                max = countWords;
                key = i;
                decrypted = decryptMsg;
                array = tryKeyLength(encrypted, i, mostCommon);
            }
        }
        // System.out.println("MAX -----> " + max);
        System.out.println(Arrays.toString(array));
        System.out.println("KEY LENGTH --> " + array.length);
        return decrypted;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String decrypted = null;
        int maxWords = 0;
        String languageName = "";
        
        for (String key : languages.keySet()) {
            String decryptedMsg = breakForLanguage(encrypted, languages.get(key));
            int wordsCount = countWords(decryptedMsg, languages.get(key));
            System.out.println(key);
            if(wordsCount > maxWords) {
                maxWords = wordsCount;
                decrypted = decryptedMsg;
                languageName = key;
            }
            // System.out.println("wordsCount --> " + wordsCount);
        }
        System.out.println("--- LANGUAGE ---");
        System.out.println(languageName);
        System.out.println("--- MAX WORD COUNT ---");
        System.out.println(maxWords);
        System.out.println("--- DECRYPTED ---");
        System.out.println(decrypted);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        StringBuilder sb = new StringBuilder();
        CaesarCracker ck = new CaesarCracker();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
          
        for(String word : dictionary) {
            sb.append(word);
        }
        int[] freqs = ck.countLetters(sb.toString().toLowerCase());
        int maxDex = ck.maxIndex(freqs);
       
        return alphabet.charAt(maxDex);
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            HashSet<String> returnedDictionary = readDictionary(fr);
            dictionaries.put(fileName, returnedDictionary);
            char mostCommon = mostCommonCharIn(returnedDictionary);
            System.out.println("most common char in " + fileName + ": " + mostCommon);
        }
        FileResource encryptedFr = new FileResource();
        String encryptedFileString = encryptedFr.asString();

        breakForAllLangs(encryptedFileString, dictionaries);
    }
    
}
