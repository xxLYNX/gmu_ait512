/**
 * Module 1 Assignment 1 Task 1: Basic Implementation of Fixed Capacity Bags
 *
 * @date 09/06/2025
 * @author Cullen Kelley
 */
package ds.bag;

/**
 * A generic bag interface for a Bag ADT Bags (ADT): an unordered collection of
 * values (objects in Java) that may have duplicates and while you can add items
 * to a bag, you cannot remove items from a bag. This bag has a fixed capacity.
 *
 * @param <Item> the type of values in the bag
 */
public interface Bag<Item> extends Iterable<Item> {

    /**
     * API: Adds an item to the bag.
     *
     * @param item the item to add
     */
    public void add(Item item);

    /**
     * API: Returns true if the bag is empty.
     *
     * @return true if the bag is empty; false otherwise
     */
    public boolean isEmpty();

    /**
     * API: Returns the number of items in the bag (zero if empty not null).
     *
     * @return the number of items in the bag
     */
    public int size();
}
