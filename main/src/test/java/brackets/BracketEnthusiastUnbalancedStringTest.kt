package brackets

import org.junit.Assert.assertFalse
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
	private val unbalancedInput3: String = "{{{{".repeat(4000)

	/** A specific input that has the same number of
	 * open and close brackets, but is not balanced.
	 */
	private val validCountNotBalancedInput: String = "{}}{{}}{{}"

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

	/** A BracketEnthusiast for the valid count not balanced input.
	 */
	private var mValidCountNotBalanced = BracketEnthusiast(
		BracketType.CURLY, validCountNotBalancedInput
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
	fun testAreBracketCountsValid_ValidCountNotBalanced_ReturnsTrue() {
		assertTrue(mValidCountNotBalanced.areBracketCountsValid)
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
	fun testAreBracketsBalanced_ValidCountNotBalanced_ReturnsFalse() {
		assertFalse(mValidCountNotBalanced.areBracketsBalanced)
	}

}