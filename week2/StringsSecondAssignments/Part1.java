
public class Part1
{
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon()
    {
        int startIndex = 0;
        String taaStop = "TAA";
        String tagStop = "TAG";
        String tgaStop = "TGA";
        String dna1 = "XXXYYYATGCGACGATAAYYZY";
        String dna2 = "XXXYYYATGCGACGYYYTAGZZZ";
        String dna3 = "XXYYCGACGTAGYYYATG";
        String dna4 = "XXXYYYATGCGACGTAGYYY";
        String dna5 = "XXXYYYATGCGACGTGAYYY";
        String dna6 = "XXXYYYZZZAAABBBCCCDDD";
        int test1 = findStopCodon(dna1, startIndex, taaStop);
        int test2 = findStopCodon(dna2, startIndex, tagStop);
        int test3 = findStopCodon(dna3, startIndex, tgaStop);
        int test4 = findStopCodon(dna4, startIndex, tagStop);
        int test5 = findStopCodon(dna5, startIndex, taaStop);
        int test6 = findStopCodon(dna6, startIndex, taaStop);
        System.out.println("DNA1 length = " + dna1.length());
        System.out.println("stopCodon position " + test1);
        System.out.println("DNA2 length = " + dna2.length());
        System.out.println("stopCodon position " + test2);
        System.out.println("DNA3 length = " + dna3.length());
        System.out.println("stopCodon position " + test3);
        System.out.println("DNA4 is " + dna4);
        System.out.println("test4 " + test4);
        System.out.println("DNA5 is " + dna5);
        System.out.println("test5 " + test5);
        System.out.println("DNA6 is " + dna6);
        System.out.println("test6 " + test6);        
    }
   
    public void testFindGene() {
    
    }
}
