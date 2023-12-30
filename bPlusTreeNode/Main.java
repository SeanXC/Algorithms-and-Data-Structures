public class Main {
    public static void main(String[] args) {
        // Create a BPlusTree with minimum degree t
        int t = 3; // Minimum degree
        BPlusTree bPlusTree = new BPlusTree(t);

        // Insert some keys
        bPlusTree.insert(10);
        bPlusTree.insert(20);
        bPlusTree.insert(5);
        bPlusTree.insert(6);
        bPlusTree.insert(12);
        bPlusTree.insert(30);
        bPlusTree.insert(7);
        bPlusTree.insert(17);

        // Search for some keys
        System.out.println("Search for 6: " + bPlusTree.search(6));
        System.out.println("Search for 15: " + bPlusTree.search(15));
        System.out.println("Search for 10: " + bPlusTree.search(10));
        System.out.println("Search for 20: " + bPlusTree.search(20));
    }
}
