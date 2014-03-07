package microvm.model;

import java.util.LinkedList;

/**
 * Simple implementation of a stack. It uses internally a linked list.
 * 
 * @author Hans Kirchner
 */
public final class StackMemory implements Cloneable {
	private LinkedList<Object> stack;

	/**
	 * Creates a new empty stack.
	 */
	public StackMemory() {
		stack = new LinkedList<Object>();
	}

	/**
	 * Creates a new stack with the same content like the given stack.
	 * 
	 * @param stack
	 *            stack to be cloned
	 */
	public StackMemory(StackMemory stack) {
		this.stack = new LinkedList<Object>(stack.stack);
	}

	public void push(Object obj) {
		stack.push(obj);
	}

	public Object pop() {
		if (isEmpty())
			return null;
		return stack.pop();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public Object peek() {
		return stack.peek();
	}

	public int hashCode() {
		return stack.hashCode();
	}

	public Object clone() {
		return new StackMemory(this);
	}

	public StackMemory copy() {
		return new StackMemory(this);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StackMemory))
			return false;

		StackMemory st = (StackMemory) obj;
		return this.stack.equals(st.stack);
	}

	public String toString() {
		return getClass().getName() + ": " + stack.toString();
	}
}