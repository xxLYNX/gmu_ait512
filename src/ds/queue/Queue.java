/**
 * Module 1.2 Assignment 2 Fixed capacity stacks and queues
 * Task 3 Basic Implementation of Fixed Capacity Queues
 *
 * @date 9/14/2025
 * @author Cullen Kelley
 */
package ds.queue;

/**
 * Interface for a generic queue (FIFO - first in first out) Queue (ADT): an
 * ordered collection of generic elements (objects in Java) that may have
 * duplicates and allows for efficient addition of elements at the end and
 * removal of elements from the front.
 *
 * @param <Item> the type of elements in the queue (any object type)
 */
public interface Queue<Item> extends Iterable<Item> {

    /**
     * API: Adds an item to the end of the queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item);

    /**
     * API: Removes and returns the item from the front of the queue.
     *
     * @return the item from the front of the queue
     */
    public Item dequeue();

    /**
     * API: Returns the number of items in the queue (zero if empty not null).
     *
     * @return the number of items in the queue (int)
     */
    public int size();

    /**
     * API: Returns true if the queue is empty.
     *
     * @return true if the queue is empty; false otherwise
     */
    default public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Default toString method with custom formatting.
     *
     * @param start the starting string (e.g. "[")
     * @param end the ending string (e.g. "]")
     * @param separator the separator string (e.g. ", ")
     * @return the formatted string representation of the queue
     */
    default public String toString(String start, String end, String separator) {
        String result = start;
        boolean needSeparator = false;
        for (Item item : this) {
            if (needSeparator) {
                result += separator;

            } else {
                needSeparator = true;
            }
            result += item.toString();
        }
    }

}
