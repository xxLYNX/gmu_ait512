package sort.select;

import ds.alg.analysis.Stopwatch;
import ds.alg.analysis.TimeAnalysis;
import util.array.ArrayUtility;
import java.util.Date;

/**
 * Provides methods to analyze the execution time of the selection sort
 * algorithm for different data types and array sizes.
 *
 * @author Cullen Kelley
 */
public class SelectionSortAnalysis {

    public static TimeAnalysis meanTime(String name, int numberOfExecutions, int numberOfElements, int min, int max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            int[] array = ArrayUtility.generateIntArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            SelectionSort.sort(array);
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    public static void printMeanExecutionTimeGrowthTable(int numberOfExecutions,
            int minArrayLength, int arrayIncrementLength, int maxArrayLength,
            int minArrayValue, int maxArrayValue) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Selection sort of N integers");
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

    /**
     * Measures the mean execution time for sorting arrays of longs
     */
    public static TimeAnalysis meanTimeLong(String name, int numberOfExecutions, int numberOfElements, long min, long max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            long[] array = ArrayUtility.generateLongArray(numberOfElements, min, max);
            watch.startWatch();
            SelectionSort.sort(array);
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    /**
     * Measures the mean execution time for sorting arrays of doubles
     */
    public static TimeAnalysis meanTimeDouble(String name, int numberOfExecutions, int numberOfElements, double min, double max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            double[] array = ArrayUtility.generateDoubleArray(numberOfElements, min, max);
            watch.startWatch();
            SelectionSort.sort(array);
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    /**
     * Measures the mean execution time for sorting arrays of Comparable objects
     */
    public static TimeAnalysis meanTimeComparable(String name, int numberOfExecutions, int numberOfElements, int min, int max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            Comparable<Integer>[] array = ArrayUtility.generateComparableArray(numberOfElements, min, max);
            watch.startWatch();
            SelectionSort.sort(array);
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    /**
     * Prints execution time growth table for long arrays
     */
    public static void printMeanExecutionTimeGrowthTableLong(int numberOfExecutions,
            int minArrayLength, int arrayIncrementLength, int maxArrayLength,
            long minArrayValue, long maxArrayValue) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Selection sort of N longs");
        System.out.println("  - Sample size for time estimation: " + numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = minArrayLength; n <= maxArrayLength; n += arrayIncrementLength) {
            String name = "Sort " + n + " longs";
            TimeAnalysis ta = meanTimeLong(name, numberOfExecutions, n, minArrayValue, maxArrayValue);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    /**
     * Prints execution time growth table for double arrays
     */
    public static void printMeanExecutionTimeGrowthTableDouble(int numberOfExecutions,
            int minArrayLength, int arrayIncrementLength, int maxArrayLength,
            double minArrayValue, double maxArrayValue) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Selection sort of N doubles");
        System.out.println("  - Sample size for time estimation: " + numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = minArrayLength; n <= maxArrayLength; n += arrayIncrementLength) {
            String name = "Sort " + n + " doubles";
            TimeAnalysis ta = meanTimeDouble(name, numberOfExecutions, n, minArrayValue, maxArrayValue);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    /**
     * Prints execution time growth table for Comparable Object arrays See
     * formal Java docs for more info I'm not writing it again here
     */
    public static void printMeanExecutionTimeGrowthTableComparable(int numberOfExecutions,
            int minArrayLength, int arrayIncrementLength, int maxArrayLength,
            int minArrayValue, int maxArrayValue) {
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Selection sort of N Comparable objects");
        System.out.println("  - Sample size for time estimation: " + numberOfExecutions);
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = minArrayLength; n <= maxArrayLength; n += arrayIncrementLength) {
            String name = "Sort " + n + " Comparable objects";
            TimeAnalysis ta = meanTimeComparable(name, numberOfExecutions, n, minArrayValue, maxArrayValue);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

    /**
     * Main method for running analysis
     */
    public static void main(String[] args) {
        String step = "Step 4: main method";
        String assignment = "M2B1: Task 3: Selection Sort Analysis\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n" + step + "\n");

        // Task 3a: Integer analysis
        //System.out.println("=== Task 3a: Integer Array Analysis ===");
        //System.out.println("Run 2:");
        //printMeanExecutionTimeGrowthTable(50, 100, 100, 1000, 1, 100);
        //System.out.println("\nRun 2:");
        //printMeanExecutionTimeGrowthTable(50, 100, 100, 1000, 1, 100);
        // Task 3b: Long analysis
        //System.out.println("\n=== Task 3b: Long Array Analysis ===");
        //printMeanExecutionTimeGrowthTableLong(50, 100, 100, 1000, 1L, 100L);
        // Task 3c: Double analysis
        System.out.println("\n=== Task 3c: Double Array Analysis ===");
        printMeanExecutionTimeGrowthTableDouble(50, 100, 100, 1000, 1.0, 100.0);
        // Task 3d: Comparable analysis
        //System.out.println("\n=== Task 3d: Comparable Object Analysis ===");
        //printMeanExecutionTimeGrowthTableComparable(50, 100, 100, 1000, 1, 100);
    }

}
