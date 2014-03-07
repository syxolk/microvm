package microvm.commands;

public interface CommandWithIntParam extends Command {
	void setParam(int value);
	int getParam();
}