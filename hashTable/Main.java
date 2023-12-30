public class Main {
    public static void main(String[] args) {
        // Create a hash table with an initial capacity.
        HashTable<String, Integer> hashTable = new HashTable<>(10);

        // Put some key-value pairs into the hash table.
        hashTable.put("apple", 50);
        hashTable.put("banana", 30);
        hashTable.put("orange", 20);
        hashTable.put("mango", 5);
        hashTable.put("lemon", 40);

        // Retrieve and print a value.
        System.out.println("The value for 'apple' is: " + hashTable.get("apple"));

        // Check if a key exists in the hash table.
        System.out.println("Does 'banana' exist? " + hashTable.containsKey("banana"));

        // Remove a key-value pair and print the removed value.
        System.out.println("Removed 'orange', value was: " + hashTable.remove("orange"));

        // Attempt to retrieve a value that doesn't exist.
        System.out.println("The value for 'papaya' is: " + hashTable.get("papaya"));

        // Check the size of the hash table.
        System.out.println("The size of the hash table is: " + hashTable.size());
    }
}
