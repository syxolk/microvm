package microvm.model;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.commands.control.HaltCommand;
import microvm.model.interfaces.ExecutorContext;
import microvm.model.interfaces.IOInterface;
import microvm.model.interfaces.Program;
import microvm.model.interfaces.RAMemory;
import microvm.model.interfaces.StackMemory;

public class Executor implements ExecutorContext {
	private Program program;
	private StackMemory stack;
	private IOInterface io;
	private RAMemory ram;
	private int pc = 0;

	public Executor(Program program, StackMemory stack, RAMemory ram,
			IOInterface io) {
		this.program = program;
		this.stack = stack;
		this.ram = ram;
		this.io = io;
	}

	public Executor(Program program, IOInterface io) {
		this(program, new StandardStackMemory(), new HashedRAMemory(), io);
	}

	public Executor(Program program) {
		this(program, new StandardStackMemory(), new HashedRAMemory(),
				new StandardIOInterface());
	}

	@Override
	public void setPC(int marker) {
		this.pc = marker;
	}

	@Override
	public void incPC() {
		this.pc++;
	}

	@Override
	public int getPC() {
		return pc;
	}

	/**
	 * TODO Programm-Counter inkrementieren, wenn jump ?!
	 * 
	 * @throws CommandExecutionException
	 */
	public void executeLine() throws CommandExecutionException {
		Command cmd = program.getComand(pc);
		cmd.execute(this);
		pc++;
	}

	public void execute() throws CommandExecutionException {
		pc = 0;
		Command command = program.getComand(pc);
		while (!(command instanceof HaltCommand)) {
			pc++;
			command.execute(this);
			command = program.getComand(pc);
		}
	}

	@Override
	public StackMemory getStack() {
		return stack;
	}

	@Override
	public IOInterface getIO() {
		return io;
	}

	@Override
	public void setPC(String marker) {
		setPC(program.getLineForMarker(marker));
	}

	@Override
	public RAMemory getRAM() {
		return ram;
	}
}