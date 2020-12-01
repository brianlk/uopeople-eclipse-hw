package array_stack;

public interface Stack<E> {
	public abstract void clear();
	
	public abstract void push(E it);
	
	public abstract E pop();
	
	public abstract E topValue();
	
	public abstract int length();
}
