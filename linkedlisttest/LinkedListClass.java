package linkedlisttest;

public class LinkedListClass {
	Node head;
	
	public void insert(int data) {
		Node newNode = new Node(data);
		newNode.next = null;
		if (this.head == null) {
			this.head = newNode;
		} else {
			Node currentNode = head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
		}
	}
	
	public void printList() {
		Node currentNode = head;
		while(currentNode != null) {
			System.out.println(currentNode.content);
			currentNode = currentNode.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedListClass list = new LinkedListClass();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.printList();
	}
}
