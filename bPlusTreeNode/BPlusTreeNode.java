import java.util.ArrayList;

public class BPlusTreeNode {
	ArrayList<Integer> keys;
	ArrayList<BPlusTreeNode> children;
	boolean isLeaf;
	int t; // Minimum degree (defines the range for the number of keys)

	public BPlusTreeNode(int t, boolean isLeaf) {
		this.t = t;
		this.isLeaf = isLeaf;
		keys = new ArrayList<>(2 * t - 1); // Maximum number of keys
		children = new ArrayList<>(2 * t); // Maximum number of children
	}

	public void insertNonFull(int key) {
		int i = keys.size() - 1;

		if (isLeaf) {
			// Find the location of the new key to be inserted
			while (i >= 0 && keys.get(i) > key) {
				i--;
			}
			// Insert the new key at the found location
			keys.add(i + 1, key);
		} else {
			// Find the child which is going to have the new key
			while (i >= 0 && keys.get(i) > key) {
				i--;
			}

			BPlusTreeNode child = children.get(i + 1);
			if (child.keys.size() == 2 * t - 1) {
				// If the child is full, then split it
				splitChild(i + 1, child);

				// After split, the middle key of child goes up and child is split into two
				if (keys.get(i + 1) < key) {
					i++;
				}
			}
			children.get(i + 1).insertNonFull(key);
		}
	}

	public void splitChild(int i, BPlusTreeNode y) {
		// Create a new node to store (t-1) keys of y
		BPlusTreeNode z = new BPlusTreeNode(y.t, y.isLeaf);
		for (int j = 0; j < t - 1; j++) {
			z.keys.add(y.keys.remove(t));
		}

		// If y is not a leaf, move the last t children of y to z
		if (!y.isLeaf) {
			for (int j = 0; j < t; j++) {
				z.children.add(y.children.remove(t));
			}
		}

		// Insert a new key in the current node
		keys.add(i, y.keys.remove(t - 1));
		children.add(i + 1, z);
	}
}
