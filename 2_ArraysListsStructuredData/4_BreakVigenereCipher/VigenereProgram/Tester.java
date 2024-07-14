import edu.duke.*;
import java.util.*;
import java.io.*;

public class Tester{
    public void testCaesarCipher() {
        int key = 2;
        int charPos = 0;
        CaesarCipher cc = new CaesarCipher(key);
        String fileName = "../titus-small.txt";
        FileResource fr = new FileResource(fileName);
        String fileString = fr.asString();
        StringBuilder sb = new StringBuilder();
        System.out.println("original --> " + fileName);
        System.out.println(fileString);
        String encrypted = cc.encrypt(fileString);
        System.out.println("encrypted with key = " + key);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted");
        System.out.println(decrypted);
        for(String word : fr.words()) {
            char currChar = word.charAt(charPos);
            char encryptedChar = cc.encryptLetter(currChar);
            sb.append(encryptedChar);
            sb.append(word.substring(charPos + 1));
        }
        System.out.println("encrypted character for each word at position: " + charPos);
        System.out.println(sb);        
    }
    
    public void testCaesarCracker() {
        CaesarCracker ck = new CaesarCracker();
        CaesarCracker ck2 = new CaesarCracker('a');
        String fileName = "../titus-small_key5.txt";
        String fileName2 = "../oslusiadas_key17.txt";
        FileResource fr = new FileResource(fileName);
        FileResource fr2 = new FileResource(fileName2);
        String fileString = fr.asString();
        String fileString2 = fr2.asString();
        int key = ck.getKey(fileString);
        int key2 = ck2.getKey(fileString2);
        String decrypted = ck.decrypt(fileString);
        String decrypted2 = ck2.decrypt(fileString2);
        System.out.println("*** original --> " + fileName);
        System.out.println(fileString);
        System.out.println("key = " + key);
        System.out.println("\ndecrypted");
        System.out.println(decrypted);
        System.out.println("*** original --> " + fileName2);
        System.out.println(fileString2);
        System.out.println("key = " + key2);
        System.out.println("\ndecrypted");
        System.out.println(decrypted2);        
    }
    
    public void testVigenereCipher() {
        int[] ciphers = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(ciphers);
        String fileName = "../titus-small.txt";
        FileResource fr = new FileResource(fileName);
        String fileString = fr.asString();
        String encrypted = vc.encrypt(fileString);
        String decrypted = vc.decrypt(encrypted);
        String keys = vc.toString();
        System.out.println("*** original --> " + fileName);
        System.out.println(fileString);
        System.out.println("encrypted");
        System.out.println(encrypted);
        System.out.println("decrypted");
        System.out.println(decrypted);
        System.out.println("keys array --> " + keys);
    }
    
    public void testVigenereSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String testString = "abcdefghijklm";
        String one = vb.sliceString(testString, 0, 3);
        String two = vb.sliceString(testString, 1, 3);
        String three = vb.sliceString(testString, 2, 3);
        String four = vb.sliceString(testString, 0, 4);
        String five = vb.sliceString(testString, 1, 4);
        String six = vb.sliceString(testString, 2, 4);
        String seven = vb.sliceString(testString, 3, 4);
        String eight = vb.sliceString(testString, 0, 5);
        String nine = vb.sliceString(testString, 1, 5);
        String ten = vb.sliceString(testString, 2, 5);
        String eleven = vb.sliceString(testString, 3, 5);
        String twelve = vb.sliceString(testString, 4, 5);
        System.out.println(testString + " ---> " + one);
        System.out.println(testString + " ---> " + two);
        System.out.println(testString + " ---> " + three);
        System.out.println(testString + " ---> " + four);
        System.out.println(testString + " ---> " + five);
        System.out.println(testString + " ---> " + six);
        System.out.println(testString + " ---> " + seven);
        System.out.println(testString + " ---> " + eight);
        System.out.println(testString + " ---> " + nine);
        System.out.println(testString + " ---> " + ten);
        System.out.println(testString + " ---> " + eleven);
        System.out.println(testString + " ---> " + twelve);
    }
    
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        String fileName = "../secretmessage1.txt";
        FileResource fr = new FileResource(fileName);
        String fileString = fr.asString();
        String key = "flute";
        int klength = key.length();
        int klength2 = 4;
        char mostCommon = 'e';
        // int[] keyArray = vb.tryKeyLength(fileString, klength, mostCommon);
        int[] keyArray2 = vb.tryKeyLength(fileString, klength2, mostCommon);
        // System.out.println(Arrays.toString(keyArray));
        System.out.println(Arrays.toString(keyArray2));
    }
    
    public void testMostCommonCharIn() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr);
        char mostCommon = vb.mostCommonCharIn(dictionary);
        
        System.out.println("Most common char --> " + mostCommon);
    }    

    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
