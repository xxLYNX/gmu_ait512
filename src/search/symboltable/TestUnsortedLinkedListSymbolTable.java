package search.symboltable;

/**
 * Test class for the UnsortedLinkedListSymbolTable implementation. This class
 * contains comprehensive tests to verify all symbol table operations.
 */
public class TestUnsortedLinkedListSymbolTable {

    /**
     * Helper method to print test operation results in a consistent format.
     *
     * @param operationNumber The operation number
     * @param description Description of the operation
     * @param symbolTable The symbol table to display
     * @param additionalInfo Any additional information to display
     */
    private static void testReport(int operationNumber, String description,
            UnsortedLinkedListSymbolTable<String, Integer> symbolTable,
            String additionalInfo) {
        System.out.println("Operation " + operationNumber + " - " + description + ":");
        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            System.out.println(additionalInfo);
        }
        System.out.println("Symbol table: " + symbolTable);
        System.out.println("size(): " + symbolTable.size() + ", isEmpty(): " + symbolTable.isEmpty());
        System.out.println();
    }

    /**
     * Main method to test the UnsortedLinkedListSymbolTable implementation.
     * Tests at least 10 operations using at least 5 different methods.
     */
    public static void main(String[] args) {
        System.out.println("Module 4 Assignment 1: Unsorted LinkedList Symbol Table - Task 3: Testing | Author: Cullen Kelley");
        System.out.println("Date: " + java.time.LocalDate.now());
        // Create a new symbol table instance
        UnsortedLinkedListSymbolTable<String, Integer> symbolTable
                = new UnsortedLinkedListSymbolTable<>();

        System.out.println("=== Testing UnsortedLinkedListSymbolTable ===\n");

        // Operation 1: Check if empty
        testReport(1, "Check if empty", symbolTable, null);

        // Operation 2: Put first key-value pair
        symbolTable.put("apple", 5);
        testReport(2, "Put 'apple' = 5", symbolTable, null);

        // Operation 3: Put second key-value pair
        symbolTable.put("banana", 3);
        testReport(3, "Put 'banana' = 3", symbolTable, null);

        // Operation 4: Put third key-value pair
        symbolTable.put("cherry", 8);
        testReport(4, "Put 'cherry' = 8", symbolTable, null);

        // Operation 5: Get existing key
        Integer value = symbolTable.get("banana");
        testReport(5, "Get 'banana'", symbolTable, "get('banana'): " + value);

        // Operation 6: Check if key exists
        boolean containsApple = symbolTable.contains("apple");
        testReport(6, "Check if 'apple' exists", symbolTable, "contains('apple'): " + containsApple);

        // Operation 7: Update existing key
        symbolTable.put("apple", 10);
        testReport(7, "Update 'apple' = 10", symbolTable, null);

        // Operation 8: Get non-existing key
        Integer nonExistent = symbolTable.get("orange");
        testReport(8, "Get 'orange' (doesn't exist)", symbolTable, "get('orange'): " + nonExistent);

        // Operation 9: Delete existing key
        symbolTable.delete("banana");
        testReport(9, "Delete 'banana'", symbolTable, null);

        // Operation 10: Test iterator (keys method)
        StringBuilder keysBuilder = new StringBuilder("Keys: ");
        for (String key : symbolTable.keys()) {
            keysBuilder.append(key).append(" ");
        }
        testReport(10, "Iterate through all keys", symbolTable, keysBuilder.toString());

        // Operation 11: Delete using null value (bonus operation)
        symbolTable.put("cherry", null);
        testReport(11, "Delete 'cherry' using put(key, null)", symbolTable, null);

        System.out.println("\n=== Test Complete ===");
    }

}
