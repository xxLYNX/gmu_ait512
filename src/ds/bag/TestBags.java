/**
 * Module 1 Assignment 1: Fixed Capacity Bag
 * Task 1: Basic Implementation of Fixed Capacity Bags
 * Task 2: Is singleton API method
 *
 * @author Cullen Kelley
 */
package ds.bag;

import java.util.Date;

public class TestBags {

    /**
     * Description: test FixedCapacityBag by creating a bag of strings with
     * capacity 5, adding 5 strings to the bag, printing the bag, and counting
     * how many items start with the letter 'b'. Implementation: instantiates
     * new FixedCapacityBag object args capacity 5, adds items to the bag,
     * STDOUT the bag items, counts how many items start with the letter 'b'
     * STDOUT the resulting count. Task2: addition of isSingleton() method test.
     */
    public static void test1FixedCapacityBag() {
        System.out.println("Create an empty bag of strings with capacity 5");
        FixedCapacityBag<String> bag = new FixedCapacityBag<>(5);
        System.out.println("Bag: " + bag.toString());
        // Task2 test isSingleton on empty bag (should be false)
        System.out.println("Is Singleton? " + bag.isSingleton());

        System.out.println("Add the item: book");
        bag.add("book");

        System.out.println("Bag: " + bag.toString());
        // Task2 Test isSingleton on bag with one item (should be true)
        System.out.println("Is Singleton? " + bag.isSingleton());
        System.out.println("Add the items: bag, computer, phone, key");
        bag.add("bag");
        bag.add("computer");
        bag.add("phone");
        bag.add("key");
        System.out.println("Bag: " + bag.toString());
        // Task2 Test isSingleton on bag with multiple elements (should be false)
        System.out.println("Is Singleton? " + bag.isSingleton());
        System.out.println("How many items start with b?");
        int count = 0;
        // We can use this form of for statement because Bag is iterable
        for (String item : bag) {
            if (item.charAt(0) == 'b') {
                count++;
            }
        }
        System.out.println("There are " + count + " items that start with b.");
        String bItems = java.util.stream.StreamSupport.stream(bag.spliterator(), false)
                .filter(item -> item.startsWith("b"))
                .collect(java.util.stream.Collectors.joining(", "));
        System.out.println("Items starting with 'b': " + bItems);

    }

    /**
     * Description: test FixedCapacityBag by creating a bag of integers with
     * capacity 20, adding the integers 1 to 20 to the bag, STDOUT the bag, and
     * STDOUT the prime numbers in the bag. Implementation: instantiates new
     * FixedCapacityBag object args capacity 20, adds items to the bag, STDOUT
     * the bag items, checks each item in the bag if it is prime using inline
     * prime check via stream, STDOUT the prime numbers in the bag.
     */
    public static void test2FixedCapacityBag() {
        System.out.println("Test2: Create a new bag and fill it with 20 integers STDOUT bag and STDOUT the prime numbers in the bag");
        FixedCapacityBag<Integer> bag = new FixedCapacityBag<>(20);
        System.out.println("Bag: " + bag.toString());
        for (int i = 1; i <= 20; i++) {
            bag.add(i);
        }
        System.out.println("Bag: " + bag.toString());
        //Inline prime check via stream
        String primes = java.util.stream.StreamSupport.stream(bag.spliterator(), false)
                .filter(n -> n > 1 && java.util.stream.IntStream.rangeClosed(2, (int) Math.sqrt(n)).allMatch(i -> n % i != 0))
                .map(String::valueOf)
                .collect(java.util.stream.Collectors.joining(", "));

        System.out.println("Primes in bag: " + primes);
        // Test adding one more item than declared capacity to the bag to check capacity constraint
        System.out.println("Testing capacity constraint by adding one more item than capacity:");
        try {
            bag.add(21); // Attempt to add the 21st item
            System.out.println("Added 21st item (unexpected behavior).");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Bag capacity constraint enforced.");
        }
    }

    /**
     * Main method to run tests.
     *
     * @param args cli arguments (not used)
     */
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("M1A1T2: Fixed Capacity Bag - Task 2a\nAuthor: Cullen Kelley\nDate: " + date.toString());
        test1FixedCapacityBag();
    }

}
