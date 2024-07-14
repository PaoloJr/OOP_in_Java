import java.util.*;
import edu.duke.*;

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
        char mostCommon = 'e';
        int key = 0;
        int max = 0;
        int[] array = new int[0];
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
        System.out.println("MAX -----> " + max);
        // VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted, 38, mostCommon));
        // decrypted = vc.decrypt(encrypted);
        // System.out.println(Arrays.toString(array));
        System.out.println("KEY LENGTH --> " + array.length);
        return decrypted;
    }

    public void breakVigenere () {
        FileResource encryptedFr = new FileResource();
        String encryptedFileString = encryptedFr.asString();
        // int klength = 4;
        // char mostCommon = 'e';
        // int[] keys = tryKeyLength(encryptedFileString, klength, mostCommon);
        // VigenereCipher vc = new VigenereCipher(keys);
        // String decrypted = vc.decrypt(encryptedFileString);
        // System.out.println("--- DECRYPTED ---");
        // System.out.println(decrypted);
        
        FileResource dictionary = new FileResource("dictionaries/English");
        HashSet<String> wordsDictionary = readDictionary(dictionary);
        String decryptForLang = breakForLanguage(encryptedFileString, wordsDictionary);
        System.out.println(" --- DECRYPT FOR LANGUAGE ---");
        System.out.println(decryptForLang);
    }
    
}
