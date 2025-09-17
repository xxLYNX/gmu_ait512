/**
 * M1B-21: Task 1 Basic Implementation of Dynamic Capacity Stacks
 *
 * @date 9/16/2025
 * @author Cullen Kelley
 */
package ds.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a generic stack (LIFO - last in first out) using a
 * dynamically resizing array. Stack (ADT): an ordered collection of generic
 * elements (objects in Java) that may have duplicates and allows for efficient
 * addition and removal of elements from the top. Operations: push, pop,
 * isEmpty, and size
 *
 * @param <Item> the type of elements in the stack (any object type)
 */
public class DynamicCapacityStack<Item> implements Stack<Item> {

    /**
     * array of stack elements (generic)
     */
    private Item[] elements;
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
    public DynamicCapacityStack(int initialCapacity) {
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
    }

    /**
     * Default constructor with initial capacity of 10.
     */
    public DynamicCapacityStack() {
        this(10); // default initial capacity
    }

    /**
     * Implementation: returns numberOfElements
     */
    @Override
    public int size() {
        return numberOfElements;
    }

    /**
     * Doubles the capacity of the elements array.
     */
    private void doubleCapacity() {
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Halves the capacity of the elements array.
     */
    private void halfCapacity() {
        if (numberOfElements >= elements.length / 2) {
            throw new RuntimeException("Stack is more than half full");
        }
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length / 2];
        for (int i = 0; i < numberOfElements; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    /**
     * Implementation: adds an item to the top of the stack, doubling capacity
     * if needed.
     */
    public void push(Item item) {
        // check if the stack is full
        if (numberOfElements == elements.length) {
            doubleCapacity();
        }
        elements[numberOfElements++] = item;
    }

    /**
     * Implementation: removes and returns the topmost item from the stack,
     * halving capacity if needed.
     */
    public Item pop() {
        // check if the stack is empty
        if (numberOfElements == 0) {
            throw new RuntimeException("Stack is empty");
        }
        numberOfElements--;
        Item top = elements[numberOfElements];
        elements[numberOfElements] = null;
        if (numberOfElements < elements.length / 4 && elements.length > 10) {
            halfCapacity();
        }
        return top;
    }

    /**
     * Implementation: returns an iterator to iterate through the stack elements
     * from top to bottom preserving stack order
     */
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
                throw new NoSuchElementException("No more elements in the stack.");
            }
        };
    }

}
