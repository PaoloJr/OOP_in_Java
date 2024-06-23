
public class Part3
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
        String dna2 = "XXXYYYATGCGACGYYZYTAGZ";
        String dna3 = "XXYYCGACGTAGYYYATG";
        String dna4 = "XXXYYYATGCGCACGTAGYYYZ";
        String dna5 = "XXXYYYATGCGACGTGAYYY";
        String dna6 = "XXXYYYZZZAAABBBCCCDDD";
        int test1 = findStopCodon(dna1, startIndex, taaStop);
        int test2 = findStopCodon(dna2, startIndex, tagStop);
        int test3 = findStopCodon(dna3, startIndex, tgaStop);
        int test4 = findStopCodon(dna4, startIndex, tagStop);
        int test5 = findStopCodon(dna5, startIndex, taaStop);
        int test6 = findStopCodon(dna6, startIndex, taaStop);
        System.out.println("DNA1 is " + dna1);
        System.out.println("DNA1 length = " + dna1.length());
        System.out.println("stopCodon position " + test1);
        System.out.println("DNA2 is " + dna2);
        System.out.println("DNA2 length = " + dna2.length());
        System.out.println("stopCodon position " + test2);
        System.out.println("DNA3 is " + dna3);
        System.out.println("DNA3 length = " + dna3.length());
        System.out.println("stopCodon position " + test3);
        System.out.println("DNA4 is " + dna4);
        System.out.println("DNA4 length = " + dna4.length());
        System.out.println("stopCodon position " + test4);
        System.out.println("DNA5 is " + dna5);
        System.out.println("DNA5 length = " + dna5.length());
        System.out.println("stopCodon position " + test5);
        System.out.println("DNA6 is " + dna6);
        System.out.println("DNA6 length = " + dna6.length());
        System.out.println("stopCodon poistion " + test6);        
    }
   
    public String findGene(String dna, int where) {
        // add the `where` parameter to indicate where to start looking
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = 0;
        /*
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        
        if (minIndex == -1) {
            return "";
        }
        */
       
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);    
    }
    
    public void testFindGene() {
        // no ATG
        String dna1 = "XXXYYYGTACCCGGGTAA";
        // with ATG and valid stopCodon
        String dna2 = "ZZZCGAATGTTTTAG";
        // with ATG and multiple valid stopCodons
        String dna3 = "ZZZXXXXCCCATGYTHVBCTAGTGATAA";
        // with ATG and no valid stopCodons
        String dna4 = "CCCATGGHJAVDFDS";
        String dna5 = "";
        String dna6 = "";
        
        String findDNA1 = findGene(dna1, 0);
        String findDNA2 = findGene(dna2, 0);
        String findDNA3 = findGene(dna3, 0);
        String findDNA4 = findGene(dna4, 0);
        
        System.out.println("DNA1 is " + dna1);
        System.out.println("Gene is " + findDNA1);
        System.out.println("DNA2 is " + dna2);
        System.out.println("Gene is " + findDNA2);
        System.out.println("DNA3 is " + dna3);
        System.out.println("Gene is " + findDNA3);
        System.out.println("DNA4 is " + dna4);
        System.out.println("Gene is " + findDNA4);
    }
    
    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            startIndex += currentGene.length();
            count += 1;
            System.out.println(currentGene);
        }
        return count;
    }
    
    public void test() {
        String dna1 = "ATGTAAGATGCCCTAGT";
        String dna2 = "ATGTAA";
        String dna3 = "ATGTAACCGATGCCGHGGTAAATGGHJKJHTAG";
        
        System.out.println("Testing dna1 " + dna1);
        int testDNA1 = countGenes(dna1);       
        System.out.println("Found " + testDNA1 + " genes");
        System.out.println("Testing dna2 " + dna2);
        int testDNA2 = countGenes(dna2);
        System.out.println("Found " + testDNA2 + " genes");
        System.out.println("Testing dna3 " + dna3);
        int testDNA3 = countGenes(dna3);
        System.out.println("Found " + testDNA3 + " genes");
    }
}
