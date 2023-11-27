package brackets.nesting;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mathtools.pairs.IntPairFixed;

/** A Node representing a Pair of Brackets.
 * Provides storage of nested Bracket Pairs.
 */
public final class BracketNode
    extends IntPairFixed {

    private ArrayList<BracketNode> mInternalNodes = null;

    /** Construct a Bracket Node.
     *  Ensures that the bracket index are non-negative.
     *  Ensures that the index increases.
     * @param open The index of the opening bracket
     * @param close The index of the closing bracket.
     */
    BracketNode(
        final int open,
        final int close
    ) {
        super(open, close);
        if (!(close > open && open > -1))
            throw new IllegalArgumentException(
                String.format(
                    "Invalid Bracket Pair: %s",
                    super.toString()
                )
            );
    }

    /** Obtain any Nodes that are contained within this Node.
     * @return An immutable List of Nodes.
     */
    @Nullable
    public List<BracketNode> getInternalNodes() {
        return mInternalNodes;
    }

    /** Determine the number of direct internal nodes.
     * @return The count of nodes directly inside this node.
     */
    public int countInternalNodes() {
        if (mInternalNodes == null)
            return 0;
        return mInternalNodes.size();
    }

    /** Add a new Node to the array of direct descendants.
     *  Ensures that the given values are nested.
     * @param open The index of the opening bracket
     * @param close The index of the closing bracket.
     * @return Whether the given node is nested, and was added.
     */
    boolean addNode(
        final int open,
        final int close
    ) {
        // Validate Nesting within this Node
        if (!(first < open && close < second))
            return false;
        // Initialize the Internal Node Array if necessary
        if (mInternalNodes == null)
            mInternalNodes = new ArrayList<>(1);
        // Create and Add the new Node
        mInternalNodes.add(
            new BracketNode(open, close)
        );
        return true;
    }

}