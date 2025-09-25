package ds.alg.analysis;

import java.util.Date;

public class TestTimeAnalysis {

    /**
     * Analyzes the execution time for the addition of the first n integers (1 +
     * 2+ ... + maxValueAdded).
     *
     * @param numberOfExecutions How many times the operation is repeated to get
     * an estimation of the mean time.
     * @param maxValueAdded The value up which the numbers are added
     */
    public static TimeAnalysis testAddition(int numberOfExecutions, int maxValueAdded) {
        Stopwatch watch = new Stopwatch();
        String name = "Add the first " + maxValueAdded + " integers";
        TimeAnalysis ta = new TimeAnalysis(name, numberOfExecutions);
        for (int trial = 0; trial < numberOfExecutions; trial++) {
            watch.startWatch();
            // tested code - start
            @SuppressWarnings("unused")
            double sum = 0;
            for (int i = 1; i <= maxValueAdded; i++) {
                sum += i;
            }
            // tested code - end
            long time = watch.elapsedTime();
            ta.add(time);
            //added this to ensure sum is used
            if (sum == -1) {
                System.out.println("Impossible");
            }
            //end addition
        }
        return ta;
    }

    /**
     * Test the stopwatch with computing the sum of the first 10 millions
     * integers as a double.
     *
     * @param args - not used
     */
    public static void main(String[] args) {
        String assignment = "M2A1: Task 4 Time analysis comparison based on number of trials\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n");

        //Leftover from Task 3
        //TimeAnalysis ta11 = testAddition(11, 10000000);
        //ta11.printStatistics();
        //Task 4 additions
        TimeAnalysis ta11 = testAddition(11, 10000000);
        ta11.printStatistics();
        TimeAnalysis ta101 = testAddition(101, 10000000);
        ta101.printStatistics();
        TimeAnalysis ta1001 = testAddition(1001, 10000000);
        ta1001.printStatistics();

    }
}
