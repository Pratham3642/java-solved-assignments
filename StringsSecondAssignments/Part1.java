
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    public static void main(String [] args){
        Part1 p1 = new Part1();
        p1.testStopCodon();
        p1.testFindGene();
        p1.printAllGenes("ATGAAATGAATGTAGATGTAA");
    }
}
