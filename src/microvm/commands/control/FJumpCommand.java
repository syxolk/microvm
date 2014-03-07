package microvm.commands.control;

import microvm.commands.CommandExecutionException;
import microvm.commands.CommandWithMarkerParam;
import microvm.model.interfaces.ExecutorContext;
import microvm.utils.ValueUtils;

public class FJumpCommand implements CommandWithMarkerParam {

	private String marker;

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		Object o = ctx.getStack().pop();

		ValueUtils.throwExceptionIfNotBoolean(o);

		if (((Boolean) o).booleanValue() == false) {
			ctx.setPC(marker);
		}
	}

	@Override
	public void setParam(String marker) {
		this.marker = marker;
	}

	@Override
	public String getParam() {
		return marker;
	}

}