package brackets.nesting

import brackets.BracketEnthusiast

/**
 */
class BracketTreeBuilder {

    private var root: BracketNode? = null

    private var recent: BracketNode? = null

    /** Add A Node to The tree.
     * @param
     * @param
     * @return
     */
    fun addNode(
        a: Int, b: Int
    ) : Boolean {
        if (root == null) {
            root = BracketNode(a, b)
            recent = null
            return true
        }
        //todo:

        return false
    }

    /** Build a Top Level Array of Bracket Nodes from an Enthusiast.
     * @param enthusiast The BracketEnthusiast to provide data.
     * @return An Array of BracketNode, or an empty Array if data invalid or empty.
     */
    fun buildFromEnthusiast(
        enthusiast: BracketEnthusiast,
    ) : Array<BracketNode> {
        // Ensure that the Enthusiast contains balanced data
        if (!enthusiast.areBracketsBalanced)
            return emptyArray()
        // Obtain the Data from the Enthusiast
        val pairs = enthusiast.getBracketPairs()
            ?: return emptyArray()
        // Clear the builder's root
        clear()
        // Convert Pairs into Nodes
        val topLevelNodes = ArrayList<BracketNode>()
        // Parse the Data Stream
        var index = 0
        while (index < pairs.size) {
            // Try to add to the Root Node
            if (addNode(pairs[index], pairs[index + 1])) {
                index += 2 // Next Pair
            } else {
                // Pop Root and Try Again
                topLevelNodes.add(root!!)
                clear()
            }
        }
        root?.also { topLevelNodes.add(it) }
        clear()
        return topLevelNodes.toTypedArray()
    }

    /** Reset the Builder references.
     */
    internal fun clear() {
        root = null
        recent = null
    }

}