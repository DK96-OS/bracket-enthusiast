package brackets

import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Testing BracketEnthusiast on Code Strings.
 */
class BracketEnthusiastCodeStringTest {

	private var mInstance: BracketEnthusiast? = null

	private val codeStringInput1: String = """
		package brackets
		
		class CodeStringClass {
			fun firstMethod() {
				println("Hello World!")
			}
			fun secondMethod(name: String): String {
				return "Hello ${"$"}name"
			}
		}
	""".trimIndent()

	private var curlyEnthusiast1 = BracketEnthusiast(
		BracketType.CURLY, codeStringInput1
	)

	private var parenthesisEnthusiast1 = BracketEnthusiast(
		BracketType.PARENTHESIS, codeStringInput1
	)

	private var squareEnthusiast1 = BracketEnthusiast(
		BracketType.SQUARE, codeStringInput1
	)

	@After
	fun testCleanup() {
		mInstance = null
	}

	@Test
	fun testOpeners_CodeString1_Curly() {
		assertArrayEquals(
			intArrayOf(40, 61, 132),
			curlyEnthusiast1.openers
		)
	}

	@Test
	fun testOpeners_CodeString1_Parenthesis() {
		assertArrayEquals(
			intArrayOf(58, 72, 109),
			parenthesisEnthusiast1.openers
		)
	}

	@Test
	fun testOpeners_CodeString1_Square() {
		assertNull(squareEnthusiast1.openers)
	}

	@Test
	fun testClosers_CodeString1_Curly() {
		assertArrayEquals(
			intArrayOf(90, 158, 160),
			curlyEnthusiast1.closers
		)
	}

	@Test
	fun testClosers_CodeString1_Parenthesis() {
		assertArrayEquals(
			intArrayOf(59, 87, 122),
			parenthesisEnthusiast1.closers
		)
	}

	@Test
	fun testClosers_CodeString1_Square() {
		assertNull(squareEnthusiast1.closers)
	}

	@Test
	fun testAreBracketsBalanced_CodeString1_Curly_ReturnsTrue() {
		assertTrue(
			curlyEnthusiast1.areBracketsBalanced
		)
	}

	@Test
	fun testAreBracketsBalanced_CodeString1_Parenthesis_ReturnsTrue() {
		assertTrue(
			parenthesisEnthusiast1.areBracketsBalanced
		)
	}

	@Test
	fun testAreBracketsBalanced_CodeString1_Square_ReturnsTrue() {
		assertTrue(
			squareEnthusiast1.areBracketsBalanced
		)
	}

	@Test
	fun testGetBracketPairs_CodeString1_Curly() {
		assertArrayEquals(
			intArrayOf(40, 160, 61, 90, 132, 158),
			curlyEnthusiast1.getBracketPairs()
		)
	}

	@Test
	fun testGetBracketPairs_CodeString1_Parenthesis() {
		assertArrayEquals(
			intArrayOf(58, 59, 72, 87, 109, 122),
			parenthesisEnthusiast1.getBracketPairs()
		)
	}

	@Test
	fun testGetBracketPairs_CodeString1_Square() {
		assertNull(
			squareEnthusiast1.getBracketPairs()
		)
	}

}