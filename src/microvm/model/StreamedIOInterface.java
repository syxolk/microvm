package microvm.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

import microvm.model.interfaces.IOInterface;
import microvm.utils.ValueUtils;

public class StreamedIOInterface implements IOInterface {
	protected PrintStream out;
	protected BufferedReader in;

	public StreamedIOInterface(PrintStream out, Reader in) {
		this.out = out;
		this.in = new BufferedReader(in);
	}

	@Override
	public void write(Object obj) {
		if (obj == null) {
			out.println("null");
		} else if (obj instanceof Integer) {
			out.println(((Integer) obj).intValue());
		} else if (obj instanceof Boolean) {
			out.println(((Boolean) obj).booleanValue() ? ValueUtils.TRUE
					: ValueUtils.FALSE);
		} else {
			out.println("unknown type: " + obj.getClass().getName());
		}
	}

	@Override
	public Object read() throws IOException {
		String line;
		while ((line = in.readLine()) != null) {
			if (ValueUtils.TRUE.equals(line)) {
				return Boolean.TRUE;
			} else if (ValueUtils.FALSE.equals(line)) {
				return Boolean.FALSE;
			} else if (ValueUtils.INTEGER_PATTERN.matcher(line).matches()) {
				return Integer.parseInt(line);
			} else {
				out.println("invalid input (only true, false, integer permitted)");
			}
		}
		return null;
	}
}