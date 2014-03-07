package microvm.commands;

import microvm.model.interfaces.ExecutorContext;

public interface Command {
	void execute(ExecutorContext ctx) throws CommandExecutionException;
}