package search.binary;

public class TestBinarySearchTree {

    /**
     * Main method to test the BinarySearchTree implementation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Module 4 Assignment 1: Binary Search Tree\nTask: Submission\nAuthor: Cullen Kelley\nDate: " + java.time.LocalDate.now());
        String name = "Cullen Kelley";
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        for (int i = 0; i < name.length(); i++) {
            bst.put(name.substring(i, i + 1).toLowerCase(), i);
        }
        System.out.println(bst.toStringHT());
    }

}
