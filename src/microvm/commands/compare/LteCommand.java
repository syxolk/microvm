package microvm.commands.compare;

import microvm.commands.Command;
import microvm.commands.CommandExecutionException;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class LteCommand implements Command {

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object o2 = ctx.getStack().pop();
		Object o1 = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNotInteger(o2);
		ValueUtils.throwExceptionIfNotInteger(o1);

		ctx.getStack().push(((Integer) o1) <= ((Integer) o2));
	}

}