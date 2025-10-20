package search.symboltable;

import java.util.Iterator;

/**
 * A naive implementation of a symbol table using an unsorted linked list of
 * associations.
 *
 * @param <Key> The generic type for the keys
 * @param <Value> The generic type for the values
 */
public class UnsortedLinkedListSymbolTable<Key, Value> implements SymbolTable<Key, Value> {

    /**
     * Inner class that represents a key-value pair node in the linked list.
     * This must be private to avoid direct access from outside.
     */
    private class Association {

        /**
         * The key for which a value is associated. Must be not null. Must not
         * be duplicated in the linked list.
         */
        private Key key;
        /**
         * The value associated with the key. Must not be null. May be
         * duplicated. Two keys may have the same value.
         */
        private Value value;
        /**
         * The link to the next association (node) in the linked list
         */
        private Association nextAssociation;

        /**
         * Constructor for creating a new Association node in the linked list.
         * It takes all three parameters needed for a node. Note - public within
         * the inner class but inner class is private so effectively private to
         * outer class
         *
         * @param key - The Key for which the value is associated
         * @param value - The associated Value
         * @param nextAssociation - The link to the next association in the
         * linked list
         */
        public Association(Key key, Value value, Association nextAssociation) {
            this.key = key;
            this.value = value;
            this.nextAssociation = nextAssociation;
        }
    }
    /**
     * Tracks the number of key-value associations in the symbol table. It can
     * be computed based on the linked-list but is cached as computed for
     * efficiency O(1) access instead of O(n) counting. Note - no implicitely
     * required to implement a symbol table.
     */
    private int numberOfAssociations;
    /**
     * Keeps a link (reference) to the first association in the linked list
     * (head node). If null, the symbol table is empty (numberOfAssociation is
     * zero). When null, it means the list is empty This is your entry point
     * into the linked list chain
     */
    private Association firstAssociation;

    /**
     * Constructor that initializes an empty symbol table. Has no parameters.
     */
    public UnsortedLinkedListSymbolTable() {
        this.numberOfAssociations = 0;
        this.firstAssociation = null;
    }

    /**
     * Return the size of the symbol table (the number of associations
     * key-value).
     *
     * @return the number of key-value pairs in the symbol table
     */
    public int size() {
        return numberOfAssociations;
    }

    /**
     * Check if the symbol table is empty.
     *
     * @return true if the symbol table has no association
     */
    public boolean isEmpty() {
        return numberOfAssociations == 0;
    }

    /**
     * Private method that returns the association for a provided key. A private
     * helper method to traverse the linked list. Starts at head node
     * (firstAssociation) and uses equals() to compare key this is going to be
     * used by get(), put(), delete(), and contains()
     *
     * @param key - The key searched
     * @return association node for the key, if any, null otherwise
     */
    private Association getAssociation(Key key) {
        Association node = firstAssociation;
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.nextAssociation;
        }
        return null;
    }

    /**
     * Put a key-value pair in the symbol table. If the value is not-null, add
     * (put) a key-value pair into the symbol table. If the key already exists
     * replace the current value. If the value is null, removes the pair from
     * the symbol table. If key is null, throws NullPointerException. Otherwise
     * add (put) a key-value pair into the symbol table.
     *
     * @param key - The Key for which the value is added
     * @param value - The Value added
     * @throws NullPointerException for a null key
     */
    public void put(Key key, Value value) {
        // check valid key (not null)
        if (key == null) {
            throw new NullPointerException();
        }
        // check if a value is added
        if (value != null) {
            // get current association if any
            Association a = getAssociation(key);
            // check if there is a current association
            if (a != null) {
                // update value for existing association
                a.value = value;
            } else {
                // new node defined, with the association between the provided key and value
                // and linking to the previous association list
                a = new Association(key, value, firstAssociation);
                // set the new  node as the first node
                firstAssociation = a;
                // increase the number of keys (associations)
                numberOfAssociations++;
            }
        } else { // value is null
            // associating null with a key will delete the key
            delete(key);
        }
    }

    /**
     * Get the value associated with the given key.
     *
     * @param key - The key to search for
     * @return the value associated with the key, null if key is not found
     * @throws NullPointerException for a null key
     */
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Association a = getAssociation(key);
        if (a != null) {
            return a.value;
        }
        return null;
    }

    /**
     * Delete a key-value pair from the symbol table. Behavior: Empty list (do
     * nothing), Deleting the first node (update firstAssociation), Deleting any
     * other node (update the previous node's link)
     *
     * @param key - The key to delete
     * @throws NullPointerException for a null key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }

        // Handle empty list
        if (firstAssociation == null) {
            return;
        }

        // Handle deletion of first node
        if (firstAssociation.key.equals(key)) {
            firstAssociation = firstAssociation.nextAssociation;
            numberOfAssociations--;
            return;
        }

        // Handle deletion of any other node
        Association current = firstAssociation;
        while (current.nextAssociation != null) {
            if (current.nextAssociation.key.equals(key)) {
                current.nextAssociation = current.nextAssociation.nextAssociation;
                numberOfAssociations--;
                return;
            }
            current = current.nextAssociation;
        }
    }

    /**
     * Check if the symbol table contains the given key.
     *
     * @param key - The key to search for
     * @return true if the key is in the symbol table, false otherwise
     * @throws NullPointerException for a null key
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return getAssociation(key) != null;
    }

    /**
     * Returns an iterator over the keys in the symbol table.
     *
     * @return an iterator over the keys
     */
    public Iterator<Key> iterator() {
        return new KeyIterator();
    }

    /**
     * Inner class that implements the Iterator interface for keys.
     */
    private class KeyIterator implements Iterator<Key> {

        private Association current;

        public KeyIterator() {
            current = firstAssociation;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Key key = current.key;
            current = current.nextAssociation;
            return key;
        }
    }

    /**
     * Returns an iterable collection of all keys in the symbol table.
     *
     * @return an iterable collection of keys
     */
    public Iterable<Key> keys() {
        return this;
    }

    /**
     * Returns a string representation of the symbol table.
     *
     * @return a string representation showing all key-value pairs
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        Association current = firstAssociation;
        while (current != null) {
            sb.append(current.key).append("=").append(current.value);
            current = current.nextAssociation;
            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }

}
