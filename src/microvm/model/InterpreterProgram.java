package microvm.model;

import java.util.List;
import java.util.Map;

import microvm.commands.Command;
import microvm.model.interfaces.Program;

public class InterpreterProgram implements Program {
	private List<Command> commands;
	private Map<String, Integer> markers;

	public InterpreterProgram(List<Command> commands,
			Map<String, Integer> markers) {

		if (commands == null || markers == null)
			throw new NullPointerException("commands or markers is null");

		this.commands = commands;
		this.markers = markers;
	}

	@Override
	public Command getComand(int line) {
		return commands.get(line);
	}

	@Override
	public int getLineForMarker(String marker) {
		if (markers.containsKey(marker)) {
			return markers.get(marker);
		} else {
			throw new IllegalStateException("Unknown marker: " + marker);
		}
	}
}