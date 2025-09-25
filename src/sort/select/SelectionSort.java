
/**
 * Sorts an array of longs using selection sort.
 *
 * @param array the array to sort
 */

public static void sort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            long min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sorts an array of doubles using selection sort.
     * @param array the array to sort
     */
    public static void sort(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            double min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sorts an array of Comparable objects using selection sort.
     * @param array the array to sort
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            T min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(min) < 0) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }
package sort.select;

/**
 * Implements selection sort for arrays of integers.
 *
 * @author Cullen Kelley
 */
public class SelectionSort {
    /**
     * Sorts an array of longs using selection sort.
     * @param array the array to sort
     */
    public static void sort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            long min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sorts an array of doubles using selection sort.
     * @param array the array to sort
     */
    public static void sort(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            double min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sorts an array of Comparable objects using selection sort.
     * @param array the array to sort
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            T min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(min) < 0) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sort the array using the selection sort method.
     *
     * @param array an array to be sorted in place
     */
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            array[iMin] = array[i];
            array[i] = min;
        }
    }
}
