
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa,String stringb){
        int count = 0;
        int index = 0;
        while(true){
            index = stringb.indexOf(stringa,index);
            if(index ==-1)
            {
                break;
            }
            count++;
            index = index + stringa.length();
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println("Test 1: " + howMany("GAA", "ATGAACGAATTGAATC")); // 3
        System.out.println("Test 2: " + howMany("AA", "ATAAAA"));            // 2
        System.out.println("Test 3: " + howMany("A", "ATAAAA"));             // 5
        System.out.println("Test 4: " + howMany("GG", "GGGGG"));             // 2
        System.out.println("Test 5: " + howMany("XYZ", "XYZXYZXYZ"));        // 3
        System.out.println("Test 6: " + howMany("Z", "ABCDEFG"));            // 0
        System.out.println("Test 7: " + howMany("", "ABCDEFG"));             // 0
    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testHowMany();
    }

}
