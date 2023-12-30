import java.util.NoSuchElementException;
import java.util.*;

public class HeapSort<Key extends Comparable<Key>> {

	private Key[] pq; // heap-ordered complete binary tree
    private int N = 0; // in pq[1..N] with pq[0] unused

    public HeapSort(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        pq[++N] = key; // Add new key at the end of the array
        swim(N); // Swim it up to maintain heap invariant
    }

    public Key delMax() {
        Key max = pq[1]; // Retrieve max key from top
        exch(1, N--); // Exchange with last item
        sink(1); // Sink it down to maintain heap invariant
        pq[N+1] = null; // Prevent loitering
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void printArray() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
        } else {
            System.out.print("Heap array: ");
            for (int i = 1; i <= N; i++) {
                System.out.print(pq[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HeapSort<Integer> maxPQ = new HeapSort<>(10); // Create a MaxPQ with capacity 10

        // Insert some keys and print the array after each insertion
        int[] keysToInsert = {4, 9, 3, 5, 1, 7, 10};
        System.out.println("Inserting elements:");
        for (int key : keysToInsert) {
            maxPQ.insert(key);
            maxPQ.printArray(); // Print the array after each insert
        }

        // Print and remove the maximum element until the queue is empty
        System.out.println("Deleting elements:");
        while (!maxPQ.isEmpty()) {
            int max = maxPQ.delMax();
            System.out.println("Max element removed: " + max);
            maxPQ.printArray(); // Print the array after each deletion
        }
    }
    
}
