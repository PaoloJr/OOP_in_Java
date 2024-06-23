public class Part2
{
    public void testHowMany()
    {   
        String first = "ATGAACGAATTGAATC";
        String firstFind = "GAA";
        String second = "ATAAAA";
        String secondFind = "AA";
        String third = "banana";
        String thirdFind = "na";
        String fourth = "something";
        String fourthFind = "ze";
        String fifth = "aabaacaa";
        String fifthFind = "a";
                
        System.out.println("First is " + first);
        int testFirst = howMany(firstFind, first);
        System.out.println("Found " + firstFind + " " + testFirst + " times");
        System.out.println("Second is " + second);
        int testSecond = howMany(secondFind, second);
        System.out.println("Found " + secondFind + " " + testSecond + " times");
        System.out.println("Third is " + third);
        int testThird = howMany(thirdFind, third);
        System.out.println("Found " + thirdFind + " " + testThird + " times");
        System.out.println("Fourth is " + fourth);
        int testFourth = howMany(fourthFind, fourth);        
        System.out.println("Found " + fourthFind + " " + testFourth + " times");
        System.out.println("Fifth is " + fifth);
        int testFifth = howMany(fifthFind, fifth);
        System.out.println("Found " + fifthFind + " " + testFifth + " times");
        
    }

    public int howMany(String stringa, String stringb)
    {   
        int count = 0;
        int currIndex = stringb.indexOf(stringa);
        while (true) {
        if (currIndex == -1) {
            break;
        }
        // System.out.println("currIndex " + currIndex);
        int nextIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        currIndex = nextIndex;
        count += 1;
        // System.out.println("nextIndex " + currIndex);
        }
        return count;
    }
}
