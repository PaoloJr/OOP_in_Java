import edu.duke.*;

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
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }        
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public float cgRatio(String dna) {
        int startIndex = dna.indexOf("ATG");
        String gene = findGene(dna, startIndex);

        String C = "C";
        String G = "G";
        float countC = 0;
        float countG = 0;
        int cIndex = gene.indexOf(C);
        int gIndex = gene.indexOf(G);
        
         while (cIndex != -1 || gIndex != -1) {
        if (cIndex != -1) {
            countC += 1;
            cIndex = gene.indexOf(C, cIndex + 1);
        }
        if (gIndex != -1) {
            countG += 1;
            gIndex = gene.indexOf(G, gIndex + 1);
        }
        }
        float total = countC + countG;
        // System.out.println("total --> " + total);
        float ratio = total/gene.length();
        // System.out.println("ratio --> " + ratio);
        return ratio;    
    }
    
    public void testCGratio() {
        String dna = "ATGMYGENEONEAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGENDONETAA";
        
        float test = cgRatio(dna);
        // System.out.println("DNA is " + dna);
        // System.out.println(test);
    }
    
    public int countCTG (String dna) {
        String ctg = "CTG";
        int ctgIndex = dna.indexOf(ctg);
        int count = 0;
        
        while (ctgIndex != -1) {
            count += 1;
            ctgIndex = dna.indexOf(ctg, ctgIndex + 1);
        }
                
        return count;
    }
    
    public void testCountCTG() {
        String dna1 = "ATGATCTGAAGAAGATAATAGAGGGCTGATGCTGCTG";
        String dna2 = "CTGCTGCTG";
        String dna3 = "ATGCCATAG";
        
        int count1 = countCTG(dna1);
        int count2 = countCTG(dna2);
        int count3 = countCTG(dna3);
        
        System.out.println("DNA1 is " + dna1);
        System.out.println("CTG count is " + count1);
        System.out.println("DNA2 is " + dna2);
        System.out.println("CTG count is " + count2);
        System.out.println("DNA3 is " + dna3);
        System.out.println("CTG count is " + count3);
    }
    
    public void processGenes(StorageResource sr) {
        int longerThan = 0;
        int highCG = 0;
        int longestString = 0;
        int checkLength = 6;
        double checkRatio = 0.35;
        
        for(String gene : sr.data()) {
            if (gene.length() > checkLength) {
                System.out.println("Gene length > " + checkLength + " --> " + gene);
                System.out.println("Length --> " + gene.length());
                longerThan += 1;
            }
            
            float getCGratio = cgRatio(gene);
            if (getCGratio > checkRatio) {
                System.out.println("CG-ratio > " + checkRatio + " --> " + gene);
                System.out.println("CG-ratio --> " + getCGratio);
                highCG += 1;
            }
            
            int currentGeneLength = gene.length();
            if (currentGeneLength > longestString) {
                longestString = currentGeneLength;
            }
        }
        System.out.println("Count of genes > " + checkLength + " = " + longerThan);
        System.out.println("Count of genes > " + checkRatio + " CG = " + highCG);
        System.out.println("Length of longest gene is " + longestString);
        
    }
    
    public void testProcessGenes() {
        // longer than 9
        //String dna1 = "ATGATCCGATAATTTATGYUIPGHJPJTGAAGA";
        // no genes longer than 9
        //String dna2 = "ATGUIPTAACGFHJKATGGHC";
        // CG-ratio higher than 0.35
        // String dna3 = "ATGCGCTGA";
        // CG-ratio lower than 0.35
        // String dna4 = "ATGHJKLFDTGA";
        // extra
        // String dna5 = "ATGAGAATGCTGCAAHJKFDSCGGTGAAGA";
        
        // String testDNA = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        // String testDNAupper = testDNA.toUpperCase();
        
        System.out.println("Processing DNA strands...");
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString().toUpperCase();
        // System.out.println(dna);
       
        StorageResource allGenes = getAllGenes(dna);
        processGenes(allGenes);
        
        /*StorageResource genes1 = getAllGenes(dna);
        for (String gene : genes1.data()) {
            allGenes.add(gene);
        }
        StorageResource genes2 = getAllGenes(dna2);
        for (String gene : genes2.data()) {
            allGenes.add(gene);
        }
        StorageResource genes3 = getAllGenes(dna3);
        for (String gene : genes3.data()) {
            allGenes.add(gene);
        }
        StorageResource genes4 = getAllGenes(dna4);
        for (String gene : genes4.data()) {
            allGenes.add(gene);
        }
        StorageResource genes5 = getAllGenes(dna5);
        for (String gene : genes5.data()) {
            allGenes.add(gene);
        }
        */
        
        // processGenes(allGenes);
        
    }
    
    public void testGetAllGenes(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
    }
    
    public void test() {
        testGetAllGenes("ATGATCCGATAATTTATGCTGCAACGGTGAAGA");
        testGetAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testGetAllGenes("");
    }
}
