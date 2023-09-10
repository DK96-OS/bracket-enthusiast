package brackets

import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertNull
import org.junit.Test

/** Testing BracketEnthusiast on Code Strings.
 */
class BracketEnthusiastCodeStringTest {

	private var mInstance: BracketEnthusiast? = null

	private val codeString1: String = """
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

	@After
	fun testCleanup() {
		mInstance = null
	}

	@Test
	fun testOpeners_CodeString1_Curly() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY,
			codeString1
		)
		assertArrayEquals(
			intArrayOf(40, 61, 132),
			mInstance!!.openers
		)
	}

	@Test
	fun testOpeners_CodeString1_Parenthesis() {
		mInstance = BracketEnthusiast(
			BracketType.PARENTHESIS,
			codeString1
		)
		assertArrayEquals(
			intArrayOf(58, 72, 109),
			mInstance!!.openers
		)
	}

	@Test
	fun testOpeners_CodeString1_Square() {
		mInstance = BracketEnthusiast(
			BracketType.SQUARE,
			codeString1
		)
		assertNull(mInstance!!.openers)
	}

	@Test
	fun testClosers_CodeString1_Curly() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY,
			codeString1
		)
		assertArrayEquals(
			intArrayOf(90, 158, 160),
			mInstance!!.closers
		)
	}

	@Test
	fun testClosers_CodeString1_Parenthesis() {
		mInstance = BracketEnthusiast(
			BracketType.PARENTHESIS,
			codeString1
		)
		assertArrayEquals(
			intArrayOf(59, 87, 122),
			mInstance!!.closers
		)
	}

	@Test
	fun testClosers_CodeString1_Square() {
		mInstance = BracketEnthusiast(
			BracketType.SQUARE,
			codeString1
		)
		assertNull(mInstance!!.closers)
	}

}