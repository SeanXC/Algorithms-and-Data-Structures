import java.util.LinkedList;

class Entry<K, V> {
	final K key;
	V value;

	Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

public class HashTable<K, V> {
	private int capacity;
	private int size;
	private LinkedList<Entry<K, V>>[] table;

	public HashTable(int capacity) {
		this.capacity = capacity;
		this.table = new LinkedList[capacity];
	}

	private int getHash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	public void put(K key, V value) {
		int index = getHash(key);
		if (table[index] == null) {
			table[index] = new LinkedList<>();
		}

		for (Entry<K, V> entry : table[index]) {
			if (entry.key.equals(key)) {
				entry.value = value;
				return;
			}
		}

		table[index].add(new Entry<>(key, value));
		size++;
	}

	public V get(K key) {
		int index = getHash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		if (bucket != null) {
			for (Entry<K, V> entry : bucket) {
				if (entry.key.equals(key)) {
					return entry.value;
				}
			}
		}
		return null;
	}

	public V remove(K key) {
		int index = getHash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		if (bucket != null) {
			for (Entry<K, V> entry : bucket) {
				if (entry.key.equals(key)) {
					V value = entry.value;
					bucket.remove(entry);
					size--;
					return value;
				}
			}
		}
		return null;
	}

	public boolean containsKey(K key) {
		int index = getHash(key);
		LinkedList<Entry<K, V>> bucket = table[index];
		if (bucket != null) {
			for (Entry<K, V> entry : bucket) {
				if (entry.key.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	public int size() {
		return size;
	}
}
