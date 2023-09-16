package brackets.tree

/** A Tree Node that may contain nested Brackets.
 * Nested Brackets
 */
class BracketTree(
	val open: Int,
	val close: Int,
) {

	/** The Brackets Nested inside this.
	 */
	var nestedBrackets: BracketHeap? = null
		private set

	/** Determine whether this Tree node is a Leaf.
	 * @return Whether there are any nested brackets.
	 */
	fun isLeaf(): Boolean {
		return nestedBrackets == null
	}

	/** Add a pair of nested brackets.
	 * Checks arguments for correctness.
	 * Checks Nested brackets for any conflicts.
	 * @param open The open bracket index.
	 * @param close The close bracket index.
	 * @return Whether the Nested Brackets were added successfully.
	 */
	fun addBrackets(
		open: Int,
		close: Int,
	) : Boolean {
		// Validate Inner Bracket Indices
		if (open > close ||
			this.open >= open ||
			this.close <= close
			) return false;
		// Add to the heap
		return if (nestedBrackets == null) {
			nestedBrackets = BracketHeap(
				BracketTree(open, close)
			)
			true
		} else {
			nestedBrackets!!.insert(
				BracketTree(open, close)
			)
		}
	}

	override fun equals(
		other: Any?
	) : Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		other as BracketTree
		if (open != other.open) return false
		if (close != other.close) return false
		if (nestedBrackets != other.nestedBrackets) return false
		return true
	}

	override fun hashCode(): Int {
		var result = open
		result = 31 * result + close
		return result
	}

}