package util.array;

import java.util.Date;
//import util.array.ArrayUtility;

public class ArrayUtilityTest {

    /**
     * Tests the toString method with various array lengths and formats. Call
     * only this class in main and check the results.
     */
    public static void toStringTest() {
        // Test empty array
        int[] empty = {};
        System.out.println("Empty array: " + ArrayUtility.toString(empty, "{", " ", "}"));
        // Test singleton array
        int[] singleton = {1};
        System.out.println("Singleton array: " + ArrayUtility.toString(singleton, "[", " ", "]"));
        // Test two element array
        int[] twoElements = {11, 12};
        System.out.println("Two element array: " + ArrayUtility.toString(twoElements, "<", ", ", ">"));
        // Test five element array
        int[] fiveElements = {1, 2, 3, 4, 5};
        System.out.println("Five element array: " + ArrayUtility.toString(fiveElements, "(", "; ", ")"));
        // Test twenty element array
        int[] twentyElements = new int[20];
        for (int i = 0; i < 20; i++) {
            twentyElements[i] = i + 1;
        }
        System.out.println("Twenty element array: " + ArrayUtility.toString(twentyElements, "[", ",", "]"));
    }

    /**
     * Serves as a validation test for the equals method in ArrayUtility.
     */
    public static void equalsTest() {
        int[] empty1 = {};
        int[] empty2 = {};
        int[] singleton1 = {1};
        int[] singleton2 = {1};
        int[] singleton3 = {2};
        int[] two1 = {1, 2};
        int[] two2 = {1, 2};
        int[] two3 = {2, 1};
        System.out.println("Empty vs Empty: " + ArrayUtility.equals(empty1, empty2));
        System.out.println("Empty vs Singleton: " + ArrayUtility.equals(empty1, singleton1));
        System.out.println("Singleton same: " + ArrayUtility.equals(singleton1, singleton2));
        System.out.println("Singleton different: " + ArrayUtility.equals(singleton1, singleton3));
        System.out.println("Two same: " + ArrayUtility.equals(two1, two2));
        System.out.println("Two different: " + ArrayUtility.equals(two1, two3));
    }

    /**
     * Tests the isSorted method with various arrays.
     */
    public static void isSortedTest() {
        int[] sorted = {1, 2, 3, 4, 5};
        int[] unsorted = {5, 3, 2, 4, 1};
        int[] singleton = {42};
        int[] empty = {};
        int[] equalElements = {7, 7, 7};
        System.out.println("Sorted: " + ArrayUtility.isSorted(sorted));
        System.out.println("Unsorted: " + ArrayUtility.isSorted(unsorted));
        System.out.println("Singleton: " + ArrayUtility.isSorted(singleton));
        System.out.println("Empty: " + ArrayUtility.isSorted(empty));
        System.out.println("Equal elements: " + ArrayUtility.isSorted(equalElements));
    }

    /**
     * Tests array utility methods with arrays containing Integer.MIN_VALUE and
     * Integer.MAX_VALUE.
     */
    public static void generateIntArrayTest() {
        int length = 10;
        int min = 1;
        int max = 30;
        int[] array = ArrayUtility.generateIntArray(length, min, max);
        // Display test array
        System.out.println("Generated array: " + ArrayUtility.toString(array, "[", ", ", "]"));
        // Display test of array attributes
        System.out.println("Array length correct: " + (array.length == length));
        boolean inRange = true;
        for (int value : array) {
            if (value < min || value > max) {
                inRange = false;
                break;
            }
        }
        System.out.println("Array elements in range? [" + min + ", " + max + "]: " + inRange);
    }

    public static void main(String[] args) {
        String step = "Step 11: generate int arry test";
        String assignment = "M2B1: Task 1d: Utility methods for arrays\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n" + step + "\n");

        //toStringTest();
        //equalsTest();
        // Task 1c: isSorted test
        //isSortedTest();
        // Task 1d: boundary value test
        generateIntArrayTest();
    }
}
