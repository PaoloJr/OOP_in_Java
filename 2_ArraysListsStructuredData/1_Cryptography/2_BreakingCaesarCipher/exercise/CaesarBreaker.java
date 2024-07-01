import edu.duke.*;

public class CaesarBreaker{
    public int[] countLetters(String message) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = alphabet.toLowerCase();
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int index = lowerAlphabet.indexOf(ch);
            if (index != -1) {
                counts[index] += 1;
            }
        }
        // for (int i = 0; i < counts.length; i++) {
            // System.out.println(i + " " + counts[i]);
        // }
        return counts;
    }
    
    public int maxIndex(int[] values) {
        int maxIndexValue = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > values[maxIndexValue]) {
                maxIndexValue = k;
            }
        }
        return maxIndexValue;
    }
        
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxIdx = maxIndex(freqs);
        // calculate distance from 'e'th index (4)
        int eKey = maxIdx - 4;
        // System.out.println("maxIdx: " + maxIdx + " " + "dkey before: " + dkey);
        // find the shift if maxIdx is less than 4
        if (maxIdx < 4) {
            eKey = 26 - (4 - maxIdx);
        }
        // System.out.println("dkey after: " + dkey);
        String message = cc.encrypt(encrypted, 26 - eKey);
        return message;
    }   
        
    public void testDecrypt() {
        // int key = 23;
        FileResource fr = new FileResource();
        String contents = fr.asString();
        String decrypted = decrypt(contents);
        System.out.println(decrypted);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k < message.length(); k += 2) {
            char currChar = message.charAt(k);
            sb.append(currChar);
        }
        return sb.toString();
    }
    
    public void testHalfOfString() {
        String testString = "Qbkm Zgis";
        // FileResource fr = new FileResource();
        // String contents = fr.asString();
        int firstStart = 0;
        int secondStart= 1;
        System.out.println("testString: " + testString);
        String result1 = halfOfString(testString, firstStart);
        String result2 = halfOfString(testString, secondStart);
        // String result3 = halfOfString(contents, firstStart);
        // String result4 = halfOfString(contents, secondStart);
        System.out.println(result1 + " length: " + result1.length());
        System.out.println(result2 + " length: " + result2.length());
        // System.out.println(contents);
        // System.out.println(result3);
        // System.out.println(result4);        
    }
    
    public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIdx = maxIndex(counts);
        int eKey = maxIdx - 4;
        if (maxIdx < 4) {
            eKey = 26 - (4 - maxIdx);
        }
        return eKey;
    }
    
    public void testGetKey() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        String firstHalf = halfOfString(contents, 0);
        String secondHalf = halfOfString(contents, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        // int key = getKey(contents);
        System.out.println(firstHalf);
        System.out.println(secondHalf);
        System.out.println("first key: " + firstKey + " Second key: " + secondKey);
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String evenHalf = halfOfString(encrypted, 0);
        String oddHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(evenHalf);
        int secondKey = getKey(oddHalf);
        System.out.println(encrypted);
        // System.out.println(evenHalf + oddHalf);
        System.out.println("First key: " + firstKey + " , " + "Second key: " + secondKey);
        // System.out.println("First dkey: " + (26 - firstKey) + " Second dkey: " + (26 - secondKey));
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - firstKey, 26 - secondKey);
        // String decrypted = cc.encryptTwoKeys(encrypted, 26 - 2, 26 - 20);
        // System.out.println(decrypted);
        return decrypted;
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String contents = fr.asString();
        String practiceString = "Top ncmy qkff vi vguv vbg ycpx";
        // String decryptedTwo = decryptTwoKeys(practiceString);
        String decryptedTwo = decryptTwoKeys(contents);
        // System.out.println(contents);
        System.out.println(decryptedTwo);
    }
}
