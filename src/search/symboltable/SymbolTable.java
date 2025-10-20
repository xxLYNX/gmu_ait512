package search.symboltable;

/**
 * A symbol table associates a value with a key. A client inserts key-value
 * pairs into the table and searches for the value for a given key. For a key
 * must be a single value associated. There are no duplicated keys (two
 * associations for the same key). If a key has no value associated (or null)
 * then it is not considered as part of the symbol table. There is no null key.
 * Key equality is tested with equals() method.
 *
 * @param <Key> The generic type for the keys
 * @param <Value> The generic type for the values
 */
public interface SymbolTable<Key, Value> extends Iterable<Key> {

    /**
     * Put a key-value pair in the symbol table. If the value is not-null, add
     * (put) a key-value pair into the symbol table. If the key already exists
     * replace the current value. If the value is null, removes the pair from
     * the symbol table.
     *
     * @param key - The key for which the value is added
     * @param value - The value added
     */
    public void put(Key key, Value value);

    /**
     * Get the value for a key from the symbol table, if any. Return null if no
     * value is associated with the key.
     *
     * @param key - The key for which the value is requested
     * @return the value associated or null if none
     */
    public Value get(Key key);

    /**
     * Remove a key and its associated value from a symbol table. Similar with
     * put(key, null).
     *
     * @param key - The key to be removed
     */
    public void delete(Key key);

    /**
     * Check if a key has a value associated in the symbol table.
     *
     * @param key - The checked key
     * @return true - if the key has an associated value in the symbol table
     */
    public boolean contains(Key key);

    /**
     * Check if the symbol table is empty (i.e. contains no key-value
     * association).
     *
     * @return true - if the symbol table has no association
     */
    public boolean isEmpty();

    /**
     * Return the size of the symbol table, i.e. the number of associations
     * stored.
     *
     * @return an int - the size of the symbol table
     */
    public int size();

    /**
     * Return an iterable over the keys of the symbol tables. Can be used in
     * for-each. Same effect with the iterator over the symbol table.
     *
     * @return an Iterable of Keys
     */
    public Iterable<Key> keys();

}
