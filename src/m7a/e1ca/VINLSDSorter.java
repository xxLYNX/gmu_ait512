package m7a.e1ca;

/**
 * LSD (Least Significant Digit) String Sorter for Vehicle Identification
 * Numbers Implements LSD Sort algorithm optimized for VIN sorting VINs are
 * 17-character fixed-length strings containing digits 0-9 and letters A-Z
 * Processes VINs from rightmost character to leftmost character
 *
 * @author Cullen Kelley
 * @version 1.0
 */
public class VINLSDSorter {

    private String[] data;
    private int vinLength;
    private static final int ALPHABET_SIZE = 256; // ASCII character set for VIN characters

    /**
     * Constructor - initialize with VIN array
     *
     * @param input array of VIN strings (17 characters each)
     */
    public VINLSDSorter(String[] input) {
        this.data = input.clone();
        this.vinLength = data[0].length(); // Standard VIN length is 17
    }

    /**
     * Execute LSD Sort algorithm on VIN array Processes each character position
     * from right (position 16) to left (position 0) After each position is
     * sorted, relative order from previous positions is maintained
     *
     * @return sorted array of VINs in lexicographic order
     */
    public String[] sort() {
        // Process each character position from right to left
        // This ensures complete lexicographic sorting after all passes
        for (int pos = vinLength - 1; pos >= 0; pos--) {
            countingSort(pos);
        }
        return data;
    }

    /**
     * Counting sort for a specific character position in VINs Groups VINs by
     * their character at the given position Uses stable sorting to preserve
     * order from previous positions
     *
     * Algorithm phases: Phase 1: Count occurrences of each character at
     * position Phase 2: Convert counts to starting indices (cumulative sum)
     * Phase 3: Place VINs into auxiliary array using indices Phase 4: Copy
     * sorted data back to original array
     *
     * @param position character position to sort by (0-indexed from left)
     */
    private void countingSort(int position) {
        // PHASE 1: Count occurrences of each character at this position
        int[] count = new int[ALPHABET_SIZE];
        for (String vin : data) {
            count[vin.charAt(position)]++;
        }

        // PHASE 2: Convert counts to starting indices (cumulative sum)
        // This creates groups for each character: 0-9 and A-Z
        int[] startIndex = new int[ALPHABET_SIZE];
        startIndex[0] = 0;
        for (int i = 1; i < ALPHABET_SIZE; i++) {
            startIndex[i] = startIndex[i - 1] + count[i - 1];
        }

        // PHASE 3: Place VINs into auxiliary array using calculated indices
        // Maintains stable sort by processing original array left-to-right
        String[] aux = new String[data.length];
        int[] currentIndex = startIndex.clone();

        for (String vin : data) {
            char charAtPos = vin.charAt(position);
            int idx = currentIndex[charAtPos];
            aux[idx] = vin;
            currentIndex[charAtPos]++;
        }

        // PHASE 4: Copy sorted array back to data for next position
        data = aux;
    }
}
