package microvm.model;

import java.util.HashMap;
import java.util.Map;

import microvm.model.interfaces.RAMemory;

public class HashedRAMemory implements RAMemory {

	private Map<String, Object> map=new HashMap<String, Object>();
	
	@Override
	public void setVariable(String name, Object value) {
		map.put(name, value);
	}

	@Override
	public Object getVariable(String name) {
		return map.get(name);
	}
}