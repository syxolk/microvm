package microvm.utils;

import java.util.regex.Pattern;

import microvm.commands.CommandExecutionException;

public final class ValueUtils {
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final Pattern INTEGER_PATTERN = Pattern.compile("\\-?[0-9]+");

	public static boolean isInteger(Object obj) {
		return obj != null && obj instanceof Integer;
	}

	public static boolean isBoolean(Object obj) {
		return obj != null && obj instanceof Boolean;
	}

	public static void throwExceptionIfNotInteger(Object obj)
			throws CommandExecutionException {
		if (obj == null)
			throw new CommandExecutionException("stack is empty");
		if (!(obj instanceof Integer))
			throw new CommandExecutionException(
					"popped value is not an integer");
	}

	public static void throwExceptionIfNotBoolean(Object obj)
			throws CommandExecutionException {
		if (obj == null)
			throw new CommandExecutionException("stack is empty");
		if (!(obj instanceof Boolean))
			throw new CommandExecutionException("popped value is not a boolean");
	}

	public static void throwExceptionIfNull(Object obj) throws CommandExecutionException {
		if (obj == null)
			throw new CommandExecutionException("value is null");
	}
}