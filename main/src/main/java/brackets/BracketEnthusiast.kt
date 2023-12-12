package brackets

import java.util.Stack

/** A Bracket Enthusiast.
 * Determines the locations of all brackets of the given type in the string.
 */
open class BracketEnthusiast(
	/** The Bracket Type.
	 */
	val bracketType: BracketType,
	/** The input string.
	 */
	val input: String,
) {

	/** The locations of all open brackets.
	 */
	val openers: IntArray?

	/** The locations of all close brackets.
	 */
	val closers: IntArray?

	/** Whether there are the same number of open and close brackets.
	 */
	val areBracketCountsValid: Boolean

	/** Whether the brackets are balanced.
	 */
	val areBracketsBalanced: Boolean

	/** Secondary Constructor for Char Arrays.
	 * @param bracketType The Bracket Type.
	 * @param input The input character array.
	 */
	constructor(
		bracketType: BracketType,
		input: CharArray,
	) : this(bracketType, java.lang.String.copyValueOf(input))

	init {
		val openList = ArrayList<Int>(4)
		val closeList = ArrayList<Int>(4)
		val charArray = charArrayOf(
			bracketType.open, bracketType.close
		)
		var index = 0
		while (index < input.length) {
			val nextBracket = input.indexOfAny(
				charArray,
				startIndex = index,
				ignoreCase = false
			)
			if (nextBracket == -1)
				break
			// Add the Bracket index to the right list
			when (input[nextBracket]) {
				bracketType.open -> openList.add(nextBracket)
				else -> closeList.add(nextBracket)
			}
			// Update Index
			index = nextBracket + 1
		}
		this.openers = if (openList.isEmpty())
			null else openList.toIntArray()
		this.closers = if (closeList.isEmpty())
			null else closeList.toIntArray()
		areBracketCountsValid = openers?.size == closers?.size
		areBracketsBalanced = areBracketsBalanced()
	}

	/** Determine if the brackets are balanced.
	 * Ensures that all nested brackets are properly closed.
	 * @return True if the brackets are balanced.
	 */
	private fun areBracketsBalanced()
		: Boolean {
		if (!areBracketCountsValid)
			return false
		if (openers == null)
			return true
		var openerIndex = 0
		var closerIndex = 0
		while (
			openerIndex < openers.size &&
			closerIndex < closers!!.size
		) {
			val opener = openers[openerIndex]
			val closer = closers[closerIndex]
			if (opener < closer) {
				openerIndex++
				closerIndex++
			} else {
				return false
			}
		}
		return true
	}

	/** Obtain the matching bracket pairs.
	 * This method returns null if the brackets are not balanced.
	 * The resulting pairs are ordered by opening bracket index.
	 * @return The bracket pairs, or null.
	 */
	fun getBracketPairs(): IntArray? {
		// Validate Input
		if (openers == null ||
			closers == null ||
			!areBracketCountsValid ||
			!areBracketsBalanced
			) return null
		// Create the Result Array, which will contain pairs of indices.
		val result = IntArray(openers.size + closers.size)
		// Result Array Index :
		var resultOpenIndex = 0
		// Iterate through the openers and closers.
		var openerIndex = 0
		var closerIndex = 0
		// Use a Stack to track the open brackets.
		val unclosedOpenResultIndex = Stack<Int>()
		while (
			openerIndex < openers.size
		) {
			val open = openers[openerIndex]
			val close = closers[closerIndex]
			if (open < close) {
				result[resultOpenIndex] = open
				unclosedOpenResultIndex.push(resultOpenIndex)
				// Update Indices
				resultOpenIndex += 2
				openerIndex++
			} else {
				val lastOpenIndex = unclosedOpenResultIndex.pop()
				result[lastOpenIndex + 1] = close
				// Update Indices
				closerIndex++
			}
		}
		while (unclosedOpenResultIndex.isNotEmpty()) {
			val lastOpenIndex = unclosedOpenResultIndex.pop()
			result[lastOpenIndex + 1] = closers[closerIndex++]
		}
		return result
	}

}
