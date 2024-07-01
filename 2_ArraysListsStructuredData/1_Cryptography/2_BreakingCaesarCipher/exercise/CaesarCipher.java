import edu.duke.*;
public class CaesarCipher{
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = alphabet.toLowerCase();
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        String lowerShiftedAlphabet = shiftedAlphabet.toLowerCase();
        // System.out.println(alphabet);
        // System.out.println(shiftedAlphabet);
        // System.out.println(lowerAlphabet);
        // System.out.println(lowerShiftedAlphabet);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                encrypted.setCharAt(i, newChar);
            } else if (Character.isLowerCase(currChar)) {
                int indexLower = lowerAlphabet.indexOf(currChar);
                char newLowerChar = lowerShiftedAlphabet.charAt(indexLower);
                encrypted.setCharAt(i, newLowerChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypt() {
        String testString = "FIRST LEGION ATTACK EAST FLANK!";
        String testString2 = "First Legion";
        String testString3 = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int testKey = 23;
        int testKey2 = 17;
        int testKey3 = 15;
        // String result = encrypt(testString, testKey);
        // String result2 = encrypt(testString2, testKey);
        // String result3 = encrypt(testString2, testKey2);
        String result4 = encrypt(testString3, testKey3);
        // System.out.println("Unencrypted: " + testString);
        // System.out.println("Encrypted: " + result);
        // System.out.println("Unencrypted: " + testString2);
        // System.out.println("Encrypted: " + result2);
        // System.out.println("Unencrypted: " + testString2);
        // System.out.println("Encrypted: " + result3);
        System.out.println("Unencrypted: " + testString3);
        System.out.println("Encrypted: " + result4);
    }
    
    public void testCaesar() {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted); 
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            String letter = Character.toString(currChar);
            if (i % 2 == 0) {
                String even = encrypt(letter, key1);
                output += even;
            } else {
                String odd = encrypt(letter, key2);
                output += odd;
            }
        }
        return output;
    }
    
    public void testEncryptTwoKeys() {
        String testString = "First Legion";
        String testString2 = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        // String result = encryptTwoKeys(testString, key1, key2);
        String result = encryptTwoKeys(testString2, key1, key2);
        System.out.println(result);
    }
}
