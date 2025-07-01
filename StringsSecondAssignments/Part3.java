
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1){
            if((currIndex-startIndex)%3==0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon,currIndex+1);
            }
        }
        return dna.length();
    }
    
    public void testStopCodon(){
                    
        String dna = "ATGCACTAACTAGCTGACTAAT"; 
        int stopIndex = findStopCodon(dna,0,"TAA");
        System.out.println("Stop Codon Index = " + stopIndex);
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length()) return "";
        return dna.substring(startIndex,minIndex+3);
    }
    
    public void testFindGene() {
        String[] testDNAs = {
            "CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT",  // multiple stop codons
        "CCCATGGGGTTTAAATAATAATAGGAGAGAGTTT",         // one stop codon
        "CCCATGGGGTTTAAATAATAATAG",                   // valid gene
        "CCCCCCGGGGTTTAAATAATAATAGGAGAGAGTTT",        // no ATG
        "ATGXXYYYZZZ"                                  // ATG but no valid stop
        };
    
        for (String dna : testDNAs) {
            System.out.println("DNA: " + dna);
            String gene = findGene(dna);
            System.out.println("Gene: " + gene);
            System.out.println("------");
        }
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){ 
            int atgIndex = dna.indexOf("ATG",startIndex);
            if(atgIndex==-1){
                break;
            }
            
            int taaIndex = findStopCodon(dna,startIndex,"TAA");
            int tgaIndex = findStopCodon(dna,startIndex,"TGA");
            int tagIndex = findStopCodon(dna,startIndex,"TAG");
            
            int minIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
            if(minIndex==dna.length()){
                startIndex = atgIndex+3;
            }
            else{
                 System.out.println("Gene found: " + dna.substring(atgIndex, minIndex + 3));
                 startIndex = minIndex+3;
            }
        }   
    }
    
    public int countGenes(String dna){
        int count = 0;
        int startIndex = 0;
        while(true){
            int atgIndex = dna.indexOf("ATG",startIndex);
            if(atgIndex ==-1)
            {
                break;
            }
            
            int taaIndex = findStopCodon(dna,atgIndex,"TAA");
            int tgaIndex = findStopCodon(dna,atgIndex,"TGA");
            int tagIndex = findStopCodon(dna,atgIndex,"TAG");
            
            int minIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
            if(minIndex==dna.length()){
                startIndex = atgIndex+3;
            }
            else{
                count++;
                startIndex = minIndex+3;
            }
        }
        return count;
    }
    public void testCountGenes() {
        String[] testDNAs = {
            "ATGTAAGATGCCCTAGT",     // 2 genes: ATGTAA and ATGCCCTAG
            "ATGAAATGAAAA",          // 0 genes: no valid stop codons
            "ATGTAAATGTAGATGTGA",    // 3 genes
            "ATGATGTAA",             // 1 gene: ATGATGTAA
            "ATGATGATG",             // 0 genes
            ""                       // empty string
        };

        for (String dna : testDNAs) {
            System.out.println("DNA: " + dna);
            int count = countGenes(dna);
            System.out.println("Number of genes: " + count);
            System.out.println("-----------");
        }
    }
    public static void main(String [] args){
        Part3 p3 = new Part3();
        p3.testStopCodon();
        p3.testFindGene();
        p3.printAllGenes("ATGAAATGAATGTAGATGTAA");
    }
    
}
