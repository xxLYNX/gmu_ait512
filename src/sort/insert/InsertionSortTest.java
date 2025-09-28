package sort.insert;

import java.util.Date;
import util.array.ArrayUtility;
import ds.alg.analysis.Stopwatch;

public class InsertionSortTest {

    /**
     * Tests insertion sort with arrays of different sizes
     */
    public static void testInsertionSortInt() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            int[] array;
            if (size == 0) {
                array = new int[0];
            } else {
                array = ArrayUtility.generateIntArray(size, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            // The following steps show the array before and after sorting, and check if sorted correctly
            // Print before sorting
            System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Sort the array & start timing
            Stopwatch.startWatch();
            InsertionSort.sort(array);
            Stopwatch.stopWatch();

            // Print after sorting
            System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Check if sorted correctly
            boolean isSorted = ArrayUtility.isSorted(array);
            System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
        }
    }

    public static void testInsertionSortLong() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            long[] array;
            if (size == 0) {
                array = new long[0];
            } else {
                array = ArrayUtility.generateLongArray(size, Long.MIN_VALUE, Long.MAX_VALUE);
            }
            // The following steps show the array before and after sorting, and check if sorted correctly
            // Print before sorting
            System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Sort the array
            InsertionSort.sort(array);

            // Print after sorting
            System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Check if sorted correctly
            boolean isSorted = ArrayUtility.isSorted(array);
            System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
        }
    }

    public static void testInsertionSortDouble() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            double[] array;
            if (size == 0) {
                array = new double[0];
            } else {
                array = ArrayUtility.generateDoubleArray(size, Double.MIN_VALUE, Double.MAX_VALUE);
            }
            // The following steps show the array before and after sorting, and check if sorted correctly
            // Print before sorting
            System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Sort the array
            InsertionSort.sort(array);

            // Print after sorting
            System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Check if sorted correctly
            boolean isSorted = ArrayUtility.isSorted(array);
            System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
        }
    }

    public static void testInsertionSortObject() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            Comparable[] array;
            if (size == 0) {
                array = new Comparable[0];
            } else {
                array = ArrayUtility.generateComparableArray(size, -3000, 3000);
            }
            // The following steps show the array before and after sorting, and check if sorted correctly
            // Print before sorting
            System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Sort the array
            InsertionSort.sort(array);

            // Print after sorting
            System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));

            // Check if sorted correctly
            boolean isSorted = ArrayUtility.isSorted(array);
            System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
        }
    }

    /**
     * Helper method to check if a long array is sorted
     */
    private static boolean isSortedLong(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String step = "Step 6: Adapt for long, double, and comparable objects";
        String assignment = "M2B2: Task 1d: Basic implementation for Insertion Sort\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n" + step + "\n");

        // Call the insertion sort test for Comparable objects
        testInsertionSortInt();
    }

}
