
public class Part1
{
   public void findAbc(String input) {
        int index = input.indexOf("abc");
        // System.out.println("index" + index);
        while (true) {
        if (index == -1 || (index > input.length() - 3 )) {
        // if (index == -1) {
            break;
        }
        // System.out.println("index + 1 is " + (index + 1));
        // System.out.println("index + 1 is " + (index + 4));        
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+4);
        // System.out.println("index " + index);
        }
   }
   public void test() {
    // System.out.println("first test " + "--> abcd");
    // findAbc("abcd");
    // System.out.println("second test " + "--> abcdefabcghi");
    // findAbc("abcdefabcghi");
    System.out.println("third test " + "--> abcdabc");
    // System.out.println("length is " + ("abcdabc").length());
    findAbc("abcdabc");
    // System.out.println("one");
    // findAbc("eusabce");
    // System.out.println("two");
    // findAbc("abcbbbabcdddabc");
    // System.out.println("three");
    // findAbc("woiehabchi");
    // System.out.println("four");
    // findAbc("aaaaabc");
    // System.out.println("five");
    // findAbc("yabcyabc");
   }
}
