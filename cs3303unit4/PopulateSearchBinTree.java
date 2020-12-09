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

}
