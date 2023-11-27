package brackets.nesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Testing Bracket Node.
 */
public final class BracketNodeTest {

	int open;
	int close;
	BracketNode mInstance;

	@Before
	public void testSetup() {
		open = 10;
		close = 60;
		mInstance = new BracketNode(open, close);
	}

	@Test
	public void testInitialCondition() {
		assertEquals(
			open, mInstance.first
		);
		assertEquals(
			close, mInstance.second
		);
		assertNull(
			mInstance.getInternalNodes()
		);
		assertEquals(
			0, mInstance.countInternalNodes()
		);
	}

	@Test
	public void testSwappedConstructorArgs_ThrowsException() {
		open = close;
		close = 10;
		assertThrows(IllegalArgumentException.class,
			() -> new BracketNode(open, close)
		);
	}

	@Test
	public void testAddNode_IsNested_ReturnsTrue() {
		assertTrue(
			mInstance.addNode(open + 1, close - 1)
		);
		assertEquals(
			1, mInstance.countInternalNodes()
		);
	}

	@Test
	public void testAddNode_NotNested_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(open, close)
		);
		assertEquals(
			0, mInstance.countInternalNodes()
		);
	}

}