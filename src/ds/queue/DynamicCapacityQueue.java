/**
 * M1B-22: Task 2 Basic Implementation of Dynamic Capacity Queues
 *
 * @date 9/16/2025
 * @author Cullen Kelley
 */
package ds.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a generic queue (FIFO - first in first out) using a
 * dynamically resizing array. Queue (ADT): an ordered collection of generic
 * elements (objects in Java) that may have duplicates and allows for efficient
 * addition of elements at the end and removal of elements from the front.
 * Operations: enqueue, dequeue, isEmpty, and size
 *
 * @param <Item> the type of elements in the queue (any object type)
 */
public class DynamicCapacityQueue<Item> implements Queue<Item> {

    /**
     * array of queue elements (generic)
     */
    private Item[] elements;
    /**
     * current number of elements in queue (size)
     */
    private int numberOfElements;

    /**
     * index of the first element in the queue
     */
    private int startIndex;
    /**
     * index of the last element in the queue
     */
    private int endIndex;

    /**
     * Function: constructor Implementation: uses a generic array to store the
     * queue elements and an int to track the number of elements in the queue
     * (size). enqueue() adds an item to the end of the queue, dequeue() removes
     * and returns an item from the front of the queue.
     */
    @SuppressWarnings("unchecked")
    public DynamicCapacityQueue(int initialCapacity) {
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
        startIndex = -1;
        endIndex = -1;
    }

    /**
     * Default constructor with initial capacity of 10.
     */
    public DynamicCapacityQueue() {
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
     * Double the capacity of the underlying array when it is full.
     */
    private void doubleCapacity() {
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length * 2];
        int oldIndex = startIndex;
        int newIndex = 0;
        for (int i = 1; i <= numberOfElements; i++) {
            newElements[newIndex++] = elements[oldIndex++];
            if (oldIndex == elements.length) {
                oldIndex = 0;
            }
        }
        elements = newElements;
        startIndex = 0;
        endIndex = numberOfElements - 1;
    }

    /**
     * Halve the capacity of the underlying array when it is less than 25% full,
     * but not less than 10.
     */
    private void halfCapacity() {
        if (numberOfElements >= elements.length / 2) {
            throw new RuntimeException("Queue capacity at >=50% - cannot half capacity without loosing data");
        }
        @SuppressWarnings("unchecked")
        Item[] newElements = (Item[]) new Object[elements.length / 2];
        int oldIndex = startIndex;
        int newIndex = 0;
        for (int i = 1; i <= numberOfElements; i++) {
            newElements[newIndex++] = elements[oldIndex++];
            if (oldIndex == elements.length) {
                oldIndex = 0;
            }
        }
        elements = newElements;
        startIndex = 0;
        endIndex = numberOfElements - 1;
    }

    /**
     * Implementation: adds an item to the end of the queue
     */
    @Override
    public void enqueue(Item item) {
        if (numberOfElements == elements.length) {
            doubleCapacity();
        }
        numberOfElements++;
        endIndex++;
        if (endIndex >= elements.length) {
            endIndex = 0;
        }
        if (startIndex == -1) {
            startIndex = 0;
        }
        elements[endIndex] = item;
    }

    /**
     * Implementation: removes and returns the item from the front of the queue
     */
    @Override
    public Item dequeue() {
        if (numberOfElements == 0) {
            throw new RuntimeException("Queue is empty");
        }
        Item item = elements[startIndex];
        elements[startIndex] = null;
        numberOfElements--;
        if (numberOfElements == 0) {
            startIndex = -1;
            endIndex = -1;
        } else {
            startIndex++;
            if (startIndex == elements.length) {
                startIndex = 0;
            }
        }
        if (numberOfElements > 10 && numberOfElements < elements.length / 4) {
            halfCapacity();
        }
        return item;
    }

    /**
     * Implementation: returns an iterator to iterate through the queue elements
     * from front to back preserving queue order
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int nextItem = startIndex;

            @Override
            public boolean hasNext() {
                return nextItem != -1;
            }

            @Override
            public Item next() {
                if (nextItem == -1) {
                    throw new RuntimeException("No more elements");
                }
                Item item = elements[nextItem];
                if (nextItem == endIndex) {
                    nextItem = -1;
                } else {
                    nextItem++;
                    if (nextItem == elements.length) {
                        nextItem = 0;
                    }
                }
                return item;
            }
        };
    }

    //Debugging methods
    /**
     * Returns the current capacity of the queue (length of the underlying
     * array).
     *
     * @return the current capacity of the queue (int)
     */
    public int getCapacity() {
        return elements.length;
    }

}
