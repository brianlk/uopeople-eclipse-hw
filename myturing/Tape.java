package myturing;

public class Tape {
	Cell head;
	Cell last;
	Cell currentCell;
	
	public Tape() {
		head = new Cell('S');
		last = new Cell('L');
		currentCell = new Cell(' ');
		head.next = currentCell;
		currentCell.prev = head;
		last.prev = currentCell;
		currentCell.next = last;
	}
	
	public void setContent(char data) {
		currentCell.content = data;
	}
	
	public char getContent() {
		return currentCell.content;
	}
	
	public Cell getCurrentCell() {
		return currentCell;
	}
	
	public void moveRight() {
//		System.out.println("move to Right: " + currentCell + "===");
		if (currentCell.next.content == 'L') {
			Cell newCell = new Cell(' ');
			currentCell.next = newCell;
			newCell.prev = currentCell;
			last.prev = newCell;
			newCell.next = last;
			currentCell = newCell;
		} else {
			currentCell = currentCell.next;
		}
	}
	
	public void moveLeft() {
		
		if (currentCell.prev != null && currentCell.prev.content == 'S')  {
			Cell newCell = new Cell(' ');
			currentCell.prev = newCell;
			newCell.next = currentCell;
			newCell.prev = head;
			head.next = newCell;
			currentCell = newCell;
			return;
		}
		if (currentCell.prev != null) {
			currentCell = currentCell.prev;
			return;
		}
		
	}
	
	
	public String getTapeContents() {
		Cell begin = head;
		String x = "";
		while (begin.next != null && begin.next.content != 'L') {
				x = x + begin.next.content;
				begin = begin.next;	
		}
		return x.trim();
	}
}
