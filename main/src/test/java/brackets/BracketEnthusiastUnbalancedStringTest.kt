package brackets

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Testing BracketEnthusiast on unbalanced strings.
 */
class BracketEnthusiastUnbalancedStringTest {

	/** An unbalanced input with different numbers
	 * of open and close brackets.
	 */
	private val unbalancedInput1: String = "{{}{}}{"
	private val unbalancedInput2: String = "{{}{}}}"
	private val unbalancedInput3: String = "{{{{".repeat(400)
	private val unbalancedInput4: String = "}}".repeat(40)

	/** A specific input that has the same number of
	 * open and close brackets, but is not balanced.
	 */
	private val validCountNotBalancedInput: String = "{}}{{}}{{}"
	private val validCountNotBalancedInput2: String = "}}{{{}}{{}"

	/** A BracketEnthusiast for each of the unbalanced inputs.
	 */
	private var mUnbalanced1 = BracketEnthusiast(
		BracketType.CURLY, unbalancedInput1
	)
	private var mUnbalanced2 = BracketEnthusiast(
		BracketType.CURLY, unbalancedInput2
	)
	private var mUnbalanced3 = BracketEnthusiast(
		BracketType.CURLY, unbalancedInput3
	)
	private var mUnbalanced4 = BracketEnthusiast(
		BracketType.CURLY, unbalancedInput4
	)

	/** A BracketEnthusiast for the valid count not balanced input.
	 */
	private var mValidCountNotBalanced = BracketEnthusiast(
		BracketType.CURLY, validCountNotBalancedInput
	)
	private var mValidCountNotBalanced2 = BracketEnthusiast(
		BracketType.CURLY, validCountNotBalancedInput2
	)

	@Test
	fun testAreBracketCountsValid_Unbalanced1_ReturnsFalse() {
		assertFalse(mUnbalanced1.areBracketCountsValid)
	}

	@Test
	fun testAreBracketCountsValid_Unbalanced2_ReturnsFalse() {
		assertFalse(mUnbalanced2.areBracketCountsValid)
	}

	@Test
	fun testAreBracketCountsValid_Unbalanced3_ReturnsFalse() {
		assertFalse(mUnbalanced3.areBracketCountsValid)
	}

	@Test
	fun testAreBracketCountsValid_Unbalanced4_ReturnsFalse() {
		assertFalse(mUnbalanced4.areBracketCountsValid)
	}

	@Test
	fun testAreBracketCountsValid_ValidCountNotBalanced_ReturnsTrue() {
		assertTrue(mValidCountNotBalanced.areBracketCountsValid)
	}

	@Test
	fun testAreBracketCountsValid_ValidCountNotBalanced2_ReturnsTrue() {
		assertTrue(mValidCountNotBalanced2.areBracketCountsValid)
	}

	@Test
	fun testAreBracketsBalanced_Unbalanced1_ReturnsFalse() {
		assertFalse(mUnbalanced1.areBracketsBalanced)
	}

	@Test
	fun testAreBracketsBalanced_Unbalanced2_ReturnsFalse() {
		assertFalse(mUnbalanced2.areBracketsBalanced)
	}

	@Test
	fun testAreBracketsBalanced_Unbalanced3_ReturnsFalse() {
		assertFalse(mUnbalanced3.areBracketsBalanced)
	}

	@Test
	fun testAreBracketsBalanced_Unbalanced4_ReturnsFalse() {
		assertFalse(mUnbalanced4.areBracketsBalanced)
	}

	@Test
	fun testAreBracketsBalanced_ValidCountNotBalanced_ReturnsFalse() {
		assertFalse(mValidCountNotBalanced.areBracketsBalanced)
	}

	@Test
	fun testAreBracketsBalanced_ValidCountNotBalanced2_ReturnsFalse() {
		assertFalse(mValidCountNotBalanced2.areBracketsBalanced)
	}

	@Test
	fun testGetBracketPairs_Unbalanced1_ReturnsNull() {
		assertNull(mUnbalanced1.getBracketPairs())
	}

	@Test
	fun testGetBracketPairs_Unbalanced2_ReturnsNull() {
		assertNull(mUnbalanced2.getBracketPairs())
	}

	@Test
	fun testGetBracketPairs_Unbalanced3_ReturnsNull() {
		assertNull(mUnbalanced3.getBracketPairs())
	}

	@Test
	fun testGetBracketPairs_Unbalanced4_ReturnsNull() {
		assertNull(mUnbalanced4.getBracketPairs())
	}

	@Test
	fun testGetBracketPairs_ValidCountNotBalanced_ReturnsNull() {
		assertNull(mValidCountNotBalanced.getBracketPairs())
	}

	@Test
	fun testGetBracketPairs_ValidCountNotBalanced2_ReturnsNull() {
		assertNull(mValidCountNotBalanced2.getBracketPairs())
	}

}