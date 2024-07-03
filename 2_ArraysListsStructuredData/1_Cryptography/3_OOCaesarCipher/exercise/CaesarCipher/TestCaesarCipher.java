import edu.duke.*;

public class TestCaesarCipher{
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String fileContents = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(fileContents);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("original text: " + fileContents);
        System.out.println("encrypted text: " + encrypted);
        // System.out.println("decrypted text: " + decrypted);
        String brokenCaesar = breakCaesarCipher(encrypted);
        System.out.println("decrypted text: " + brokenCaesar);
    }
    
    public String breakCaesarCipher(String input) {
        int[] counts = countLetters(input);
        int maxIdx = maxIndex(counts);
        // System.out.println("String s: " + s + " --> maxIdx value: " + maxIdx);
        int eKey = maxIdx - 4;
        if (maxIdx < 4) {
            eKey = 26 - (4 - maxIdx);
        }
        // finding the eKey separately here (from simpleTests we created the encrypted text with key: 18)
        // System.out.println("eKey: " + eKey);
        CaesarCipher cc = new CaesarCipher(eKey);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
}
