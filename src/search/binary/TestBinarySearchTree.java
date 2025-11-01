package search.binary;

public class TestBinarySearchTree {

    public static void main(String[] args) {
        String name = "Cullen Kelley";
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        for (int i = 0; i < name.length(); i++) {
            bst.put(name.substring(i, i + 1).toLowerCase(), i);
        }
        System.out.println(bst.toStringHT());
    }

}
