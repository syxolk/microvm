package microvm.model.interfaces;

import microvm.model.StackMemory;

public interface ExecutorContext extends ProgramCounter {
	StackMemory getStack();
	IOInterface getIO();
	RAMemory getRAM();
}