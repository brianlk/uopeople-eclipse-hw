package cs3303unit3;

public class CreateLinkedList {
	public static int[] intArray = new int[] {2, 1, 0};
	public static Node top = null;
	public static Node current;
	
	
	public static void main(String[] args) {
		top = new Node();
		top.value = -1;
	    // root will be the beginning of the linked list
		
		
		for (int i=0; i < intArray.length; i++) {
			push(intArray[i]);
		}
		pop();
		pop();
	    // Move through the list and print out each value
	    printList(top);
	}

	public static void printList(Node node) {
	  if (node != null) {
		  System.out.println(" Value: " + node.value);
		  printList(node.ptr);
	   }
	}
	
	public static void push(int i) {
		if (top.ptr == null && top.value < 0) {
			Node root = new Node(i);
			current = root;
			top.ptr = current;
		} else {
			Node root = new Node(i);
			top.ptr = root;
			root.ptr = current;
			current = root;
		}
	}
	
	public static void pop() {
		current = top.ptr;
		top.ptr = current.ptr;
	}
}
