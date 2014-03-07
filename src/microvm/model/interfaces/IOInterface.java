package microvm.model.interfaces;

import java.io.IOException;

public interface IOInterface {
	public void write(Object obj) throws IOException;
	public Object read() throws IOException;
}