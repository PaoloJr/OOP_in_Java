import edu.duke.*;

public class CaesarCipherTwo{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private String shiftedAlphabetLower1;
    private String shiftedAlphabetLower2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            String letter = Character.toString(currChar);
            
            if (i % 2 == 0) {
                
            }
        }
        
        return encrypted.toString();
    }
}
