/**
 * Module 1 Assignment 1 Task 1: Basic Implementation of Fixed Capacity Bags
 *
 * @date 09/06/2025
 * @author Cullen Kelley
 */
package ds.bag;

import java.util.Iterator;

/**
 * Implementation: a fixed-capacity bag of the Bag ADT using an array
 * Specification: capacity is set at bag creation and cannot be changed.
 *
 * @param <Item> the type of values in the bag
 */
public class FixedCapacityBag<Item> implements Bag<Item> {

    /**
     * Array to store bag items and current size of the bag The array is the
     * actual storage for the bag's elements.
     */
    private final Item[] elements;

    /**
     * Current size of the bag i.e. number of elements in the bag
     */
    private int numberOfElements;

    /**
     * Constructor: creates an empty bag with the specified capacity.
     *
     * @param capacity the maximum number of items the bag can hold
     * @throws IllegalArgumentException if capacity is negative
     */
    @SuppressWarnings("unchecked")
    public FixedCapacityBag(int capacity) {
        elements = (Item[]) new Object[capacity];
        numberOfElements = 0;
    }

    /**
     * Returns the number of elements in the bag.
     *
     * @return the number of elements in the bag; empty bag returns 0 not null
     */
    @Override
    public int size() {
        return numberOfElements;
    }

    /**
     * Returns true if the bag is empty, false otherwise.
     *
     * @return true if the bag is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Specification: required modifier function add(v, B) for Bag ADT
     * Description: adds an item/element to the bag. If the bag is full, throws
     * a RuntimeException. Implementation: adds the item to the end of the
     * elements array and increments the numberOfElements variable.
     *
     * @param item the item to add
     * @throws RuntimeException if the bag is full
     */
    @Override
    public void add(Item item) {
        if (numberOfElements == elements.length) {
            throw new RuntimeException("Bag is full");
        }
        elements[numberOfElements] = item;
        numberOfElements++;
    }

    /**
     * Returns an iterator that iterates over the items in the bag. And will
     * test for proceeding elements via hasNext() throws a RuntimeException if
     * there are no more elements. Otherwise returns the proceeding element via
     * next().
     *
     * @return <code>Iterator</code> This iterator iterates over the items in
     * the bag
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < numberOfElements;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return elements[index++];
                }
                throw new RuntimeException("No more elements in the bag");
            }
        };
    }

    /**
     * Returns a string representation of the bag. Overrides the default
     *
     * @return a string representation of the bag
     */
    public String toString() {
        String result = "[";
        for (Item item : this) {
            result += item.toString() + " ";
        }
        result += "]";
        return result;
    }

}
