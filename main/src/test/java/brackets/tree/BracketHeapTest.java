package brackets.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Testing BracketHeap.
 * Enjoy this java test class.
 * Sometimes, java tests just feel simpler.
 */
public final class BracketHeapTest {

	private BracketHeap mInstance;
	private BracketTree tree1;
	private BracketTree tree2;

	@Before
	public void testSetup() {
		tree1 = new BracketTree(3, 50);
		tree2 = new BracketTree(75, 120);
		// Initialize with one Tree
		mInstance = new BracketHeap(tree1);
	}

	@Test
	public void testInitialCondition() {
		assertEquals(
			1, mInstance.getSize()
		);
	}

	@Test
	public void testGetAt_Zero_ReturnsTree1() {
		assertEquals(
			tree1, mInstance.getAt(0)
		);
	}

	@Test
	public void testGetAt_One_ReturnsNull() {
		assertNull(
			mInstance.getAt(1)
		);
	}

	@Test
	public void testGetAt_NegativeValue_ReturnsNull() {
		assertNull(
			mInstance.getAt(-1)
		);
	}

	@Test
	public void testInsert_Tree1_ReturnsFalse() {
		assertFalse(
			mInstance.insert(tree1)
		);
	}

	@Test
	public void testInsert_Tree2_ReturnsTrue() {
		assertTrue(
			mInstance.insert(tree2)
		);
		assertEquals(
			2, mInstance.getSize()
		);
	}

}