
/**
 * Write a description of class Part1 here.
 *
 * @author (PJ)
 * @version (1.0, 03.01.2024)
 */
public class Part1
{
    public void testSimpleGene()
    {
    // dna with no ATG
    String dna = "AATCGCTAATATGCT";
    System.out.println("DNA is " + dna);
    String gene = findSimpleGene(dna);
    System.out.println(gene);
    /* if (gene.startsWith("ATG")) {
        System.out.println(gene);
    } else {
        System.out.println("");
    } */
    
    // dna with not TAA
    String dna2 = "ACGATGCGCTATCGA";
    System.out.println("DNA is " + dna2);
    String gene2 = findSimpleGene(dna2);
    System.out.println(gene2);
    /*if (gene2.endsWith("TAA")) {
        System.out.println(gene2);
    } else {
        System.out.println("");
    }*/
    
    // dna with no ATG or TAA
    String dna3 = "ACGTTACGCAAACTA";
    System.out.println("DNA is " + dna3);
    String gene3 = findSimpleGene(dna3);
    System.out.println(gene3);
    
    // dna with ATG & TAA and substring is a multiple of 3 (a gene)
    String dna4 = "ATGTTACGCAAAGCTAGCTAA";
    System.out.println("DNA is " + dna4);
    String gene4 = findSimpleGene(dna4);
    System.out.println(gene4);
    
    // dna with ATG & TAA and substring is not a multiple of 3
    String dna5 = "AAATGCCCTAACTAGATTAAGAAACC";
    System.out.println("Question #1, DNA is " + dna5);
    String gene5 = findSimpleGene(dna5);
    System.out.println(gene5);    
    }
    
    public String findSimpleGene(String dna)
    {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
        return "";
        }
        
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
        return "";
        }
        result = dna.substring(startIndex, stopIndex+3);
        if (result.length() % 3 == 0) {
        return result;
        } else {
        return "";
        }
    }
}
