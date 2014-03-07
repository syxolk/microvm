package microvm.commands.control;

import microvm.commands.CommandExecutionException;
import microvm.commands.CommandWithMarkerParam;
import microvm.model.interfaces.ExecutorContext;

public class JumpCommand implements CommandWithMarkerParam {

	private String marker;

	@Override
	public void execute(ExecutorContext ctx) throws CommandExecutionException {
		ctx.setPC(marker);
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
