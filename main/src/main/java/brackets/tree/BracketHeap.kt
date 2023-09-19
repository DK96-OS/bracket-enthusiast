package brackets.tree

/** The bracket Heap structures sibling bracket pairs.
 * The pairs must not overlap.
 */
class BracketHeap {

	/** The current Size of the Heap.
	 */
	private var mHeapSize: Int = 0

	/** The Array backing the Heap.
	 */
	private var mArray: Array<BracketTree?> = emptyArray()

	constructor(
		vararg bracketTree: BracketTree
	) {
		for (tree in bracketTree)
			insert(tree)
	}

	/** Obtain the Size of the heap.
	 */
	fun getSize(): Int = mHeapSize

	/** Obtain the BracketTree at the given index.
	 * @param index The position of the BracketTree.
	 * @return The BracketTree, or null if invalid index, or the position is empty (ie heap not filled).
	 */
	fun getAt(
		index: Int
	) : BracketTree? {
		if (index < 0 ||
			index >= mArray.size
			) return null
		return mArray[index]
	}

	/** Insert a new Bracket Tree into the Heap.
	 * @param bracketTree The new Tree to insert.
	 * @return Whether the new Tree was able to be inserted.
	 */
	fun insert(
		bracketTree: BracketTree,
	) : Boolean {
		val insertionIndex = mHeapSize
		if (insertionIndex >= mArray.size) {
			// resize Array
			val initialArray = mArray
			mArray = arrayOfNulls(
				((initialArray.size + 1) shl 1) - 1
			)
			for (i in initialArray.indices) {
				mArray[i] = initialArray[i]
			}
		}
		// The element begins at the bottom
		var newBracketIndex = insertionIndex
		//
		mArray[newBracketIndex] = bracketTree
		mHeapSize++
		// Compare with Parent elements
		while (!validParent(newBracketIndex)) {
			// Check for Bracket Conflict
			val parentIndex = newBracketIndex ushr 1

			// Have to reorder the elements in the heap
			swapElements(newBracketIndex, parentIndex)
			newBracketIndex = parentIndex
		}
		return true
	}

	/**
	 */
	internal fun checkForConflict(
		tree1: BracketTree,
		tree2: BracketTree,
	) : Boolean {
		if (tree1.close < tree2.open ||
			tree1.open > tree2.close
			) return false
		if (tree1.open == tree2.open) {

		} else {

		}
		return true
	}

	/** Determine whether an element belongs under it's current parent.
	 * @param index The index of the element to test.
	 * @return Whether the Parent is valid for this element.
	 */
	internal fun validParent(
		index: Int
	) : Boolean {
		if (index == 0)
			return true
		val parentIndex = index / 2
		val parent = mArray[parentIndex]!!
		return parent.close < mArray[index]!!.open
	}

	/** Swap two elements in the heap.
	 * @param first The index of one element.
	 * @param second The index of the other element.
	 * @return Whether both elements existed at the given indices.
	 */
	internal fun swapElements(
		first: Int,
		second: Int
	) : Boolean {
		val element1 = mArray[first]
		val element2 = mArray[second]
		if (element1 == null || element2 == null)
			return false
		mArray[first] = element2
		mArray[second] = element1
		return true
	}

}