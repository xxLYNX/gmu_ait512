/**
 * Module 1b Assignment 1: Stack
 *
 * @date 9/13/2025
 * @author Cullen Kelley
 */
package ds.stack;

import java.util.Iterator;

/**
 * Implementation of a generic stack (LIFO - last in first out) using a fixed
 * capacity array. Stack (ADT): an ordered collection of generic elements
 * (objects in Java) that may have duplicates and allows for efficient addition
 * and removal of elements from the top. Operations: push, pop, isEmpty, and
 * size
 *
 * @param <Item> the type of elements in the stack (any object type)
 */
public class FixedCapacityStack<Item> implements Stack<Item> {

    /**
     * array of stack elements (generic)
     */
    private final Item[] elements;
    /**
     * current number of elements in stack (size)
     */
    private int numberOfElements;

    /**
     * Function: constructor Implementation: uses a generic array to store the
     * stack elements and an int to track the number of elements in the stack
     * (size). push() adds an item
     */
    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int capacity) {
        elements = (Item[]) new Object[capacity]; // create array of given capacity
        numberOfElements = 0; // stack is initially empty
    }

    /**
     * Implementation: returns numberOfElements
     */
    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public void push(Item item) {
        //check if stack is at capacity
        if (numberOfElements == elements.length) {
            throw new RuntimeException("Stack is full - cannot push new item");
        }
        elements[numberOfElements++] = item; //add item and increment size   
    }

    @Override
    public Item pop() {
        if (numberOfElements == 0) {
            throw new RuntimeException("Stack is empty - cannot pop item");
        }
        numberOfElements--;
        Item top = elements[numberOfElements];
        elements[numberOfElements] = null;
        return top;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < numberOfElements;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return elements[index++];
                }
                throw new RuntimeException("No more elements in stack");
            }
        };
    }

}
