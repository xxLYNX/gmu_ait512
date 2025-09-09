/**
 * Module 1 Assignment 1 Task 1: Basic Implementation of Fixed Capacity Bags
 *
 * @date 09/06/2025
 * @author Cullen Kelley
 */
package ds.bag;

import java.util.Date;

public class TestBags {

    /**
     * Description: test FixedCapacityBag by creating a bag of strings with
     * capacity 5, adding 5 strings to the bag, printing the bag, and counting
     * how many items start with the letter 'b'.
     *
     */
    public static void test1FixedCapacityBag() {
        System.out.println("Create an empty bag of strings with capacity 5");
        FixedCapacityBag<String> bag = new FixedCapacityBag<>(5);
        System.out.println("Bag: " + bag.toString());

        System.out.println("Add the item: book");
        bag.add("book");

        System.out.println("Bag: " + bag.toString());
        System.out.println("Add the items: bag, computer, phone, key");
        bag.add("bag");
        bag.add("computer");
        bag.add("phone");
        bag.add("key");
        System.out.println("Bag: " + bag.toString());
        System.out.println("How many items start with b?");
        int count = 0;
        // We can use this form of for statement because Bag is iterable.
        for (String item : bag) {
            if (item.charAt(0) == 'b') {
                count++;
            }
        }
        System.out.println("There are " + count + " items that start with b.");
    }

    /**
     * Main method to run tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Fixed Capacity Bag - Task 1a - by Cullen Kelley");
        Date date = new Date();
        System.out.println("Date: " + date.toString());
        test1FixedCapacityBag();
    }

}
