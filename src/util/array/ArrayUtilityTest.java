package util.array;

import java.util.Date;

public class ArrayUtilityTest {

    /**
     * Tests the toString method with various array lengths and formats.
     */
    public static void toStringTest() {
        int[] empty = {};
        System.out.println("Empty array: " + ArrayUtility.toString(empty, "{", " ", "}"));
        int[] singleton = {1};
        System.out.println("Singleton array: " + ArrayUtility.toString(singleton, "[", " ", "]"));
        int[] twoElements = {11, 12};
        System.out.println("Two element array: " + ArrayUtility.toString(twoElements, "<", ", ", ">"));
        int[] fiveElements = {1, 2, 3, 4, 5};
        System.out.println("Five element array: " + ArrayUtility.toString(fiveElements, "(", "; ", ")"));
        int[] twentyElements = new int[20];
        for (int i = 0; i < 20; i++) {
            twentyElements[i] = i + 1;
        }
        System.out.println("Twenty element array: " + ArrayUtility.toString(twentyElements, "[", ",", "]"));
    }

    /**
     * Tests the equals method with various array pairs.
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
    public static void boundaryValueTest() {
        int[] boundaryArray = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        int[] reversedBoundary = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};
        int[] minOnly = {Integer.MIN_VALUE};
        int[] maxOnly = {Integer.MAX_VALUE};

        System.out.println("Boundary array: " + ArrayUtility.toString(boundaryArray, "[", ", ", "]"));
        System.out.println("Reversed boundary: " + ArrayUtility.toString(reversedBoundary, "[", ", ", "]"));
        System.out.println("Min only: " + ArrayUtility.toString(minOnly, "[", ", ", "]"));
        System.out.println("Max only: " + ArrayUtility.toString(maxOnly, "[", ", ", "]"));

        System.out.println("Boundary equals reversed: " + ArrayUtility.equals(boundaryArray, reversedBoundary));
        System.out.println("Boundary isSorted: " + ArrayUtility.isSorted(boundaryArray));
        System.out.println("Reversed isSorted: " + ArrayUtility.isSorted(reversedBoundary));
        System.out.println("Min only isSorted: " + ArrayUtility.isSorted(minOnly));
        System.out.println("Max only isSorted: " + ArrayUtility.isSorted(maxOnly));
    }

    public static void main(String[] args) {
        String assignment = "M2A2: Task 1: Utility methods for arrays\n";
        Date date = new Date();
        String Ran = "Date: " + date.toString();
        String author = "Author: Cullen Kelley";
        System.out.println(assignment + Ran + "\n" + author + "\n");
        // Task 1a: toString test

        toStringTest();
        // Task 1b: equals test
        equalsTest();

        // Task 1c: isSorted test
        isSortedTest();

        // Task 1d: boundary value test
        boundaryValueTest();
    }
}
