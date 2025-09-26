
package sort.select;
/**
 * Implements the selection sort algorithm for sorting an array of integers in
 * increasing order.
 *
 * @author Cullen Kelley
 */

 	






public static void sort(int[] array) {     
		// take each element from the array (except the last one)
		for (int i=0; i<array.length-1; i++) {
			// compute the index of the  minimum of the array [i, length-1]
			int iMin = i;
			int min  = array[i];
			for (int j=i+1; j<array.length; j++) {
				if (array[j]<min) {
					iMin = j;
					min = array[j];
				}
			}
			// exchange the i  element with the minimum element
			array[iMin] = array[i];
			array[i] = min;
		}
	}
