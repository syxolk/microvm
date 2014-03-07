package microvm.commands.arithmetic;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class NegCommand implements Command {
	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object o = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNotInteger(o);

		ctx.getStack().push(((Integer) o) * -1);
	}
}