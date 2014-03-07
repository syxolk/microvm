package microvm.model.interfaces;

public interface RAMemory {
	void setVariable(String name, Object value);
	Object getVariable(String name);
}