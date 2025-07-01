
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public String findProtein(String dna){
        dna = dna.toLowerCase();
        int start = dna.indexOf("atg");
        if(start == -1){
            return "";
        }
        int end = dna.indexOf("tag",start+3);
        if((end-start)%3 == 0){
            return dna.substring(start,end+3);
        }
        else{
            return "";
        }
    }

    public void testing(){
        String a = "CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT";
        String ap = "ATGGGGTTTAAATAATAATAG";
        String result = findProtein(a);
        if(ap.toLowerCase().equals(result)){
             System.out.println("success for " + ap + " length " + ap.length());
        }
        else{
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }
}