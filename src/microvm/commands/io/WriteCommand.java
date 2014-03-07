package microvm.commands.io;

import java.io.IOException;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;

public class WriteCommand implements Command {

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		try {
			ctx.getIO().write(ctx.getStack().pop());
		} catch (IOException e) {
			throw new CommandExecutionException(e.getMessage());
		}
	}
}