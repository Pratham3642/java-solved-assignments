/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    
    public void printYoutubeLinks(){
        
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word : url.lines()){
            String lowerWord = word.toLowerCase();
            if(lowerWord.contains("youtube.com")){
                int start = word.indexOf("\"");
                int end  = word.lastIndexOf("\"");
                
                if(start != -1 && end != -1 && end > start){
                    String link = word.substring(start+1,end);
                    System.out.println(link);
                }
            }
        }
    }
    
    public static void main(String[] args){
        Part4 p4 = new Part4();
        p4.printYoutubeLinks();
    }
}
