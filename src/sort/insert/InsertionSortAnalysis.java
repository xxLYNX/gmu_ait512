package sort.insert;

import java.util.Date;

import ds.alg.analysis.Stopwatch;
import ds.alg.analysis.TimeAnalysis;
import util.array.ArrayUtility;

/**
 * Provides methods to analyze the execution time of the insertion sort
 * algorithm for different data types and array sizes.
 *
 * @author Cullen Kelley
 */
public class InsertionSortAnalysis {

    public static TimeAnalysis meanTime(String name, int numberOfExecutions, int numberOfElements, int min, int max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            Comparable[] array = ArrayUtility.generateComparableArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            InsertionSort.sort(array);
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    /**
     * Prints a table showing the growth of the mean execution time for
     * insertion sort as the array size increases.
     *
     * @param numberOfExecutions How many times the sorting is repeated to get
     * an estimation of the mean time.
     * @param minArrayLength The minimum size of the array to be sorted.
     * @param arrayIncrementLength The increment in size between successive
     * arrays.
     * @param maxArrayLength The maximum size of the array to be sorted.
     * @param minArrayValue The minimum value for elements in the array.
     * @param maxArrayValue The maximum value for elements in the array.
     */
    public static void printMeanExecutionTimeGrowthTable(
            int numberOfExecutions,
            int minArrayLength,
            int arrayIncrementLength,
            int maxArrayLength,
            int minArrayValue,
            int maxArrayValue
    ) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Insertion sort of N Objects");
        System.out.println("  - Sample size for time estimation: " + numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = minArrayLength; n <= maxArrayLength; n += arrayIncrementLength) {
            String name = "Sort " + n + " integers";
            TimeAnalysis ta = meanTime(name, numberOfExecutions, n, minArrayValue, maxArrayValue);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    public static void main(String[] args) {
        String step = "Step 4-5: Adapt for int, long, double, and comparable objects";
        String assignment = "M2B2: Task 2d: Insertion Sort Analysis Objects (dates)\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n" + step + "\n");

        // First run: Medium arrays to start seeing timing differences
        System.out.println("=== RUN 1: Medium Arrays (1,000-10,000 elements) ===");
        printMeanExecutionTimeGrowthTable(
                30, // numberOfExecutions
                1000, // minArrayLength  
                1000, // arrayIncrementLength
                10000, // maxArrayLength
                1, // minArrayValue
                100000 // maxArrayValue
        );

        System.out.println("\n");

        // Second run: Large arrays to clearly see quadratic growth
        System.out.println("=== RUN 2: Large Arrays (10,000-50,000 elements) ===");
        printMeanExecutionTimeGrowthTable(
                30, // numberOfExecutions (fewer trials for larger arrays)
                10000, // minArrayLength
                5000, // arrayIncrementLength  
                50000, // maxArrayLength
                1, // minArrayValue
                100000 // maxArrayValue
        );

    }

}
