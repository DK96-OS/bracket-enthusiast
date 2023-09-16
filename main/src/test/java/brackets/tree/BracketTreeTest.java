package brackets.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Testing BracketTree.
 */
public final class BracketTreeTest {

	BracketTree mInstance;
	int open;
	int close;

	@Before
	public void testSetup() {
		open = 3;
		close = 50;
		mInstance = new BracketTree(open, close);
	}

	@Test
	public void testInitialCondition() {
		assertEquals(
			open, mInstance.getOpen()
		);
		assertEquals(
			close, mInstance.getClose()
		);
		assertTrue(
			mInstance.isLeaf()
		);
	}

	@Test
	public void testAddBrackets_InvalidBrackets_ReturnsFalse() {
		assertFalse(
			mInstance.addBrackets(5, 1)
		);
		assertTrue(
			mInstance.isLeaf()
		);
	}

	@Test
	public void testAddBrackets_NotNestedOpen_ReturnsFalse() {
		assertFalse(
			mInstance.addBrackets(open, close - 1)
		);
		assertTrue(
			mInstance.isLeaf()
		);
	}

	@Test
	public void testAddBrackets_NotNestedClose_ReturnsFalse() {
		assertFalse(
			mInstance.addBrackets(open + 1, close)
		);
		assertTrue(
			mInstance.isLeaf()
		);
	}

	@Test
	public void testAddBrackets_Nested_ReturnsTrue() {
		assertTrue(
			mInstance.addBrackets(open + 1, close - 1)
		);
		assertFalse(
			mInstance.isLeaf()
		);
		var nestedBrackets = mInstance.getNestedBrackets();
		assertNotNull(nestedBrackets);
		assertEquals(
			1, nestedBrackets.getSize()
		);
	}

	@Test
	public void testAddBrackets_MultipleNested_ReturnsTrue() {
		int index = open + 1;
		while (index < close - 1 - 3) {
			assertTrue(
				mInstance.addBrackets(index++, ++index)
			);
			index += 3;
		}
		assertFalse(
			mInstance.isLeaf()
		);
		var result = mInstance.getNestedBrackets();
		assertNotNull(result);
		assertEquals(
			(close - open - 2) / 5,
			result.getSize()
		);
	}

}