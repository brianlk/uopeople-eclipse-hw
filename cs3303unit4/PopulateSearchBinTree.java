package cs3303unit4;

import java.util.*;

// Declare the node class
class Node {
	int value;
	Node left, right;	
	public Node(int input) {
		value = input;
		left = right = null;
	}
}


public class PopulateSearchBinTree {

	// Declare the root
	Node root;
	int searchValue;
	int count = 0;
	boolean found = false;
	
	PopulateSearchBinTree() {
		root = null;
	}
	
	void insert(Node node, int key) {
		if (root == null) {
			root = new Node(key);
			return;
		}
		if (key < node.value) {
			if (node.left == null) {
				node.left = new Node(key);
			} else {
				insert(node.left, key);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(key);
			} else {
				insert(node.right, key);
			}
		}
		return;
		
	}
	
	Node search(Node root, int key) {
		count++;
		if (root == null || root.value == key) {
			System.out.println("Found: " + key);
			return root;	
		}
		if (root.value > key)
			return search(root.left, key);
		return search(root.right, key);
	}
	
	public static void main(String[] args) {
		PopulateSearchBinTree tree = new PopulateSearchBinTree();
		System.out.println("Integers to add to binary search tree: ");
		int[] inputArr = {10, 5, 12, 3, 1, 13, 7, 2, 4, 14, 9, 8, 6, 11};

		for (int i=0; i < inputArr.length; i++) {
			System.out.println("insert integer: " + inputArr[i]);
			tree.insert(tree.root, inputArr[i]);
		}
		System.out.println("Search an integer: ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		tree.search(tree.root, num);
		in.close();
		System.out.print("Count: " + tree.count);
	}

}
