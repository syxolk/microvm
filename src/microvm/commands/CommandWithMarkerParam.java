package microvm.commands;

public interface CommandWithMarkerParam extends Command {
	void setParam(String marker);
	String getParam();
}