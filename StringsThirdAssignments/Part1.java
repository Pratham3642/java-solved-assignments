
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
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
    
    public String findGene(String dna,int index){
        int startIndex = dna.indexOf("ATG",index);
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
    
    public StorageResource getAllGenes(String dna){
        StorageResource g =new StorageResource();
        int startIndex = 0;
        while(true){ 
            String gene = findGene(dna,startIndex);
            if(gene.isEmpty()){
                break;
            }
            g.add(gene);
            startIndex = dna.indexOf(gene,startIndex)+gene.length();
        }   
        return g;
    }
    public void testGetAllGenes() {
        String dna = "ATGTAAGATGCCCTAGTAAATGTGA";
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println("Gene: " + gene);
        }
    }
    
}
