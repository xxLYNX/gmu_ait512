/**
 * Module 1b Assignment 1: Stack
 *
 * @date 9/13/2025
 * @author Cullen Kelley
 */
package ds.stack;

/**
 * Interface for a generic stack (LIFO - last in first out) Stack (ADT): an
 * ordered collection of generic elements (objects in Java) that may have
 * duplicates and allows for efficient addition and removal of elements from the
 * top.
 *
 * @param <Item> the type of elements in the stack (any object type)
 */
public interface Stack<Item> extends Iterable<Item> {

    /**
     * API: Adds an item to the top of the stack.
     *
     * @param item the item to add
     */
    public void push(Item item);

    /**
     * API: Removes and returns the topmost item from the top of the stack.
     *
     * @return the item from the top of the stack
     */
    public Item pop();

    /**
     * API: Returns the number of items in the stack (zero if empty not null).
     *
     * @return the number of items in the stack (int)
     */
    public int size();

    /**
     * API: Returns true if the stack is empty.
     *
     * @return true if the stack is empty; false otherwise
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
     * @return the formatted string representation of the stack
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
        result += end;
        return result;
    }

}
