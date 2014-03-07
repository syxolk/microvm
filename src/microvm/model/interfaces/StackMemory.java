package microvm.model.interfaces;

public interface StackMemory {
	void push(Object obj);
	Object pop();
	Object peek();
	boolean isEmpty();
}