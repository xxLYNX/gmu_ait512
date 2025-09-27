package sort.select;

import java.util.Date;
import util.array.ArrayUtility;

/**
 * Provides methods to test the selection sort algorithm with different data
 * types.
 *
 * @author Cullen Kelley
 */
public class SelectionSortTest {

    /**
     * Tests the selection sort with arrays of ints.
     */
    public static void testSelectionSort() {
        int[] a10 = ArrayUtility.generateIntArray(10, 1, 20);
        System.out.printf("Random array with 10 elements, from 1 to 20: \n%s\n", ArrayUtility.toString(a10, "{", ", ", "}"));
        SelectionSort.sort(a10);
        System.out.printf("Sorted array : \n%s\n", ArrayUtility.toString(a10, "{", ", ", "}"));
    }

    /**
     * Tests the selection sort with arrays of longs.
     */
    public static void testSelectionSortLong() {
        int[] sizes = {0, 1, 2, 5, 10};
        System.out.println("\n=== Task 2b: Testing Long Arrays ===");
        for (int size : sizes) {
            long[] arr = ArrayUtility.generateLongArray(size, 1L, 100L);
            System.out.printf("long[%d] before: %s\n", size, java.util.Arrays.toString(arr));
            SelectionSort.sort(arr);
            System.out.printf("long[%d] after:  %s\n", size, java.util.Arrays.toString(arr));
        }
    }

    /**
     * Tests the selection sort with arrays of doubles.
     */
    public static void testSelectionSortDouble() {
        int[] sizes = {0, 1, 2, 5, 10};
        System.out.println("\n=== Task 2c: Double Arrays ===");
        for (int size : sizes) {
            double[] arr = ArrayUtility.generateDoubleArray(size, 1.0, 100.0);
            System.out.printf("double[%d] before: %s\n", size, java.util.Arrays.toString(arr));
            SelectionSort.sort(arr);
            System.out.printf("double[%d] after:  %s\n", size, java.util.Arrays.toString(arr));
        }
    }

    /**
     * Tests the selection sort with arrays of Comparable objects (Strings).
     */
    public static void testSelectionSortComparable() {
        int[] sizes = {0, 1, 2, 5, 10};
        System.out.println("\n=== Task 2d: Comparable Objects ===");
        for (int size : sizes) {
            String[] arr = new String[size];
            for (int i = 0; i < size; i++) {
                arr[i] = "S" + (int) (Math.random() * 100);
            }
            System.out.printf("String[%d] before: %s\n", size, java.util.Arrays.toString(arr));
            SelectionSort.sort(arr);
            System.out.printf("String[%d] after:  %s\n", size, java.util.Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        String step = "Step 4: main method";
        String assignment = "M2B1: Task 3a: Selection Sort Analysis\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n" + step + "\n");

        // Task 2a: int arrays (already working)
        //System.out.println("=== Task 2a: Int Arrays ===");
        //testSelectionSort();
        // Task 2b: long arrays
        //testSelectionSortLong();
        // Task 2c: double arrays
        testSelectionSortDouble();
        // Task 2d: comparable objects
        //testSelectionSortComparable();
    }

}
