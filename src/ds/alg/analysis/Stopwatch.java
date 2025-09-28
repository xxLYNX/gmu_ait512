/**
 * Stopwatch utility for measuring elapsed time.
 *
 * @author Cullen Kelley
 *
 */
package ds.alg.analysis;

/**
 * Stopwatch utility for measuring elapsed time.
 *
 * @author Cullen Kelley
 *
 */
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

    /**
     * Constructor that initializes stopwatch and starts it (tracks in
     * miliseconds).
     */
    public Stopwatch() {
        startWatch();
    }

    /**
     * Returns the elapsed time (in milliseconds) since the stopwatch was
     * started.
     *
     * @return elapsed time in milliseconds as {@code long}
     */
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

}
