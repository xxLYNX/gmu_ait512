/**
 * Test client for Stopwatch utility for measuring elapsed time.
 *
 * @author Cullen Kelley
 *
 */
package ds.alg.analysis;

import java.util.Date;

/**
 * Test client for Stopwatch class.
 */
public class TestStopwatch {

    public static void main(String[] args) {
        String assignment = "M2A1: Task 3 Time analysis class\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n");

        Stopwatch w = new Stopwatch();
        double sum = 0.0;
        for (int i = 1; i <= 2000000000; i++) {
            sum += i;
        }
        long t = w.elapsedTime();
        System.out.println("Computes the sum of the first billion integers as a double: " + sum);
        System.out.println("Sum  (double) = " + sum);
        System.out.println("Time (mili-s) = " + t);
    }

}
