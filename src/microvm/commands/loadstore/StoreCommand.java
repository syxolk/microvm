package microvm.commands.loadstore;

import microvm.commands.CommandExecutionException;
import microvm.commands.CommandWithMarkerParam;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class StoreCommand implements CommandWithMarkerParam {

	private String var;

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object value = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNull(value);

		ctx.getRAM().setVariable(var, value);
	}

	@Override
	public void setParam(String marker) {
		this.var = marker;
	}

	@Override
	public String getParam() {
		return var;
	}
}