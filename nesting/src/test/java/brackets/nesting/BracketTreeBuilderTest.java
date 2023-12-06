package brackets.nesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Testing the BracketTree Builder class.
 * The builder is setup with an initial root node.
 */
public final class BracketTreeBuilderTest {

	int openRoot;
	int closeRoot;
	BracketTreeBuilder mInstance;

	@Before
	public void testSetup() {
		openRoot = 2;
		closeRoot = 10;
		mInstance = new BracketTreeBuilder();
		//
		assertTrue(
			mInstance.addNode(openRoot, closeRoot)
		);
	}

	@Test
	public void testInitialCondition() {
		assertNotNull(
			mInstance.root
		);
		assertEquals(
			openRoot,
			mInstance.root.open
		);
		assertEquals(
			closeRoot,
			mInstance.root.close
		);
	}

	@Test
	public void testAddNode_SameAsRoot_ReturnsFalse() {
		assertFalse(
			mInstance.addNode(
				openRoot, closeRoot
			)
		);
		assertNotNull(
			mInstance.root
		);
	}

	@Test
	public void testAddNode_WithinRoot_ReturnsTrue() {
		final int open = 4;
		final int close = 8;
		assertTrue(
			mInstance.addNode(
				open, close
			)
		);
		final var root = mInstance.root;
		assertNotNull(root);
		// Check that the root contain the inner values
		assertEquals(
			1, root.countSubNodes()
		);
		assertEquals(
			open, root.getInternalNodes().get(0).open
		);
		assertEquals(
			close, root.getInternalNodes().get(0).close
		);
	}

	@Test
	public void testClear_IsEmpty() {
		mInstance.clear();
		assertNull(
			mInstance.root
		);
		assertNull(
			mInstance.recent
		);
	}

}