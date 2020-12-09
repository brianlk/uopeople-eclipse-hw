package cs3303unit4;

import java.util.*;

public class PopulateSearchBinTree {
	// Declare the node class
	class Node {
		int value;
		Node left, right;	
		public Node(int input) {
			value = input;
			left = right = null;
		}
	}
	
	// Declare the root
	Node root;
	
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
	
	public static void main(String[] args) {
		PopulateSearchBinTree tree = new PopulateSearchBinTree();
		int checkInput = 0;
		System.out.print("Integers to add to binary search tree: ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] inputArr = input.replaceAll("\\s+", "").split(",");
		for (int i=0; i < inputArr.length - 1; i++) {
			int x = Integer.parseInt(inputArr[i]);
			tree.insert(tree.root, x);
		}
		System.out.println(tree.root.left.value);
        
	}

}
