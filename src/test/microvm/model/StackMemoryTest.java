package test.microvm.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import microvm.model.StackMemory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for {@link microvm.model.StackMemory}
 * 
 * @author Hans Kirchner
 * 
 */
@RunWith(JUnit4.class)
public class StackMemoryTest {
	@Test
	public void testFunctionality() {
		StackMemory st = new StackMemory();
		assertTrue(st.isEmpty());

		st.push(10);
		assertFalse(st.isEmpty());
		assertEquals(10, st.peek());

		assertEquals(10, st.pop());
		assertTrue(st.isEmpty());
	}

	@Test
	public void testClone() {
		StackMemory orig = new StackMemory();
		orig.push(10);

		StackMemory clone = orig.copy();
		assertNotSame(orig, clone);
		assertEquals(orig, clone);
	}
}