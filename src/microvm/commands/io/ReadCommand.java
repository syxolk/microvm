package microvm.commands.io;

import java.io.IOException;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;

public class ReadCommand implements Command {

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		try {
			ctx.getStack().push(ctx.getIO().read());
		} catch (IOException e) {
			throw new CommandExecutionException(e.getMessage());
		}
	}

}