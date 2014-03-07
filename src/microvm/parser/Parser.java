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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import microvm.commands.Command;
import microvm.commands.arithmetic.AddCommand;
import microvm.model.InterpreterProgram;

public class Parser implements Closeable {
	public static final String DEFAULT_CHARSET = "utf-8";

	private static final Pattern MARKER_PATTERN = Pattern
			.compile("\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\s*:(.*)");

	private static final Pattern SIMPLE_COMMAND_PATTERN = Pattern
			.compile("([a-z]*)");

	private BufferedReader reader;

	public Parser(Reader reader) {
		this.reader = new BufferedReader(reader);
	}

	public Parser(File file) throws UnsupportedEncodingException,
			FileNotFoundException {
		this(new InputStreamReader(new FileInputStream(file), DEFAULT_CHARSET));
	}

	public InterpreterProgram parse() throws IOException, ParseException {
		List<Command> commands = new ArrayList<Command>();
		Map<String, Integer> markers = new HashMap<String, Integer>();

		String line;
		Matcher matcher;
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
					
					String command=matcher.group(1).toLowerCase();
					
					if("add".equals(command)) {
						commands.add(new AddCommand());
					} else {
						throw new ParseException("unknown command: "+line, 0);
					}
					
				}

			}
		}

		return new InterpreterProgram(commands, markers);
	}

	public void close() throws IOException {
		reader.close();
	}
}