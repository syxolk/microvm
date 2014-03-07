package microvm.model.interfaces;

public interface ProgramCounter {
	void setPC(int marker);
	void incPC();
	int getPC();
}