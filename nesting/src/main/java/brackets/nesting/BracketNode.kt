package brackets.nesting

/** A Node representing a Pair of Brackets.
 * Provides storage of nested Bracket Pairs.
 */
class BracketNode internal constructor(
    val start: Int,
    val end: Int,
) {

    private var mInternalNodes: ArrayList<BracketNode>? = null

    /** Obtain any Nodes that are contained within this Node.
     */
    fun getInternalNodes()
        : List<BracketNode>?
        = mInternalNodes

    /**
     */
    internal fun addNode(
        start: Int,
        end: Int,
    ) : Boolean {
        //
        return false
    }

}