package sort.shell;

import java.util.Date;
import util.array.ArrayUtility;

public class ShellSortTest {

    /**
     * Helper method to print int array test results
     */
    private static void printTestInt(int[] array, int size) {
        System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        ShellSort.sort(array);
        System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        boolean isSorted = ArrayUtility.isSorted(array);
        System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
    }

    /**
     * Helper method to print long array test results
     */
    private static void printTestLong(long[] array, int size) {
        System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        ShellSort.sort(array);
        System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        boolean isSorted = ArrayUtility.isSorted(array);
        System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
    }

    /**
     * Helper method to print double array test results
     */
    private static void printTestDouble(double[] array, int size) {
        System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        ShellSort.sort(array);
        System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        boolean isSorted = ArrayUtility.isSorted(array);
        System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
    }

    /**
     * Helper method to print Comparable array test results
     */
    private static void printTestComparable(Comparable<?>[] array, int size) {
        System.out.printf("Array[%d] before: %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        ShellSort.sort(array);
        System.out.printf("Array[%d] after:  %s\n", size, ArrayUtility.toString(array, "[", ", ", "]"));
        boolean isSorted = ArrayUtility.isSorted(array);
        System.out.printf("Array[%d] is sorted: %s\n\n", size, isSorted);
    }

    /**
     * Tests shell sort with int arrays of different sizes
     */
    public static void testShellSortInt() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size and generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            int[] array;
            if (size == 0) {
                array = new int[0];
            } else {
                array = ArrayUtility.generateIntArray(size, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            // Use helper method to test and print results
            printTestInt(array, size);
        }
    }

    /**
     * Tests shell sort with long arrays of different sizes
     */
    public static void testShellSortLong() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size and generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            long[] array;
            if (size == 0) {
                array = new long[0];
            } else {
                array = ArrayUtility.generateLongArray(size, Long.MIN_VALUE, Long.MAX_VALUE);
            }

            // Use helper method to test and print results
            printTestLong(array, size);
        }
    }

    /**
     * Tests shell sort with double arrays of different sizes
     */
    public static void testShellSortDouble() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size and generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            double[] array;
            if (size == 0) {
                array = new double[0];
            } else {
                array = ArrayUtility.generateDoubleArray(size, Double.MIN_VALUE, Double.MAX_VALUE);
            }

            // Use helper method to test and print results
            printTestDouble(array, size);
        }
    }

    /**
     * Tests shell sort with Comparable object arrays of different sizes
     */
    public static void testShellSortComparable() {
        // Define array sizes to test
        int[] sizes = {0, 1, 2, 5, 10};

        // Iterate through each size and generate a corresponding array of random values
        for (int size : sizes) {
            // Generate random array
            Comparable<?>[] array;
            if (size == 0) {
                array = new Comparable[0];
            } else {
                array = ArrayUtility.generateComparableArray(size, -3000, 3000);
            }

            // Use helper method to test and print results
            printTestComparable(array, size);
        }
    }

    public static void main(String[] args) {
        String step = "Step 5: Shell Sort Tests for All Data Types";
        String assignment = "M2B3: Task d: Basic implementation for Shell Sort\n";
        Date date = new Date();
        String ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + ran + "\n" + author + "\n" + step + "\n");

        // Test shell sort with int arrays
        //System.out.println("=== Testing Shell Sort with INT Arrays ===");
        //testShellSortInt();
        //System.out.println("=== Testing Shell Sort with LONG Arrays ===");
        //testShellSortLong();
        //System.out.println("=== Testing Shell Sort with DOUBLE Arrays ===");
        //testShellSortDouble();
        System.out.println("=== Testing Shell Sort with COMPARABLE Arrays ===");
        testShellSortComparable();
    }

}
