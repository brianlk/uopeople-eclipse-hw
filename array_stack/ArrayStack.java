package array_stack;

public class ArrayStack<E> implements Stack<E> {
	private static final int defaultSize = 10;
	private int maxSize;
	private int top;
	private E[] listArray;
	
	ArrayStack() {
		this(defaultSize);
	}
	ArrayStack(int size) {
		maxSize = size;
		top = 0;
		listArray = (E[]) new Object[size];
	}
	public void clear() {
		top = 0;
	}
	public void push(E it) {
		assert top != maxSize : "Stack is full";
		listArray[top++] = it;
	}
	public E pop() {
		return listArray[--top];
	}
	public E topValue() {
		return listArray[top-1];
	}
	public int length() {
		return top;
	}
	
	public static void main(String[] args) {
		char o;
		ArrayStack x = new ArrayStack();
		x.push('a');
		o = (char)x.pop();
		System.out.println(o);
	}
}
