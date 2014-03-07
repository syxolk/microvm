package microvm.model;

import java.util.LinkedList;

import microvm.model.interfaces.StackMemory;

/**
 * Simple implementation of a stack. It uses internally a linked list.
 * 
 * @author Hans Kirchner
 */
public final class StandardStackMemory implements StackMemory, Cloneable {
	private LinkedList<Object> stack;

	/**
	 * Creates a new empty stack.
	 */
	public StandardStackMemory() {
		stack = new LinkedList<Object>();
	}

	/**
	 * Creates a new stack with the same content like the given stack.
	 * 
	 * @param stack
	 *            stack to be cloned
	 */
	public StandardStackMemory(StandardStackMemory stack) {
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
		return new StandardStackMemory(this);
	}

	public StandardStackMemory copy() {
		return new StandardStackMemory(this);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StandardStackMemory))
			return false;

		StandardStackMemory st = (StandardStackMemory) obj;
		return this.stack.equals(st.stack);
	}

	public String toString() {
		return getClass().getName() + ": " + stack.toString();
	}
}