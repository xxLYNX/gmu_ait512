/**
 * Time analysis class.
 *
 * @author Cullen Kelley
 *
 */
package ds.alg.analysis;

import ds.bag.FixedCapacityBag;
import java.util.Date;
import java.util.Iterator;

public class TimeAnalysis extends FixedCapacityBag<Long> {

    /**
     * The name of the method/algorithm being analyzed.
     */
    private String name;

    /**
     * Whether the statistics were already computed.
     */
    private boolean statComputed = false;

    /**
     * The minimum execution time.
     */
    private long min;

    /**
     * The maximum execution time.
     */
    private long max;

    /**
     * The sum of all execution times.
     */
    private double sum;

    /**
     * The mean execution time.
     */
    private double mean;

    /**
     * The sample variance of the execution time.
     */
    private double variance;

    /**
     * The sample standard deviation of the execution time.
     */
    private double standardDeviation;

    /**
     * The minimum mean execution time with 99.9% confidence. Left value of the
     * confidence interval.
     */
    private double minMean999Confidence;

    /**
     * The maximum mean execution time with 99.9% confidence. Right value of the
     * confidence interval.
     */
    private double maxMean999Confidence;

    /**
     * Creates a time analysis class for the provided number of executions.
     *
     * @param name - the name of the method/algorithm tested
     * @param numberOfExecutions - how many execution times will be recorded
     */
    public TimeAnalysis(String name, int numberOfExecutions) {
        super(numberOfExecutions);
        if (numberOfExecutions < 1) {
            throw new RuntimeException("At least one execution expected.");
        }
        this.name = name;
    }

    /**
     * Creates a time analysis class for the provided execution times.
     *
     * @param name - the name of the method/algorithm tested
     * @param executionTimes - an array with the execution times to be analyzed.
     */
    public TimeAnalysis(String name, long[] executionTimes) {
        this(name, executionTimes.length);
        for (long v : executionTimes) {
            add(v);
        }
        computeStatistics();
    }

    /**
     * Checks if all the executions were performed. If not, throws a
     * RuntimeException.
     */
    private void checkExecutionEnded() {
        if (size() < getCapacity()) {
            throw new RuntimeException(
                    "Not all experiments performed (only " + size() + " out of " + getCapacity() + ")");
        }
    }

    /**
     * Computes the following statistics if the executions ended and they are
     * not already computed:
     * <ul>
     * <li>Minimum execution time</li>
     * <li>Maximum execution time</li>
     * <li>Mean execution time</li>
     * <li>Sample standard deviation</li>
     * <li>99.9% confidence interval</li>
     * </ul>
     */
    private void computeStatistics() {
        checkExecutionEnded();
        if (statComputed) {
            return;
        }
        Iterator<Long> iterator = iterator();
        long firstValue = iterator.next();
        min = firstValue;
        max = firstValue;
        sum = firstValue;
        while (iterator.hasNext()) {
            long value = iterator.next();
            if (min > value) {
                min = value;
            } else if (max < value) {
                max = value;
            }
            sum += value;
        }
        mean = sum / size();

        variance = 0;
        for (long value : this) {
            variance += (value - mean) * (value - mean);
        }
        variance = variance / (getCapacity() - 1);
        standardDeviation = Math.sqrt(variance);

        double e = tValue999(getCapacity() - 1) * standardDeviation / Math.sqrt(getCapacity());
        minMean999Confidence = mean - e;
        maxMean999Confidence = mean + e;

        statComputed = true;
    }

    /**
     * Add a new execution time to the analysis.
     *
     * @param time the execution time in milliseconds as a long
     */
    public long getMinTime() {
        computeStatistics();
        return min;
    }

    /**
     * Compute (if needed) and return the maximum execution time recorded.
     *
     * @return the maximum execution time in milliseconds as a long
     */
    public long getMaxTime() {
        computeStatistics();
        return max;
    }

    /**
     * Compute (if needed) and return the mean execution time.
     *
     * @return the mean execution time in milliseconds as a double
     */
    public double getMeanTime() {
        computeStatistics();
        return mean;
    }

    /**
     * Compute (if needed) and return the sample standard deviation of the
     * execution time.
     *
     * @return the standard deviation of the execution time in milliseconds as a
     * double
     */
    public double getTimeStandardDeviation() {
        computeStatistics();
        return standardDeviation;

    }

    /**
     * Compute (if needed) and return the estimated minimum mean execution time
     * with 99.9% confidence.
     *
     * @return the estimated minimum mean execution time in milliseconds as a
     * double
     */
    public double getMinMean999Confidence() {
        computeStatistics();
        return minMean999Confidence;
    }

    /**
     * Compute (if needed) and return the estimated maximum mean execution time
     * with 99.9% confidence.
     *
     * @return the estimated maximum mean execution time in milliseconds as a
     * double
     */
    public double getMaxMean999Confidence() {
        computeStatistics();
        return maxMean999Confidence;
    }

    /**
     * Print the statistics to the standard output.
     */
    public void printStatistics() {
        computeStatistics();
        System.out.println("Execution Time Analysis for: " + name);
        System.out.println("  - Sample number of values       = " + size());
        System.out.println("  - Sample minimum execution time = " + min);
        System.out.println("  - Sample maximum execution time = " + max);
        System.out.println("  - Sample mean value             = " + mean);
        System.out.println("  - Sample standard deviation     = " + standardDeviation);
        System.out.println(
                "  - Mean 99.9% confidence interval: (" + minMean999Confidence + ", " + maxMean999Confidence + ")");
    }

    /**
     * t test values for 99.9% confidence level for degree of freedoms from 1
     * (index 0) to 30 (index 29).
     */
    public static final double t999[] = {636.6, 31.60, 12.92, 8.610, 6.869, 5.959, 5.408, 5.041, 4.781, 4.587, 4.437,
        4.318, 4.221, 4.140, 4.073, 4.015, 3.965, 3.922, 3.883, 3.850, 3.819, 3.792, 3.768, 3.745, 3.725, 3.707,
        3.690, 3.674, 3.659, 3.646};

    /**
     * Returns the t test value for the provided degree of freedoms and 99.9%
     * confidence level.
     *
     * @param df the degree of freedoms (at least 1)
     * @return the t test value as a double
     */
    private static double tValue999(int df) {
        if (df < 1) {
            throw new RuntimeException("Invalid degree of freedoms");
        }
        if (df >= 1000) {
            return 3.3;
        }
        if (df >= 100) {
            return 3.39;
        }

        if (df >= 80) {
            return 3.416;
        }
        if (df >= 60) {
            return 3.460;
        }
        if (df >= 50) {
            return 3.496;
        }
        if (df >= 40) {
            return 3.551;
        }
        if (df >= 30) {
            return 3.646;
        }
        return t999[df - 1];
    }

    /**
     * Test client for TimeAnalysis class.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        String assignment = "M2A1: Task 3 Time analysis class\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n");

        long[] a = {1025, 1017, 1031, 1009, 1005, 1022};
        TimeAnalysis ta = new TimeAnalysis("test", a);
        ta.printStatistics();
    }

}

// 636.6, 31.60, 12.92, 8.610, 6.869, 5.959, 5.408, 5.041, 4.781, 4.587, 4.437, 4.318, 4.221, 4.140, 4.073, 
// 4.015, 3.965, 3.922, 3.883, 3.850, 3.819, 3.792, 3.768, 3.745, 3.725, 3.707, 3.690, 3.674, 3.659, 3.646
