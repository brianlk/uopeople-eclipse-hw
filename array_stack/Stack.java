package array_stack;

public interface Stack<ch> {
	public abstract void clear();
	
	public abstract void push(ch it);
	
	public abstract ch pop();
	
	public abstract ch topValue();
	
	public abstract int length();
}
