/**
 * Module 1b Assignment 1: Stack
 *
 * @date 9/13/2025
 * @author Cullen Kelley
 */
package ds.stack;

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

    /**
     * Main method to run the stack tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        String assignment = "M1B-21: Basic Implementation of Dynamic Capacity Stacks";
        Date date = new Date();
        System.out.println(assignment + "\nModule 1 Assignment: Dynamic Capacity Stack - Task 1b | Author: Cullen Kelley\nDate: " + date.toString());
        //String[] items = {"a", "b", "c", "d", "e"};
        //DynamicCapacityStack<String> dcs = new DynamicCapacityStack<>(3);
        Integer[] items = {1, 2, 3, 4, 5};
        DynamicCapacityStack<Integer> dcs = new DynamicCapacityStack<>(3);
        testStack(dcs, items, 6);
    }

}
