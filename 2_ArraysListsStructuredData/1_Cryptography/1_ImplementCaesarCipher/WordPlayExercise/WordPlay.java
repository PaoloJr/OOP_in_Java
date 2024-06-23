import edu.duke.*;

public class WordPlay {
   public boolean isVowel(char ch) {
        char upper = Character.toUpperCase(ch);
        String vowels = "AEIOU";
        int foundMatch = vowels.indexOf(upper);
        
        if (foundMatch != -1) {
        return true;
        } else {
        return false;
        }
    }
    
   public void testWordPlay() {
    char testCharFalse = 'F';
    char testCharTrue = 'a';
    boolean result1 = isVowel(testCharFalse);
    System.out.println(testCharFalse + " is " + result1);
    boolean result2 = isVowel(testCharTrue);
    System.out.println(testCharTrue + " is " + result2);
   }
}
