package search.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import search.symboltable.SymbolTable;

/**
 * Implementation of a binary search tree for symbol ordering.
 *
 * @param <Key> The generic type for the keys, must implement Comparable
 * @param <Value> The generic type for the values
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    /**
     * Inner class that represents a node in the binary search tree.
     */
    private class Node {

        // made key final
        /**
         * The key for the node, must be not null
         */
        private final Key key;
        /**
         * The value associated with the key, must be not null
         */
        private Value value;

        private Node leftSubtree;
        private Node rightSubtree;

        /**
         * Size of the subtree rooted at this node
         */
        private int size;

        /**
         * Constructor for creating a new node in the binary search tree.
         *
         * @param key - The key for the node
         * @param value - The associated value
         */
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.leftSubtree = null;
            this.rightSubtree = null;
        }
    }

    private Node rootNode;

    /**
     * Constructor for creating an empty binary search tree.
     */
    public BinarySearchTree() {
        rootNode = null;
    }

    /**
     * Returns the size of the subtree rooted at the given node.
     *
     * @param subtree
     * @return
     */
    private int size(Node subtree) {
        if (subtree == null) {
            return 0;
        }
        return subtree.size;
    }

    /**
     * Returns the size of the binary search tree.
     */
    @Override
    public int size() {
        return size(rootNode);
    }

    /**
     * Returns true if the binary search tree is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves the value associated with the given key in the subtree rooted
     * at subtreeRoot.
     *
     * @param subtreeRoot
     * @param key
     * @return
     */
    private Value get(Node subtreeRoot, Key key) {
        // check if an empty subtree
        if (subtreeRoot == null) // not found in an empty subtree
        {
            return null;
        }
        // compare the searched key with the root key
        int cmp = key.compareTo(subtreeRoot.key);
        // if the key is less than the key of the root node
        if (cmp < 0) // search in the left subtree
        {
            return get(subtreeRoot.leftSubtree, key);
        }// if the key is greater than the key of the root node
        else if (cmp > 0) // search in the right subtree
        {
            return get(subtreeRoot.rightSubtree, key);
        }// if the key is equal with the key of the root node
        else // key found, return the associated value (not-null)
        {
            return subtreeRoot.value;
        }
    }

    /**
     * Checks if the binary search tree contains the given key.
     */
    @Override
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }
        return get(key) != null;
    }

    /**
     * Retrieves the value associated with the given key.
     */
    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }
        return get(rootNode, key);
    }

    /**
     * Helper method to insert a key-value pair into the subtree rooted at
     * treeRoot.
     *
     * @param treeRoot The root of the subtree
     * @param key The key to insert
     * @param value The value associated with the key
     * @return The updated subtree root
     */
    private Node put(Node treeRoot, Key key, Value value) {
        //I liked the comments from the assignment so I kep them for clarity when I review
        if (treeRoot == null) //if the tree is empty create and return a single node tree with  this association.
        {
            return new Node(key, value);
        }
        // compare the key with the key in the root node of the tree
        int cmp = key.compareTo(treeRoot.key);
        // if the key is less than the key of the root node
        if (cmp < 0) // put the association in the left subtree
        {
            treeRoot.leftSubtree = put(treeRoot.leftSubtree, key, value);
        }// if the key is greater than the key of the root node
        else if (cmp > 0) // put the association in the right subttree
        {
            treeRoot.rightSubtree = put(treeRoot.rightSubtree, key, value);
        }// if the keys are equal (cmp ==0) 
        else // update the value associated with the key (in the root node)
        {
            treeRoot.value = value;
        }
        // update the size of the tree
        treeRoot.size = 1 + size(treeRoot.leftSubtree) + size(treeRoot.rightSubtree);
        return treeRoot;
    }

    /**
     * Inserts a key-value pair into the binary search tree. If the value is
     * null, removes the key from the tree.
     */
    @Override
    public void put(Key key, Value value) {

        if (key == null) { // check valid key
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }

        if (value == null) { // if value is null delete the key
            delete(key);
            return;
        }

        rootNode = put(rootNode, key, value); // add the association key-value and update the tree
    }

    /**
     * Deletes the minimum key in the subtree rooted at x.
     *
     * @param x
     * @return
     */
    private Node deleteMin(Node x) {
        if (x.leftSubtree == null) {
            return x.rightSubtree;
        }
        x.leftSubtree = deleteMin(x.leftSubtree);
        x.size = size(x.leftSubtree) + size(x.rightSubtree) + 1;
        return x;
    }

    /**
     * Deletes the minimum key in the binary search tree. If the tree is empty,
     * throw an exception.
     *
     * @return
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table is empty - no minimum element");
        }
        rootNode = deleteMin(rootNode);
    }

    /**
     * Deletes the maximum key in the binary search tree. If the tree is empty,
     * throw an exception.
     *
     * @return
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        rootNode = deleteMax(rootNode);
        assert check();
    }

    /**
     * Deletes the maximum key in the subtree rooted at x.
     *
     * @param x
     * @return
     */
    private Node deleteMax(Node x) {
        if (x.rightSubtree == null) {
            return x.leftSubtree;
        }
        x.rightSubtree = deleteMax(x.rightSubtree);
        x.size = size(x.leftSubtree) + size(x.rightSubtree) + 1;
        return x;
    }

    /**
     * Deletes the key (and its associated value) from the binary search tree.
     */
    @Override
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with a null key");
        }
        rootNode = delete(rootNode, key);
        assert check();
    }

    /**
     * Helper method to delete a key from the subtree rooted at x.
     *
     * @param x The root of the subtree
     * @param key The key to delete
     * @return The updated subtree root
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.leftSubtree = delete(x.leftSubtree, key);
        } else if (cmp > 0) {
            x.rightSubtree = delete(x.rightSubtree, key);
        } else {
            if (x.rightSubtree == null) {
                return x.leftSubtree;
            }
            if (x.leftSubtree == null) {
                return x.rightSubtree;
            }
            Node t = x;
            x = min(t.rightSubtree);
            x.rightSubtree = deleteMin(t.rightSubtree);
            x.leftSubtree = t.leftSubtree;
        }
        x.size = size(x.leftSubtree) + size(x.rightSubtree) + 1;
        return x;
    }

    // hope
    /**
     * Returns the minimum key in the binary search tree.
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(rootNode).key;
    }

    /**
     * Helper method to find the node with the minimum key in the subtree rooted
     * at x.
     *
     * @param x The root of the subtree
     * @return The node with the minimum key
     */
    private Node min(Node x) {
        if (x.leftSubtree == null) {
            return x;
        } else {
            return min(x.leftSubtree);
        }
    }

    /**
     * Returns the maximum key in the binary search tree.
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(rootNode).key;
    }

    /**
     * Helper method to find the node with the maximum key in the subtree rooted
     * at x.
     *
     * @param x The root of the subtree
     * @return The node with the maximum key
     */
    private Node max(Node x) {
        if (x.rightSubtree == null) {
            return x;
        } else {
            return max(x.rightSubtree);
        }
    }

    /**
     * Returns the largest key less than or equal to the given key.
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(rootNode, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        } else {
            return x.key;
        }
    }

    /**
     * Helper method to find the node with the largest key less than or equal to
     * the given key in the subtree rooted at x.
     *
     * @param x The root of the subtree
     * @param key The key to compare
     * @return The node with the largest key less than or equal to the given key
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.leftSubtree, key);
        }
        Node t = floor(x.rightSubtree, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Returns the smallest key greater than or equal to the given key.
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(rootNode, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too large");
        } else {
            return x.key;
        }
    }

    /**
     * Helper method to find the node with the smallest key greater than or
     * equal to the given key in the subtree rooted at x.
     *
     * @param x The root of the subtree
     * @param key The key to compare
     * @return The node with the smallest key greater than or equal to the given
     * key
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.leftSubtree, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }
        return ceiling(x.rightSubtree, key);
    }

    /**
     * Returns the key of rank rank.
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(rootNode, rank);
    }

    /**
     * Helper method to find the key of rank rank in the subtree rooted at x.
     *
     * @param x The root of the subtree
     * @param rank The rank to find
     * @return The key of rank rank
     */
    private Key select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.leftSubtree);
        if (leftSize > rank) {
            return select(x.leftSubtree, rank);
        } else if (leftSize < rank) {
            return select(x.rightSubtree, rank - leftSize - 1);
        } else {
            return x.key;
        }
    }

    /**
     * Returns the number of keys less than the given key.
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, rootNode);
    }

    /**
     * Helper method to compute the number of keys less than the given key in
     * the subtree rooted at x.
     *
     * @param key The key to compare
     * @param x The root of the subtree
     * @return The number of keys less than the given key
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.leftSubtree);
        } else if (cmp > 0) {
            return 1 + size(x.leftSubtree) + rank(key, x.rightSubtree);
        } else {
            return size(x.leftSubtree);
        }
    }

    /**
     * Returns a string representation of the binary search tree.
     */
    private String toString(Node x) {
        if (x == null) {
            return "";
        }
        return "(" + toString(x.leftSubtree) + ") " + x.key.toString() + "=" + x.value.toString() + " (" + toString(x.rightSubtree) + ")";
    }

    /**
     * Returns a string representation of the binary search tree.
     */
    @Override
    public String toString() {
        return toString(rootNode);
    }

    /**
     * Helper method to create a string representation of the binary search tree
     * in a hierarchical format.
     */
    private String toStringHT(String prefix, String left, String val, String right, Node x) {
        if (x == null) {
            return "";
        }
        String result = "";
        result += toStringHT(prefix + right + " ", "| ", "+- ", "  ", x.rightSubtree);
        result += prefix + val + x.key + "(" + x.value + ")\n";
        result += toStringHT(prefix + left + " ", "  ", "+- ", "| ", x.leftSubtree);
        return result;
    }

    /**
     * Returns a string representation of the binary search tree.
     */
    public String toStringHT() {
        return toStringHT("", "", "", "", rootNode);
    }

    /**
     * Returns an iterable collection of all keys in the symbol table.
     *
     * @return an iterable collection of keys
     */
    @Override
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new ArrayList<>(); // Use diamond operator with proper generics not return new ArrayList();
        }
        return keys(min(), max());
    }

    /**
     * Returns all keys in the given range [lo, hi].
     *
     * @param lo The lower bound key
     * @param hi The upper bound key
     * @return An iterable collection of keys in the specified range
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        ArrayList<Key> queue = new ArrayList<>(); // instead of ArrayList<Key>(); you can use <> instad!
        keys(rootNode, queue, lo, hi);
        return queue;
    }

    /**
     * Helper method to collect keys in the given range [lo, hi] from the
     * subtree rooted at x.
     *
     * @param x The root of the subtree
     * @param queue The collection to store the keys
     * @param lo The lower bound key
     * @param hi The upper bound key
     */
    private void keys(Node x, ArrayList<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.leftSubtree, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.rightSubtree, queue, lo, hi);
        }
    }

    /**
     * Returns an iterator over the keys in the binary search tree.
     */
    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
    }

    /**
     * Returns the number of keys in the range [lo, hi].
     *
     * @param lo The lower bound key
     * @param hi The upper bound key
     * @return The number of keys in the specified range
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Returns the height of the binary search tree.
     */
    public int height() {
        return height(rootNode);
    }

    /**
     * Helper method to compute the height of the subtree rooted at x.
     *
     * @param x The root of the subtree
     * @return The height of the subtree
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.leftSubtree), height(x.rightSubtree));
    }

    /**
     * Returns the keys in level-order traversal.
     *
     * @return An iterable collection of keys in level-order
     */
    public Iterable<Key> levelOrder() {
        ArrayList<Key> keys = new ArrayList<>(); // instead of ArrayList<Key>(); you can use <> instad!
        ArrayList<Node> queue = new ArrayList<>(); //dropped Node from <>
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            Node x = queue.remove(0);
            if (x == null) {
                continue;
            }
            keys.add(x.key);
            queue.add(x.leftSubtree);
            queue.add(x.rightSubtree);
        }
        return keys;
    }

// sanity check
    private boolean check() {
        if (!isBST()) {
            System.out.println("Not in symmetric order");
        }
        if (!isSizeConsistent()) {
            System.out.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()) {
            System.out.println("Ranks not consistent");
        }
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() {
        return isBST(rootNode, null, null);
    }

    /**
     * Helper method to check if the subtree rooted at x is a binary search tree
     * within the given min and max bounds.
     *
     * @param x The root of the subtree
     * @param min The minimum key bound
     * @param max The maximum key bound
     * @return true if the subtree is a binary search tree, false otherwise
     */
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.leftSubtree, min, x.key) && isBST(x.rightSubtree, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(rootNode);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.size != size(x.leftSubtree) + size(x.rightSubtree) + 1) {
            return false;
        }
        return isSizeConsistent(x.leftSubtree) && isSizeConsistent(x.rightSubtree);
    }

    /**
     * Helper method to check if the rank and select operations are consistent.
     *
     * @return true if rank and select are consistent, false otherwise
     */
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }

}
