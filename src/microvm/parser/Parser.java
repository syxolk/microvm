package microvm.parser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import microvm.commands.Command;
import microvm.commands.CommandWithIntParam;
import microvm.commands.CommandWithMarkerParam;
import microvm.commands.arithmetic.AddCommand;
import microvm.commands.arithmetic.DivCommand;
import microvm.commands.arithmetic.ModCommand;
import microvm.commands.arithmetic.MulCommand;
import microvm.commands.arithmetic.NegCommand;
import microvm.commands.arithmetic.SubCommand;
import microvm.commands.compare.EqCommand;
import microvm.commands.compare.GtCommand;
import microvm.commands.compare.GteCommand;
import microvm.commands.compare.LtCommand;
import microvm.commands.compare.LteCommand;
import microvm.commands.compare.NeqCommand;
import microvm.commands.consts.ConstCommand;
import microvm.commands.consts.FalseCommand;
import microvm.commands.consts.TrueCommand;
import microvm.commands.control.FJumpCommand;
import microvm.commands.control.HaltCommand;
import microvm.commands.control.JumpCommand;
import microvm.commands.io.ReadCommand;
import microvm.commands.io.WriteCommand;
import microvm.commands.loadstore.LoadCommand;
import microvm.commands.loadstore.StoreCommand;
import microvm.commands.logic.AndCommand;
import microvm.commands.logic.NotCommand;
import microvm.commands.logic.OrCommand;
import microvm.model.InterpreterProgram;

public class Parser implements Closeable {
	public static final String DEFAULT_CHARSET = "utf-8";

	private static final String COMMAND_REGEX = "([a-z]+)";

	private static final String MARKER_REGEX = "([a-zA-Z_][a-zA-Z0-9_]*)";

	private static final String INT_REGEX = "([0-9]+)";

	private static final Pattern MARKER_PATTERN = Pattern.compile("\\s*"
			+ MARKER_REGEX + "\\s*:(.*)");

	private static final Pattern SIMPLE_COMMAND_PATTERN = Pattern
			.compile(COMMAND_REGEX);

	private static final Pattern INTEGER_PARAMETER_COMMAND_PATTERN = Pattern
			.compile(COMMAND_REGEX + "\\s*" + INT_REGEX);

	private static final Pattern MARKER_PARAMETER_COMMAND_PATTERN = Pattern
			.compile(COMMAND_REGEX + "\\s*" + MARKER_REGEX);

	private static final Map<String, Class<? extends Command>> COMMANDS = new HashMap<String, Class<? extends Command>>();
	private static final Map<String, Class<? extends CommandWithIntParam>> COMMANDS_INT = new HashMap<String, Class<? extends CommandWithIntParam>>();
	private static final Map<String, Class<? extends CommandWithMarkerParam>> COMMANDS_MARKER = new HashMap<String, Class<? extends CommandWithMarkerParam>>();

	static {
		// arithmetic
		COMMANDS.put("add", AddCommand.class);
		COMMANDS.put("sub", SubCommand.class);
		COMMANDS.put("mul", MulCommand.class);
		COMMANDS.put("div", DivCommand.class);
		COMMANDS.put("mod", ModCommand.class);
		COMMANDS.put("neg", NegCommand.class);

		// logic
		COMMANDS.put("and", AndCommand.class);
		COMMANDS.put("or", OrCommand.class);
		COMMANDS.put("not", NotCommand.class);

		// consts
		COMMANDS_INT.put("const", ConstCommand.class);
		COMMANDS.put("true", TrueCommand.class);
		COMMANDS.put("false", FalseCommand.class);

		// io
		COMMANDS.put("write", WriteCommand.class);
		COMMANDS.put("read", ReadCommand.class);

		// commpare
		COMMANDS.put("lt", LtCommand.class);
		COMMANDS.put("lte", LteCommand.class);
		COMMANDS.put("eq", EqCommand.class);
		COMMANDS.put("neq", NeqCommand.class);
		COMMANDS.put("gt", GtCommand.class);
		COMMANDS.put("gte", GteCommand.class);

		// control
		COMMANDS.put("halt", HaltCommand.class);
		COMMANDS_MARKER.put("jump", JumpCommand.class);
		COMMANDS_MARKER.put("fjump", FJumpCommand.class);
		
		//load/store
		COMMANDS_MARKER.put("load", LoadCommand.class);
		COMMANDS_MARKER.put("store", StoreCommand.class);
	}

	private BufferedReader reader;

	public Parser(Reader reader) {
		this.reader = new BufferedReader(reader);
	}

	public Parser(File file) throws UnsupportedEncodingException,
			FileNotFoundException {
		this(new InputStreamReader(new FileInputStream(file), DEFAULT_CHARSET));
	}

	public InterpreterProgram parse() throws IOException, ParseException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException,
			InvocationTargetException, NoSuchMethodException {
		List<Command> commands = new ArrayList<Command>();
		Map<String, Integer> markers = new HashMap<String, Integer>();

		String line;
		Matcher matcher;
		int lineCounter = 0;

		while ((line = reader.readLine()) != null) {
			// check for marker (e.g. loop:)
			if ((matcher = MARKER_PATTERN.matcher(line)).matches()) {
				markers.put(matcher.group(1), commands.size());
				line = matcher.group(2);
			}

			// remove whitespace
			line = line.trim();

			// check for commands and parse them
			if (!line.isEmpty()) {

				// check for commands without parameters (e.g. ADD)
				if ((matcher = SIMPLE_COMMAND_PATTERN.matcher(line)).matches()) {

					String command = matcher.group(1).toLowerCase();

					if (COMMANDS.containsKey(command)) {
						commands.add(COMMANDS.get(command).newInstance());
					} else {
						throw new ParseException("unknown command: " + command,
								lineCounter);
					}

				} else if ((matcher = INTEGER_PARAMETER_COMMAND_PATTERN
						.matcher(line)).matches()) {

					String command = matcher.group(1).toLowerCase();
					int value = Integer.parseInt(matcher.group(2));

					if (COMMANDS_INT.containsKey(command)) {
						CommandWithIntParam cmdObject = COMMANDS_INT.get(
								command).newInstance();
						cmdObject.setParam(value);
						commands.add(cmdObject);
					} else {
						throw new ParseException(
								"unknown command with int param: " + command,
								lineCounter);
					}

				} else if ((matcher = MARKER_PARAMETER_COMMAND_PATTERN
						.matcher(line)).matches()) {

					String command = matcher.group(1).toLowerCase();
					String marker = matcher.group(2);

					if (COMMANDS_MARKER.containsKey(command)) {
						CommandWithMarkerParam cmdObject = COMMANDS_MARKER.get(
								command).newInstance();
						cmdObject.setParam(marker);
						commands.add(cmdObject);
					} else {
						throw new ParseException(
								"unknown command with marker param: " + command,
								lineCounter);
					}

				} else {

					throw new ParseException("cannot parse line: " + line,
							lineCounter);

				}

			}

			lineCounter++;
		}

		return new InterpreterProgram(commands, markers);
	}

	public void close() throws IOException {
		reader.close();
	}
}