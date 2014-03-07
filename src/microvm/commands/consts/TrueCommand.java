package microvm.commands.consts;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;

public class TrueCommand implements Command {

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		ctx.getStack().push(Boolean.TRUE);
	}

}