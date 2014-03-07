package microvm.model;

import java.io.IOException;
import java.io.InputStreamReader;

public class StandardIOInterface extends StreamedIOInterface {
	public StandardIOInterface() {
		super(System.out, new InputStreamReader(System.in));
	}
	
	public Object read() throws IOException {
		out.print("> ");
		return super.read();
	}
}