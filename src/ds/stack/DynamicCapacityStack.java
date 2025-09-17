/**
 * M1B-21: Task 1 Basic Implementation of Dynamic Capacity Stacks
 *
 * @date 9/16/2025
 * @author Cullen Kelley
 */
package ds.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicCapacityStack<Item> implements Stack<Item> {

    private Item[] elements;

    private int numberOfElements;

    @SuppressWarnings("unchecked")
    public DynamicCapacityStack(int initialCapacity) {
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
    }

    public DynamicCapacityStack() {
        this(10); // default initial capacity
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    private void doubleCapacity() {
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

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

    public void push(Item item) {
        // check if the stack is full
        if (numberOfElements == elements.length) {
            doubleCapacity();
        }
        elements[numberOfElements++] = item;
    }

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
