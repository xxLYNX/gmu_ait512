/**
 * Module 1.2 Assignment 2 Fixed capacity stacks and queues
 * Task 3 Basic Implementation of Fixed Capacity Queues
 *
 * @date 9/14/2025
 * @author Cullen Kelley
 */
package ds.queue;

import java.util.Date;

/**
 * Test class for all queue implementations.
 */
public class TestQueue {

    /**
     * Test: Print the details of a queue including its elements, size, and
     * whether it is empty.
     *
     * @param queue the queue to print
     * @param name the name of the queue for identification
     */
    private static <Item> void printQueue(Queue<Item> queue, String name) {
        System.out.println("Queue: " + name);
        System.out.println("  - elements = " + queue.toString("<< ", " <<", ", "));
        System.out.println("  - size = " + queue.size());
        System.out.println("  - isEmpty = " + queue.isEmpty());
        // My addition: print the front item if the queue is not empty

        if (!queue.isEmpty()) {
            System.out.print("  - front: ");
            for (Object item : queue) {
                System.out.println(item);
                break; // Only print the first (front) item
            }
        }

    }

    /**
     * Test: Enqueue (add) an item to the queue and print the updated queue.
     *
     * @param queue the queue to add the item to
     * @param name the name of the queue for identification
     * @param item the item to add to the queue
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
        // My addition: confirm if the item was added
        /*
        boolean success = false;
        try {
            queue.enqueue(item);
            success = true;
            System.out.println("  - Add successful");
        } catch (Exception e) {
            System.out.println("  - Exception: " + e.getMessage());
        }
        System.out.println("  - After enqueue: size = " + queue.size() + ", isEmpty = " + queue.isEmpty());
        printQueue(queue, name);
        if (!success) {
            System.out.println("  - Item was not added due to exception.");
        }
         */
    }

    /**
     * Test: Dequeue (remove and return) an item from the queue and print the
     * updated queue.
     *
     * @param queue the queue to remove the item from
     * @param name the name of the queue for identification
     */
    private static <Item> void testDequeueItem(Queue<Item> queue, String name) {
        System.out.println();
        System.out.println("Dequeue (remove and return) the next item from a queue");
        System.out.println("  - queue = " + name);
        try {
            Item item = queue.dequeue();
            System.out.println("  - dequeue successfull");
            System.out.println("  - item = " + item.toString());
        } catch (Exception e) {
            System.out.println("  - runtime exception: " + e.getMessage());
        }
        printQueue(queue, name);
    }

    /**
     * Main method to execute the queue tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("Module 1 Assignment 1: Fixed Capacity Stack - Task 1a | Author: Cullen Kelley\nDate: " + date.toString());

        Queue<String> fcq3 = new FixedCapacityQueue<>(3);
        String fcq3Name = "fixed capacity queue of 3 elements";
        printQueue(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, "my first item");
        testEnqueueItem(fcq3, fcq3Name, "my second item");
        testEnqueueItem(fcq3, fcq3Name, "my third item");
        testEnqueueItem(fcq3, fcq3Name, "my fourth item");
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testEnqueueItem(fcq3, fcq3Name, "my fourth item");
        testEnqueueItem(fcq3, fcq3Name, "my fifth item");
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
        testDequeueItem(fcq3, fcq3Name);
    }

}
