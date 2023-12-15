package brackets

import org.junit.After
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Testing BracketEnthusiast on BracketStrings
 */
class BracketEnthusiastBracketStringTest {

	private var mInstance: BracketEnthusiast? = null

	private val bracketSample: String = "{{}{}}"
	private val bracketSample2: String = "{{{}}{}{}}{}"

	@After
	fun testCleanup() {
		mInstance = null
	}

	@Test
	fun testOpeners_BracketSample_() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample
		)
		assertArrayEquals(
			intArrayOf(0, 1, 3),
			mInstance!!.openers
		)
	}

	@Test
	fun testOpeners_BracketSample2_() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample2
		)
		assertArrayEquals(
			intArrayOf(0, 1, 2, 5, 7, 10),
			mInstance!!.openers
		)
	}

	@Test
	fun testClosers_BracketSample_() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample
		)
		assertArrayEquals(
			intArrayOf(2, 4, 5),
			mInstance!!.closers
		)
	}

	@Test
	fun testClosers_BracketSample2_() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample2
		)
		assertArrayEquals(
			intArrayOf(3, 4, 6, 8, 9, 11),
			mInstance!!.closers
		)
	}

	@Test
	fun testAreBracketsBalanced_BracketSample_ReturnsTrue() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample
		)
		assertTrue(
			mInstance!!.areBracketsBalanced
		)
	}

	@Test
	fun testAreBracketsBalanced_CharArray_BracketSample_ReturnsTrue() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample.toCharArray()
		)
		assertTrue(
			mInstance!!.areBracketsBalanced
		)
	}


	@Test
	fun testAreBracketsBalanced_BracketSample2_ReturnsTrue() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample2
		)
		assertTrue(
			mInstance!!.areBracketsBalanced
		)
	}


	@Test
	fun testAreBracketsBalanced_CharArray_BracketSample2_ReturnsTrue() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY, bracketSample2.toCharArray()
		)
		assertTrue(
			mInstance!!.areBracketsBalanced
		)
	}

	@Test
	fun testGetBracketPairs_BracketSample_ReturnsCorrectPairs() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY,
			bracketSample
		)
		val result = mInstance!!.getBracketPairs()
		assertNotNull(result)
		assertArrayEquals(
			intArrayOf(0, 5, 1, 2, 3, 4),
			result
		)
	}

	@Test
	fun testGetBracketPairs_BracketSample2_ReturnsCorrectPairs() {
		mInstance = BracketEnthusiast(
			BracketType.CURLY,
			bracketSample2
		)
		val result = mInstance!!.getBracketPairs()
		assertNotNull(result)
		assertArrayEquals(
			intArrayOf(0, 9, 1, 4, 2, 3, 5, 6, 7, 8, 10, 11),
			result
		)
	}

}