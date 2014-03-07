package microvm.commands.consts;

import microvm.commands.CommandExecutionException;
import microvm.commands.CommandWithIntParam;
import microvm.model.interfaces.ExecutorContext;

public class ConstCommand implements CommandWithIntParam {

	private int value;

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		ctx.getStack().push(value);
	}

	@Override
	public void setParam(int value) {
		this.value=value;
	}

	@Override
	public int getParam() {
		return value;
	}
}