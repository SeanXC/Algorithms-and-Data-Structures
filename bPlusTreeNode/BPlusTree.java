public class BPlusTree {
	BPlusTreeNode root;
	int t; // Minimum degree

	public BPlusTree(int t) {
		this.root = null;
		this.t = t;
	}

	public void insert(int key) {
		if (root == null) {
			root = new BPlusTreeNode(t, true);
			root.keys.add(key);
		} else {
			if (root.keys.size() == 2 * t - 1) {
				// Root is full, need to split
				BPlusTreeNode newNode = new BPlusTreeNode(t, false);
				newNode.children.add(root);
				newNode.splitChild(0, root);

				// New root has two children now. Decide which child is going to have new key
				int i = 0;
				if (newNode.keys.get(0) < key) {
					i++;
				}
				newNode.children.get(i).insertNonFull(key);

				// Change root
				root = newNode;
			} else {
				// Insert key into the non-full root
				root.insertNonFull(key);
			}
		}
	}

	public Integer search(int key) {
		return root == null ? null : search(root, key);
	}

	private Integer search(BPlusTreeNode node, int key) {
		int i = 0;
		while (i < node.keys.size() && key > node.keys.get(i)) {
			i++;
		}

		if (i < node.keys.size() && key == node.keys.get(i)) {
			return node.keys.get(i); // Found the key
		}

		if (node.isLeaf) {
			return null; // Key not found
		}

		return search(node.children.get(i), key); // Search in the appropriate child
	}
}
