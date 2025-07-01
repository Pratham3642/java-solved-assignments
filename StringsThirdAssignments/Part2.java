
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna){
        int count =0;
        for(int i=0;i<dna.length();i++){
            char ch = dna.charAt(i);
            if(ch=='C'||ch=='G'){
                count++;
            }
        }
        return (double) count/dna.length();
    }
    
    public int countCTG(String dna){
        int count =0;
        int index = dna.indexOf("CTG");
        while(index!= -1){
            count++;
            index = dna.indexOf("CTG",index+3);
        }
        return count++;
    }
    public void testMethods(){
        String dna = "ATGCCATAGCTGCTGCTG";
        System.out.println("CG Ratio of DNA: " + cgRatio(dna));
        System.out.println("Count of CTG codon: " + countCTG(dna));
    }
}
