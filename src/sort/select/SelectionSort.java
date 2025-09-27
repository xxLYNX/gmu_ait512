/**
 * Implements the selection sort algorithm for sorting an array of integers in
 * increasing order.
 *
 * @author Cullen Kelley
 */
package sort.select;

/**
 * IMPLEMENTATION: Implements the selection sort algorithm for sorting arrays of
 * various data types in increasing order. FUNCTIONALITY: Provides static
 * methods to sort arrays of integers, longs, doubles, and Comparable objects.
 * USAGE: Call the appropriate static sort method with the array to be sorted as
 * an argument. DESCRIPTION: The selection sort algorithm repeatedly selects the
 * minimum element from the unsorted portion of the array and swaps it with the
 * first unsorted element, effectively growing the sorted portion of the array
 * until the entire array is sorted. This is a kind of in-place sort with O(n^2)
 * time complexity. https://xlinux.nist.gov/dads/HTML/selectionSort.html
 *
 * @author Cullen Kelley
 */
public class SelectionSort {

    /**
     * Sorts the given array of integers in increasing order using the selection
     * sort algorithm.
     *
     * @param array the array to be sorted
     */
    public static void sort(int[] array) {
        // take each element from the array (except the last one)
        for (int i = 0; i < array.length - 1; i++) {
            // compute the index of the  minimum of the array [i, length-1]
            int iMin = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    iMin = j;
                    min = array[j];
                }
            }
            // exchange the i element with the minimum element
            array[iMin] = array[i];
            array[i] = min;
        }
    }

    /**
     * Sorts an array of longs in increasing order using the selection sort
     * algorithm.
     *
     * @param array
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
     * Sorts an array of doubles in increasing order using the selection sort
     * algorithm.
     *
     * @param array the array to be sorted
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
     * Sorts an array of Comparable objects in increasing order using the
     * selection sort algorithm.
     *
     * @param array the array to be sorted
     */
    @SuppressWarnings("unchecked")
    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int iMin = i;
            Comparable min = array[i];
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

}
