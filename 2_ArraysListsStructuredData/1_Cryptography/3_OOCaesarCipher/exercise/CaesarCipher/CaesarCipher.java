import edu.duke.*;

public class CaesarCipher{
    private String alphabet;
    private String lowerAlphabet;
    private int mainKey;
    private String shiftedAlphabet;
    private String lowerShiftedAlphabet;   
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlphabet = alphabet.toLowerCase();
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        lowerShiftedAlphabet = shiftedAlphabet.toLowerCase();
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                sb.setCharAt(i, newChar);
            } else if (Character.isLowerCase(currChar)) {
                int indexLower = lowerAlphabet.indexOf(currChar);
                char newLowerChar = lowerShiftedAlphabet.charAt(indexLower);
                sb.setCharAt(i, newLowerChar);
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        // create new object with decryption key (26 - encryption key)
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
