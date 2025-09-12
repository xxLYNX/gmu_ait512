/**
 * Module 1 Assignment 1: Fixed Capacity Bag
 * Task 1: Basic Implementation of Fixed Capacity Bags
 * Task 2: Is singleton API method
 * Task 3: Improved toString() method & formatting
 *
 * @date 9/12/2025
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
     * Description: test FixedCapacityBag by creating a bag of doubles with
     * capacity 10, adding 10 doubles to the bag, printing the bag, and testing
     * isSingleton() method at each step. Implementation: instantiates new
     * FixedCapacityBag object args capacity 10, STDOUT the bag items and
     * isSingleton() result, adds one item to the bag, STDOUT the bag items and
     * isSingleton() result, adds remaining items to the bag, STDOUT the bag
     * items and isSingleton() result.
     */
    public static void test3FixedCapacityBag() {
        System.out.println("Test 3: Create a bag of doubles with capacity 10, populate with elements, and test:");
        FixedCapacityBag<Double> bag = new FixedCapacityBag<>(10);
        System.out.println("Bag: " + bag.toString() + " " + "Is bag singleton? " + bag.isSingleton());
        bag.add(1.0);
        System.out.println("Bag: " + bag.toString() + " " + "Is bag singleton? " + bag.isSingleton());
        for (double i = 2.3; i <= 10.1; i += 1.30) {
            bag.add(i);
        }
        System.out.println("Bag: " + bag.toString() + " " + "Is bag singleton? " + bag.isSingleton());
    }

    public static void test4FixedCapacityBag() {
        System.out.println("Test 4: Creat Bag<Long> with capacity 10 and perform various toString tests");

        // Test 1: Empty bag
        FixedCapacityBag<Long> bag1 = new FixedCapacityBag<>(3);
        System.out.println("Test 1 - Empty bag: " + bag1.toString() + " | Size: " + bag1.size());

        // Test 2: Bag with one long value (greater than Integer.MAX_VALUE)
        FixedCapacityBag<Long> bag2 = new FixedCapacityBag<>(3);
        long bigLong = 3000000000L; // > Integer.MAX_VALUE
        bag2.add(bigLong);
        System.out.println("Test 2 - One big long: " + bag2.toString() + " | Size: " + bag2.size());

        // Test 3: Bag with multiple long values, including a negative and a zero
        FixedCapacityBag<Long> bag3 = new FixedCapacityBag<>(5);
        bag3.add(0L);
        bag3.add(-1234567890123L);
        bag3.add(9876543210L);
        System.out.println("Test 3 - Multiple longs: " + bag3.toString() + " | Size: " + bag3.size());
    }

    /**
     * Main method to run tests.
     *
     * @param args cli arguments (not used)
     */
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("M1A1T3: Fixed Capacity Bag - Task 3a\nAuthor: Cullen Kelley\nDate: " + date.toString());
        //test1FixedCapacityBag();
        //test2FixedCapacityBag();
        //test3FixedCapacityBag();
        test4FixedCapacityBag();
    }

}
