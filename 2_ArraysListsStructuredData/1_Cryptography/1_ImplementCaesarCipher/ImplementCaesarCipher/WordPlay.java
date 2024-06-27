import edu.duke.*;

public class WordPlay {
   public boolean isVowel(char ch) {
        String vowels = "AEIOUaeiou";
        int foundMatch = vowels.indexOf(ch);
        
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
   
   public String replaceVowels(String phrase, char ch) {
     StringBuilder vowelsReplaced = new StringBuilder(phrase);
        
     for (int i = 0; i < vowelsReplaced.length(); i++) {
       char currChar = vowelsReplaced.charAt(i);
       if(isVowel(currChar)) {
        vowelsReplaced.setCharAt(i, ch);
      }
     }
     return vowelsReplaced.toString();
   }
    
   public void testReplaceVowels() {
       String testPhrase = "Hello World";
       char testCH = '*';
       String result = replaceVowels(testPhrase, testCH);
       System.out.println(result);
   }
   
   public String emphasize(String phrase, char ch) {
       StringBuilder newPhrase = new StringBuilder(phrase);
       char oddIdx = '+';
       char evenIdx = '*';       
       
       for (int i = 0; i < newPhrase.length(); i++) {
            char currChar = newPhrase.charAt(i);
            char lower = Character.toLowerCase(currChar);
            char upper = Character.toUpperCase(currChar);
            if ((lower == ch || upper == ch) && (i % 2 == 0)) {
                newPhrase.setCharAt(i, evenIdx);
            }
            else if ((lower == ch || upper == ch) && (i % 2 != 0)) {
                newPhrase.setCharAt(i, oddIdx);
            }
       }
       
       return newPhrase.toString();
   }
   
   public void testEmphasize() {
       String testPhrase = "dna ctgaaactga";
       char testCh = 'a';
       String testPhrase2 = "Mary Bella Abracadabra";
       char testCh2 = 'a';
       
       String result = emphasize(testPhrase, testCh);
       String result2 = emphasize(testPhrase2, testCh2);
       System.out.println(result);
       System.out.println(result2);       
   }
}
