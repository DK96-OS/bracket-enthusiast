package brackets

import java.util.Stack

/** A Bracket Enthusiast.
 * Determines the locations of all brackets of the given type in the string.
 */
class BracketEnthusiast(
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

	init {
		val openChar = bracketType.open
		val closeChar = bracketType.close
		val openList = ArrayList<Int>(4)
		val closeList = ArrayList<Int>(4)
		var index = 0
		while (index < input.length) {
			val nextOpen = input.indexOf(openChar, index)
			val nextClose = input.indexOf(closeChar, index)
			if (nextOpen == -1 && nextClose == -1) {
				break
			}
			index = if (nextOpen == -1) {
				closeList.add(nextClose)
				nextClose + 1
			} else if (nextClose == -1) {
				openList.add(nextOpen)
				nextOpen + 1
			} else if (nextOpen < nextClose) {
				openList.add(nextOpen)
				nextOpen + 1
			} else {
				closeList.add(nextClose)
				nextClose + 1
			}
		}
		this.openers = if (openList.isEmpty())
			null else openList.toIntArray()
		this.closers = if (closeList.isEmpty())
			null else closeList.toIntArray()
		areBracketCountsValid = openers?.size == closers?.size
		areBracketsBalanced = areBracketCountsValid &&
			areBracketsBalanced()
	}

	/** Determine if the brackets are balanced.
	 * Ensures that all nested brackets are properly closed.
	 * @return True if the brackets are balanced.
	 */
	private fun areBracketsBalanced()
		: Boolean {
		val openers = openers!!
		val closers = closers!!
		var openerIndex = 0
		var closerIndex = 0
		while (
			openerIndex < openers.size &&
			closerIndex < closers.size
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
		if (!areBracketCountsValid ||
			openers == null ||
			closers == null
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
