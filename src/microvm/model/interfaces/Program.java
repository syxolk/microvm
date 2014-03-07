package microvm.model.interfaces;

import microvm.commands.Command;

public interface Program {
	Command getComand(int line);
	int getLineForMarker(String marker);
}