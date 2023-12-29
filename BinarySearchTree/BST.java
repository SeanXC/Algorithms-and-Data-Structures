/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 14/11/23 07:33:39
 *
 *  @author Shengxin Chen
 *
 *************************************************************************/
package csu22011_a3;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: O(h)
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() {

		return height(root);
	}
	private int height(Node rt)
	{
		if(rt == null)
		{
			return -1;
		}
		else if((rt.left == null)&(rt.right == null))
		{
			return 0;
		}
		else
		{
			if(height(rt.left)>height(rt.right))
			{
				return height(rt.left)+1;
			}
			else
			{
				return height(rt.right)+1;
			}
		}
	}

	//worst case: as a linked list, the running time is equal to the number of nodes in the tree - 1, so the worst runing time is O(N), when h is the number of keys in the tree 


	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() {
		//				if (isEmpty()) 
		//					return null;
		int medianPos = (size()-1) / 2;
		//System.out.println(medianPos);
		return median(root, medianPos) ;
	}

	public Key median(Node currrentNode, int medianPos) 
	{
		if(currrentNode == null)
		{
			return null;
		}
		int pos = index(currrentNode.key);
		if(medianPos < pos)	
		{
			return median(currrentNode.left, medianPos);
		}
		else if (medianPos > pos)
		{
			return median(currrentNode.right, medianPos);
		}
		else 
		{
			return currrentNode.key;
		}

	}

	public int index (Key key)
	{
		return index(root, key);
	}

	public int index(Node node, Key key)
	{
		int tmp = key.compareTo(node.key); //keyRand 
		if(tmp >0 ) // keyRand > currentKeyRank
		{
			return (size(node.left)+index(node.right, key)+1);
		}
		else if(tmp < 0 )  // keyRand < currentKeyRank
		{
			return index(node.left, key);
		}
		else
		{//find key rank
			return size(node.left)-1+1;
		}

	}

	// the worst case tree 	is a Skewed tree (like a linked list, the pos function will  go through the whole tree to find the key, which is O(N), N is the number of keys 


	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		if (isEmpty()) return "()";
		return printKeysInOrder(root);
	}
	public String printKeysInOrder(Node rt)
	{
		if (rt == null)
		{
			return "()";
		}
		else 
		{
			return "(" + printKeysInOrder(rt.left) + rt.key + printKeysInOrder(rt.right)+")";
		}

	}


	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		return prettyPrintKeys(root, "");
	}	

	public String prettyPrintKeys(Node rt, String prefix)
	{
		if (rt == null)
		{
			return prefix + "-null"+"\n";
		}
		else 
		{
			String tmp = prefix + "-" + rt.key + "\n";
			String leftPrefix = prefix +" "+"|";
			tmp += prettyPrintKeys(rt.left, leftPrefix);
			String rightPrefix = prefix + "  ";
			tmp += prettyPrintKeys(rt.right, rightPrefix);
			return tmp;
		}
	}


	/**
	 * Deletes a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	public void delete(Key key) {
		if (this.contains(key)) root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		int cmp = key.compareTo(node.key);
		if (cmp < 0)        node.left = delete(node.left, key);
		else if (cmp > 0)   node.right = delete(node.right, key);
		else {
			if (node.left == null)   return node.right;
			if (node.right == null)  return node.left;

			Node temp = node;
			node = max(temp.left);
			node.left = deleteMax(temp.left);
			node.right = temp.right;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	// find max key
	public Node max(Node node)
	{
		return predecessor(node);
	}

	private Node predecessor(Node node)
	{
		if (node.right != null) node = max(node.right);
		return node;
	}

	// Deletes the maximum value in the tree 
	public void deleteMax(){
		root = deleteMax(root);
	}

	private Node deleteMax(Node node){
		if(node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.N = 1 + size(node.left) + size(node.right);
		return node; 
	}
	/**
	 * This method takes an array of Key objects and determines whether the
	 * given sequence can represent the pre-order traversal of a BST.
	 *
	 * @param keys an array of keys	
	 */
	public boolean isBSTPreOrder(Key[] keys) 
	{
		if(keys.length == 0)
		{
			return true;
		}
		else
		{		
			return isBSTPreOrderRec(keys, 0, keys.length-1);
		}
	}
	private boolean isBSTPreOrderRec(Key[] keys, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return true;
		}
		int mid = startIndex + 1;
		for(;mid < endIndex && keys[mid].compareTo(keys[startIndex])<0 ; mid++);

		for(int i = mid; i< endIndex ; i++)
		{
			if(keys[i].compareTo(keys[startIndex])<0)
			{
				return false;
			}
		}    
		return isBSTPreOrderRec(keys, startIndex+1, mid-1 )&&isBSTPreOrderRec(keys, mid, endIndex);

	}


	//			public static void main(String[] args) {
	//		
	//				Integer[] keys = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1   }; 
	//		
	//				BST<Integer, Object> bst = new BST<>();
	//		
	//				boolean result = bst.isBSTPreOrder(keys);
	//				System.out.println("The array represents a pre-order traversal of a BST: " + result);
	//			}



	//{
	//		if (keys.length == 0) {
	//			return true;
	//		}
	//		BST<Key, Value> createdBST = new BST<>();
	//		for (Key key : keys) {
	//			createdBST.put(key, null);
	//		}
	//
	//		Queue<Key> q = new LinkedList<Key>();
	//		preOrder(createdBST.root, q);
	//
	//		int i = 0;
	//		for(Key key : q)
	//		{
	//			if(key.compareTo(keys[i])!=0)
	//			{
	//				return false;
	//			}
	//			i++;
	//		}
	//		return true;
	//	}
	//
	//	private void preOrder(Node x, Queue<Key> q) {
	//		if (x == null) {return;}
	//		q.add(x.key); 
	//		preOrder(x.left, q);
	//		preOrder(x.right, q);
	//	}

}
