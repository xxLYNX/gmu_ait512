package search.binary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import search.symboltable.SymbolTable;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private class Node {

        // made key final
        private final Key key;
        private Value value;

        private Node leftSubtree;
        private Node rightSubtree;

        private int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.leftSubtree = null;
            this.rightSubtree = null;
        }
    }

    private Node rootNode;

    public BinarySearchTree() {
        rootNode = null;
    }

    private int size(Node subtree) {
        if (subtree == null) {
            return 0;
        }
        return subtree.size;
    }

    @Override
    public int size() {
        return size(rootNode);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

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

    @Override
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }
        return get(key) != null;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }
        return get(rootNode, key);
    }

    private Node put(Node treeRoot, Key key, Value value) {
        // if the tree is empty
        if (treeRoot == null) // create and return a single node tree with  this association.
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
        // return the updated tree
        return treeRoot;
    }

    @Override
    public void put(Key key, Value value) {
        // check valid key
        if (key == null) {
            throw new IllegalArgumentException("Keys are not allowed to be null.");
        }
        // if value is null delete the key
        if (value == null) {
            delete(key);
            return;
        }
        // add the association key-value and update the tree
        rootNode = put(rootNode, key, value);
    }

    private Node deleteMin(Node x) {
        if (x.leftSubtree == null) {
            return x.rightSubtree;
        }
        x.leftSubtree = deleteMin(x.leftSubtree);
        x.size = size(x.leftSubtree) + size(x.rightSubtree) + 1;
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table is empty - no minimum element");
        }
        rootNode = deleteMin(rootNode);
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow");
        }
        rootNode = deleteMax(rootNode);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.rightSubtree == null) {
            return x.leftSubtree;
        }
        x.rightSubtree = deleteMax(x.rightSubtree);
        x.size = size(x.leftSubtree) + size(x.rightSubtree) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with a null key");
        }
        rootNode = delete(rootNode, key);
        assert check();
    }

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
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(rootNode).key;
    }

    private Node min(Node x) {
        if (x.leftSubtree == null) {
            return x;
        } else {
            return min(x.leftSubtree);
        }
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(rootNode).key;
    }

    private Node max(Node x) {
        if (x.rightSubtree == null) {
            return x;
        } else {
            return max(x.rightSubtree);
        }
    }

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

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(rootNode, rank);
    }

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

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, rootNode);
    }

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

    private String toString(Node x) {
        if (x == null) {
            return "";
        }
        return "(" + toString(x.leftSubtree) + ") " + x.key.toString() + "=" + x.value.toString() + " (" + toString(x.rightSubtree) + ")";
    }

    @Override
    public String toString() {
        return toString(rootNode);
    }

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

    public String toStringHT() {
        return toStringHT("", "", "", "", rootNode);
    }

    @Override
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new ArrayList<>(); // Use diamond operator with proper generics not return new ArrayList();
        }
        return keys(min(), max());
    }

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

    @Override
    public Iterator<Key> iterator() {
        return keys().iterator();
    }

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

    public int height() {
        return height(rootNode);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.leftSubtree), height(x.rightSubtree));
    }

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
