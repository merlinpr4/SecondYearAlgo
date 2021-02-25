/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Merlin Prasad
 *
 *************************************************************************/

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
public class BST <Key extends Comparable<Key>, Value> {
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
		if (val == null) 
		{
			delete(key);
			return; 
		}
		else
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
	 * Asymptotic  running time using Theta notation: Theta(square root (N))
	 * For a BST that is suffiently random with deletion the average running time is close to square root of N as the tree 
	 * ended up leaning to the left (source lecture 14 recording)
	 * 
	 * worst case scenario would a bst which is ordered like a list which would lead to very large height 
	 * and O(N). However this case is very unlikely.
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
		if (root == null)
		{
			return -1;	
		}
		else if (size(root) == 1 ) 
		{
			return 0;
		}
		else 
		{
			return height(root, root.key,0);
		}
	}

	private int height(Node x, Key key,int H) {	
		int right = size(x.right);
		int left  = size(x.left);	
		if (left>= right)
		{
			if (left != 0)
			{
				H = H + 1 ;
				return height(x.left,key,H);
			}
		}
		else 
		{
			H ++ ;
			return height(x.right,key,H);	
		}	
		return H;
	}


	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	//TODO fill in the correct implementation. The running time should be Theta(h), where h is the height of the tree.

	public Key median() {
		if (isEmpty())
		{
			return null;
		}
		else if (size(root) == 1 ) 
		{
			return root.key;
		}
		int N = size(root);
		int pos = (N - 1)/2;
		return median(root,pos);
	}

	private Key median(Node x,int pos)  
	{
		int t = size(x.left);
		if(t > pos) 
		{
			return median(x.left,pos);
		}
		else if( t < pos) 
		{
			return median(x.right,pos-t-1);
		}
		else return x.key;
	}



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
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		String keys = "";
		if (isEmpty())
		{
			return "()";
		}
		else
		{ 
			LinkedList<String> q = new LinkedList<String>();
			inorder(root, q);
			int size = q.size();
			for(int i = 0 ; i<size ; i++)
			{
				keys += q.pop();
			}
		}
		return keys;
	}


	private void inorder(Node x, LinkedList<String> q)
	{	
		if (x == null) 
		{
			q.addLast ("()"); 
			return;
		}
		q.addLast ("(");			
		inorder(x.left, q);	

		q.addLast(x.key.toString());
		inorder(x.right, q); 	

		q.addLast (")");
	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() { //return a string
		String keys = "";
		String prefix = "";
		if (isEmpty())
		{
			return "-null\n";
		}
		else
		{
			keys = prettyPrint(root,prefix);
		}
		return keys;
	}

	private String prettyPrint(Node x, String prefix)
	{
		String pretty = "";
		if (x == null) 
		{
			return pretty += prefix + "-null\n";
		}

		pretty +=  prefix + "-" + x.key + "\n" ;

		pretty += prettyPrint(x.left, (prefix + " |"));  

		pretty += prettyPrint(x.right,prefix + "  "); 

		return  pretty;
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
		root = delete(root,key);
	}

	private Node delete(Node x , Key key)
	{
		if (x == null) 
		{
			return null;  
		}
		int cmp = key.compareTo(x.key);  
		if      (cmp < 0) 
		{
			x.left  = delete(x.left,  key);  
		}
		else if (cmp > 0) 
		{
			x.right = delete(x.right, key); 
		}
		else {       
			if (x.right == null) 
			{
				return x.left; 
			}
			if (x.left  == null)
			{
				return x.right;
			}

			Node t = x;   
			x = max(t.left);     
			x.left = deleteMax(t.left);    
			x.right = t.right;  
		}
		x.N = size(x.left) + size(x.right) + 1;  
		return x;
	}

	private Node deleteMax(Node x)
	{
		if(x.right == null)
		{
			return x.left;
		}

		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x ;
	}

	private Node max(Node x)
	{
		if(x.right == null)
		{
			return x;
		}
		x  = max(x.right);		
		return x  ;
	}
}
