/**
 * Module 1.2 Assignment 2 Fixed capacity stacks and queues
 * Task 3 Basic Implementation of Fixed Capacity Queues
 *
 * @date 9/14/2025
 * @author Cullen Kelley
 */
package ds.queue;

java

.util.Iterator;

/**
 * Implementation of a generic queue (FIFO - first in first out) using a fixed
 * capacity array. Queue (ADT): an ordered collection of generic elements
 * (objects in Java) that may have duplicates and allows for efficient addition
 * of elements at the end and removal of elements from the front. Operations:
 * enqueue, dequeue, isEmpty, and size
 *
 * @param <Item> the type of elements in the queue (any object type)
 */
public class FixedCapacityQueue<Item> implements Queue<Item> {

    private final Item[] elements;
    private int numberOfElements;

    private int startIndex;
    private int endIndex;

    @SuppressWarnings("unchecked")
    public FixedCapacityQueue(int capacity) {
        elements = (Item[]) new Object[capacity];
        numberOfElements = 0;
        startIndex = -1;
        endIndex = -1;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public void enqueue(Item item) {
        if (numberOfElements == elements.length) {
            throw new RuntimeException("Queue is full");
        }
        if (numberOfElements == 0) {
            startIndex = 0;
            endIndex = 0;
        } else {
            endIndex++;
            if (endIndex == elements.length) {
                endIndex = 0;
            }
        }
        elements[endIndex] = item;
        numberOfElements++;
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

}
