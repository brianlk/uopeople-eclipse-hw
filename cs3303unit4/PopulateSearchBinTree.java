package cs3303unit4;

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
			root = new Node(50);
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
		tree.insert(tree.root, 50);
		tree.insert(tree.root, 40);
		tree.insert(tree.root, 60);
		tree.insert(tree.root, 10);
		tree.insert(tree.root, 81);
		tree.insert(tree.root, 71);
		System.out.println(tree.root.right.right.left.value);
	}

}
