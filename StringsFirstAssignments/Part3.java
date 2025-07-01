
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa,String stringb){
        int firstOccurrence = stringb.indexOf(stringa);
        if(firstOccurrence == -1){
            return false;
        }
        int secondOccurrence = stringb.indexOf(stringa,firstOccurrence+stringa.length());
        return secondOccurrence != -1;
        
        
    }
    
    public String lastPart(String stringa, String stringb){
        int firstOccurrence = stringb.indexOf(stringa);
        if(firstOccurrence == -1){
            return stringb;
        }

        return stringb.substring(firstOccurrence+stringa.length());
    }
    
    public void testing(){
         // Testing twoOccurrences
        System.out.println("Testing twoOccurrences:");
        System.out.println("Result: " + twoOccurrences("by", "A story by Abby Long")); // true
        System.out.println("Result: " + twoOccurrences("a", "banana")); // true
        System.out.println("Result: " + twoOccurrences("atg", "ctgtatgta")); // false

        // Testing lastPart
        System.out.println("\nTesting lastPart:");
        System.out.println("The part of the string after 'an' in 'banana' is: " + lastPart("an", "banana"));
        System.out.println("The part of the string after 'zoo' in 'forest' is: " + lastPart("zoo", "forest"));
        System.out.println("The part of the string after 'by' in 'A story by Abby Long' is: " + lastPart("by", "A story by Abby Long"));
    }
    
    public static void main(String [] args){
        Part3 p = new Part3();
        p.testing();
    }
}
