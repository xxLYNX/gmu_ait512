/**
 * Stopwatch utility for measuring elapsed time.
 *
 * @author Cullen Kelley
 *
 */
package ds.alg.analysis;

public class Stopwatch {

    /**
     * Saves the start time when the stopwatch is started (also restarted).
     */
    private long startTime;

    /**
     * Initializes the start time to the current time.
     */
    public void startWatch() {
        startTime = System.currentTimeMillis();
    }
}
