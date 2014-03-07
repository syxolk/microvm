package microvm.commands.logic;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class NotCommand implements Command {
	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object o = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNotBoolean(o);

		ctx.getStack().push(!((Boolean) o));
	}
}