/**
 * Module 1b Assignment 1: Stack
 *
 * @date 9/13/2025
 * @author Cullen Kelley
 */
package ds.stack;

import ds.queue.DynamicCapacityQueue;
import ds.queue.Queue;
import java.util.Date;

/**
 * Test class for all stack implementations.
 */
public class TestStacks {

    /**
     * Test: Push an item onto the stack and print the updated stack.
     *
     * @param stack the stack to push the item onto
     * @param item the item to push onto the stack
     */
    private static <Item> void testPushItem(Stack<Item> stack, Item item) {
        System.out.println();
        System.out.println("Push item: " + item.toString());
        try {
            stack.push(item);
        } catch (Exception e) {
            System.out.println("Error pushing an item in the stack: " + e.getMessage());
        }
        System.out.println("Updated stack: " + stack.toString("[", ">", ", "));
    }

    /**
     * Test: Pop an item from the stack and print the updated stack.
     *
     * @param stack the stack to pop the item from
     * @return the popped item (or null if stack was empty)
     */
    private static <Item> Item testPopItem(Stack<Item> stack) {
        System.out.println();
        System.out.println("Pop item from stack");
        Item item = null;
        try {
            item = stack.pop();
            System.out.println("Popped item: " + item);
        } catch (Exception e) {
            System.out.println("Error poping an item from the stack: " + e.getMessage());
        }
        System.out.println("Updated stack: " + stack.toString("[", ">", ", "));
        return item;
    }

    /**
     * Test:Perform a sequence of tests on an empty stack.
     *
     * @param s the tested empty stack
     */
    private static <Item> void testStack(Stack<Item> s, Item[] items, int numberOfPops) {
        System.out.println("Empty stack: " + s.toString("[", ">", ", "));
        for (Item item : items) {
            testPushItem(s, item);
        }
        for (int i = 0; i < numberOfPops; i++) {
            testPopItem(s);
        }
    }

    // Section for testing queues
    /**
     * Test: Enqueue (add) an item to the queue and print the updated queue.
     * NOTE: This method was implemented by me to supplement the provided code
     * as needed.
     *
     * @param <Item>
     * @param queue
     * @param name
     * @param item
     */
    private static <Item> void testEnqueueItem(Queue<Item> queue, String name, Item item) {
        System.out.println();
        System.out.println("Enqueue (add) an item to a queue");
        System.out.println("  - queue = " + name);
        System.out.println("  - item = " + item);
        try {
            queue.enqueue(item);
            System.out.println("  - add successfull");
        } catch (Exception e) {
            System.out.println("  - runtime exception: " + e.getMessage());
        }
        printQueue(queue, name);
    }

    /**
     * Test: Dequeue (remove and return) an item from the queue and print the
     * updated queue. NOTE: This method was implemented by me to supplement the
     * provided code as needed.
     *
     * @param <Item>
     * @param queue
     * @param name
     */
    private static <Item> void testDequeueItem(Queue<Item> queue, String name) {
        System.out.println();
        System.out.println("Dequeue (remove and return) the next item from a queue");
        System.out.println("  - queue = " + name);
        try {
            Item item = queue.dequeue();
            System.out.println("  - dequeue successfull");
            System.out.println("  - item = " + item);
        } catch (Exception e) {
            System.out.println("  - runtime exception: " + e.getMessage());
        }
        printQueue(queue, name);
    }

    /**
     * Test: Perform a complex sequence of enqueue and dequeue operations on a
     * queue.
     *
     * @param <Item> the type of elements in the queue (any object type)
     * @param queue the tested queue
     * @param name the name of the tested queue
     * @param items1 the first set of items to enqueue
     * @param deq1 the number of items to dequeue after the first set
     * @param items2 the second set of items to enqueue
     * @param deq2 the number of items to dequeue after the second set
     */
    private static <Item> void testQueue(Queue<Item> queue, String name, Item[] items1, int deq1, Item[] items2, int deq2) {
        System.out.println();
        System.out.println("Complex test of the queue " + name);
        for (Item item : items1) {
            testEnqueueItem(queue, name, item);
        }
        for (int i = 0; i < deq1; i++) {
            testDequeueItem(queue, name);
        }

        for (Item item : items2) {
            testEnqueueItem(queue, name, item);
        }
        for (int i = 0; i < deq2; i++) {
            testDequeueItem(queue, name);
        }

    }

    /**
     * Print the details of a queue including its name, elements, size, and
     * whether it is empty.
     *
     * @param <Item>
     * @param queue
     * @param name
     */
    private static <Item> void printQueue(Queue<Item> queue, String name) {
        System.out.println("Queue: " + name);
        System.out.println("  - elements = " + queue.toString("<< ", " <<", ", "));
        System.out.println("  - size = " + queue.size());
        System.out.println("  - isEmpty = " + queue.isEmpty());
        // Show capacity if available
        if (queue instanceof ds.queue.DynamicCapacityQueue) {
            System.out.println("  - capacity = " + ((ds.queue.DynamicCapacityQueue<?>) queue).getCapacity());
        }
    }
    // End section for testing queues

    /**
     * Main method to run the stack tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        String assignment = "M1B-22: Task 2 Basic Implementation of Dynamic Capacity Queues";
        Date date = new Date();
        System.out.println(assignment + "\nModule 1 Assignment: Basic Implementation of Dynamic Capacity Queues - Task 2b | Author: Cullen Kelley\nDate: " + date.toString());
        //String[] items = {"a", "b", "c", "d", "e"};
        //DynamicCapacityStack<String> dcs = new DynamicCapacityStack<>(3);
        //Integer[] items = {1, 2, 3, 4, 5};
        //DynamicCapacityStack<Integer> dcs = new DynamicCapacityStack<>(3);
        //testStack(dcs, items, 6);

        // Queue tests
        Queue<Double> dcq1 = new DynamicCapacityQueue<>(1);
        String dcq1Name = "Dynamic capacity queue with 1 initial capacity";
        printQueue(dcq1, dcq1Name);
        Double[] strings1 = {1.1, 10.1};
        Double[] strings2 = {100.3, 1000.4};
        testQueue(dcq1, dcq1Name, strings1, 1, strings2, 2);
    }

}
