import edu.duke.*;

public class TestCaesarCipherTwo {
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
        int maxIndex = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > values[maxIndex]) {
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
      public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k < message.length(); k += 2) {
            char currChar = message.charAt(k);
            sb.append(currChar);
        }
        return sb.toString();
    }
    
       public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIdx = maxIndex(counts);
        // System.out.println("String s: " + s + " --> maxIdx value: " + maxIdx);
        int eKey = maxIdx - 4;
        if (maxIdx < 4) {
            eKey = 26 - (4 - maxIdx);
        }
        return eKey;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String fileContents = fr.asString();
        System.out.println("original: " + fileContents);
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        String encrypted = cct.encrypt(fileContents);
        System.out.println("encrypted: " + encrypted);
        String decrypted = cct.decrypt(encrypted);
        System.out.println("decrypted: " + decrypted); 
        String breakCaesarTest = breakCaesarCipher(fileContents);
        System.out.println("breakCaesar decryption: " + breakCaesarTest);
    }
    
    public String breakCaesarCipher(String input) {
        String evenHalf = halfOfString(input, 0);
        String oddHalf = halfOfString(input, 1);
        int eKeyEven1 = getKey(evenHalf);
        int eKeyOdd1 = getKey(oddHalf);
        System.out.println("keyEven: " + eKeyEven1);
        System.out.println("keyOdd: " + eKeyOdd1);
        CaesarCipherTwo cct = new CaesarCipherTwo(eKeyEven1, eKeyOdd1);
        
        String decrypted = cct.decrypt(input);
        return decrypted;
    }
}
