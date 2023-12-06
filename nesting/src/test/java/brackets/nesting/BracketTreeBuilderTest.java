package brackets.nesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import brackets.BracketEnthusiast;
import brackets.BracketType;

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
			mInstance.getRoot()
		);
		assertEquals(
			openRoot,
			mInstance.getRoot().open
		);
		assertEquals(
			closeRoot,
			mInstance.getRoot().close
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
			mInstance.getRoot()
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
		final var root = mInstance.getRoot();
		assertNotNull(root);
		// Check that the root contain the inner values
		assertEquals(
			1, root.countSubNodes()
		);
		assertEquals(
			open, ((BracketNode) root.getSubNodeAt(0)).open
		);
		assertEquals(
			close, ((BracketNode) root.getSubNodeAt(0)).close
		);
	}

	@Test
	public void testClear_IsEmpty() {
		mInstance.clear();
		assertNull(
			mInstance.getRoot()
		);
	}

	@Test
	public void testBuildFromEnthusiast_OnePair_ReturnsSingleNodeArray() {
		final var input = new BracketEnthusiast(
			BracketType.CURLY, "{ hi }"
		);
		final var result = mInstance.buildFromEnthusiast(input);
		assertNotNull(result);
		assertEquals(
			1, result.length
		);
		assertEquals(
			0, result[0].open
		);
		assertEquals(
			5, result[0].close
		);
	}

	@Test
	public void testBuildFromEnthusiast_TwoPairsNested() {
		final var input = new BracketEnthusiast(
			BracketType.CURLY, " { { } } "
		);
		final var result = mInstance.buildFromEnthusiast(input);
		assertNotNull(result);
		assertEquals(
			1, result.length
		);
		final BracketNode outerBracket = result[0];
		assertEquals(
			1, outerBracket.open
		);
		assertEquals(
			7, outerBracket.close
		);
		assertEquals(
			1, outerBracket.countSubNodes()
		);
		final BracketNode innerBracket = (BracketNode) outerBracket.getSubNodeAt(0);
		assertEquals(
			3, innerBracket.open
		);
		assertEquals(
			5, innerBracket.close
		);
	}

	@Test
	public void testBuildFromEnthusiast_UnbalancedBrackets_ReturnsNull() {
		final var input = new BracketEnthusiast(
			BracketType.CURLY, "{{ } {"
		);
		assertNull(
			mInstance.buildFromEnthusiast(input)
		);
	}

	@Test
	public void testBuildFromEnthusiast_EmptyPairs() {
		final var input = new BracketEnthusiast(
			BracketType.CURLY, " hi "
		);
		assertNull(
			mInstance.buildFromEnthusiast(input)
		);
	}

	@Test
	public void testBuildFromEnthusiast_TwoRootLevelPairs_ReturnsArrayLength2() {
		final var input = new BracketEnthusiast(
			BracketType.CURLY, " { 1 } { hi } "
		);
		final var result = mInstance.buildFromEnthusiast(input);
		assertNotNull(result);
		assertEquals(
			2, result.length
		);
	}

}