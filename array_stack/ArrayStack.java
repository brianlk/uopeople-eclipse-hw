package array_stack;

public class ArrayStack<ch> implements Stack<ch> {
	private static final int defaultSize = 10;
	private int maxSize;
	private int top;
	private ch[] listArray;
	
	ArrayStack() {
		this(defaultSize);
	}
	ArrayStack(int size) {
		maxSize = size;
		top = 0;
		listArray = (ch[]) new Object[size];
	}
	public void clear() {
		top = 0;
	}
	public void push(ch it) {
		assert top != maxSize : "Stack is full";
		listArray[top++] = it;
	}
	public ch pop() {
		return listArray[--top];
	}
	public ch topValue() {
		return listArray[top-1];
	}
	public int length() {
		return top;
	}
	
	public static void main(String[] args) {
		ArrayStack x = new ArrayStack();
		x.push('a');
		System.out.println(x.length());
		System.out.println(x.pop());
	}
}
