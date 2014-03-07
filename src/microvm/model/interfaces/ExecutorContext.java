package microvm.model.interfaces;

public interface ExecutorContext extends ProgramCounter {
	StackMemory getStack();
	IOInterface getIO();
	RAMemory getRAM();
}