package sort.shell;

import java.util.Date;

import ds.alg.analysis.Stopwatch;
import ds.alg.analysis.TimeAnalysis;
import util.array.ArrayUtility;

public class ShellSortAnalysis {

    public static TimeAnalysis meanTime(String name, int numberOfExecutions, int numberOfElements, int min, int max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            int[] array = ArrayUtility.generateIntArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            ShellSort.sort(array);
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    public static TimeAnalysis meanTimeLong(String name, int numberOfExecutions, int numberOfElements, long min, long max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            long[] array = ArrayUtility.generateLongArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            ShellSort.sort(array);
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    public static TimeAnalysis meanTimeDouble(String name, int numberOfExecutions, int numberOfElements, double min, double max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            double[] array = ArrayUtility.generateDoubleArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            ShellSort.sort(array);
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
        }
        return ta;
    }

    public static TimeAnalysis meanTimeComparable(String name, int numberOfExecutions, int numberOfElements, int min, int max) {
        Stopwatch watch = new Stopwatch();
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            // generate the array
            Comparable<?>[] array = ArrayUtility.generateComparableArray(numberOfElements, min, max);
            watch.startWatch();
            // tested code - start
            ShellSort.sort(array);
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
        System.out.println("  - Method: Shell sort of N integers");
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
        String step = "Step 4: Shell Sort Analysis All Data Types";
        String assignment = "M2B3: Task 2a: Shell Sort Analysis\n";
        Date date = new Date();
        String ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + ran + "\n" + author + "\n" + step + "\n");

        // ============= INT TESTS =============
        System.out.println("=== RUN 1: Medium Int Arrays (1,000-10,000 elements) ===");
        printMeanExecutionTimeGrowthTable(
                30, // numberOfExecutions
                1000, // minArrayLength  
                1000, // arrayIncrementLength
                10000, // maxArrayLength
                1, // minArrayValue
                100000 // maxArrayValue
        );

        System.out.println("\n");

        System.out.println("=== RUN 2: Large Int Arrays (10,000-50,000 elements) ===");
        printMeanExecutionTimeGrowthTable(
                30, // numberOfExecutions
                10000, // minArrayLength
                5000, // arrayIncrementLength  
                50000, // maxArrayLength
                1, // minArrayValue
                100000 // maxArrayValue
        );

// I know these are very ugly copy-pastes but I'm out of time to refactor them into pretty methods
        // ============= LONG TESTS =============
        System.out.println("\n=== RUN 3: Medium Long Arrays (1,000-10,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N longs");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 1000; n <= 10000; n += 1000) {
            String name = "Sort " + n + " longs";
            TimeAnalysis ta = meanTimeLong(name, 30, n, 1L, 100000L);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");

        System.out.println("\n=== RUN 4: Large Long Arrays (10,000-50,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N longs");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 10000; n <= 50000; n += 5000) {
            String name = "Sort " + n + " longs";
            TimeAnalysis ta = meanTimeLong(name, 30, n, 1L, 100000L);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");

        // ============= DOUBLE TESTS =============
        System.out.println("\n=== RUN 5: Medium Double Arrays (1,000-10,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N doubles");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 1000; n <= 10000; n += 1000) {
            String name = "Sort " + n + " doubles";
            TimeAnalysis ta = meanTimeDouble(name, 30, n, 1.0, 100000.0);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");

        System.out.println("\n=== RUN 6: Large Double Arrays (10,000-50,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N doubles");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 10000; n <= 50000; n += 5000) {
            String name = "Sort " + n + " doubles";
            TimeAnalysis ta = meanTimeDouble(name, 30, n, 1.0, 100000.0);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");

        System.out.println("\n=== RUN 7: Medium Comparable Arrays (1,000-10,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N comparable objects");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 1000; n <= 10000; n += 1000) {
            String name = "Sort " + n + " comparable objects";
            TimeAnalysis ta = meanTimeComparable(name, 30, n, 1, 100000);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");

        System.out.println("\n=== RUN 8: Large Comparable Arrays (10,000-50,000 elements) ===");
        System.out.println("Mean execution time growth table");
        System.out.println("  - Method: Shell sort of N comparable objects");
        System.out.println("  - Sample size for time estimation: 30");
        System.out.println("|-----------|--------|------|------|------------------|");
        System.out.println("|         N |   Mean |  Min |  Max |         CI 99.9% |");
        System.out.println("|-----------|--------|------|------|------------------|");
        for (int n = 10000; n <= 50000; n += 5000) {
            String name = "Sort " + n + " comparable objects";
            TimeAnalysis ta = meanTimeComparable(name, 30, n, 1, 100000);
            System.out.printf("| %9d | %6.1f | %4d | %4d | (%6.1f, %6.1f) |\n", n, ta.getMeanTime(),
                    ta.getMinTime(), ta.getMaxTime(), ta.getMinMean999Confidence(), ta.getMaxMean999Confidence());
        }
        System.out.println("|-----------|--------|------|------|------------------|");
    }

}
