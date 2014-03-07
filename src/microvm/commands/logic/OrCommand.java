package microvm.commands.logic;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class OrCommand implements Command {
	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object o2 = ctx.getStack().pop();
		Object o1 = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNotBoolean(o2);
		ValueUtils.throwExceptionIfNotBoolean(o1);

		ctx.getStack().push(((Boolean) o1) || ((Boolean) o2));
	}
}