/**
 * M1B-22: Task 2 Basic Implementation of Dynamic Capacity Queues
 *
 * @date 9/16/2025
 * @author Cullen Kelley
 */
package ds.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicCapacityQueue<Item> implements Queue<Item> {

    private Item[] elements;
    private int numberOfElements;

    private int startIndex;
    private int endIndex;

    @SuppressWarnings("unchecked")
    public DynamicCapacityQueue(int initialCapacity) {
        elements = (Item[]) new Object[initialCapacity];
        numberOfElements = 0;
        startIndex = -1;
        endIndex = -1;
    }

    public DynamicCapacityQueue() {
        this(10); // default initial capacity
    }

    @Override
    public int size() {
        return numberOfElements;
    }

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
