
/**
 * 
 * @author (PJ)
 * @version (1.0 03.06.2024)
 */
public class Part3
{
    public void testing()
    {
        String stringa = "n";
        String stringb = "banana";
        System.out.println("stringa is " + stringa);
        System.out.println("stringb is " + stringb);
        Boolean result = twoOccurences(stringa, stringb);
        System.out.println(result);
        
        String stringc = "atg";
        String stringd = "ctgtatgta";
        System.out.println("stringc is " + stringc);
        System.out.println("stringd is " + stringd);
        Boolean result2 = twoOccurences(stringc, stringd);
        System.out.println(result2);
        
        String stringe = "by";
        String stringf = "A story by Abby Long";
        System.out.println("stringe is " + stringe);
        System.out.println("stringf is " + stringf);
        Boolean result3 = twoOccurences(stringe, stringf);
        System.out.println(result3);
        
        String stringg = "an";
        String stringh = "banana";
        String result4 = lastPart(stringg, stringh);
        System.out.println("The part of the string after an in banana is " + result4);
        
        String stringi = "zoo";
        String stringj = "forest";
        String result5 = lastPart(stringi, stringj);
        System.out.println("The part of the string after zoo is " + result5);
    }

    public Boolean twoOccurences(String stringa, String stringb)
    {
       int firstIndex = stringb.indexOf(stringa);
       if (firstIndex == -1) {
            return false;
       }
        
       int secondIndex = stringb.indexOf(stringa, firstIndex + 1);
       if (secondIndex == -1) {
            return false;
       } else {
            return true;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if (first == -1) {
            return stringb;
        } else {
        return stringb.substring(first + stringa.length());
        }
    }
}
