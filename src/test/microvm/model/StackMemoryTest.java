package test.microvm.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import microvm.model.StandardStackMemory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test class for {@link microvm.model.StandardStackMemory}
 * 
 * @author Hans Kirchner
 * 
 */
@RunWith(JUnit4.class)
public class StackMemoryTest {
	@Test
	public void testFunctionality() {
		StandardStackMemory st = new StandardStackMemory();
		assertTrue(st.isEmpty());

		st.push(10);
		assertFalse(st.isEmpty());
		assertEquals(10, st.peek());

		assertEquals(10, st.pop());
		assertTrue(st.isEmpty());
	}

	@Test
	public void testClone() {
		StandardStackMemory orig = new StandardStackMemory();
		orig.push(10);

		StandardStackMemory clone = orig.copy();
		assertNotSame(orig, clone);
		assertEquals(orig, clone);
	}
}