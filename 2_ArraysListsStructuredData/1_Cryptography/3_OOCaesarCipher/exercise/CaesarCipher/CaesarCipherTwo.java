import edu.duke.*;

public class CaesarCipherTwo{
    private String alphabet;
    private String lowerAlphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String lowerShiftedAlphabet1;
    private String lowerShiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        // shiftedAlphabetLower1 = alphabet.toLowerCase().substring(key1) + alphabet.toLowerCase().substring(0, key1);
        // shiftedAlphabetLower2 = alphabet.toLowerCase().substring(key2) + alphabet.toLowerCase().substring(0, key2);
        lowerShiftedAlphabet1 = shiftedAlphabet1.toLowerCase();
        lowerShiftedAlphabet2 = shiftedAlphabet2.toLowerCase();
        
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    // System.out.println("alphabet: " + alphabet);
    // System.out.println("shifted1: " + shiftedAlphabet1);
    // System.out.println("shifted2: " + shiftedAlphabet2);            
    
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            // even index check
            if ((i % 2 == 0) && (Character.isLowerCase(currChar))) {
                int idx = lowerAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = lowerShiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i % 2 == 0) && (Character.isUpperCase(currChar))) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            // odd index check
            else if ((i % 2 != 0) && (Character.isLowerCase(currChar))) {
                int idx = lowerAlphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = lowerShiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i % 2 != 0) && (Character.isUpperCase(currChar))) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    
       public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
