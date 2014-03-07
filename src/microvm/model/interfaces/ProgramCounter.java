package microvm.model.interfaces;

public interface ProgramCounter {
	void setPC(int marker);
	void setPC(String marker);
	void incPC();
	int getPC();
}