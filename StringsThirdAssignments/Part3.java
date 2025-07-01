import edu.duke.*;

public class Part3 {

    // Calculates C-G ratio in a gene
    public double cgRatio(String dna) {
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            char ch = dna.charAt(i);
            if (ch == 'C' || ch == 'G') {
                count++;
            }
        }
        return (double) count / dna.length();
    }

    // Processes a StorageResource of genes
    public void processGenes(StorageResource sr) {
        int countLong = 0;
        int countVeryLong = 0;
        int countHighCG = 0;
        int maxLen = 0;

        for (String gene : sr.data()) {
            // Length > 9
            if (gene.length() > 9) {
                System.out.println("Longer than 9: " + gene);
                countLong++;
            }

            // Length > 60
            if (gene.length() > 60) {
                System.out.println("Longer than 60: " + gene);
                countVeryLong++;
            }

            // High CG Ratio
            if (cgRatio(gene) > 0.35) {
                System.out.println("High CG ratio: " + gene);
                countHighCG++;
            }

            // Track longest gene
            if (gene.length() > maxLen) {
                maxLen = gene.length();
            }
        }

        System.out.println("Number of genes longer than 9: " + countLong);
        System.out.println("Number of genes longer than 60: " + countVeryLong);
        System.out.println("Number of genes with CG ratio > 0.35: " + countHighCG);
        System.out.println("Length of longest gene: " + maxLen);
    }

    // Dummy gene-finding method for test cases
    public StorageResource getGenesFrom(String dna) {
        StorageResource store = new StorageResource();
        // Simple dummy example - just adds some hardcoded gene-like substrings
        // Replace this with your actual gene-finding logic (e.g., from getAllGenes())
        store.add("ATGCGCGTAAGTGA");
        store.add("ATGTAG");
        store.add("ATGCCCCCCCCCGTAA");
        store.add("ATGAAATTTTAA");
        return store;
    }

    public void testProcessGenes() {
        // Example 1: Genes > 9 chars
        System.out.println("Test 1:");
        String dna1 = "ATGCGCGTAAGTGACCCATGCCCCCCCCCGTAA";
        processGenes(getGenesFrom(dna1));

        // Example 2: No genes > 9
        System.out.println("Test 2:");
        String dna2 = "ATGTAGATGTAAATGTGA";
        processGenes(getGenesFrom(dna2));

        // Example 3: High CG ratio
        System.out.println("Test 3:");
        String dna3 = "ATGCGCGCGCGCGTAA";
        processGenes(getGenesFrom(dna3));

        // Example 4: Low CG ratio
        System.out.println("Test 4:");
        String dna4 = "ATGAAATTTTAAATGAAATAA";
        processGenes(getGenesFrom(dna4));

        // Example 5: Real DNA file
        System.out.println("Test 5: Real file data");
        FileResource fr = new FileResource("brca1line.fa");
        String dna5 = fr.asString();
        StorageResource genes = getAllGenes(dna5);  // Make sure this method exists
        processGenes(genes);
    }

    // Add or copy your getAllGenes method from Part1 here
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

    // Helper to find a stop codon index
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
}
