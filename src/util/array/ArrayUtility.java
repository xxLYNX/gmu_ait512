package util.array;

/**
 * Utility methods for working with integer arrays.
 *
 * @author Cullen Kelley
 */
public class ArrayUtility {

    /**
     * Converts an array of integers to a string with custom start, separator,
     * and end.
     *
     * @param array the array to convert
     * @param start the string to start with
     * @param separator the separator between elements
     * @param end the string to end with
     * @return the formatted string
     */
    public static String toString(int[] array, String start, String separator, String end) {
        String result = start;
        boolean needSeparator = false;
        for (int elem : array) {
            if (needSeparator) {
                result += separator;
            }
            result += elem;
            needSeparator = true;
        }
        result += end;
        return result;
    }

    public static String toString(long[] array, String start, String separator, String end) {
        String result = start;
        boolean needSeparator = false;
        for (long elem : array) {
            if (needSeparator) {
                result += separator;
            }
            result += elem;
            needSeparator = true;
        }
        result += end;
        return result;
    }

    /**
     * Converts an array of doubles to a string with custom start, separator,
     * and end.
     *
     * @param array the array to convert
     * @param start the string to start with
     * @param separator the separator between elements
     * @param end the string to end with
     * @return the formatted string
     */
    public static String toString(double[] array, String start, String separator, String end) {
        String result = start;
        boolean needSeparator = false;
        for (double elem : array) {
            if (needSeparator) {
                result += separator;
            }
            result += elem;
            needSeparator = true;
        }
        result += end;
        return result;
    }

    /**
     * Converts an array of Comparable objects to a string with custom start,
     * separator, and end.
     *
     * @param array the array to convert
     * @param start the string to start with
     * @param separator the separator between elements
     * @param end the string to end with
     * @return the formatted string
     */
    @SuppressWarnings("unchecked")
    public static String toString(Comparable[] array, String start, String separator, String end) {
        String result = start;
        boolean needSeparator = false;
        for (Comparable elem : array) {
            if (needSeparator) {
                result += separator;
            }
            result += elem;
            needSeparator = true;
        }
        result += end;
        return result;
    }

    /**
     * Checks if two integer arrays are equal in length and content.
     *
     * @param a1 the first array
     * @param a2 the second array
     * @return true if arrays are equal, false otherwise
     */
    public static boolean equals(int[] a1, int[] a2) {
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    // Series of overloaded isSorted methods for different data types
    /**
     * API: Checks if an integer array is sorted in increasing order.
     *
     * @param a the array to check
     * @return true if sorted, false otherwise
     */
    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a long array is sorted in increasing order.
     *
     * @param a the array to check
     * @return true if sorted, false otherwise
     */
    public static boolean isSorted(long[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a double array is sorted in increasing order.
     *
     * @param a the array to check
     * @return true if sorted, false otherwise
     */
    public static boolean isSorted(double[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a Comparable array is sorted in increasing order.
     *
     * @param a the array to check
     * @return true if sorted, false otherwise
     */
    @SuppressWarnings("unchecked")
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates an array of random integers within a specified range. TEST:
     * generateIntArray is tested in ArrayUtilityTest.java
     *
     * @param length the length of the array
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return the generated array
     */
    public static int[] generateIntArray(int length, int min, int max) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (min + Math.random() * (1.0 + max - min));
        }
        return array;
    }
// Added methods to generate arrays of other types

    /**
     * Generates an array of random long integers within a specified range.
     *
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static long[] generateLongArray(int length, long min, long max) {
        long[] array = new long[length];
        for (int i = 0; i < length; i++) {
            array[i] = (long) (min + Math.random() * (1.0 + max - min));
        }
        return array;
    }

    /**
     * Generates an array of random doubles within a specified range.
     *
     * @param length the length of the array
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return the generated array
     */
    public static double[] generateDoubleArray(int length, double min, double max) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = min + Math.random() * (max - min);
        }
        return array;
    }

    /**
     * Generates an array of random Comparable objects (Dates) within a
     * specified day range from today.
     *
     * @param length the length of the array
     * @param minDaysFromNow the minimum days from current date (can be negative
     * for past dates)
     * @param maxDaysFromNow the maximum days from current date (can be negative
     * for past dates)
     * @return the generated array of Date objects
     */
    public static Comparable[] generateComparableArray(int length, int minDaysFromNow, int maxDaysFromNow) {
        Comparable[] array = new Comparable[length];
        long currentTime = System.currentTimeMillis();
        long millisecondsPerDay = 24 * 60 * 60 * 1000L; // 24 hours * 60 min * 60 sec * 1000 ms

        for (int i = 0; i < length; i++) {
            // Generate random days within the specified range
            int randomDays = minDaysFromNow + (int) (Math.random() * (maxDaysFromNow - minDaysFromNow + 1));
            long randomTime = currentTime + (randomDays * millisecondsPerDay);
            array[i] = new java.util.Date(randomTime);
        }
        return array;
    }

}
