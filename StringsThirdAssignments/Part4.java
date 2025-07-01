import edu.duke.*;

public class Part4 {
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;

        while (true) {
            int startCodon = dna.indexOf("ATG", startIndex);
            if (startCodon == -1) break;

            int taa = findStopCodon(dna, startCodon, "TAA");
            int tag = findStopCodon(dna, startCodon, "TAG");
            int tga = findStopCodon(dna, startCodon, "TGA");

            int minIndex = Math.min(Math.min(taa, tag), tga);
            if (minIndex == dna.length()) {
                startIndex = startCodon + 3;
            } else {
                geneList.add(dna.substring(startCodon, minIndex + 3));
                startIndex = minIndex + 3;
            }
        }
        return geneList;
    }

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testCountGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource genes = getAllGenes(dna);
        System.out.println("Number of genes found: " + genes.size());
    }

    public static void main(String[] args) {
        Part4 gc = new Part4();
        gc.testCountGenes();
    }
}
