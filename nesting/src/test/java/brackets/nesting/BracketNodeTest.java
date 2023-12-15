package brackets.nesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
			open, mInstance.open
		);
		assertEquals(
			close, mInstance.close
		);
	}

	@Test
	public void testConstructor_InvalidArguments1_ThrowsException() {
		// Swap the Arguments, so open is greater than close
		open = close;
		close = 10;
		assertThrows(IllegalArgumentException.class,
			() -> new BracketNode(open, close)
		);
	}

	@Test
	public void testConstructor_InvalidArguments2_ThrowsException() {
		// Make open less than zero
		open = -1;
		close = 1;
		assertThrows(IllegalArgumentException.class,
			() -> new BracketNode(open, close)
		);
	}

	@Test
	public void testGetIndices() {
		final var indices = mInstance.getIndices();
		assertEquals(
			open, indices[0]
		);
		assertEquals(
			close, indices[1]
		);
	}

	@Test
	public void testGetIndices_AlternateValues() {
		open = 300;
		close = 50001;
		mInstance = new BracketNode(open, close);
		final var indices = mInstance.getIndices();
		assertEquals(
			open, indices[0]
		);
		assertEquals(
			close, indices[1]
		);
	}

	@Test
	public void testCountSubNodes_Empty_ReturnsZero() {
		assertEquals(
			0, mInstance.countSubNodes()
		);
	}

	@Test
	public void testGetSubNodeAt_Empty_ReturnsNull() {
		assertNull(
			mInstance.getSubNodeAt(0)
		);
	}

	@Test
	public void testAddNode_IsNested_ReturnsTrue() {
		assertTrue(
			mInstance.addNode(open + 1, close - 1)
		);
		assertEquals(
			1, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_NotNested_Open_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(open - 1, open + 3)
		);
		assertEquals(
			0, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_NotNested_Close_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(open + 3, close + 1)
		);
		assertEquals(
			0, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_NotNested_MatchesOpen_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(open, open + 3)
		);
		assertEquals(
			0, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_NotNested_MatchesClose_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(open + 1, close)
		);
		assertEquals(
			0, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_TwoNestedInRoot_ReturnsTrue() {
		assertTrue(
			mInstance.addNode(open + 1, open + 2)
		);
		assertTrue(
			mInstance.addNode(open + 3, open + 4)
		);
		assertEquals(
			2, mInstance.countSubNodes()
		);
	}

	@Test
	public void testAddNode_TwoNestedDeep_ReturnsTrue() {
		assertTrue(
			mInstance.addNode(open + 1, open + 10)
		);
		assertTrue(
			mInstance.addNode(open + 2, open + 4)
		);
		assertEquals(
			1, mInstance.countSubNodes()
		);
		assertEquals(
			1, mInstance.getSubNodeAt(0).countSubNodes()
		);
		assertEquals(
			0, mInstance.getSubNodeAt(0).getSubNodeAt(0).countSubNodes()
		);
	}

	@Test
	public void testAddNode_ThreeNestedDeep_ReturnsTrue() {
		assertTrue(
			mInstance.addNode(open + 1, open + 10)
		);
		assertTrue(
			mInstance.addNode(open + 2, open + 8)
		);
		assertTrue(
			mInstance.addNode(open + 4, open + 6)
		);
		assertEquals(
			1, mInstance.countSubNodes()
		);
		assertEquals(
			1, mInstance.getSubNodeAt(0).countSubNodes()
		);
		assertEquals(
			1, mInstance.getSubNodeAt(0).getSubNodeAt(0).countSubNodes()
		);
		assertEquals(
			0, mInstance.getSubNodeAt(0).getSubNodeAt(0).getSubNodeAt(0).countSubNodes()
		);
	}

	@Test
	public void testFindNodeContainingIndex_NoSubNodes_ReturnsNull() {
		// Check a wide range of indices, both inside and out
		for (int i = -5; i < close + 5; ++i) {
			assertNull(
				mInstance.findNodeContainingIndex(i)
			);
		}
	}

	@Test
	public void testFindNodeContainingIndex_OneSubNode_IndexWithinSubNode_ReturnsNode() {
		final int subNodeOpen = open + 3;
		final int subNodeClose = close - 10;
		assertTrue(
			mInstance.addNode(subNodeOpen, subNodeClose)
		);
		for (int i = subNodeOpen; i <= subNodeClose; ++i) {
			final var node = mInstance.findNodeContainingIndex(i);
			assertNotNull(node);
			assertTrue(
				node instanceof BracketNode
			);
			assertEquals(
				subNodeOpen, ((BracketNode) node).open
			);
			assertEquals(
				subNodeClose, ((BracketNode) node).close
			);
		}
	}

	@Test
	public void testFindNodeContainingIndex_OneSubNode_IndexOutsideSubNode_ReturnsNull() {
		final int subNodeOpen = open + 3;
		final int subNodeClose = close - 10;
		assertTrue(
			mInstance.addNode(subNodeOpen, subNodeClose)
		);
		// Check Below the open index
		assertNull(
			mInstance.findNodeContainingIndex(open - 1)
		);
		assertNull(
			mInstance.findNodeContainingIndex(open - 2)
		);
		// Check Above the close index
		assertNull(
			mInstance.findNodeContainingIndex(close + 1)
		);
		assertNull(
			mInstance.findNodeContainingIndex(close + 2)
		);
	}

}