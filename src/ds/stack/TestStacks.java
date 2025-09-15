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

    private static void testPushItem(Stack<String> stack, String item) {
        System.out.println();
        System.out.println("Push item: " + item); //.toString()
        try {
            stack.push(item);
        } catch (Exception e) {
            System.out.println("Error pushing an item in the stack: " + e.getMessage());
        }
        System.out.println("Updated stack: " + stack.toString("[", ">", ", "));
    }

    /**
     * Test:Perform a sequence of tests on an empty stack.
     *
     * @param s the tested empty stack
     */
    private static void testStringStack(Stack<String> s) {
        System.out.println("Empty stack: " + s.toString("[", "]", ", "));
        testPushItem(s, "Virginia");
        testPushItem(s, "California");
        testPushItem(s, "Florida");
        testPushItem(s, "Texas");
        testPushItem(s, "New York");
        testPushItem(s, "Ohio"); //should fail - stack full
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("Module 1b Assignment 1: Fixed Capacity Bag - Task 1a | Author: Cullen Kelley\nDate: " + date.toString());
        FixedCapacityStack<String> fcs = new FixedCapacityStack<>(5);
        testStringStack(fcs);
    }

}
