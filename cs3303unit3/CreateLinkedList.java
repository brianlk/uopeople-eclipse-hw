package cs3303unit3;

public class CreateLinkedList {
	public static void main(String[] args) {
		Node link, plink;
	    // root will be the beginning of the linked list
		Node root = new Node(5);
	    // each additional node will link to preceeding one
		link = new Node(1);
	    link.ptr = root;
	    plink = link;

	    link = new Node(8);
	    link.ptr = plink;
	    plink = link;

	    link = new Node(6);
	    link.ptr = plink;
	    plink = link;

	    link = new Node(3);
	    link.ptr = plink;
	    plink = link;
	   
	    // Move through the list and print out each value
	    printList(link);
	}

	public static void printList(Node node) {
	  if (node != null) {
		  System.out.println(" Value: " + node.value);
		  printList(node.ptr);
	   }
	}
}
